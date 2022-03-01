package com.itheima.common.contants;

public class RabbitMQConstants {

    /**
     * 黑马面面问题相关的交换机
     */
    public final static String EXCHANGE_QUESTION="heima.mm.question";

    /**
     * 问题导入索引库所使用的路由key
     */
    public final static String ROUTING_KEY_ES="question.es";

    /**
     * 导入es索引库所使用的队列
     */
    public final static String QUEUE_ES="heima.mm.queue.es";
}
