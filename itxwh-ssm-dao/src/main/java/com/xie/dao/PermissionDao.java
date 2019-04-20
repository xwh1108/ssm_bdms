package com.xie.dao;

import com.xie.domain.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PermissionDao {

    @Select("select * from permission where id in (select permissionId from role_permission where roleId=#{roleId})")
    List<Permission> findByRoleId(String id) throws Exception;

    @Select("select * from permission")
    List<Permission> findAll() throws Exception;

    @Insert("insert into permission(permissionName,url) values(#{permissionName},#{url})")
    void save(Permission permission) throws Exception;
}
