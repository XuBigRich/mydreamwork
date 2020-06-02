package cn.piao888.mydreamwork.converter;

import cn.piao888.mydreamwork.pojo.User;
import cn.piao888.mydreamwork.util.BaseUtil;
import cn.piao888.mydreamwork.util.ShiroUtil;
import cn.piao888.mydreamwork.vo.RegisterUserVo;
import org.springframework.beans.BeanUtils;

import java.util.Date;

public class RegisterUserVo20User {

    public static User convert(RegisterUserVo registerUserVo) {
        User user=new User();
        BeanUtils.copyProperties(registerUserVo,user);
        user.setCreate_time(new Date());
        user.setSalt(BaseUtil.random(6));
        user.setCreate_user(ShiroUtil.getUser().getId());
        user.setUpdate_user(ShiroUtil.getUser().getId());
        return user;
    }
}
