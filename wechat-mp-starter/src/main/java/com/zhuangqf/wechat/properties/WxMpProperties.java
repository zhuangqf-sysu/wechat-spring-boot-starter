package com.zhuangqf.wechat.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;


/**
 * Created by zhuangqf on 9/24/17.
 */
@ConfigurationProperties(prefix = "wechat.mp")
public class WxMpProperties {

    List<WxMpClientProperties> client;
    Map<String,WxMpMessageRuleProperties> rules;

    public List<WxMpClientProperties> getClient() {
        return client;
    }

    public void setClient(List<WxMpClientProperties> client) {
        this.client = client;
    }

    public Map<String, WxMpMessageRuleProperties> getRules() {
        return rules;
    }

    public void setRules(Map<String, WxMpMessageRuleProperties> rules) {
        this.rules = rules;
    }
}

