package org.xry.adminmodule.Service.Imp;

import cn.hutool.core.util.RandomUtil;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xry.adminmodule.Service.AdminService;
import org.xry.adminmodule.dao.AdminMapper;
import org.xry.adminmodule.pojo.Admin;
import org.xry.adminmodule.pojo.Code;
import org.xry.adminmodule.pojo.RedisConst;
import org.xry.interceptors.exception.Exceptions.serviceException;
import org.xry.interceptors.utils.Jwt;
import org.xry.interceptors.utils.RedisUtils.RedisCacheUtil;
import org.xry.interceptors.utils.ThreadLocalUtils.UserId;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class AdminServiceImp implements AdminService {
    @Autowired
    private AdminMapper mapper;

    @Resource
    private RedisCacheUtil redisCacheUtil;

//--直接操作数据库的方法，若为查询语句且未命中，应返回null-------------------------------------------------------------------------------

    //注册
    public void registerAdmin(Admin admin) {
        if(admin.getAdminName()==null||admin.getPassword()==null)
            throw new serviceException("管理员注册的用户名或密码为空值!检查前端参数校验", Code.BUSINESS_ERROR);
        if(!mapper.selectByAdminName(admin.getAdminName()).isEmpty())throw new serviceException("用户名重复注册！", Code.SELECT_ERROR);
        mapper.insertAdmin(admin);
    }

    //从数据库查管理员信息
    private Admin getAdminById(Integer id) {
        //请求数据
        List<Admin> admins = mapper.selectByAdminId(id);
        //未命中结果
        if(admins.isEmpty())return null;
        //命中结果
        return admins.getFirst();
    }



//--缓存查询方法，利用工具类实现查询---------------------------------------------------------------------------------------------------------------

    //登录    返回合法token/缓存token
    public String loginAdmin(Admin admin) {
        if(admin.getAdminName()==null||admin.getPassword()==null)
            throw new serviceException("账号密码登录空参", Code.BUSINESS_ERROR);

        //获取数据库结果
        List<Admin> list = mapper.selectByAdminNameAndPassword(admin.getAdminName(),admin.getPassword());
        //无结果检查
        if(list.isEmpty()){
            System.out.println("未注册的用户");
            return null;
        }

        //获取完整的用户信息对象并做检查
        Admin adminInfo = list.getFirst();
        System.out.println(adminInfo);

        //用核心标识生成token
        Map<String,Object> claims = new HashMap<>(1);               //1*建HashMap,作为Claim
        claims.put("id",adminInfo.getId());
        String token = Jwt.genToken(claims);

        //存入token
        redisCacheUtil.setList(RedisConst.TOKEN+adminInfo.getId(),token,RedisConst.TOKEN_EXPIRE,TimeUnit.MINUTES);

        //存入用户缓存(查都查了)
        redisCacheUtil.set(RedisConst.USER+adminInfo.getId(),adminInfo,RedisConst.ADMIN_EXPIRE,TimeUnit.MINUTES);

        return token;
    }

    //短信登录 发送验证码
    public void messageLoginSend(String phone) {
        if(mapper.selectByPhone(phone).isEmpty())
            throw new serviceException("当前手机号未注册！",Code.SELECT_ERROR);
        String message = RandomUtil.randomNumbers(6);
        //发送短信
        System.out.println(message);

        //Redis存缓存
        redisCacheUtil.set(RedisConst.PHONE+phone,message,RedisConst.MSG_EXPIRE,TimeUnit.MINUTES);
    }

    // 短信登录 验证
    public String messageLoginValidate(String phone, String code) {
        String message = redisCacheUtil.get(RedisConst.PHONE+phone);

        if(!message.equals(code)){
            //验证码错误
            throw new serviceException("短信登录验证码错误或已过期",Code.SELECT_ERROR);
        }

        //通过验证，生成token
        Map<String,Object> claims = new HashMap<>(1);       //1*建HashMap,作为Claim
        Admin adminInfo=mapper.selectByPhone(phone).getFirst();
        claims.put("id",adminInfo.getId());                   //存入数据
        String token = Jwt.genToken(claims);

        //做缓存
        //存入token
        redisCacheUtil.setList(RedisConst.TOKEN+adminInfo.getId(),token,RedisConst.TOKEN_EXPIRE,TimeUnit.MINUTES);

        //存入用户缓存(查都查了)
        redisCacheUtil.set(RedisConst.USER+ UserId.getId(),adminInfo,RedisConst.ADMIN_EXPIRE,TimeUnit.MINUTES);

        return token;
    }


    //查登录的管理员信息
    public Admin getAdmin() {
        System.out.println("查询管理员信息");
        return redisCacheUtil.queryCache(RedisConst.USER,UserId.getId(), Admin.class,this::getAdminById,RedisConst.ADMIN_EXPIRE,TimeUnit.SECONDS);
    }

    //修改管理员信息
    public void updateAdmin(Admin admin) {
        if(admin.getAdminName()==null||admin.getPassword()==null)
            throw new serviceException("管理员修改信息时的账密参数异常",Code.BUSINESS_ERROR);
        if( mapper.updateAdmin(admin) > 0)throw new serviceException("数据库更新管理员信息异常",Code.UPDATE_ERROR);

        System.out.println("管理员信息已在数据库更新，即将删除用户信息缓存");
        redisCacheUtil.delete(RedisConst.USER+UserId.getId());
    }





}
