package plus.ldl.jd.task;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import plus.ldl.jd.HttpUtils;
import plus.ldl.jd.entity.JdItem;
import plus.ldl.jd.service.ItemService;

import java.util.Date;

/**
 * @author ldl.plus
 * @date 2020年03月15日  23:01
 */
@Component
public class ItemTask {

    @Autowired
    private HttpUtils httpUtils;

    @Autowired
    private ItemService itemService;

    @Scheduled(fixedDelay = 10 * 1000)
    public void itemTask() throws Exception {
        // https://list.jd.com/list.html?cat=9987,653,655&page=5
        String url = "https://search.jd.com/Search?keyword=%E6%89%8B%E6%9C%BA&enc=utf-8&qrst=1&rt=1&stop=1&vt=2&suggest=1.def.0.V15--12s0%2C20s0%2C38s0%2C97s0&wq=shouji&s=53&click=0&page=";

        //
        for (int i = 1; i < 10; i += 2) {
            String html = httpUtils.doGetHtml(url + i);
            System.out.println("html = " + html);
            this.parse(html);
            System.out.println("第" + i + "页");
        }
        System.out.println("手机数据爬取完成");
    }

    private void parse(String html) {
        Document document = Jsoup.parse(html);
        Elements spuEles = document.select("div#J_goodsList > ul > li");
        for (Element spuEle : spuEles) {
            long spu = Long.parseLong(spuEle.attr("data-spu"));
            Elements skuEles = spuEle.select("li.ps-item");
            for (Element skuEle : skuEles) {
                Elements skuLis = skuEle.select("[data-sku]");
                long sku = Long.parseLong(skuLis.attr("data-sku"));
                //根据sku查询商品数据
                JdItem item = new JdItem();
                item.setSku(sku);

                int size = itemService.findAll(item).size();
                if (size > 0) {
                    continue;
                }
                item.setSpu(spu);

                String itemUrl = "https://item.jd.com/" + sku + ".html";
                item.setUrl(itemUrl);

                String picUrl = "https:" + skuLis.attr("src");
                System.out.println("picUrl = " + picUrl);
                // item.setPic();
                // item.setPrice();
                // item.setTitle();
                Date date = new Date();
                item.setCreated(date);
                item.setUpdated(date);
            }
        }
    }
}
