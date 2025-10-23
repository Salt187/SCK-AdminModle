package org.xry.adminmodule.dao;

import org.apache.ibatis.annotations.*;
import org.xry.adminmodule.pojo.Admin;

import java.util.List;

@Mapper
public interface AdminMapper {
    //新增管理员用户
    @Insert("insert into admin(admin_name, password) values (#{adminName},#{password})")
    Integer insertAdmin(Admin admin);

    //根据用户修改用户密码
    @Update("update admin set password=#{newPassword} where admin_name=#{adminName}")
    Integer updateAdminPassword(@Param("newPassword") String newPassword,@Param("adminName") String adminName);

    //按用户名查询全部信息
    @Select("select * from admin where admin_name=#{adminName}")
    List<Admin> selectByAdminName(@Param("adminName") String adminName);

    //按电话查询全部信息
    @Select("select * from admin where phone=#{phone}")
    List<Admin> selectByPhone(@Param("phone") String phone);

    //按用户名和密码查询
    @Select("select * from admin where admin_name=#{adminName} and password=#{password}")
    List<Admin> selectByAdminNameAndPassword(@Param("adminName") String adminName,@Param("password") String password);

    //按id查管理员信息
    @Select("select * from admin where id=#{id}")
    List<Admin> selectByAdminId(@Param("id") Integer id);

    //修改管理员信息
    @Update("update admin set email = #{email} where admin_name = #{adminName}")
    Integer updateAdmin(Admin admin);
}
