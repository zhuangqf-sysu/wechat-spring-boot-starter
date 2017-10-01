# wechat-spring-boot-starter

> 封装[weixin-java-tools](https://github.com/wechat-group/weixin-java-tools)为spring-boot-starter

### 使用

引入编译后的jar包，位于libs目录下，并加入其依赖：

```
    compile fileTree(dir:'../wechat-mp-starter/build/libs',include:['*.jar'])
    compile "com.github.binarywang:weixin-java-mp:2.8.0"
```

根据各个项目的application.ymal.temp配置项目

### wechat-mp-start

*微信公众号平台封装*

详情请见https://github.com/wechat-group/weixin-java-tools/wiki/MP_Quick-Start

注入以下bean：

mpService: WxMpService 用于管理公众号实例

mpserviceFactory：WxMpService的集合，用于多公众号管理

mpMessageRouterFactory：WxMpMessageRouter的集合，微信消息路由

同时，注入一个Controller作为微信回调接口

以下是application.yaml:

```
wechat.mp.client:
  - name:
    config:
      appid:
      secret:
      token:
      aesKey:
    router:
      -


wechat.mp.rules:
    - name:
      end:
      async:
      fromUser:
      msgType:
      event:
      eventKey:
      eventKeyRegex:
      content:
      rContent:
      matcherClass:
      reEnter:
      handlers:
          - handlerClass:
      interceptors:
          - interceptorClass:
```

`wechat.mp.url` 回调接口的url（全url = http://yourWebside/wx/{wechat.mp.client.name}）

`wechat.mp.client` 公众号相关配置，可以配置多个，默认第一个为mpService，具体bean可以通过`mpserviceFactory.getName({wechat.mp.client.name})获得

`wechat.mp.rules` 微信消息路由规则，用以处理微信消息，详情[MP_微信消息路由器](https://github.com/wechat-group/weixin-java-tools/wiki/MP_%E5%BE%AE%E4%BF%A1%E6%B6%88%E6%81%AF%E8%B7%AF%E7%94%B1%E5%99%A8)


