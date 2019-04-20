package com.xie.dao;

import com.xie.domain.Role;
import com.xie.domain.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserDao {
    @Select("select * from users where username=#{username}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "email",column = "email"),
            @Result(property = "password",column = "password"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status"),
            @Result(column = "id", property = "roles", javaType = List.class, many =
                @Many(select = "com.xie.dao.RoleDao.findRoleByUserId"))
    })
    public UserInfo findByUserName(String username) throws Exception;

    @Select("select * from users")
    List<UserInfo> findAll() throws Exception;

    @Insert("insert into users(email,username,password,phoneNum,status) values(#{email},#{username},#{password},#{phoneNum},#{status})")
    void save(UserInfo userInfo) throws Exception;

    @Select("select * from users where id=#{id}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(column = "username", property = "username"),
            @Result(column = "email", property = "email"),
            @Result(column = "password", property = "password"),
            @Result(column = "phoneNum", property = "phoneNum"),
            @Result(column = "status", property = "status"),
            @Result(column = "id", property = "roles", javaType = List.class, many =
                    @Many(select = "com.xie.dao.RoleDao.findRoleByUserId"))

    })
    UserInfo findById(String id);


    @Select("select * from role where id not in (select roleId from users_role where userId=#{userId})")
    List<Role> findOtherRole(String userId);


    @Insert("insert into users_role values(#{userId},#{roleId})")
    void addRoleToUser(@Param("userId") String userId,@Param("roleId") String roleId);
}
