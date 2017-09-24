package com.zhuangqf.wechat.properties;

import java.util.List;

/**
 * Created by zhuangqf on 9/24/17.
 */
public class WxMpMessageRuleProperties {

    private String name;
    private boolean end = true;
    private boolean async = true;
    private String fromUser;
    private String msgType;
    private String event;
    private String eventKey;
    private String eventKeyRegex;
    private String content;
    private String rContent;
    private String matcherClass;
    private boolean reEnter = false;
    private List<String> handlers;
    private List<String> interceptors;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEnd() {
        return end;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }

    public boolean isAsync() {
        return async;
    }

    public void setAsync(boolean async) {
        this.async = async;
    }

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getEventKey() {
        return eventKey;
    }

    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }

    public String getEventKeyRegex() {
        return eventKeyRegex;
    }

    public void setEventKeyRegex(String eventKeyRegex) {
        this.eventKeyRegex = eventKeyRegex;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getrContent() {
        return rContent;
    }

    public void setrContent(String rContent) {
        this.rContent = rContent;
    }

    public String getMatcherClass() {
        return matcherClass;
    }

    public void setMatcherClass(String matcherClass) {
        this.matcherClass = matcherClass;
    }

    public boolean isReEnter() {
        return reEnter;
    }

    public void setReEnter(boolean reEnter) {
        this.reEnter = reEnter;
    }

    public List<String> getHandlers() {
        return handlers;
    }

    public void setHandlers(List<String> handlers) {
        this.handlers = handlers;
    }

    public List<String> getInterceptors() {
        return interceptors;
    }

    public void setInterceptors(List<String> interceptors) {
        this.interceptors = interceptors;
    }

    @Override
    public String toString() {
        return "WxMpMessageRuleProperties{" +
                "name='" + name + '\'' +
                ", end=" + end +
                ", async=" + async +
                ", fromUser='" + fromUser + '\'' +
                ", msgType='" + msgType + '\'' +
                ", event='" + event + '\'' +
                ", eventKey='" + eventKey + '\'' +
                ", eventKeyRegex='" + eventKeyRegex + '\'' +
                ", content='" + content + '\'' +
                ", rContent='" + rContent + '\'' +
                ", matcherClass='" + matcherClass + '\'' +
                ", reEnter=" + reEnter +
                ", handlers=" + handlers +
                ", interceptors=" + interceptors +
                '}';
    }
}
