package com.zhuangqf.wechat.config;

import com.zhuangqf.wechat.factory.WxMpMessageRouterFactory;
import com.zhuangqf.wechat.factory.WxMpServiceFactory;
import com.zhuangqf.wechat.properties.WxMpClientProperties;
import com.zhuangqf.wechat.properties.WxMpMessageRuleProperties;
import com.zhuangqf.wechat.properties.WxMpProperties;
import com.zhuangqf.wechat.units.BeanUnits;
import me.chanjar.weixin.mp.api.*;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by zhuangqf on 9/24/17.
 */
@Configuration
@AutoConfigureAfter({WxMpAutoConfiguration.class})
@EnableConfigurationProperties({WxMpProperties.class})
@ConditionalOnBean(name = {"mpServiceFactory","rules"})
public class WxMpDefaultAutoConfiguration {

    @Resource
    private WxMpServiceFactory mpServiceFactory;

    @Resource
    private WxMpProperties wxMpProperties;

    @Resource
    private Map<String,List<WxMpMessageRuleProperties>> mpRouters;

    @Bean
    public WxMpService mpService(){
        if(wxMpProperties.getClient()==null) return null;
        if(wxMpProperties.getClient().size()==0) return null;
        String name = wxMpProperties.getClient().get(0).getName();
        return mpServiceFactory.getService(name);
    }

    @Bean
    public WxMpMessageRouterFactory mpMessageRouterFactory(){
        WxMpMessageRouterFactory factory = new WxMpMessageRouterFactory();
        if(wxMpProperties.getClient()==null) return factory;
        if(wxMpProperties.getClient().size()==0) return factory;
        for(WxMpClientProperties properties: wxMpProperties.getClient()){
            String name = properties.getName();
            WxMpService service = mpServiceFactory.getService(name);
            if(service==null) continue;
            WxMpMessageRouter router = new WxMpMessageRouter(service);
            router = setRules(router,mpRouters.get(name));
            factory.addMpMessageRouter(name,router);
        }
        return factory;
    }

    private WxMpMessageRouter setRules(WxMpMessageRouter router,
                                       List<WxMpMessageRuleProperties>rules){
        WxMpMessageRouterRule rule = router.rule();
        for(WxMpMessageRuleProperties properties:rules){
            if(!properties.isAsync()) rule = rule.async(properties.isAsync());
            if(!isEmpty(properties.getFromUser())) rule = rule.fromUser(properties.getFromUser());
            if(!isEmpty(properties.getMsgType())) rule = rule.msgType(properties.getMsgType());
            if(!isEmpty(properties.getEvent())) rule = rule.event(properties.getEvent());
            if(!isEmpty(properties.getEventKey())) rule = rule.eventKey(properties.getEventKey());
            if(!isEmpty(properties.getContent())) rule = rule.content(properties.getContent());
            if(!isEmpty(properties.getrContent())) rule = rule.rContent(properties.getrContent());
            if(properties.isReEnter()) rule.setReEnter(properties.isReEnter());
            rule.setHandlers(createHandlers(properties.getHandlers()));
            rule.setInterceptors(createInterceptors(properties.getInterceptors()));
            if(properties.isEnd()) router = rule.end();
            else router = rule.next();
        }

        return router;
    }

    private List<WxMpMessageInterceptor> createInterceptors(List<String> interceptors) {
        List<WxMpMessageInterceptor> list = new ArrayList<>();
        if(interceptors==null) return list;
        if(interceptors.size()==0) return list;
        for(String interceptor:interceptors){
            WxMpMessageInterceptor instance = BeanUnits.newInstance(interceptor);
            list.add(instance);
        }
        return list;
    }

    private List<WxMpMessageHandler> createHandlers(List<String> handlers) {
        List<WxMpMessageHandler> list = new ArrayList<>();
        if(handlers==null) return list;
        if(handlers.size()==0) return list;
        for(String handler:handlers){
            WxMpMessageHandler instance = BeanUnits.newInstance(handler);
            list.add(instance);
        }
        return list;
    }

    private boolean isEmpty(String content) {
        return content != null &&
                !Objects.equals(content, "") &&
                content.length() > 0;
    }
}
