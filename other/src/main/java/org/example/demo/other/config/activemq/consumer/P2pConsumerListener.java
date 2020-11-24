package org.example.demo.other.config.activemq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.example.demo.other.config.activemq.model.QueenMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @author ag777 <837915770@vip.qq.com>
 * @Description 消费者
 * @Date: 2020/11/23 15:34
 */
@Component
@Slf4j
public class P2pConsumerListener {
    @JmsListener(destination = "message.queue")
    public void insertVisitLog(QueenMessage queenMessage) {
        System.out.println("消费者接收数据 : " + queenMessage);
    }

    @JmsListener(destination = "message.queue")
    public void insertVisitLog2(QueenMessage queenMessage) {
        System.out.println("消费者接收数据2 : " + queenMessage);
    }
}
