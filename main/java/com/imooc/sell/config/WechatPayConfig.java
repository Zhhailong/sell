package com.imooc.sell.config;

import com.lly835.bestpay.config.WxPayH5Config;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @Auther: zhanghailong
 * @Date: 2018/8/11 22:39
 * @Description: 支付配置
 */
@Component
public class WechatPayConfig {

    @Autowired
    private WechatAccoutConfig accoutConfig;

    @Bean
    public BestPayServiceImpl bestPayService(){

        BestPayServiceImpl bestPayService = new BestPayServiceImpl();
        bestPayService.setWxPayH5Config(wxPayH5Config());

        return bestPayService;
    }

    @Bean
    public WxPayH5Config wxPayH5Config(){

        WxPayH5Config wxPayH5Config = new WxPayH5Config();
        wxPayH5Config.setAppId(accoutConfig.getMpAppId());
        wxPayH5Config.setAppSecret(accoutConfig.getMpAppSecret());
        wxPayH5Config.setMchId(accoutConfig.getMchId());
        wxPayH5Config.setMchKey(accoutConfig.getMchKey());
        wxPayH5Config.setKeyPath(accoutConfig.getKeyPath());

        return wxPayH5Config;

    }
}
