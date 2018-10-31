package com.mt.demo.springbootrabbitmqdemo.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.stereotype.Service;

/**
 * ConfirmCallbackListener
 *
 * @author MT.LUO
 * 2018/10/30 14:50
 * @Description:
 */
@Slf4j
@Service
public class ConfirmCallbackListener implements RabbitTemplate.ConfirmCallback {
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        log.info("ConfirmCallbackListener CorrelationData: {}, ack: {}, cause: {}", correlationData, ack, cause);
    }
}
