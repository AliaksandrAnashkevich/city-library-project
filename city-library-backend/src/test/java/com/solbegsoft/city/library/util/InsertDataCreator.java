package com.solbegsoft.city.library.util;

import com.solbegsoft.city.library.model.City;
import com.solbegsoft.city.library.model.Role;
import com.solbegsoft.city.library.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

public class InsertDataCreator {

    private static final String ROLE_ALLOW_SEE_NAME = "ROLE_ALLOW_SEE";

    public static Role insertRoleAllowSee(){
        Role role = new Role();
        role.setName(ROLE_ALLOW_SEE_NAME);
        return role;
    }

    public static User insertUser(PasswordEncoder passwordEncoder, Set<Role> roles){
        User user = new User();
        user.setEmail(TestDataCreator.VALID_EMAIL);
        user.setPassword(passwordEncoder.encode(TestDataCreator.PASSWORD));
        user.setRoles(roles);
        return user;
    }

    public static City insertCity(){
        City city = new City();
        city.setName(TestDataCreator.CITY_NAME);
        city.setPhoto(TestDataCreator.CITY_PHOTO);
        return city;
    }

    public static City insertUpdatedCity(){
        City city = new City();
        city.setName(TestDataCreator.UPDATE_CITY_NAME);
        city.setPhoto(TestDataCreator.UPDATE_CITY_PHOTO);
        return city;
    }
}
