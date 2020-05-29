package plus.ldl.provider.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import plus.ldl.domain.Goods;
import plus.ldl.feign.GoodsInterface;

import java.util.Arrays;

/**
 * @author ldl.plus
 * @date 2020年05月24日  16:28
 */
@RestController
@RequestMapping("/provider")
public class ProviderController implements GoodsInterface {


    @Autowired
    private Environment environment;

    @Override
    @GetMapping("/goods/{id}")
    public Goods findOne(@PathVariable("id") int id) {
        return new Goods(id, "小米10Pro", 5999, 1000);
    }

    @GetMapping("/env")
    public String getEnvironment() {
        String[] activeProfiles = environment.getActiveProfiles();
        String[] defaultProfiles = environment.getDefaultProfiles();
        System.out.println(Arrays.toString(activeProfiles));
        System.out.println(Arrays.toString(defaultProfiles));
        String port = environment.getProperty("local.server.port");
        String ip = environment.getProperty("local.server.ip");
        System.out.println("property = " + ip);
        System.out.println("port = " + port);
        return Arrays.toString(activeProfiles) + "\n" + Arrays.toString(defaultProfiles);
    }
}
