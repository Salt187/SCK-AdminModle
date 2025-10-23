package org.xry.adminmodule.Service.Imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xry.adminmodule.Service.WorkerService;
import org.xry.adminmodule.dao.WorkerMapper;
import org.xry.adminmodule.exception.Exceptions.serviceException;
import org.xry.adminmodule.pojo.Code;
import org.xry.adminmodule.pojo.Worker;

import java.util.List;

@Service
public class WorkerServiceImp implements WorkerService {
    @Autowired
    private WorkerMapper mapper;

    public List<Worker> selectAllWorker() {
        return mapper.selectAllWorker();
    }

    public List<Worker> selectAllWorkerByName(String workerName) {
        if (workerName == null || workerName.trim().isEmpty()) return selectAllWorker();
        return mapper.selectWorkerByName(workerName);
    }

    //新增员工
    public void insertWorker(Worker worker) {
        System.out.println("调用函数");
        if(mapper.insertWorker(worker)==0)
            throw new serviceException("数据库新增员工异常！", Code.INSERT_ERROR);
        System.out.println("存入成功");
    }
}
