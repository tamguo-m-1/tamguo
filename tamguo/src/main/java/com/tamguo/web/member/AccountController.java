package com.tamguo.web.member;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.tamguo.dao.redis.CacheService;
import com.tamguo.model.MemberEntity;
import com.tamguo.service.IMemberService;
import com.tamguo.util.DateUtils;
import com.tamguo.util.Result;
import com.tamguo.util.Setting;
import com.tamguo.util.ShiroUtils;
import com.tamguo.util.Status;
import com.tamguo.util.UploaderMessage;

@Controller
public class AccountController {
	
	@Autowired
	public IMemberService memberService;
	@Value("${file.storage.path}")
	private String fileStoragePath;
	@Autowired
	private Setting setting;
	@Autowired
	private CacheService cacheService;

	private static final String AVATOR_NO_FORMAT = "00000";
	private static final String AVATOR_PREFIX = "MTX";
	
	public Logger logger = LoggerFactory.getLogger(getClass());

	@RequestMapping(value = "/member/account", method = RequestMethod.GET)
	public ModelAndView index(ModelAndView model , HttpSession session){
		model.setViewName("member/account");
		MemberEntity member = (MemberEntity) session.getAttribute("currMember");
		model.addObject("member" , memberService.findByUid(member.getUid()));
		return model;
	}
	
	@RequestMapping(value = "/member/account/update", method = RequestMethod.POST)
	@ResponseBody
	public Result updateMember(@RequestBody MemberEntity member){
		member.setUid(ShiroUtils.getUserId());
		memberService.updateMember(member);
		return Result.successResult(member);
	}
	
	@RequestMapping(value = "/member/updatePwd", method = RequestMethod.GET)
	public ModelAndView updatePwd(ModelAndView model){
		model.setViewName("member/updatePwd");
		return model;
	}
	
	@RequestMapping(value = "/member/uploadFile", method = RequestMethod.POST)
	@ResponseBody
	public UploaderMessage uploadFileHandler(@RequestParam("file") MultipartFile file,HttpServletRequest request) throws IOException {

		if (!file.isEmpty()) {
			InputStream in = null;
			OutputStream out = null;
			
			try {
				String path = fileStoragePath + DateUtils.format(new Date(), "yyyyMMdd");
				File dir = new File(path);
				if (!dir.exists())
					dir.mkdirs();
				String avatorName = this.getAvatorNo() + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
				in = file.getInputStream();
				out = new FileOutputStream(path + "/" + avatorName);
				byte[] b = new byte[1024];
				int len = 0;
				while ((len = in.read(b)) > 0) {
					out.write(b, 0, len);
				}
				out.close();
				in.close();
				logger.info("Server File Location=" + path + avatorName);

				UploaderMessage msg = new UploaderMessage();
				msg.setStatus(Status.SUCCESS);
				msg.setStatusMsg("File upload success");
				msg.setFilePath("files/" + DateUtils.format(new Date(), "yyyyMMdd") + "/" + avatorName);
				msg.setFileDomain(setting.domain);
				return msg;
			} catch (Exception e) {
				UploaderMessage msg = new UploaderMessage();
				msg.setStatus(Status.ERROR);
				msg.setError("File upload file");
				return msg;
			} finally {
				if (out != null) {
					out.close();
					out = null;
				}

				if (in != null) {
					in.close();
					in = null;
				}
			}
		} else {
			UploaderMessage msg = new UploaderMessage();
			msg.setStatus(Status.ERROR);
			msg.setError("File is empty");
			return msg;
		}
	}
	

	private String getAvatorNo() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String format = sdf.format(new Date());
		DecimalFormat df = new DecimalFormat(AVATOR_NO_FORMAT);
		String key = AVATOR_PREFIX + format;
		Long incr = cacheService.incr(key);
		String avatorNo = AVATOR_PREFIX + df.format(incr);
		return avatorNo;
	}
}
