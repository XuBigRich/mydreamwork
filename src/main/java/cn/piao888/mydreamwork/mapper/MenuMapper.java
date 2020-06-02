package cn.piao888.mydreamwork.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface MenuMapper {
    List<String> getResUrlsByPrivilegeId(Integer privilegeId);
}
