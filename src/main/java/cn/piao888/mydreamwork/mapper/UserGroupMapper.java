package cn.piao888.mydreamwork.mapper;

import cn.piao888.mydreamwork.pojo.UserGroup;
import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface UserGroupMapper {
    UserGroup getByUserId(Integer userid);
}
