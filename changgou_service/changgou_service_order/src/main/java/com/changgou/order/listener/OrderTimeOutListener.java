package com.changgou.order.listener;


import com.changgou.order.service.OrderService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderTimeOutListener {

    @Autowired
    private OrderService orderService;

    @RabbitListener(queues = "queue.ordertimeout")
    public void receiveCloseOrderMessage(String message){
        System.out.println("接收到关闭订单消息"+message);

        try {
            orderService.closeOrder(message);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
