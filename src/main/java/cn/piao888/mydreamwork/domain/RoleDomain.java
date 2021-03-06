package cn.piao888.mydreamwork.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;
@Data
@AllArgsConstructor
public class RoleDomain {

    private String id;
    private String roleName;
    /**
     * 角色对应权限集合
     */
    private Set<Permissions> permissions;
    //省略set、get方法等.....

}