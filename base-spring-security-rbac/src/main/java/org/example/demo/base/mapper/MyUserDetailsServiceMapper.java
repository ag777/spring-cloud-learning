package org.example.demo.base.mapper;


import org.example.demo.base.model.MyUserDetails;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface MyUserDetailsServiceMapper {

    //根据userID查询用户信息
    @Select("SELECT user_id,username,password\n" +
            "FROM user u\n" +
            "WHERE u.username = #{username}")
    MyUserDetails findByUserName(@Param("username") String username);

    //根据userID查询用户角色列表
    @Select("SELECT\n" +
            "role_id, role_name\n" +
            "FROM\n" +
            "role\n" +
            "INNER JOIN user_role USING (role_id)\n" +
            "INNER JOIN `user` USING (user_id)\n" +
            "WHERE user_id=#{userId}")
    List<Map<String, Object>> findRoleByUserName(@Param("userId") String userId);


    //根据用户角色查询用户接口访问权限
    @Select({
       "<script>",
         "SELECT url " ,
         "FROM permission m " ,
         "INNER JOIN role_permission USING (permission_id) " ,
         "WHERE role_id IN ",
         "<foreach collection='roleIds' item='roleId' open='(' separator=',' close=')'>",
            "#{roleId}",
         "</foreach>",
      "</script>"
    })
    List<String> findApiByRoleIds(@Param("roleIds") List<Integer> roleIds);

}