package com.xie.dao;

import com.xie.domain.Permission;
import com.xie.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface RoleDao {



    @Select("select * from role where id in (select roleId from users_role where userId=#{userId})")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(column="roleName",property="roleName"),
            @Result(column="roleDesc",property="roleDesc"),
            @Result(column="id",property="permissions",javaType=List.class,many=
                    @Many(select="com.xie.dao.PermissionDao.findByRoleId"))
    })
    public List<Role> findRoleByUserId(String userId) throws Exception;

    @Select("select * from role")
    List<Role> findAll() throws Exception;


    @Insert("insert into role(roleName,roleDesc) values(#{roleName},#{roleDesc})")
    void save(Role role);


    @Select("select * from role where id=#{id}")
    Role findById(String id) throws Exception;

    @Select("select * from permission where id not in (select permissionId from role_permission where roleId=#{id})")
    List<Permission> findOtherPermission(String id) throws Exception;


    @Insert("insert into role_permission(permissionId,roleId) values(#{permissionId},#{roleId})")
    void addPermissionToRole(@Param("permissionId") String permissionId,@Param("roleId") String roleId);
}
