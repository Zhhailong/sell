package com.imooc.sell.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Auther: zhanghailong
 * @Date: 2018/8/10 20:45
 * @Description:
 */
@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WechatAccoutConfig {

    private String mpAppId;

    private String mpAppSecret;

    /**
     * 商户号
     * */
    private String mchId;

    /**
     * 商户密钥
     * */
    private String mchKey;

    /**
     * 商户证书路径
     * */
    private String keyPath;

    /**
     * 微信支付异步通知地址
     * */
    private String notifyUrl;
}
