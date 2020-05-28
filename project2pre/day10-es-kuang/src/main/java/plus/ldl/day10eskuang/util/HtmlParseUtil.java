package plus.ldl.day10eskuang.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import plus.ldl.day10eskuang.domain.Content;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ldl.plus
 * @date 2020年05月15日  18:39
 */
public class HtmlParseUtil {
    public static void main(String[] args) throws Exception {
        parseJD("心理学").forEach(System.out::println);
    }

    public static List<Content> parseJD(String keywords) throws Exception {
        List<Content> list = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            String url = "https://search.jd.com/Search?keyword=" + keywords + "&page=" + i + "&s=90&click=0";
            Document document = Jsoup.parse(new URL(url), 30000);

            Element element = document.getElementById("J_goodsList");

            Elements elements = element.getElementsByTag("li");

            for (Element el : elements) {
                String img = el.getElementsByTag("img").eq(0).attr("src");
                String price = el.getElementsByClass("p-price").eq(0).text();
                String name = el.getElementsByClass("p-name").eq(0).text();

                list.add(new Content(name, img, price));
            }
            Thread.sleep(500);
        }
        return list;
    }
}
