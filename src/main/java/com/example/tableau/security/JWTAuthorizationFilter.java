package com.example.tableau.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JWTAuthorizationFilter extends OncePerRequestFilter {
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, 
                                    HttpServletResponse response, 
                                    FilterChain filterChain)
            throws ServletException, IOException {
        
        System.out.println("========================================");
        System.out.println("🔍 Requête: " + request.getMethod() + " " + request.getRequestURI());
        
        String jwt = request.getHeader("Authorization");
        System.out.println("🔍 Authorization Header: " + (jwt != null ? "Présent" : "Absent"));
        
        if (jwt == null || !jwt.startsWith(SecParams.PREFIX)) {
            System.out.println("⚠️ Pas de token JWT - Accès refusé");
            System.out.println("========================================");
            filterChain.doFilter(request, response);
            return;
        }
        
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SecParams.SECRET)).build();
            jwt = jwt.substring(SecParams.PREFIX.length());
            
            DecodedJWT decodedJWT = verifier.verify(jwt);
            String username = decodedJWT.getSubject();
            List<String> roles = decodedJWT.getClaims().get("roles").asList(String.class);
            
            System.out.println("✅ Token valide");
            System.out.println("👤 Utilisateur: " + username);
            System.out.println("🎭 Rôles: " + roles);
            
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            for (String r : roles) {
                authorities.add(new SimpleGrantedAuthority(r));
            }
            
            UsernamePasswordAuthenticationToken user = 
                new UsernamePasswordAuthenticationToken(username, null, authorities);
            
            SecurityContextHolder.getContext().setAuthentication(user);
            System.out.println("✅ Authentification OK");
            
        } catch (Exception e) {
            System.err.println("❌ ERREUR JWT: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
        
        System.out.println("========================================");
        filterChain.doFilter(request, response);
    }
}
