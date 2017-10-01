package com.zhuangqf.wechat.config;

import com.zhuangqf.wechat.factory.WxMpServiceFactory;
import com.zhuangqf.wechat.properties.WxMpClientProperties;
import com.zhuangqf.wechat.properties.WxMpMessageRuleProperties;
import com.zhuangqf.wechat.properties.WxMpProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by zhuangqf on 9/24/17.
 */
@Configuration
@EnableConfigurationProperties({WxMpProperties.class})
public class WxMpAutoConfiguration {

    private static Logger logger = LoggerFactory.getLogger(WxMpAutoConfiguration.class);

    @Resource
    private WxMpProperties wxMpProperties;

    @Bean
    public WxMpServiceFactory mpServiceFactory(){
        WxMpServiceFactory factory = new WxMpServiceFactory();
        if(wxMpProperties.getClient()==null) return factory;
        if(wxMpProperties.getClient().size()==0) return factory;
        for(WxMpClientProperties properties: wxMpProperties.getClient()){
            factory.addService(properties.getName(),properties.getConfig());
        }
        return factory;
    }


    @Bean
    public Map<String,List<WxMpMessageRuleProperties>> mpRouters(){
        Map<String,List<WxMpMessageRuleProperties>> routers =
                new HashMap<>();
        Map<String,WxMpMessageRuleProperties> rules = wxMpProperties.getRules();

        for(WxMpClientProperties properties:wxMpProperties.getClient()){
            String name = properties.getName();
            List<String> routerList = properties.getRouter();
            List<WxMpMessageRuleProperties> router = new ArrayList<>();
            for(String ruleName:routerList){
                WxMpMessageRuleProperties ruleProperties
                        = rules.get(ruleName);
                router.add(ruleProperties);
            }
            routers.put(name,router);
        }
        return routers;
    }

}
