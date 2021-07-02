package com.l024.ljl.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.l024.ljl.entity.SysUserEntity;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("TokenService")
public class TokenService {

    public String getToken(SysUserEntity user) {
        Date start = new Date();
        long currentTime = System.currentTimeMillis() + 60* 60 * 24 * 1000;//一小时有效时间
        Date end = new Date(currentTime);
        String token = "";
        token = JWT.create().withAudience(user.getPhone()).withIssuedAt(start).withExpiresAt(end)
                .sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }
}