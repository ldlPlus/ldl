package plus.ldl.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import plus.ldl.consumer.domain.Goods;

/**
 * @author ldl.plus
 * @date 2020年05月05日  11:31
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;

    // @Autowired
    // private DiscoveryClient discoveryClient;

    @GetMapping("/{id}")
    public Goods findGoodsById(@PathVariable("id") Integer id) {
        System.out.println("OrderController.findGoodsById...");

/*        List<ServiceInstance> instances = discoveryClient.getInstances("EUREKA-PROVIDER");

        ServiceInstance serviceInstance = instances.get(0);
        String scheme = serviceInstance.getScheme();
        System.out.println("scheme = " + scheme);
        URI uri = serviceInstance.getUri();
        System.out.println("uri = " + uri);
        String serviceId = serviceInstance.getServiceId();
        System.out.println("serviceId = " + serviceId);
        Map<String, String> metadata = serviceInstance.getMetadata();
        System.out.println("metadata = " + metadata);*/

        String url = "http://EUREKA-PROVIDER/goods/findOne/" + id;
        return restTemplate.getForObject(url, Goods.class);
    }
}
