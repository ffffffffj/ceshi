package com.changgou.oauth;

import org.junit.Test;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;

public class ParseJwtTest {

    @Test
    public void parseJwt(){
        //基于公钥解析jwt
        String jwt = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJzY29wZSI6WyJhcHAiXSwibmFtZSI6bnVsbCwiaWQiOm51bGwsImV4cCI6MTY1ODIzNjc0NywiYXV0aG9yaXRpZXMiOlsidXNlciIsImFjY291dGFudCIsInNhbGVzbWFuIl0sImp0aSI6Ijc4MjdiNGY3LTU1YjYtNGZhOS1hNzVjLWFlZTE5ZDdhOTNhMCIsImNsaWVudF9pZCI6ImNoYW5nZ291IiwidXNlcm5hbWUiOiJzdW53dWtvbmcifQ.AO1Kr9iXf4bxPOYej7Q-l66aFhDrHStkQJKaQ9oWdx6JGGuUceQuq3Dvh7p7PC--4CobdC0YRWLx7r5A8bhCij0TcRnd491gmuS-ywhlis6vu5urJ2C6q9NjcMFt1WSbpMoTmfAgdC32CZUhFKfafpKZaHdhoHB4wUXhXu3ToTt9do2jNYy5vVM_MUF0MF99I1GBuN80hXvSjb2TbXMH97lCBgUaSKeinsZfUrNwHu9bfRT5Qc0tAi2IfK8pJGznFx2Z1viZMPYM7fG_Y8PK_yaTFbEQyBVKbkSP-elFgPHrLqzIT2CQWliy8-KC6o76oiw_N1P6uGvc7G1s78z2lg";

        String publicKey = "-----BEGIN PUBLIC KEY-----MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAvFsEiaLvij9C1Mz+oyAmt47whAaRkRu/8kePM+X8760UGU0RMwGti6Z9y3LQ0RvK6I0brXmbGB/RsN38PVnhcP8ZfxGUH26kX0RK+tlrxcrG+HkPYOH4XPAL8Q1lu1n9x3tLcIPxq8ZZtuIyKYEmoLKyMsvTviG5flTpDprT25unWgE4md1kthRWXOnfWHATVY7Y/r4obiOL1mS5bEa/iNKotQNnvIAKtjBM4RlIDWMa6dmz+lHtLtqDD2LF1qwoiSIHI75LQZ/CNYaHCfZSxtOydpNKq8eb1/PGiLNolD4La2zf0/1dlcr5mkesV570NxRmU1tFm8Zd3MZlZmyv9QIDAQAB-----END PUBLIC KEY-----";

        Jwt token = JwtHelper.decodeAndVerify(jwt, new RsaVerifier(publicKey));

        String claims = token.getClaims();
        System.out.println(claims);
    }
}
