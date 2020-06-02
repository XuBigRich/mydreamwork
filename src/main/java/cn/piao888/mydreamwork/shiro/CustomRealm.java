package cn.piao888.mydreamwork.shiro;

import cn.piao888.mydreamwork.domain.ShiroUser;
import cn.piao888.mydreamwork.pojo.Privilege;
import cn.piao888.mydreamwork.pojo.Role;
import cn.piao888.mydreamwork.pojo.User;
import cn.piao888.mydreamwork.service.Impl.UserAuthServiceServiceImpl;
import cn.piao888.mydreamwork.service.UserAuthService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.Permissions;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private UserAuthService userAuthService;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //加这一步的目的是在Post请求的时候会先进认证，然后在到请求
        if (authenticationToken.getPrincipal() == null) {
            return null;
        }
        UserAuthService shiroFactory = UserAuthServiceServiceImpl.me();
        //获取用户信息
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        User user = shiroFactory.user(token.getUsername());
        ShiroUser shiroUser = shiroFactory.shiroUser(user);
//        User user = userAuthService.user(token.getUsername());
//        ShiroUser shiroUser = userAuthService.shiroUser(user);
        if (user == null) {
            //这里返回后会报出对应异常
            return null;
        } else {
            //这里验证authenticationToken和simpleAuthenticationInfo的信息
            return shiroFactory.info(shiroUser, user, super.getName());
        }
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //使用Holder的方法 获取 UserAuthService对象
        UserAuthService shiroFactory = UserAuthServiceServiceImpl.me();
        //使用Autowired的方法 获取 UserAuthService对象
        ShiroUser shiroUser = (ShiroUser) principalCollection.getPrimaryPrincipal();
        List<Integer> roleList = shiroUser.getRoleList();

        Set<String> permissionSet = new HashSet<>();
        Set<String> roleNameSet = new HashSet<>();
        //添加角色和权限
        for (Integer role : shiroUser.getRoleList()) {
            //通过角色查询出权限
            List<Privilege> privileges =shiroFactory.findPermissionsByRoleId(role);
            //添加权限
            for (Privilege privilege : privileges) {
               List<String> permissions=shiroFactory.findPermissionsByPermissionId(privilege);
                permissionSet.addAll(permissions);
            }
        }
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setStringPermissions(permissionSet);
        simpleAuthorizationInfo.setRoles(roleNameSet);
        return simpleAuthorizationInfo;
    }


}