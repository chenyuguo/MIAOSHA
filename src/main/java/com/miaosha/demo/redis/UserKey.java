package com.miaosha.demo.redis;

public class UserKey extends BasePrefix {

    public static final int tokenExpireTime = 3600 * 2 * 24;
    private UserKey(int tokenExpireTime, String prefix) {
        super(tokenExpireTime,prefix);
    }
    private UserKey(String prefix) {
        super(prefix);
    }
    public static UserKey id = new UserKey("id");
    public static UserKey name = new UserKey("name");
    public static UserKey token = new UserKey(tokenExpireTime,"token");



}
