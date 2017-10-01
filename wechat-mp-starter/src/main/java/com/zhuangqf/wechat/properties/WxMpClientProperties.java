package com.zhuangqf.wechat.properties;

import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;

import java.util.List;

/**
 * Created by zhuangqf on 9/24/17.
 */
public class WxMpClientProperties {
    private String name;
    private WxMpInMemoryConfigStorage config;
    private List<String> router;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WxMpInMemoryConfigStorage getConfig() {
        return config;
    }

    public void setConfig(WxMpInMemoryConfigStorage config) {
        this.config = config;
    }

    public List<String> getRouter() {
        return router;
    }

    public void setRouter(List<String> router) {
        this.router = router;
    }

    @Override
    public String toString() {
        return "WxMpClientProperties{" +
                "name='" + name + '\'' +
                ", config=" + config +
                ", router=" + router +
                '}';
    }
}
