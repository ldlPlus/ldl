package plus.ldl.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import plus.ldl.springcloud.service.MessageProvider;

/**
 * @author ldl.plus
 * @date 2020年05月09日  11:31
 */
@RestController
public class SendMessageController {
    @Autowired
    private MessageProvider messageProvider;

    @GetMapping("/sendMessage")
    public String sendMessage() {
        return messageProvider.send();
    }
}
