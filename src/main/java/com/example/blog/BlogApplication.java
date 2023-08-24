package com.example.blog;

import com.example.blog.config.CustomUserDetails;
import com.example.blog.entity.Role;
import com.example.blog.entity.User;
import com.example.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

@SpringBootApplication
public class BlogApplication {
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public BlogApplication(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }
    public void authenticationManager(AuthenticationManagerBuilder authenticationManagerBuilder, UserRepository repository) throws Exception {
        if(repository.count() == 0)
            repository.save(new User("admin","adminPassword",Arrays.asList(new Role("USER"), new Role("ACTUATOR"), new Role("ADMIN"))));
        authenticationManagerBuilder.userDetailsService(username -> new CustomUserDetails(repository.findUserByUserName(username)));
    }
    private UserDetailsService userDetailsService(final UserRepository repository) {
        return username -> new CustomUserDetails(repository.findUserByUserName(username));
    }


}
