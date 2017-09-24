package com.zhuangqf.wechat.properties;

import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;

/**
 * Created by zhuangqf on 9/24/17.
 */
public class WxMpClientProperties {
    private String name;
    private WxMpInMemoryConfigStorage config;

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
}
