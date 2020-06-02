package cn.piao888.mydreamwork.mapper;

import cn.piao888.mydreamwork.pojo.Privilege;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface PrivilegeMapper {
    //根据角色id获取权限id组
    List<Privilege> getPrivilegeIds(Integer roleId);
}
