package plus.ldl.jsouptest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.net.URL;

/**
 * @author ldl.plus
 * @date 2020年03月13日  22:11
 */
public class JsoupTest1 {
    /**
     *
     */
    @Test
    public void test12() throws Exception {
        Document document = Jsoup.parse(new URL("http://www.itcast.cn"), 1000);
        // System.out.println("document = " + document);
        // Element element = document.getElementById("test");
        //
        // String id = element.id();
        // System.out.println("id = " + id);

        /*Elements elements = document.select("span");
        for (Element element : elements) {
            System.out.println("element = " + element.text());
        }*/

        Elements elements = document.select(".next");
        for (Element element : elements) {
            System.out.println("element = " + element.attr("href"));

        }
        String title = document.getElementsByTag("title").first().text();
        System.out.println("title = " + title);

    }
}