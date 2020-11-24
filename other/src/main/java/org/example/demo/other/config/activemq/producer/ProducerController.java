package org.example.demo.other.config.activemq.producer;

import org.example.demo.other.config.activemq.model.QueenMessage;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.jms.Queue;
import javax.jms.Topic;

/**
 * @author ag777 <837915770@vip.qq.com>
 * @Description 生产者
 * @Date: 2020/11/23 15:35
 */
@RestController
public class ProducerController {
    @Resource
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Resource
    private Queue messageQueue;
    @Resource
    private Topic messageTopic;

    @RequestMapping("/send")
    public QueenMessage send(){
        QueenMessage queenMessage = new QueenMessage("测试","测试内容");
        jmsMessagingTemplate.convertAndSend(messageQueue,queenMessage);
        return queenMessage;
    }

    @RequestMapping("/sendtopic")
    public QueenMessage sendTopic(){
        QueenMessage queenMessage = new QueenMessage("测试","测试内容");
        jmsMessagingTemplate.convertAndSend(messageTopic,queenMessage);
        return queenMessage;
    }
}
