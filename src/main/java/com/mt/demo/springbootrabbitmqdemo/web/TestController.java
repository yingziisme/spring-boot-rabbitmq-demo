package com.mt.demo.springbootrabbitmqdemo.web;

import com.mt.demo.springbootrabbitmqdemo.listener.HandleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * TestController
 *
 * @author MT.LUO
 * 2018/10/26 15:34
 * @Description:
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ConnectionFactory connectionFactory;

    @Autowired
    private HandleService handleService;

    @GetMapping("/send")
    public void send(@RequestParam String topic, @RequestParam String route, @RequestParam String msg) {
        log.info("send topic[{}], msg: {}", topic, msg);

        rabbitTemplate.convertAndSend(topic, route, msg);
    }

    @GetMapping("/fanout")
    public void fanout(@RequestParam String topic, @RequestParam String msg) {
        log.info("send fanout [{}], msg: {}", topic, msg);

        rabbitTemplate.convertAndSend(ExchangeTypes.FANOUT, topic, msg);
    }

    @GetMapping("/create")
    public void create(@RequestParam String query, @RequestParam String bindKey, @RequestParam String exchange,
                       @RequestParam String exchangeType) {
        log.info("create query:{}, bindkey: {}, exchange: {}, type: {}", query, bindKey, exchange, exchangeType);

        try {
            connectionFactory.createConnection().createChannel(false).queueDeclare(query, true, false, false, null);
            connectionFactory.createConnection().createChannel(false).exchangeDeclare(exchange, exchangeType);
            connectionFactory.createConnection().createChannel(false).queueBind(query, exchange, bindKey);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/listener")
    public void listener(@RequestParam String query, @RequestParam int message, @RequestParam int consumer) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.setQueueNames(query);
        container.setExposeListenerChannel(true);
        container.setPrefetchCount(message);//设置每个消费者获取的最大的消息数量
        container.setConcurrentConsumers(consumer);//消费者个数
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);//设置确认模式为手工确认
        container.setMessageListener(handleService);//监听处理类
        container.start();
    }
}
