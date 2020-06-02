package cn.piao888.mydreamwork.controller;

import cn.piao888.mydreamwork.converter.RegisterUserVo20User;
import cn.piao888.mydreamwork.domain.ShiroUser;
import cn.piao888.mydreamwork.pojo.User;
import cn.piao888.mydreamwork.service.UserAuthService;
import cn.piao888.mydreamwork.util.ShiroUtil;
import cn.piao888.mydreamwork.vo.RegisterUserVo;
import cn.piao888.mydreamwork.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@Api(tags = "用户登陆注册接口") //标记当前Controller的功能
public class LoginController {

@Autowired
private UserAuthService userAuthService;
    /**
     * 点击登录执行的动作
     */
    @ApiOperation(value = "用户登录接口",notes = "根据用户名密码登陆")
    //描述一个参数，可以配置参数的中文含义，也可以给参数设置默认值，required = true表示如果swagger测试为必填,defaultValue默认值
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name="username",value = "用户名",required = true),
            @ApiImplicitParam(name="password",value = "用户密码",required = true),
            @ApiImplicitParam(name="remember",value = "地址",required = true,defaultValue = "false")
    })
    public Boolean loginVali(UserVo userVo) {

        String username = userVo.getUsername();
        String password = userVo.getPassword();
        String remember = userVo.getRemember();


        Subject currentUser = ShiroUtil.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password.toCharArray());

        if ("on".equals(remember)) {
            token.setRememberMe(true);
        } else {
            token.setRememberMe(false);
        }
        try {
            currentUser.login(token);
            //所有异常认证的父类异常
        }catch (AuthenticationException e) {
                System.out.println("登录失败："+ e.getMessage());
            }
        ShiroUser shiroUser = ShiroUtil.getUser();
        return true;
    }

    //注册
    @ApiImplicitParams({
            @ApiImplicitParam(name ="account",value="账号",required = true),
            @ApiImplicitParam(name ="password",value="密码",required = true),
            @ApiImplicitParam(name ="name",value="姓名",required = true),
            @ApiImplicitParam(name ="birthday",value="生日",required = true),
            @ApiImplicitParam(name ="sex",value="性别",required = true),
            @ApiImplicitParam(name ="email",value="邮箱",required = true),
            @ApiImplicitParam(name ="phone",value="手机号",required = true)
    })
    @ApiOperation(value="注册新用户")
    @PostMapping("signUser")
    public boolean signUser(RegisterUserVo registerUserVo){
       User user= RegisterUserVo20User.convert(registerUserVo);
       userAuthService.RegisterUser(user);
       return true;
    }

}
