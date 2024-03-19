package com.eproject.service.brand;

import com.eproject.data.model.brandmodel.BrandEntity;
import com.eproject.data.model.usermodel.RoleEntity;
import com.eproject.data.model.usermodel.UserEntity;
import com.eproject.data.model.usermodel.UserRolesEnum;
import com.eproject.repository.BrandRepository;
import com.eproject.repository.RoleRepository;
import com.eproject.webapi.admincontroller.CreateBrandAccountRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class BrandService implements IBrandService {
    @Autowired
    BrandRepository _brandRepository;

    @Autowired
    RoleRepository _roleRepository;

    @Autowired
    private BCryptPasswordEncoder _passwordEncoder;
    @Override
    public BrandEntity updateBrand() {
        return null;
    }

    @Override
    public BrandEntity addNewBrand(CreateBrandAccountRequest request) {
        try{
            //generate random password
            String rndPassword = KeyGenerators.string().generateKey();
            String encoded = _passwordEncoder.encode(rndPassword);
            //Create new brand
            BrandEntity brand = new BrandEntity(request.brandName,
                    request.hotline,
                    request.email);
            //Create brand_master user
            UserEntity user = new UserEntity(
                    request.email,
                    request.hotline,
                    request.brandName,
                    encoded);

            Set<String> roleNames = new HashSet<String>();
            roleNames.add(UserRolesEnum.BRAND_MASTER.roleName);
            roleNames.add(UserRolesEnum.BRAND.roleName);
            Set<RoleEntity> roles = _roleRepository.getAllByRoleNameIn(roleNames);

            user.setUserRoles(roles);

            Set<UserEntity> users = new HashSet<UserEntity>();
            users.add(user);

            brand.setUsers(users);

            BrandEntity res = _brandRepository.saveAndFlush(brand);

            //Send email
            return res;
        }catch (Exception ex){
            throw ex;
        }
    }
}
