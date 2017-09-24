package com.zhuangqf.wechat.config;

import com.zhuangqf.wechat.factory.WxMpServiceFactory;
import com.zhuangqf.wechat.properties.WxMpProperties;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * Created by zhuangqf on 9/24/17.
 */
@Configuration
@AutoConfigureAfter({WxMpAutoConfiguration.class})
@EnableConfigurationProperties({WxMpProperties.class})
public class WxMpDefaultAutoConfiguration {

    @Autowired(required = false)
    @Qualifier("mpServiceFactory")
    private WxMpServiceFactory mpServiceFactory;

    @Resource
    private WxMpProperties wxMpProperties;

    @Bean
    @ConditionalOnBean(name = "mpServiceFactory")
    public WxMpService mpService(){
        if(wxMpProperties.getClient()==null) return null;
        if(wxMpProperties.getClient().size()==0) return null;
        String name = wxMpProperties.getClient().get(0).getName();
        return mpServiceFactory.getService(name);
    }

}
