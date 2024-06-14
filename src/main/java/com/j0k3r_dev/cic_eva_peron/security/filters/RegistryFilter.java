package com.j0k3r_dev.cic_eva_peron.security.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Configuration
public class RegistryFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("------------------------------------------------------------------------------------------");
        System.out.println("Registrando peticiones");
        System.out.println("Metodo: "+request.getMethod());
        System.out.println("URI: "+request.getRequestURI());
        if(request.getMethod().equals("POST") && request.getRequestURI().contains("/api/permission/save/")){
            System.out.println("Registrando PERMISO en el sistema");
        }
        if(request.getMethod().equals("POST") && request.getRequestURI().contains("/api/role/create")){
            System.out.println("Registrando ROL en el sistema");
        }

        System.out.println("------------------------------------------------------------------------------------------");
        filterChain.doFilter(request,response);
    }

}
