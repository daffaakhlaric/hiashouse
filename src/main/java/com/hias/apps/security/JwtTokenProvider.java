package com.hias.apps.security;

import java.util.Arrays;
import java.util.Base64;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenProvider {

	@Value("${security.jwt.token.secret-key:secret}")
    private String secretKey = "secret";
    @Value("${security.jwt.token.expire-length:3600000}")
    private long validityInMilliseconds = 604800000; // 1h to be 7 days

    @Autowired
    private UserDetailsService userDetailsService;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    /**Create Token Using JWT with parameter Username and list role*/
    public String createToken(String username, List<String> roles) {
    	Claims claim = Jwts.claims().setSubject(username);
    	claim.put("roles", roles);
    	Date now = new Date();

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 7);

        return Jwts.builder()//
                .setClaims(claim)//
                .setIssuedAt(now)//
                .setExpiration(cal.getTime())//
                .signWith(SignatureAlgorithm.HS256, secretKey)//
                .compact();
    }


    public Authentication getAuthentication(String token) {
    	UserDetails userDetails = this.userDetailsService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }


    public String getUsername(String token) {
    	return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }


    public String resolveToken(HttpServletRequest req) {
    	String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }
    public boolean validateToken(String token) {
    	try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            if (claims.getBody().getExpiration().before(new Date())) {
                return false;
            }
            return true;
        } catch (JwtException | IllegalArgumentException e) {
//            throw new InvalidJwtAuthenticationException("Expired or invalid JWT token");
        	return false;
        }

    }


    public Date getExpirationDateFromToken(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getExpiration();
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }



    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }


    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsername(token);
        return (
              username.equals(userDetails.getUsername())
                    && !isTokenExpired(token));
    }

    UsernamePasswordAuthenticationToken getAuthentication(final String token, final Authentication existingAuth, final UserDetails userDetails) {

        final JwtParser jwtParser = Jwts.parser().setSigningKey(secretKey);

        final Jws<Claims> claimsJws = jwtParser.parseClaimsJws(token);

        final Claims claims = claimsJws.getBody();

        final Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get("roles").toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        return new UsernamePasswordAuthenticationToken(userDetails, "", authorities);
    }

    protected Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }
}
