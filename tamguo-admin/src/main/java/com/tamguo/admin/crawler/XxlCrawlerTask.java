package com.tamguo.admin.crawler;

import com.tamguo.admin.model.QuestionEntity;
import com.tamguo.admin.service.IQuestionService;
import com.xuxueli.crawler.XxlCrawler;
import com.xuxueli.crawler.annotation.PageFieldSelect;
import com.xuxueli.crawler.annotation.PageSelect;
import com.xuxueli.crawler.parser.PageParser;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

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


    @PageSelect(cssQuery = ".child-wrap")
    public static class PageVo {

        @PageFieldSelect(cssQuery = ".que-stem")
        private String question;

        @PageFieldSelect(cssQuery = ".que-options")
        private String answer;


        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }

        @Override
        public String toString() {
            return "PageVo{" +
                    "question='" + question + '\'' +
                    "answer='" + answer + '\'' +
                    '}';
        }
    }

    @Scheduled(cron = "0 0/1 * * * ?")
    public void crawlerData() {
        XxlCrawler crawler = new XxlCrawler.Builder()
                .setUrls("https://tiku.baidu.com/tikupc/paperdetail/4baa90f5f61fb7360b4c656b")
                .setWhiteUrlRegexs("https://tiku\\.baidu\\.com/tikupc/paperdetail/4baa90f5f61fb7360b4c656b")
                .setPageParser(new PageParser<PageVo>() {
                    @Override
                    public void parse(Document html, Element pageVoElement, PageVo pageVo) {
                        // 解析封装 PageVo 对象
                        String pageUrl = html.baseUri();
                        System.out.println(pageUrl + "：" + pageVo.toString());
                        QuestionEntity question = new QuestionEntity();
                        question.setChapterId(new BigInteger("1"));
                        question.setCourseId("1012550050327625730");
                        question.setPaperId(new BigInteger("1012550408013676545"));
                        question.setContent(pageVo.getQuestion() == null ? "无" : pageVo.getQuestion());//问题
                        question.setAnswer(pageVo.getAnswer() == null ? "无" : pageVo.getAnswer());//回答
                        question.setAnalysis("暂无解释");
                        question.setQuestionType("5");
                        question.setReviewPoint("语文");
                        question.setSubjectId("13");
                        question.setScore(10);
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
