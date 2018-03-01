package com.hry.spring.rabbitmq.boot.raw;

import com.hry.spring.rabbitmq.boot.simple1.RabbitConfigure1;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.RabbitConnectionFactoryBean;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.stereotype.Component;

/**
 * Created by huangrongyou@yixin.im on 2018/2/11.
 */
@Component
public class SendRawMsg {

    // 此类封装我们rabbitMQ的配置属性，这里直接从Spring的上下文中获取
    @Autowired
    private RabbitProperties config;

    /**
     * 发送消息
     *
     * @param msgContent
     */
    public void sendRaw(String msgContent) throws Exception {


        // 对应类：RabbitAutoConfiguration -> RabbitTemplateConfiguration.rabbitTemplate()
        RabbitTemplate rabbitTemplate = new RabbitTemplate(createConnection());
//        MessageConverter messageConverter = this.messageConverter.getIfUnique();
//        if (messageConverter != null) {
//            rabbitTemplate.setMessageConverter(messageConverter);
//        }
//        rabbitTemplate.setMandatory(determineMandatoryFlag());
//        RabbitProperties.Template templateProperties = this.properties.getTemplate();
//        RabbitProperties.Retry retryProperties = templateProperties.getRetry();
//        if (retryProperties.isEnabled()) {
//            rabbitTemplate.setRetryTemplate(createRetryTemplate(retryProperties));
//        }
//        if (templateProperties.getReceiveTimeout() != null) {
//            rabbitTemplate.setReceiveTimeout(templateProperties.getReceiveTimeout());
//        }
//        if (templateProperties.getReplyTimeout() != null) {
//            rabbitTemplate.setReplyTimeout(templateProperties.getReplyTimeout());
//        }
        rabbitTemplate.convertAndSend(RabbitConfigure1.SPRING_BOOT_EXCHANGE, RabbitConfigure1.SPRING_BOOT_BIND_KEY, msgContent);



    }

    private ConnectionFactory createConnection() throws Exception {
        // 对应类：RabbitAutoConfiguration -> RabbitConnectionFactoryCreator.rabbitConnectionFactory()
        // 创建连接工厂
        RabbitConnectionFactoryBean factory = new RabbitConnectionFactoryBean();
        if (config.determineHost() != null) {
            factory.setHost(config.determineHost());
        }
        factory.setPort(config.determinePort());
        if (config.determineUsername() != null) {
            factory.setUsername(config.determineUsername());
        }
        if (config.determinePassword() != null) {
            factory.setPassword(config.determinePassword());
        }
        if (config.determineVirtualHost() != null) {
            factory.setVirtualHost(config.determineVirtualHost());
        }
        if (config.getRequestedHeartbeat() != null) {
            factory.setRequestedHeartbeat(config.getRequestedHeartbeat());
        }
        RabbitProperties.Ssl ssl = config.getSsl();
        if (ssl.isEnabled()) {
            factory.setUseSSL(true);
            if (ssl.getAlgorithm() != null) {
                factory.setSslAlgorithm(ssl.getAlgorithm());
            }
            factory.setKeyStore(ssl.getKeyStore());
            factory.setKeyStorePassphrase(ssl.getKeyStorePassword());
            factory.setTrustStore(ssl.getTrustStore());
            factory.setTrustStorePassphrase(ssl.getTrustStorePassword());
        }
        if (config.getConnectionTimeout() != null) {
            factory.setConnectionTimeout(config.getConnectionTimeout());
        }
        factory.afterPropertiesSet();
        // 创建连接工厂： CachingConnectionFactory 实际对我们rabbitMQ官方提供的java client.jar中的类进行封装，之前的文章已经讲过来这里略
        // CachingConnectionFactory会对打开和RabbbitMQ的连接进行缓存
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(
                factory.getObject());
        connectionFactory.setAddresses(config.determineAddresses());
        connectionFactory.setPublisherConfirms(config.isPublisherConfirms());
        connectionFactory.setPublisherReturns(config.isPublisherReturns());
        if (config.getCache().getChannel().getSize() != null) {
            connectionFactory
                    .setChannelCacheSize(config.getCache().getChannel().getSize());
        }
        if (config.getCache().getConnection().getMode() != null) {
            connectionFactory
                    .setCacheMode(config.getCache().getConnection().getMode());
        }
        if (config.getCache().getConnection().getSize() != null) {
            connectionFactory.setConnectionCacheSize(
                    config.getCache().getConnection().getSize());
        }
        if (config.getCache().getChannel().getCheckoutTimeout() != null) {
            connectionFactory.setChannelCheckoutTimeout(
                    config.getCache().getChannel().getCheckoutTimeout());
        }
        return connectionFactory;
    }

    public void a() throws Exception {
        // RabbitAnnotationDrivenConfiguration -> rabbitListenerContainerFactoryConfigurer()和rabbitListenerContainerFactory()
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(createConnection());

        // 其它的SimpleRabbitListenerContainerFactory的配置，可以参考:SimpleRabbitListenerContainerFactoryConfigurer.configure()方法

        // RabbitListenerAnnotationBeanPostProcessor 拦截@RabbitListener

    }
}
