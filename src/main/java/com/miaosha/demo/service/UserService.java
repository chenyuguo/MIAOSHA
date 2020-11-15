package com.miaosha.demo.service;

import com.miaosha.demo.dao.UserDao;
import com.miaosha.demo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public boolean login(User user){
        String name = user.getName();
        User userInData = userDao.getByName(name);
        if(user.getPassword().equals(userInData.getPassword())){
            return true;
        }
        else{
            return false;
        }

    }


}
