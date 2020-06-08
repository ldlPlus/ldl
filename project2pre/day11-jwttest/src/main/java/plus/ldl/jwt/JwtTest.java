package plus.ldl.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * @author ldl.plus
 * @date 2020年06月05日  22:38
 */
public class JwtTest {
    public static void main(String[] args) {
        Date date = new Date(System.currentTimeMillis() + 1000 * 60 * 60);
        // 生成jwt令牌
        JwtBuilder jwtBuilder = Jwts.builder()
                // 设置jwt编码
                .setId("66")
                // 设置jwt主题
                .setSubject("黑马程序员")
                // 设置jwt签发日期
                .setIssuedAt(new Date())
                // 设置jwt的过期时间
                .setExpiration(date)
                // 手动设置信息 自定义claims
                .claim("roles", "admin")
                .claim("company", "itcast")
                // HS256加密算法，秘钥
                .signWith(SignatureAlgorithm.HS256, "itheima");
        // 生成jwt
        String jwtToken = jwtBuilder.compact();
        System.out.println("jwtToken = " + jwtToken);
        System.out.println("length = " + jwtToken.length());

        //解析jwt令牌得到其中数据
        Claims claims = Jwts.parser().setSigningKey("itheima").parseClaimsJws(jwtToken).getBody();
        System.out.println("claims = " + claims);

    }
}
