package com.zhuangqf.wechat.factory;

import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zhuangqf on 9/24/17.
 */
public class WxMpServiceFactory {

    private ConcurrentHashMap<String,WxMpService> mpServices =
            new ConcurrentHashMap<>();

    public WxMpService getService(String name){
        return mpServices.get(name);
    }

    public void addService(String name,WxMpInMemoryConfigStorage config){
        WxMpService service = new WxMpServiceImpl();
        service.setWxMpConfigStorage(config);
        mpServices.put(name,service);
    }

    public ConcurrentHashMap.KeySetView<String, WxMpService> keySet(){
        return mpServices.keySet();
    }

}
