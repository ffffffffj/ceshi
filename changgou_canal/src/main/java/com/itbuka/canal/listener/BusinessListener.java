package com.itbuka.canal.listener;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.itbuka.canal.config.RabbitMQConfig;
import com.xpand.starter.canal.annotation.CanalEventListener;
import com.xpand.starter.canal.annotation.ListenPoint;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

@CanalEventListener//声明这是一个监听类
public class BusinessListener {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    /*
    eventType 操作数据库的类型
    rowdata 操作数据库得数据
     */
    @ListenPoint(schema = "changgou_business",table = "tb_ad")
    public void adUpdate(CanalEntry.EventType eventType,CanalEntry.RowData rowData){
        System.out.println("广告数据发生了变化");
        //获取改变之前得数据
//        rowData.getBeforeColumnsList().forEach((c)-> System.out.println("改变之前得数据:"+c.getName()+"::"+c.getValue()));
        //获取改变之后得数据
//        rowData.getAfterColumnsList().forEach((c)-> System.out.println("改变之后得数据:"+c.getName()+"::"+c.getValue()));
        for (CanalEntry.Column column : rowData.getAfterColumnsList()){
            if ("position".equals(column.getName())){
                System.out.println("发送最新的数据到MQ:"+column.getValue());

                //发送消息
                rabbitTemplate.convertAndSend("", RabbitMQConfig.AD_UPDATE_QUEUE,column.getValue());
            }
        }
    }
}
