package com.tamguo.admin.crawler;

import com.tamguo.admin.model.QuestionEntity;
import com.tamguo.admin.service.IQuestionService;
import com.xuxueli.crawler.XxlCrawler;
import com.xuxueli.crawler.annotation.PageFieldSelect;
import com.xuxueli.crawler.annotation.PageSelect;
import com.xuxueli.crawler.parser.PageParser;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashSet;
import java.util.UUID;

/**
 * 爬虫示例01：爬取页面数据并封装VO对象
 *
 * @author xuxueli 2017-10-09 19:48:48
 */
@Component
@EnableScheduling
public class XxlCrawlerTask {
    Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private IQuestionService iQuestionService;

    @PageSelect(cssQuery = "body")
    public static class PageVo {

        @PageFieldSelect(cssQuery = ".bdjson")
        private String question;


//        @PageFieldSelect(cssQuery = ".comment-content")
//        private List<String> answer;
//
//        @PageFieldSelect(cssQuery = "#read")
//        private String analysis;

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

//        public List<String> getAnswer() {
//            return answer;
//        }
//
//        public void setAnswer(List<String> answer) {
//            this.answer = answer;
//        }
//
//        public String getAnalysis() {
//            return analysis;
//        }
//
//        public void setAnalysis(String analysis) {
//            this.analysis = analysis;
//        }

        @Override
        public String toString() {
            return "PageVo{" +
                    "question='" + question + '\'' +
//                    ", answer=" + answer +
//                    ", analysis=" + analysis +
                    '}';
        }
    }

    @Scheduled(cron = "0 0/1 * * * ?")
    public void crawlerData() {
        logger.info("jianlaile");
        XxlCrawler crawler = new XxlCrawler.Builder()
                .setUrls(new HashSet(Arrays.asList(new String[]{"https://tiku.baidu.com/tikupc/paperdetail/be9503eb19e8b8f67c1cb911"})))
                .setWhiteUrlRegexs(new HashSet(Arrays.asList(new String[]{"https://tiku\\.baidu\\.com/tikupc/paperdetail/[a-zA-Z\\d]+"})))
                .setPageParser(new PageParser<PageVo>() {
                    @Override
                    public void parse(Document document, PageVo pageVo) {
                        // 解析封装 PageVo 对象
                        String pageUrl = document.baseUri();
                        System.out.println(pageUrl + "：" + pageVo.toString());
                        QuestionEntity question = new QuestionEntity();
                        question.setChapterId(new BigInteger("1"));
                        question.setCourseId("1");
                        question.setPaperId(new BigInteger("1"));
                        question.setAnswer("A.12 B.43");
                        question.setContent(pageVo.getQuestion());
                        question.setAnalysis("12321");
                        question.setQuestionType("1");
                        question.setAuditStatus("1");
                        question.setReviewPoint("10");
                        question.setSubjectId("1");
                        question.setScore(20);
                        question.setYear("2018");


                        try {
                            iQuestionService.save(question);
                        } catch (Exception e) {
                            logger.error("错误信息[{}]", e);
                        }

                    }
                })
                .build();

        System.out.println("start");
        crawler.start(true);
        System.out.println("end");
    }

}
