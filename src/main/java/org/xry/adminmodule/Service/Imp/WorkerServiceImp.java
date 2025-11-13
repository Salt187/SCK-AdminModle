package org.xry.adminmodule.Service.Imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xry.adminmodule.Service.WorkerService;
import org.xry.adminmodule.dao.WorkerMapper;
import org.xry.adminmodule.pojo.Code;
import org.xry.adminmodule.pojo.Worker;
import org.xry.interceptors.exception.Exceptions.serviceException;

import java.util.List;
import java.util.Map;

@Service
public class WorkerServiceImp implements WorkerService {
    @Autowired
    private WorkerMapper mapper;

    //----已弃用------------------------
    public List<Worker> selectAllWorker(Map<String, String> map) {
        //若有姓名参数
        String name = map.get("name");
        if(name != null)return mapper.selectWorkerByName(map.get("name"));
        System.out.println("正常查全部");
        return mapper.selectAllWorker();
    }

    //----------------------------
    public List<Worker> selectWorker(Map<String, String> map) {
        //姓名 偏移量 查询数目限制
        String name = map.get("name");
        int limit = Integer.parseInt(map.get("pageSize"));
        Integer offset = (Integer.parseInt(map.get("currentPage"))-1)*limit;

        if(name!=null)return mapper.selectWorkerByName(name);

        return mapper.selectWorker(offset, limit);
    }


    //新增员工
    public void insertWorker(Worker worker) {
        System.out.println("调用函数");
        if(mapper.insertWorker(worker)==0)
            throw new serviceException("数据库新增员工异常！", Code.INSERT_ERROR);
        System.out.println("存入成功");
    }

    //查询员工数量
    public Integer selectWorkerNum(){
        return mapper.selectWorkerNum();
    }
}
