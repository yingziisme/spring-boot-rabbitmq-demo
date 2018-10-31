package com.mt.demo.springbootrabbitmqdemo.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * HelloListener
 *
 * @author MT.LUO
 * 2018/10/30 11:07
 * @Description:
 */
@Slf4j
@Component
@RabbitListener(queues = "hello")
public class HelloListener {

    @RabbitHandler
    public void process(String hello) {
        log.info("Receiver: {}", hello);
    }

}
