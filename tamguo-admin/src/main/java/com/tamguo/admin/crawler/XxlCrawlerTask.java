package com.tamguo.admin.crawler;

import com.xuxueli.crawler.XxlCrawler;
import com.xuxueli.crawler.annotation.PageFieldSelect;
import com.xuxueli.crawler.annotation.PageSelect;
import com.xuxueli.crawler.parser.PageParser;
import org.jsoup.nodes.Document;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * 爬虫示例01：爬取页面数据并封装VO对象
 *
 * @author xuxueli 2017-10-09 19:48:48
 */
public class XxlCrawlerTask {

    @PageSelect(cssQuery = "body")
    public static class PageVo {

        @PageFieldSelect(cssQuery = ".bdjson")
        private String question;


        @PageFieldSelect(cssQuery = ".comment-content")
        private List<String> answer;

        @PageFieldSelect(cssQuery = "#read")
        private String analysis;

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public List<String> getAnswer() {
            return answer;
        }

        public void setAnswer(List<String> answer) {
            this.answer = answer;
        }

        public String getAnalysis() {
            return analysis;
        }

        public void setAnalysis(String analysis) {
            this.analysis = analysis;
        }

        @Override
        public String toString() {
            return "PageVo{" +
                    "question='" + question + '\'' +
                    ", answer=" + answer +
                    ", analysis=" + analysis +
                    '}';
        }
    }

    public static void main(String[] args) {
        XxlCrawler crawler = new XxlCrawler.Builder()
                .setUrls(new HashSet(Arrays.asList(new String[]{"https://tiku.baidu.com/tikupc/paperdetail/be9503eb19e8b8f67c1cb911"})))
                .setWhiteUrlRegexs(new HashSet(Arrays.asList(new String[]{"https://tiku\\.baidu\\.com/tikupc/paperdetail/[a-zA-Z\\d]+"})))
                .setPageParser(new PageParser<PageVo>() {
                    @Override
                    public void parse(Document document, PageVo pageVo) {
                        // 解析封装 PageVo 对象
                        String pageUrl = document.baseUri();
                        System.out.println(pageUrl + "：" + pageVo.toString());
                    }
                })
                .build();

        System.out.println("start");
        crawler.start(true);
        System.out.println("end");
    }

}
