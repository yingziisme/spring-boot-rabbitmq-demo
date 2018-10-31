package com.mt.demo.springbootrabbitmqdemo.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
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
@RabbitListener(bindings = @QueueBinding(value = @Queue("hello4"), exchange = @Exchange(value = "topic", type =
        ExchangeTypes.TOPIC), key = "hello"))
public class HelloListener4 {

    @RabbitHandler
    public void process(String hello) {
        log.info("Receiver4: {}", hello);
    }

}
