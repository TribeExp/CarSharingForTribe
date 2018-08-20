package com.admin_team.carsharing.utils.authentification;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Component
public class TokenProvider implements Serializable {

    /**
     * Get email from token
     * @param token - user's token
     * @return mail from token
     */
    public String getMailFromToken(String token) {
        log.info("getMailFromToken() - getting mail from token");
        return getClaimFromToken(token, Claims::getSubject);
    }

    /**
     * Get expiration date from token
     * @param token - user's token
     * @return {@link Date} expiration date
     */
    public Date getExpirationDateFromToken(String token) {
        log.info("getExpirationDateFromToken() - getting expiration date from token");
        return getClaimFromToken(token, Claims::getExpiration);
    }

    /**
     * Get claim from user token
     * @param token - user's token
     * @param claimsResolver - claims resolver
     * @param <T> - the type of claims that we want to receive
     * @return claims that we want to receive
     */
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        log.info("getClaimFromToken() - getting concrete claim from token");
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Parse the token and get all claims
     * @param token - user's token
     * @return - all {@link Claims} of the token
     */
    private Claims getAllClaimsFromToken(String token) {
        log.info("getAllClaimsFromToken() - getting all claims from token");
        return Jwts.parser()
                .setSigningKey(Constants.SIGNING_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Check expiration date from token
     * @param token - user's token
     * @return true if token expired
     */
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        final boolean expired = expiration.before(new Date());
        log.info("isTokenExpired() " + expired);
        return expired;
    }

    /**
     * Generate new token from {@link Authentication}
     * @param authentication {@link Authentication}
     * @return token
     */
    public String generateToken(Authentication authentication) {
        final String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        log.info("generateToken()");
        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim(Constants.AUTHORITIES_KEY, authorities)
                .signWith(SignatureAlgorithm.HS256, Constants.SIGNING_KEY)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + Constants.ACCESS_TOKEN_VALIDITY_SECONDS*1000))
                .compact();
    }

    /**
     * Token validation
     * @param token - user's token
     * @param userDetails {@link UserDetails}
     * @return true if the token is valid
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getMailFromToken(token);
        final boolean isValid = (
                username.equals(userDetails.getUsername())
                        && !isTokenExpired(token));
        log.info("validateToken() result: " + isValid);
        return isValid;
    }

    /**
     * Get {@link UsernamePasswordAuthenticationToken}
     * @param token - user's token
     * @param existingAuth {@link Authentication}
     * @param userDetails {@link UserDetails}
     * @return {@link UsernamePasswordAuthenticationToken}
     */
    UsernamePasswordAuthenticationToken getAuthentication(final String token, final Authentication existingAuth, final UserDetails userDetails) {
        final JwtParser jwtParser = Jwts.parser().setSigningKey(Constants.SIGNING_KEY);
        final Jws<Claims> claimsJws = jwtParser.parseClaimsJws(token);
        final Claims claims = claimsJws.getBody();

        final Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(Constants.AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());
        log.info("getAuthentication()");
        return new UsernamePasswordAuthenticationToken(userDetails, "", authorities);
    }

}
