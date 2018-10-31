package com.mt.demo.springbootrabbitmqdemo.web;

import com.mt.demo.springbootrabbitmqdemo.listener.ConfirmCallbackListener;
import com.mt.demo.springbootrabbitmqdemo.listener.ReturnCallBackListener;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitTemplateConfig
 *
 * @author MT.LUO
 * 2018/10/30 14:52
 * @Description:
 */
@Configuration
public class RabbitTemplateConfig {


    @Autowired
    private ReturnCallBackListener returnCallBackListener;

    @Autowired
    private ConfirmCallbackListener confirmCallbackListener;

    @Bean
    public RabbitTemplate getRabbitTemplate(CachingConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setConfirmCallback(confirmCallbackListener);
        /**
         * 当mandatory标志位设置为true时
         * 如果exchange根据自身类型和消息routingKey无法找到一个合适的queue存储消息
         * 那么broker会调用basic.return方法将消息返还给生产者
         * 当mandatory设置为false时，出现上述情况broker会直接将消息丢弃
         */
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setReturnCallback(returnCallBackListener);

        return rabbitTemplate;
    }
}
