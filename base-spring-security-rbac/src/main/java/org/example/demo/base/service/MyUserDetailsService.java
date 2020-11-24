package org.example.demo.base.service;


import org.example.demo.base.mapper.MyUserDetailsServiceMapper;
import org.example.demo.base.model.MyUserDetails;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class MyUserDetailsService implements UserDetailsService {

    @Resource
    private MyUserDetailsServiceMapper myUserDetailsServiceMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //加载基础用户信息
        MyUserDetails myUserDetails = myUserDetailsServiceMapper.findByUserName(username);

        //加载用户角色列表
        List<Map<String, Object>> roles = myUserDetailsServiceMapper.findRoleByUserName(username);
        if(!roles.isEmpty()) {
            List<Integer> roleIds = new ArrayList<>();
            List<String> roleCodes = new ArrayList<>();
            for (Map<String, Object> roleMap : roles) {
                Integer roleId = (Integer) roleMap.get("role_id");
                String roleName = (String) roleMap.get("role_name");
                roleIds.add(roleId);
                //角色是一个特殊的权限，ROLE_前缀
                roleCodes.add("ROLE_"+roleName);
            }

            //通过用户角色列表加载用户的资源权限列表
            List<String> authorties = myUserDetailsServiceMapper.findApiByRoleIds(roleIds);
            authorties.addAll(roleCodes);
            myUserDetails.setAuthorities(
                    AuthorityUtils.commaSeparatedStringToAuthorityList(
                            String.join(",",authorties)
                    )
            );

        }


        return myUserDetails;
    }
}
