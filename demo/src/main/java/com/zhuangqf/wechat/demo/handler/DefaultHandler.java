package com.zhuangqf.wechat.demo.handler;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutTextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by zhuangqf on 9/30/17.
 */
public class DefaultHandler implements WxMpMessageHandler {

    private static Logger logger = LoggerFactory.getLogger(DefaultHandler.class);

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService, WxSessionManager sessionManager) throws WxErrorException {

        String user = wxMessage.getFromUser();
        logger.info("receive a message from ",user);

        logger.info("handle begin");
        for(String key: context.keySet()){
            logger.info(key,context.get(key));
        }

        WxMpXmlOutTextMessage m = WxMpXmlOutMessage.TEXT().content("testing")
                .fromUser(wxMessage.getToUser()).toUser(user).build();
        return m;
    }
}
