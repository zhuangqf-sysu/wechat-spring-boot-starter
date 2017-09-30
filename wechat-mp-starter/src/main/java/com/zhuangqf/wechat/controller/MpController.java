package com.zhuangqf.wechat.controller;

import com.zhuangqf.wechat.factory.WxMpMessageRouterFactory;
import com.zhuangqf.wechat.factory.WxMpServiceFactory;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by zhuangqf on 9/24/17.
 */
@RestController
@ConditionalOnBean(name= {"mpMessageRouterFactory","mpServiceFactory"})
public class MpController {

    private static Logger logger = LoggerFactory.getLogger(MpController.class);

    @Resource
    private WxMpMessageRouterFactory mpMessageRouterFactory;

    @Resource
    private WxMpServiceFactory mpServiceFactory;

    @RequestMapping("wx/{mpName}")
    public String mpRequest(@ModelAttribute("mpName") String mpName,
                            String echostr, String signature,String nonce,
                            String timestamp,String encrypt_type,
                            HttpServletRequest request) throws IOException {

        logger.info("mpName:"+mpName);

        WxMpMessageRouter wxMpMessageRouter = mpMessageRouterFactory.getRouter(mpName);
        WxMpService wxMpService = mpServiceFactory.getService(mpName);

        if(wxMpService==null){
            logger.error("未启用此微信服务端:" + mpName);
            return "未启用此微信服务端:" + mpName;
        }
        if(wxMpMessageRouter==null){
            logger.error("该微信服务端的消息路由未设置：" + mpName);
            return "该微信服务端的消息路由未设置：" + mpName;
        }

        if (!wxMpService.checkSignature(timestamp, nonce, signature)) {
            // 消息签名不正确，说明不是公众平台发过来的消息
            logger.info("非法请求");
            return "非法请求";
        }

        if (StringUtils.isNotBlank(echostr)) {
            // 说明是一个仅仅用来验证的请求，回显echostr
            logger.info("微信接口验证:"+echostr);
            return echostr;
        }

        if ("aes".equals(encrypt_type)) {
            // 是aes加密的消息
            String msgSignature = request.getParameter("msg_signature");
            WxMpXmlMessage inMessage = WxMpXmlMessage.fromEncryptedXml(request.getInputStream(),
                    wxMpService.getWxMpConfigStorage(), timestamp, nonce, msgSignature);
            WxMpXmlOutMessage outMessage = wxMpMessageRouter.route(inMessage);
            return outMessage.toEncryptedXml(wxMpService.getWxMpConfigStorage());
        }else{
            WxMpXmlMessage inMessage = WxMpXmlMessage.fromXml(request.getInputStream());
            WxMpXmlOutMessage outMessage = wxMpMessageRouter.route(inMessage);
            return outMessage.toXml();
        }
    }

}
