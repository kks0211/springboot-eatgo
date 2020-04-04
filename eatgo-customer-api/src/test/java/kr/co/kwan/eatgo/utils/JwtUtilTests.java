package kr.co.kwan.eatgo.utils;


import io.jsonwebtoken.Claims;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class JwtUtilTests {
    private static final String secret = "12345678901234567890123456789012";

    private JwtUtil jwtUtil;

    @Before
    public  void setUp (){
        jwtUtil = new JwtUtil(secret);
    }

    @Test
    public void createToken(){
        String token = jwtUtil.createToken(1004L, "John", null);

        assertThat(token, containsString("."));
    }

    @Test
    public void getClaims(){
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjEwMDQsIm5hbWUiOiJKb2huIn0.8hm6ZOJykSINHxL-rf0yV882fApL3hyQ9-WGlJUyo2A";
        Claims claims =  jwtUtil.getClaims(token);

        assertThat(claims.get("userId", Long.class)).isEqualTo(1004L);
        assertThat(claims.get("name")).isEqualTo("John");
    }

}