package plus.ldl.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ldl.plus
 * @date 2020年05月02日  19:23
 * 自定义配置
 */
@Configuration
public class MyselfRule {
    /**
     * 定义为随机策略
     *
     * @return
     */
    @Bean
    public IRule myRule() {
        return new RandomRule();
    }
}
