package com.kurdi.inventoryservice.auth.filters;

import com.google.common.base.Strings;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Key;
import java.util.ArrayList;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.List;

public class JwtTokenVerifierFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader = request.getHeader("Authorization");

        if (Strings.isNullOrEmpty(authorizationHeader)) {
            filterChain.doFilter(request, response);
            return;
        }
        List<SimpleGrantedAuthority> userAuthorities = new ArrayList<SimpleGrantedAuthority>();
        //token verification logic
        String userId = "";
        try {
            String secret = "asdfSFS34wfsdfsdfSDSD32dfsddDDerQSNCK34SOWEK5354fdgdf4";
            Key key = new SecretKeySpec(Base64.getDecoder().decode(secret), SignatureAlgorithm.HS256.getJcaName());
            authorizationHeader = authorizationHeader.split(" ")[1];
            Claims jwtClaims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(authorizationHeader).getBody();
            userId = jwtClaims.getId();
            ArrayList<LinkedHashMap<String, String>> a = (ArrayList<LinkedHashMap<String, String>>) jwtClaims.get("authorities");
            a.stream().forEach(aa -> userAuthorities.add(new SimpleGrantedAuthority(aa.values().stream().findFirst().get())));

        } catch (JwtException e) {
            //don't trust the JWT!
            filterChain.doFilter(request, response);
        }


        //TODO: get auth from token payload.
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userId,
                null,
                userAuthorities
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);


        filterChain.doFilter(request, response);


    }

}
