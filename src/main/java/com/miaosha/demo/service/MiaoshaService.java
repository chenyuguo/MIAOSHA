package com.miaosha.demo.service;

import com.miaosha.demo.dao.GoodsDao;
import com.miaosha.demo.domain.Goods;
import com.miaosha.demo.domain.MiaoshaOrder;
import com.miaosha.demo.domain.MiaoshaUser;
import com.miaosha.demo.domain.OrderInfo;
import com.miaosha.demo.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MiaoshaService {
    @Autowired
    GoodsService goodsService;

    @Autowired
    OrderService orderService;
    @Transactional
    public OrderInfo miaosha (MiaoshaUser user, GoodsVo goods) {
        //减库存
        goodsService.reduceStock(goods);
        //下订单
        return orderService.createOrder(user,goods);

    }

}
