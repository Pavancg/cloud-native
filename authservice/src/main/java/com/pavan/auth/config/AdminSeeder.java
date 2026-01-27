package com.pavan.auth.config;

import com.pavan.auth.entity.User;
import com.pavan.auth.enums.Role;
import com.pavan.auth.enums.UserStatus;
import com.pavan.auth.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminSeeder {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    @PostConstruct
    public void createAdmin() {

        if (userRepository.findByEmail("admin@company.com").isEmpty()) {

            User admin = new User();
            admin.setFullName("System Admin");
            admin.setEmail("admin@company.com");
            admin.setPassword(encoder.encode("admin123"));
            admin.setRole(Role.ADMIN);
            admin.setStatus(UserStatus.ACTIVE);

            userRepository.save(admin);
        }
    }
}
