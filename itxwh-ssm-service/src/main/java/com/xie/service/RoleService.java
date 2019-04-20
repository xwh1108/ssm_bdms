package com.xie.service;

import com.xie.domain.Permission;
import com.xie.domain.Role;

import java.util.List;

public interface RoleService {
    public List<Role> findAll() throws Exception;

    public void save(Role role) throws Exception;

    Role findById(String id) throws Exception;

    List<Permission> findOtherPermission(String id) throws Exception;

    void addPermissionToRole(String roleId, String[] ids);
}
