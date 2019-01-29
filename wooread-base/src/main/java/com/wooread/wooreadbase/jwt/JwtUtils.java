package com.wooread.wooreadbase.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;


public class JwtUtils {
    public static final String TOKEN_HEADER_NAME = "Authorization";
    public static final String SECRET = "XX#$%()(#*!()!KL<><QK sdfkjswaredcom.wooreadni45fdf>?N<:{LWPW";

    public static final String SUBJECT_JAVA_CLASS = "SUBJECT_JAVA_CLASS";

    public static class TokenBuilder {
        private JWTCreator.Builder builder = JWT.create();

        public TokenBuilder(Map<String, String> claims) {
            if (claims != null) {
                for (String key : claims.keySet()) {
                    builder.withClaim(key, claims.get(key));
                }
            }
        }

        public TokenBuilder() {

        }

        public void addSubject(String subject) {
            builder.withSubject(subject);
        }

        public void addExpTime(Date date) {
            builder.withExpiresAt(date);
        }

        public void addClaim(String key, String value) {
            builder.withClaim(key, value);
        }

        public String sign() {
            try {
                return builder.sign(Algorithm.HMAC256(SECRET));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }

    public static class DecodedToken {

        private DecodedJWT decode;

        public DecodedToken(String token) {
            decode = JWT.decode(token);
        }

        public boolean isTrust() {
            try {
                Algorithm.HMAC256(SECRET).verify(decode);
                return true;
            } catch (SignatureVerificationException e) {
                return false;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return false;
            }
        }

        public boolean isExp() {
            return decode.getExpiresAt().before(new Date());
        }

        public Date getExpTime() {
            return decode.getExpiresAt();
        }

        public String getClaim(String key) {
            return decode.getClaim(key).asString();
        }

        public String getSubject() {
            return decode.getSubject();
        }

        public <T> T getSubject(Class<T> clazz) {
            return new Gson().fromJson(getSubject(), clazz);
        }

    }

}
