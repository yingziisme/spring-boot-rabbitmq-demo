package com.mt.demo.springbootrabbitmqdemo.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

/**
 * ReturnCallBackListener
 *
 * @author MT.LUO
 * 2018/10/30 11:44
 * @Description:
 */
@Slf4j
@Service
public class ReturnCallBackListener implements RabbitTemplate.ReturnCallback {
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        log.info("return message:{}, code: {}, text: {}, exhcange:{}, routingkey:{}", message, replyCode, replyText,
                exchange, routingKey);
    }
}
