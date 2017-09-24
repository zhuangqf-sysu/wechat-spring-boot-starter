package com.zhuangqf.wechat.config;

import com.zhuangqf.wechat.factory.WxMpServiceFactory;
import com.zhuangqf.wechat.properties.WxMpClientProperties;
import com.zhuangqf.wechat.properties.WxMpMessageRuleProperties;
import com.zhuangqf.wechat.properties.WxMpProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zhuangqf on 9/24/17.
 */
@Configuration
@EnableConfigurationProperties({WxMpProperties.class})
public class WxMpAutoConfiguration {

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
    public List<WxMpMessageRuleProperties> rules(){
        return wxMpProperties.getRules();
    }

}
