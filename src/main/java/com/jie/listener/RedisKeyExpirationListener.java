package com.jie.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RedisKeyExpirationListener extends KeyExpirationEventMessageListener {

    // 把我们上面一步配置的bean注入进去
    public RedisKeyExpirationListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }


    /**
     * 针对redis数据失效事件，进行数据处理
     *
     * @param message
     * @param pattern
     */
    @Override
    public void onMessage(Message message, byte[] pattern) {
        // 用户做自己的业务处理即可,注意message.toString()可以获取失效的key
        String expiredKey = message.toString();
        log.info("onMessage --> redis 过期的key是：{}", expiredKey);
        try {
            // 对过期key进行处理

            log.info("过期key处理完成：{}", expiredKey);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("处理redis 过期的key异常：{}", expiredKey, e);
        }
    }
}
