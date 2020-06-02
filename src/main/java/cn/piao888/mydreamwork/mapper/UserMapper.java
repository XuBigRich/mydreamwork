package cn.piao888.mydreamwork.mapper;

import cn.piao888.mydreamwork.pojo.User;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface UserMapper {
    User getByAccount(String account);
    List<User> getByUserGroupId(Integer ugid);
    User selectById(Integer userId);
    void setUser(User user);
}
