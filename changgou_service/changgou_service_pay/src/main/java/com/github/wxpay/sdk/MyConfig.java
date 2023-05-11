package com.github.wxpay.sdk;

import java.io.InputStream;


public class MyConfig extends WXPayConfig{
    @Override
    public String getAppID() {
        return "wx796055a9a5d2822b";
    }

    @Override
    public String getMchID() {
        return "1617197168";
    }

    @Override
    public String getKey() {
        return "sahuan66sahuan66sahuan66sahuan66";
    }

    @Override
    public InputStream getCertStream() {
        return null;
    }

    @Override
    public IWXPayDomain getWXPayDomain() {
        return new IWXPayDomain() {
            @Override
            public void report(String s, long l, Exception e) {

            }

            @Override
            public DomainInfo getDomain(WXPayConfig wxPayConfig) {
                return new DomainInfo("api.mch.weixin.qq.com",true);
            }
        };
    }
}
