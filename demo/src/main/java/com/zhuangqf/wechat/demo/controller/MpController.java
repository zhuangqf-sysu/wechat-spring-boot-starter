package com.zhuangqf.wechat.demo.controller;

import com.zhuangqf.wechat.factory.WxMpServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by zhuangqf on 9/24/17.
 */
@RestController
public class MpController {

    private static Logger logger = LoggerFactory.getLogger(MpController.class);

    @Resource
    private WxMpServiceFactory mpServiceFactory;


    @RequestMapping("service")
    public String service(){
        for(String name:mpServiceFactory.keySet()){
            logger.debug(name,mpServiceFactory.getService(name));
        }
        return mpServiceFactory.keySet().toString();
    }

}
