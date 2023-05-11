package com.changgou.seckill.service.impl;

import com.alibaba.fastjson.JSON;
import com.changgou.seckill.config.ConfirmMessageSender;
import com.changgou.seckill.config.RabbitMQConfig;
import com.changgou.seckill.pojo.SeckillGoods;
import com.changgou.seckill.pojo.SeckillOrder;
import com.changgou.seckill.service.SecKillOrderService;
import com.changgou.util.IdWorker;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SecKillOrderServiceImpl implements SecKillOrderService {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private ConfirmMessageSender confirmMessageSender;

    public static final String SECKILL_GOODS_KEY="seckill_goods_";

    public static final String SECKILL_GOODS_STOCK_COUNT_KEY="seckill_goods_";

    @Override
    public boolean add(Long id, String time, String username) {
        SeckillGoods seckillGoods= (SeckillGoods) redisTemplate.boundHashOps(SECKILL_GOODS_KEY+time).get(id);
        String redisStock= (String) redisTemplate.opsForValue().get(SECKILL_GOODS_STOCK_COUNT_KEY+id);
        if(StringUtils.isEmpty(redisStock))
            return false;
        int stock=Integer.parseInt(redisStock);
        if(seckillGoods==null||stock<=0)
            return false;
        Long decrement=redisTemplate.opsForValue().decrement(SECKILL_GOODS_STOCK_COUNT_KEY+id);
        if(decrement==0){
            redisTemplate.boundHashOps(SECKILL_GOODS_KEY+time).delete(id);
            redisTemplate.delete(SECKILL_GOODS_STOCK_COUNT_KEY+id);
        }
        SeckillOrder seckillOrder=new SeckillOrder();
        seckillOrder.setSeckillId(idWorker.nextId());
        seckillOrder.setSeckillId(id);
        seckillOrder.setMoney(seckillGoods.getCostPrice());
        seckillOrder.setUserId(username);
        seckillOrder.setSellerId(seckillGoods.getSellerId());
        seckillOrder.setCreateTime(new Date());
        seckillOrder.setStatus("0");
        confirmMessageSender.sendMessage("",RabbitMQConfig.SECKILL_ORDER_QUEUE, JSON.toJSONString(seckillOrder));
        return true;
    }
}
