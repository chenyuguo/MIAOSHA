package com.miaosha.demo.redis;

public interface KeyPrefix {

    public int expiredSeconds();

    public String getPrefix();
}
