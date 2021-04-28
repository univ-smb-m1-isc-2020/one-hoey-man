package com.onehoeyman.battle.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @RequestMapping(value = "/")
    @ResponseBody
    public String index(){
        return "Welcome";
    }
}
