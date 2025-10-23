package org.xry.adminmodule.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.xry.adminmodule.Service.AdminService;
import org.xry.adminmodule.pojo.Admin;
import org.xry.adminmodule.pojo.Code;
import org.xry.adminmodule.pojo.Result;


import java.util.Map;

@RestController
@RequestMapping("/Admin")
public class AdminController {
    @Autowired
    private AdminService as;

    //注册
    @PostMapping("/register")
    public Result register(@RequestBody Admin admin) {
        System.out.println(admin);
        as.registerAdmin(admin);
        return new Result(null,"注册成功", Code.INSERT_OK);
    }

    //登录
    @PostMapping("/login")
    public Result login(@RequestBody Admin admin) {
        System.out.println("登入信息接入:"+admin);
        return new Result(as.loginAdmin(admin),"登陆成功",Code.SELECT_OK);   //调工具类生成token并返回
    }

    //短信登录 发送验证码    &
    @PostMapping("/mLogin")
    public Result mLogin(@RequestParam Map<String,String> phoneMap) {
        as.messageLoginSend(phoneMap.get("phoneNumber"));
        return new Result(null,"验证码已发送，有效时长5分钟",Code.INSERT_OK);
    }

    //短信登录  验证验证码   &
    @PostMapping("/MessageLoginValidate")
    public Result MessageLoginValidate(@RequestBody Map<String,String> phoneMap) {
        String token = as.messageLoginValidate(phoneMap.get("phoneNumber"),phoneMap.get("phoneCode"));
        System.out.println(token);

        return new Result(token,"验证成功",Code.SELECT_OK);
    }

    //查询当前管理员信息 &
    @GetMapping("/adminInfo")
    public Result adminInfo() {
        return new Result(as.getAdmin(),"查询成功",Code.SELECT_OK);
    }

    //更新管理员信息   #
    @PutMapping("/updateAdmin")
    public Result updateAdmin(@RequestBody Admin admin) {
        as.updateAdmin(admin);
        return new Result(null,"修改成功",Code.UPDATE_OK);

    }


}
