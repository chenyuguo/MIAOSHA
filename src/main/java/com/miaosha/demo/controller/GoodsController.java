package com.miaosha.demo.controller;

import com.miaosha.demo.domain.MiaoshaUser;
import com.miaosha.demo.domain.User;
import com.miaosha.demo.redis.RedisService;
import com.miaosha.demo.result.CodeMsg;
import com.miaosha.demo.result.Result;
import com.miaosha.demo.service.GoodsService;
import com.miaosha.demo.service.MiaoshaUserService;
import com.miaosha.demo.vo.GoodsDetailVo;
import com.miaosha.demo.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    MiaoshaUserService miaoshaUserService;
    @Autowired
    RedisService redisService;
    @Autowired
    GoodsService goodsService;

    @RequestMapping("/list")
    public Result list(MiaoshaUser user) {
        if(user == null) {
            return Result.error(CodeMsg.NONE_LOGIN);
        }
        else {
            return Result.success(goodsService.listGoodsVo());
        }
    }

    @RequestMapping("/detail/{id}")
    public Result<GoodsDetailVo> detail(@PathVariable("id") long id, MiaoshaUser user) {
        GoodsVo good = goodsService.getGoodsVoByGoodsId(id);

        if(good != null) {

            int miaoshaStatus = 0;
            long remainSeconds = 0;
            long startDate = good.getStartDate().getTime();
            long endDate = good.getEndDate().getTime();
            long now = System.currentTimeMillis();

            if(now < startDate) {//未到秒杀时间
                miaoshaStatus = 0;
                remainSeconds = (startDate - now) / 1000;
            }
            else if(now > endDate) {//超过秒杀时间了
                miaoshaStatus = 2;
                remainSeconds = -1;
            }
            else {
                miaoshaStatus = 1;
                //剩余时间
                remainSeconds = (endDate - now) / 1000;
            }

            GoodsDetailVo goodsDetailVo = new GoodsDetailVo();
            goodsDetailVo.setGoods(good);
            goodsDetailVo.setUser(user);
            goodsDetailVo.setMiaoshaStatus(miaoshaStatus);
            goodsDetailVo.setRemainSeconds(remainSeconds);
            return Result.success(goodsDetailVo);
        }
        else {
            return Result.error(CodeMsg.NONE_GOOD);
        }
    }
}
