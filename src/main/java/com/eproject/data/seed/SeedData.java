package com.eproject.data.seed;

import com.eproject.data.usermodel.BrandEntity;
import com.eproject.data.usermodel.UserEntity;
import com.eproject.repository.user.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SeedData implements CommandLineRunner {
    @Autowired
    IUserRepository _userRepository;

    @Autowired
    private BCryptPasswordEncoder _passwordEncoder;


    private final String defaultPassword = "1qaz!QAZ";
    @Override
    public void run(String... args) throws Exception {
        seedUserData();
    }

    private void seedUserData() {
        if (_userRepository.count() == 0) {
            UserEntity admin = new UserEntity("admin@test.com", "0123456789", "Admin Seed" , _passwordEncoder.encode(defaultPassword));
            UserEntity seedBrand = new UserEntity("brand@test.com", "0123456788", "Brand Seed" , _passwordEncoder.encode(defaultPassword), new BrandEntity("Brand Seed"));
            UserEntity seedUser = new UserEntity("user@test.com", "0123456787", "User Seed" , _passwordEncoder.encode(defaultPassword));
            _userRepository.save(admin);
            _userRepository.save(seedBrand);
            _userRepository.save(seedUser);
        }
        System.out.println(_userRepository.count());
    }
}
