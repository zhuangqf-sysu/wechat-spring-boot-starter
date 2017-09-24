package com.zhuangqf.wechat.properties;

import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpMessageRouterRule;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;


/**
 * Created by zhuangqf on 9/24/17.
 */
@ConfigurationProperties(prefix = "wechat.mp")
public class WxMpProperties {

    List<WxMpClientProperties> client;
    List<WxMpMessageRuleProperties> rules;

    public List<WxMpClientProperties> getClient() {
        return client;
    }

    public void setClient(List<WxMpClientProperties> client) {
        this.client = client;
    }

    public List<WxMpMessageRuleProperties> getRules() {
        return rules;
    }

    public void setRules(List<WxMpMessageRuleProperties> rules) {
        this.rules = rules;
    }
}

