package com.zhuangqf.wechat.factory;

import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zhuangqf on 9/24/17.
 */
public class WxMpMessageRouterFactory {

    private ConcurrentHashMap<String,WxMpMessageRouter> routers =
            new ConcurrentHashMap<>();

    public WxMpMessageRouter getRouter(String name){
        return routers.get(name);
    }

    public void addMpMessageRouter(String name, WxMpMessageRouter router){
        routers.put(name,router);
    }

    public ConcurrentHashMap.KeySetView<String, WxMpMessageRouter> keySet(){
        return routers.keySet();
    }

}
