package com.changgou.seckill.controller;

import com.changgou.entity.Result;
import com.changgou.entity.StatusCode;
import com.changgou.seckill.config.TokenDecode;
import com.changgou.seckill.service.SecKillOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seckillorder")
public class SecKillOrderController {
    @Autowired
    private SecKillOrderService secKillOrderService;
    @Autowired
    private TokenDecode tokenDecode;
    @RequestMapping("/add")
    public Result add(@RequestParam("time") String time,@RequestParam("id") Long id){
        String username=tokenDecode.getUserInfo().get("username");
        boolean result=secKillOrderService.add(id,time,username);
        if(result){
            return new Result<>(true, StatusCode.OK,"下单成功");
        }
        else{
            return new Result<>(false, StatusCode.ERROR,"下单成功");
        }
    }
}
