package com.eproject.data.seed;

import com.eproject.data.model.brandmodel.BrandEntity;
import com.eproject.data.model.usermodel.RoleEntity;
import com.eproject.data.model.usermodel.UserEntity;
import com.eproject.data.model.usermodel.UserRolesEnum;
import com.eproject.repository.BrandRepository;
import com.eproject.repository.ReferenceDataRepository;
import com.eproject.repository.RoleRepository;
import com.eproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class SeedData implements CommandLineRunner {
    @Autowired
    UserRepository _userRepository;
    @Autowired
    RoleRepository _roleRepository;
    @Autowired
    BrandRepository _brandRepository;
    @Autowired
    ReferenceDataRepository _referenceDataRepository;

    @Autowired
    private BCryptPasswordEncoder _passwordEncoder;

    private final String defaultPassword = "1qaz!QAZ";

    @Override
    public void run(String... args) throws Exception {
        seedRoleData();
        seedUserData();
    }

    private void seedUserData() {
        if (_userRepository.count() == 0) {
            List<RoleEntity> roleEntities = _roleRepository.findAll();
            String encoded = _passwordEncoder.encode(defaultPassword);
            //admin
            UserEntity admin = new UserEntity("admin@test.com", "0123456789", "Admin Seed", encoded);
            admin.setUserRoles(roleEntities.stream().filter(x -> x.roleName.equals(UserRolesEnum.ADMIN.roleName)).collect(Collectors.toSet()));
            //brand
            UserEntity brand = new UserEntity("brand@test.com", "0123456788", "Brand Seed", _passwordEncoder.encode(defaultPassword));
            BrandEntity brandEntity = new BrandEntity("Brand Seed", "0123456788", "brand@test.com");
            BrandEntity brandRs = _brandRepository.saveAndFlush(brandEntity);
            brand.setUserRoles(roleEntities.stream().filter(x -> x.roleName.equals(UserRolesEnum.BRAND.roleName) || x.roleName.equals(UserRolesEnum.BRAND_MASTER.roleName)).collect(Collectors.toSet()));
            brand.setBrand(brandRs);
            //user
            UserEntity user = new UserEntity("user@test.com", "0123456787", "User Seed", _passwordEncoder.encode(defaultPassword));
            user.setUserRoles(roleEntities.stream().filter(x -> x.roleName.equals(UserRolesEnum.USER.roleName)).collect(Collectors.toSet()));

            _userRepository.saveAndFlush(admin);
            _userRepository.saveAndFlush(brand);
            _userRepository.saveAndFlush(user);
        }
        System.out.println(_userRepository.count());
    }
    private void seedRoleData() {
        if (_roleRepository.count() == 0){
            List<RoleEntity> roles = new ArrayList<>();
            roles.add(UserRolesEnum.USER);
            roles.add(UserRolesEnum.ADMIN);
            roles.add(UserRolesEnum.BRAND);
            roles.add(UserRolesEnum.BRAND_MASTER);
            _roleRepository.saveAllAndFlush(roles);
        }
        System.out.println(_roleRepository.count());
    }
}
