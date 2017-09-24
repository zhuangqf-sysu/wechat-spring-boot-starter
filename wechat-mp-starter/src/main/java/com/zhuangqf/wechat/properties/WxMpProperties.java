package com.zhuangqf.wechat.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;


/**
 * Created by zhuangqf on 9/24/17.
 */
@ConfigurationProperties(prefix = "wechat.mp")
public class WxMpProperties {

    List<WxMpClientProperties> client;

    public List<WxMpClientProperties> getClient() {
        return client;
    }

    public void setClient(List<WxMpClientProperties> client) {
        this.client = client;
    }
}

