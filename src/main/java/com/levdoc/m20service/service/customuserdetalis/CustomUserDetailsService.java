package com.levdoc.m20service.service.customuserdetalis;

import com.levdoc.m20service.constants.UserRoleConstants;
import com.levdoc.m20service.model.UserModel;
import com.levdoc.m20service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Value("admin")
    private String adminUserName;
    @Value("$2a$10$Iln3ZMVmCjjEn2CYKMiYnO.JKJbYyjhnZuKzx/k8GP9qOGTemsxhO")
    private String adminPassword;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.equals(adminUserName)) {
            return new CustomUserDetalis(null, username, adminPassword,
                    List.of(new SimpleGrantedAuthority("ROLE_" + UserRoleConstants.ADMIN)));
        } else {
            UserModel user = userRepository.findUserModelByLogin(username);

            if (user == null) throw new UsernameNotFoundException("Пользоватлеь не найден!");

            List<GrantedAuthority> authorities = new ArrayList<>();

            if (user.getRole().getRoleTextDisplay().equals("Администратор")) {
                authorities.add(new SimpleGrantedAuthority("ROLE_" + UserRoleConstants.ADMIN));
            } else if (user.getRole().getRoleTextDisplay().equals("Лаборант")) {
                authorities.add(new SimpleGrantedAuthority("ROLE_" + UserRoleConstants.LABWORKER));
            } else if (user.getRole().getRoleTextDisplay().equals("Врач")) {
                authorities.add(new SimpleGrantedAuthority("ROLE_" + UserRoleConstants.DOCTOR));
            }

            return new CustomUserDetalis(user.getId().intValue(), username, user.getPassword(), authorities);
        }
    }
}
