package org.xry.adminmodule.Service;


import org.xry.adminmodule.pojo.Admin;

public interface AdminService {

    //注册功能
    void registerAdmin(Admin admin);

    //登录功能
    String loginAdmin(Admin admin);

    //获取个人信息
    Admin getAdmin();

    //修改个人信息
    void updateAdmin(Admin admin);

    //生成验证码，存入Redis
    void messageLoginSend(String phone);

    //验证验证码,不做删除
    String messageLoginValidate(String phone, String code);
}
