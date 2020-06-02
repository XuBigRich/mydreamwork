package cn.piao888.mydreamwork.mapper;

import cn.piao888.mydreamwork.pojo.Role;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface RoleMapper {
    Role selectById(int role_id);
    List<Role> getByUser(int userid);
    List<Role> getByUserGroup(int userGroupid);
}
