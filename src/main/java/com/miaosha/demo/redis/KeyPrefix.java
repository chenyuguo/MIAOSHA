package com.miaosha.demo.redis;

public interface KeyPrefix {

    public int getExpiredSeconds();

    public String getPrefix();
}
