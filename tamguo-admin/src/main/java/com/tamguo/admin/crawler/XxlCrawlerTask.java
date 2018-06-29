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


    @PageSelect(cssQuery = ".que-multi")
    public static class PageVo {

        @PageFieldSelect(cssQuery = ".que-multi")
        private String question;


        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }



        @Override
        public String toString() {
            return "PageVo{" +
                    "question='" + question + '\'' +
                    '}';
        }
    }

    @PageSelect(cssQuery = ".question-box-inner")
    public static class PageLiZongVo {

        @PageFieldSelect(cssQuery = ".question-box-inner")
        private String question;


        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }



        @Override
        public String toString() {
            return "PageLiZongVo{" +
                    "question='" + question + '\'' +
                    '}';
        }
    }

    @PageSelect(cssQuery = ".question-box-inner")
    public static class PageWenZongVo {

        @PageFieldSelect(cssQuery = ".question-box-inner")
        private String question;


        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }



        @Override
        public String toString() {
            return "PageWenZongVo{" +
                    "question='" + question + '\'' +
                    '}';
        }
    }

    /**
     * @description 2018北京语文
     * @author sh00859
     * @date 2018/6/29
     */
//    @Scheduled(cron = "0 0/1 * * * ?")
    public void crawlerYWData() {
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
//                        question.setAnswer(pageVo.getAnswer() == null ? "无" : pageVo.getAnswer());//回答
                        question.setAnswer("无");//回答
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


    /**
     * @description 2018北京理综
     * @author sh00859
     * @date 2018/6/29
     */
//    @Scheduled(cron = "0 0 19 * * ?")
    public void crawlerSXData() {
        XxlCrawler crawler = new XxlCrawler.Builder()
                .setUrls("https://tiku.baidu.com/tikupc/paperdetail/de62bec66137ee06eff91868")
                .setWhiteUrlRegexs("https://tiku\\.baidu\\.com/tikupc/paperdetail/de62bec66137ee06eff91868")
                .setPageParser(new PageParser<PageLiZongVo>() {
                    @Override
                    public void parse(Document html, Element pageVoElement, PageLiZongVo pageVo) {
                        // 解析封装 PageVo 对象
                        String pageUrl = html.baseUri();
                        System.out.println(pageUrl + "：" + pageVo.toString());
                        QuestionEntity question = new QuestionEntity();
                        question.setChapterId(new BigInteger("1"));
                        question.setCourseId("1012652550204428289");
                        question.setPaperId(new BigInteger("1012652716789600257"));
                        question.setContent(pageVo.getQuestion() == null ? "无" : pageVo.getQuestion());//问题
//                        question.setAnswer(pageVo.getAnswer() == null ? "无" : pageVo.getAnswer());//回答
                        question.setAnswer("无");//回答
                        question.setAnalysis("暂无解释");
                        question.setQuestionType("5");
                        question.setReviewPoint("理综");
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


    /**
     * @description 2018北京文综
     * @author sh00859
     * @date 2018/6/29
     */
    @Scheduled(cron = "0 27 19 * * ?")
    public void crawlerWZData() {
        XxlCrawler crawler = new XxlCrawler.Builder()
                .setUrls("https://tiku.baidu.com/tikupc/paperdetail/acf80b22bcd126fff7050b72")
                .setWhiteUrlRegexs("https://tiku\\.baidu\\.com/tikupc/paperdetail/acf80b22bcd126fff7050b72")
                .setPageParser(new PageParser<PageWenZongVo>() {
                    @Override
                    public void parse(Document html, Element pageVoElement, PageWenZongVo pageVo) {
                        // 解析封装 PageVo 对象
                        String pageUrl = html.baseUri();
                        System.out.println(pageUrl + "：" + pageVo.toString());
                        QuestionEntity question = new QuestionEntity();
                        question.setChapterId(new BigInteger("1"));
                        question.setCourseId("1012658027151851521");
                        question.setPaperId(new BigInteger("1012658169615581186"));
                        question.setContent(pageVo.getQuestion() == null ? "无" : pageVo.getQuestion());//问题
//                        question.setAnswer(pageVo.getAnswer() == null ? "无" : pageVo.getAnswer());//回答
                        question.setAnswer("无");//回答
                        question.setAnalysis("暂无解释");
                        question.setQuestionType("5");
                        question.setReviewPoint("理综");
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
