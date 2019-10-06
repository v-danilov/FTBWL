package ru.malifaux.programming.FTBWL.common.security.support;

import java.util.Calendar;
import java.util.Date;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;
import lombok.*;

/**
 * Util for token generate
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JwtTokenUtils {

    public static String generateToken(JwtUserClaims user, String secret) {
        if (user == null) {
            return null;
        }
        Date expirationDate = expirationDate();
        return Jwts.builder()
                .setClaims(user)
                .setSubject(user.getLogin())
                .setIssuedAt(new Date())
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public static JwtUserClaims parseToken(String token, String secret) {
        return new JwtUserClaims((DefaultClaims)Jwts.parser().setSigningKey(secret).parse(token).getBody());
    }

    protected static Date expirationDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        return calendar.getTime();
    }
}