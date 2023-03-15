package com.jaimecorg.facultad.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import model.Permiso;
import model.Usuario;

@Service
public class UsuarioServiceImpl implements UserDetailsService{
    @Value("${url.autenticacion.rest.service}")
    String urlApi;

    @Autowired
    RestTemplate restTemplate;

    public Usuario findByName(String username) {

        Map<String, String> params = new HashMap<String, String>();
        params.put("username", username);
        params.put("password", "prueba");

        Usuario u = restTemplate.getForObject(urlApi + "usuario/buscar?username={username}&password={password}",
                Usuario.class, params);
        return u;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = findByName(username);

        List<GrantedAuthority> roles = new ArrayList<>();

        List<Permiso> permisos = user.getPermisos();

        for (Permiso p : permisos) {
            roles.add(new SimpleGrantedAuthority(p.getNombre()));
        }

        // UserDetails userDetails = new User(user.getName(), user.getPassword(),
        // roles);
        UserDetails userDetails = User.builder()
                .username(user.getName())
                .password(user.getPassword())
                .authorities(roles)
                .build();
        return userDetails;
    }
}
