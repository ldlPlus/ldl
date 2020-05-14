package plus.ldl.springcloud.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import plus.ldl.springcloud.service.MessageProvider;

import java.util.UUID;

/**
 * @author ldl.plus
 * @date 2020年05月09日  11:22
 */
// @Service
@EnableBinding(Source.class)
public class MessageProviderImpl implements MessageProvider {

    /**
     * 消息发送管道
     */
    @Autowired
    private MessageChannel output;

    @Override
    public String send() {
        String serial = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(serial).build());
        System.out.println("MessageProviderImpl.send......." + serial);
        return "MessageProviderImpl.send......." + serial;
    }
}
