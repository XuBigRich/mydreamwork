/**
 * Copyright 2018-2020 stylefeng & fengshuonan (https://gitee.com/stylefeng)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.piao888.mydreamwork.service.Impl;

import cn.piao888.mydreamwork.constant.PermissionType;
import cn.piao888.mydreamwork.domain.ShiroUser;
import cn.piao888.mydreamwork.mapper.*;
import cn.piao888.mydreamwork.pojo.Privilege;
import cn.piao888.mydreamwork.pojo.Role;
import cn.piao888.mydreamwork.pojo.User;
import cn.piao888.mydreamwork.service.UserAuthService;
import cn.piao888.mydreamwork.state.ManagerStatus;
import cn.piao888.mydreamwork.util.ConstantFactory;
import cn.piao888.mydreamwork.util.SpringContextHolder;
import org.apache.shiro.authc.CredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@DependsOn("springContextHolder")
@Transactional(readOnly = true)
public class UserAuthServiceServiceImpl implements UserAuthService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PrivilegeMapper privilegeMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private ElementMapper elementMapper;



    public static UserAuthService me() {
        return SpringContextHolder.getBean(UserAuthService.class);
    }

    @Override
    public User user(String account) {

        User user = userMapper.getByAccount(account);

        // 账号不存在
        if (null == user) {
            throw new CredentialsException();
        }
        // 账号被冻结
        if (user.getStatus() != ManagerStatus.OK.getCode()) {
            throw new LockedAccountException();
        }
        return user;
    }

    @Override
    // 根据用户 查询出需要的shiroUser
    public ShiroUser shiroUser(User user) {
        ShiroUser shiroUser = new ShiroUser();

        shiroUser.setId(user.getUser_id());
        shiroUser.setAccount(user.getAccount());
        //查询用户组id
        shiroUser.setUserGroupId(ConstantFactory.me().getUserGroupId(user.getUser_id()));
        //查询用户组名称
        shiroUser.setUserGroup(ConstantFactory.me().getUserGroupName(user.getUser_id()));
        shiroUser.setName(user.getName());
        // 查询 用户 与用户组权限集合
        List<Role> roles=new ArrayList<>();
        List<Role> userRoles=roleMapper.getByUser(user.getUser_id());
        List<Role> userGroupRoles=roleMapper.getByUserGroup(shiroUser.getUserGroupId());
        roles.addAll(userRoles);
        roles.addAll(userGroupRoles);
        List<Integer> roleList = new ArrayList<Integer>();
        List<String> roleNameList = new ArrayList<String>();
        for (Role role : roles) {
            roleList.add(role.getRole_id());
            roleNameList.add(role.getName());
        }
        shiroUser.setRoleList(roleList);
        shiroUser.setRoleNames(roleNameList);
        return shiroUser;
    }

    @Override
    public List<Privilege> findPermissionsByRoleId(Integer roleId) {
        return privilegeMapper.getPrivilegeIds(roleId);
    }

    @Override
    public List findPermissionsByPermissionId(Privilege Permissions) {
        switch (  Permissions.getType()){
            case PermissionType.FILE:
                return  menuMapper.getResUrlsByPrivilegeId(Permissions.getPrivilege_id());
        }
        return null;
    }


    @Override
    public String findRoleNameByRoleId(Integer roleId) {
        return ConstantFactory.me().getSingleRoleTip(roleId);
    }

    @Override
    public SimpleAuthenticationInfo info(ShiroUser shiroUser, User user, String realmName) {
        String credentials = user.getPassword();

        // 密码加盐处理
        String source = user.getSalt();
        ByteSource credentialsSalt = new Md5Hash(source);
        return new SimpleAuthenticationInfo(shiroUser, credentials, credentialsSalt, realmName);
    }

    @Override
    public boolean RegisterUser(User user) {
        userMapper.setUser(user);
        return true;
    }

}
