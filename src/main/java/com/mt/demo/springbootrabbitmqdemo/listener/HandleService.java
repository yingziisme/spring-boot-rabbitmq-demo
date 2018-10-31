package com.mt.demo.springbootrabbitmqdemo.listener;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.stereotype.Service;

/**
 * HandleService
 *
 * @author MT.LUO
 * 2018/10/30 15:22
 * @Description:
 */
@Slf4j
@Service
public class HandleService implements ChannelAwareMessageListener {
    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        log.info("HandleService: {}, {}, {}", message, channel, Thread.currentThread());
    }
}
