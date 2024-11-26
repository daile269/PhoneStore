package com.phonestoreweb.phonestore.controllers.web;

import com.phonestoreweb.phonestore.models.User;
import com.phonestoreweb.phonestore.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
public class WebUserController {

    @Autowired
    private IUserService userService;


    @PostMapping(value = "/register")
    @ResponseBody
    public User createUser(@RequestBody User user){
        if(userService.userIsExits(user.getUsername())){
            return null;
        }
        return userService.saveUser(user);
    }


    @GetMapping(value = "/myInfo")
    @ResponseBody
    public User getMyInfo(){
        return userService.getMyInfo();
    }
}
