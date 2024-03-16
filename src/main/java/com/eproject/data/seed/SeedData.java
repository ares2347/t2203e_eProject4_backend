package com.eproject.data.seed;

import com.eproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class SeedData implements CommandLineRunner {
    @Autowired
    UserRepository _userRepository;

    @Autowired
    private BCryptPasswordEncoder _passwordEncoder;

    private final String defaultPassword = "1qaz!QAZ";

    @Override
    public void run(String... args) throws Exception {
//        seedRoleData();
//        seedUserData();
    }

//    private void seedUserData() {
//        if (_userRepository.count() == 0) {
//            Set<RoleEntity> adminRoles = new HashSet<RoleEntity>();
//            adminRoles.add(_roleRepository.findRoleEntitiesByName("ADMIN"));
//            Set<RoleEntity> userRoles = new HashSet<RoleEntity>();
//            userRoles.add(_roleRepository.findRoleEntitiesByName("USER"));
//            Set<RoleEntity> brandRoles = new HashSet<RoleEntity>();
//            brandRoles.add(_roleRepository.findRoleEntitiesByName("BRAND"));
//            UserEntity admin = new UserEntity("admin@test.com", "0123456789", "Admin Seed", _passwordEncoder.encode(defaultPassword), adminRoles);
//            UserEntity seedBrand = new UserEntity("brand@test.com", "0123456788", "Brand Seed", _passwordEncoder.encode(defaultPassword), brandRoles ,new BrandEntity("Brand Seed"));
//            UserEntity seedUser = new UserEntity("user@test.com", "0123456787", "User Seed", _passwordEncoder.encode(defaultPassword), userRoles);
//            _userRepository.saveAndFlush(admin);
//            _userRepository.saveAndFlush(seedBrand);
//            _userRepository.saveAndFlush(seedUser);
//        }
//        System.out.println(_userRepository.count());
//    }
//
//    private void seedRoleData() {
//        if (_roleRepository.count() == 0){
//            RoleEntity admin = new RoleEntity("ADMIN");
//            RoleEntity user = new RoleEntity("USER");
//            RoleEntity brand = new RoleEntity("BRAND");
//            _roleRepository.saveAndFlush(admin);
//            _roleRepository.saveAndFlush(user);
//            _roleRepository.saveAndFlush(brand);
//        }
//        System.out.println(_roleRepository.count());
//    }
}
