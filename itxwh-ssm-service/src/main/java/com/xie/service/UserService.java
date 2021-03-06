package com.xie.service;

import com.xie.domain.Role;
import com.xie.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<UserInfo> findAll() throws Exception;

    void save(UserInfo userInfo) throws Exception;

    UserInfo findById(String id);

    List<Role> findOtherRole(String userId);

    void addRoleToUser(String userId, String[] ids);
}
