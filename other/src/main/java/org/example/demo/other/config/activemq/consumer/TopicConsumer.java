package org.example.demo.other.config.activemq.consumer;

import org.example.demo.other.config.activemq.model.QueenMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @author ag777 <837915770@vip.qq.com>
 * @Description 群发订阅监听
 * @Date: 2020/11/23 16:21
 */
@Component
public class TopicConsumer {
    @JmsListener(destination = "message.topic", containerFactory="jmsListenerContainerTopic")
    public void receiver1(QueenMessage queenMessage) {
        System.out.println("消费者1 : " + queenMessage);
    }

    @JmsListener(destination = "message.topic", containerFactory="jmsListenerContainerTopic")
    public void receiver2(QueenMessage queenMessage) {
        System.out.println("消费者2  : " + queenMessage);
    }

    @JmsListener(destination = "message.topic", containerFactory="jmsListenerContainerTopic")
    public void receiver3(QueenMessage queenMessage) {
        System.out.println("消费者3  : " + queenMessage);
    }
}
