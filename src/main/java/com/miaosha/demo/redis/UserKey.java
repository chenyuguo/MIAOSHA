package com.miaosha.demo.redis;

public class UserKey extends BasePrefix {

    private UserKey( String prefix) {
        super(prefix);
    }

    public static UserKey id = new UserKey("id");
    public static UserKey name = new UserKey("name");


}
