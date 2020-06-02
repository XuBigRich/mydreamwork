package cn.piao888.mydreamwork.controller.demo;

import cn.piao888.mydreamwork.pojo.User;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@Api(tags = "用户数据接口") //标记当前Controller的功能
public class UserDemoController {
 
    @ApiOperation(value = "查询用户",notes = "根据id查询用户")
    //描述一个参数，可以配置参数的中文含义，也可以给参数设置默认值，required = true表示如果swagger测试为必填,defaultValue默认值
    @ApiImplicitParam(name= "id",value = "用户id",required = true,defaultValue = "66")
    @GetMapping("/user")
    public User getUserById(Integer id){
        User user = new User();
        user.setUser_id(id);
        return user;
    }
    @ApiOperation(value = "删除用户",notes = "根据id删")
    @ApiImplicitParam(name = "id",value = "用户id",required = true,defaultValue = "55")
    @ApiResponses({
            @ApiResponse(code = 200,message = "删除成功"),
            @ApiResponse(code = 500,message = "失败")
    })
    @DeleteMapping("/user/{id}")
    public void deleteUserById(@PathVariable Integer id){
        System.out.println("deleteUserById:"+id);
    }
 
    @PostMapping("/user")
    @ApiOperation(value = "添加用户",notes = "添加用户接口")
    public User addUser(@RequestBody User user) {
        return user;
 
    }
 
    @PutMapping("/user")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value = "用户id",required = true,defaultValue = "77"),
            @ApiImplicitParam(name="username",value = "用户名",required = true,defaultValue = "taoge"),
            @ApiImplicitParam(name="address",value = "地址",required = true,defaultValue = "深圳")
    })
    @ApiOperation(value = "更新用户",notes= "根据id更新用户的接口")
    @ApiIgnore  //表示忽略生成此接口
    public User updateUserById(@RequestBody User user) {
        return user;
    }
}