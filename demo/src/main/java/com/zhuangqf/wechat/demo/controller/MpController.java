package com.zhuangqf.wechat.demo.controller;

import com.zhuangqf.wechat.factory.WxMpMessageRouterFactory;
import com.zhuangqf.wechat.factory.WxMpServiceFactory;
import com.zhuangqf.wechat.properties.WxMpMessageRuleProperties;
import me.chanjar.weixin.mp.api.WxMpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zhuangqf on 9/24/17.
 */
@RestController
public class MpController {

    private static Logger logger = LoggerFactory.getLogger(MpController.class);

    @Resource
    private WxMpServiceFactory mpServiceFactory;

    @Resource
    private WxMpService mpService;

    @Resource
    private WxMpMessageRouterFactory mpMessageRouterFactory;

    @Resource
    private List<WxMpMessageRuleProperties> rules;


    @RequestMapping("serviceFactory")
    public String serviceFactory(){
        for(String name:mpServiceFactory.keySet()){
            System.out.println(name+":"+mpServiceFactory.getService(name).toString());
            logger.debug(name,mpServiceFactory.getService(name));
        }
        return mpServiceFactory.keySet().toString();
    }

    @RequestMapping("service")
    public String service(){
        return mpService.toString();
    }

    @RequestMapping("router")
    public String router(){
        for(String name:mpMessageRouterFactory.keySet()){
            System.out.println(name+":"+mpMessageRouterFactory.getRouter(name).toString());
            logger.debug(name,mpServiceFactory.getService(name));
        }
        return mpMessageRouterFactory.keySet().toString();
    }


    @RequestMapping("rules")
    public String rules(){
        return rules.toString();
    }

}
