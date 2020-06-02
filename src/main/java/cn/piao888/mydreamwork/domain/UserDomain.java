package cn.piao888.mydreamwork.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;
@Data
@AllArgsConstructor
public class UserDomain {
    private String id;
    private String userName;
    private String password;
    /**
     * 用户对应的角色集合
     */
    private Set<RoleDomain> roles;
    //省略set、get方法等.....
}