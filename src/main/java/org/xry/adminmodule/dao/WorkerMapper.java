package org.xry.adminmodule.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.xry.adminmodule.pojo.Worker;

import java.util.List;

@Mapper
public interface WorkerMapper {
    //查询所有员工
    @Select("select * from worker")
    List<Worker> selectAllWorker();

    //根据名字查询员工
    List<Worker> selectWorkerByName(@Param("name") String name);

    //新增一位员工
    @Insert("insert into worker(name, in_date, in_age, position, state, salary, phone)" +
            " values (#{name},#{inDate},#{inAge},#{position},#{state},#{salary},#{phone})")
    Integer insertWorker(Worker worker);
}