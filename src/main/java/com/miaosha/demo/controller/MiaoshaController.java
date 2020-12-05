package com.miaosha.demo.controller;

import com.miaosha.demo.domain.MiaoshaOrder;
import com.miaosha.demo.domain.MiaoshaUser;
import com.miaosha.demo.domain.OrderInfo;
import com.miaosha.demo.redis.RedisService;
import com.miaosha.demo.result.CodeMsg;
import com.miaosha.demo.result.Result;
import com.miaosha.demo.service.GoodsService;
import com.miaosha.demo.service.MiaoshaService;
import com.miaosha.demo.service.OrderService;
import com.miaosha.demo.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MiaoshaController {
    @Autowired
    GoodsService goodsService;
    @Autowired
    RedisService redisService;
    @Autowired
    OrderService orderService;
    @Autowired
    MiaoshaService miaoshaService;

    @RequestMapping("/do_miaosha")
    public Result miaosha(@RequestBody MiaoshaUser user, @RequestParam("goodsId") long id){
        GoodsVo goods = goodsService.getGoodsVoByGoodsId(id);
        
        int stock = goods.getStockCount();
        if(stock <= 0) {
            return Result.error(CodeMsg.NONE_GOOD);
        }
        MiaoshaOrder miaoshaOrder = orderService.getMiaoshaOrderByUserIdGoodsId(user.getId(),id);
        if(miaoshaOrder != null) {
            return Result.error(CodeMsg.REPEATE_MIAOSHA);
        }

        OrderInfo orderInfo = miaoshaService.miaosha(user,goods);

        return Result.success(orderInfo);




    }



}
