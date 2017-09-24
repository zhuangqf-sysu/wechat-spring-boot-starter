package com.zhuangqf.wechat.controller;

import com.zhuangqf.wechat.factory.WxMpMessageRouterFactory;
import com.zhuangqf.wechat.factory.WxMpServiceFactory;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by zhuangqf on 9/24/17.
 */
@RestController("${wechat.mp.url}")
@ConditionalOnBean(name= {"mpMessageRouterFactory","mpServiceFactory"})
public class MpController {

    @Resource
    private WxMpMessageRouterFactory mpMessageRouterFactory;

    @Resource
    private WxMpServiceFactory mpServiceFactory;

    @RequestMapping("{mpName}")
    public void mpRequest(HttpServletRequest request,
                          HttpServletResponse response,
                          String mpName) throws IOException {
        WxMpMessageRouter wxMpMessageRouter = mpMessageRouterFactory.getRouter(mpName);
        WxMpService wxMpService = mpServiceFactory.getService(mpName);

        String signature = request.getParameter("signature");
        String nonce = request.getParameter("nonce");
        String timestamp = request.getParameter("timestamp");

        if (!wxMpService.checkSignature(timestamp, nonce, signature)) {
            // 消息签名不正确，说明不是公众平台发过来的消息
            response.getWriter().println("非法请求");
            return;
        }

        String echostr = request.getParameter("echostr");
        if (StringUtils.isNotBlank(echostr)) {
            // 说明是一个仅仅用来验证的请求，回显echostr
            response.getWriter().println(echostr);
            return;
        }

        String encryptType = StringUtils.isBlank(request.getParameter("encrypt_type")) ?
                "raw" :
                request.getParameter("encrypt_type");

        if ("raw".equals(encryptType)) {
            // 明文传输的消息
            WxMpXmlMessage inMessage = WxMpXmlMessage.fromXml(request.getInputStream());
            WxMpXmlOutMessage outMessage = wxMpMessageRouter.route(inMessage);
            response.getWriter().write(outMessage.toXml());
            return;
        }

        if ("aes".equals(encryptType)) {
            // 是aes加密的消息
            String msgSignature = request.getParameter("msg_signature");
            WxMpXmlMessage inMessage = WxMpXmlMessage.fromEncryptedXml(request.getInputStream(),
                    wxMpService.getWxMpConfigStorage(), timestamp, nonce, msgSignature);
            WxMpXmlOutMessage outMessage = wxMpMessageRouter.route(inMessage);
            response.getWriter().write(outMessage.toEncryptedXml(wxMpService.getWxMpConfigStorage()));
            return;
        }

        response.getWriter().println("不可识别的加密类型");
        return;
    }
    }

}
