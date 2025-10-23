package org.xry.adminmodule.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.xry.adminmodule.Service.WorkerService;
import org.xry.adminmodule.pojo.Code;
import org.xry.adminmodule.pojo.Result;
import org.xry.adminmodule.pojo.Worker;

import java.util.Map;

@RestController
@RequestMapping("/Worker")
public class WorkerController {
    @Autowired
    private WorkerService ws;

    //查询员工
    @GetMapping("/findAll")
    public Result getWorkers() {
        return new Result(ws.selectAllWorker(),"已查询全部数据", Code.SELECT_OK);
    }

    //搜索员工
    @PostMapping("/selectByName")
    public Result SelectWorkerByName(@RequestBody Map<String,String> nameMap) {
        System.out.println("查询请求： "+nameMap.get("name"));
        return new Result(ws.selectAllWorkerByName(nameMap.get("name")),"已查询指定数据",Code.SELECT_OK);
    }

    //添加员工
    @PostMapping("/insertWorker")
    public Result InsertWorker(@RequestBody Worker worker) {
        System.out.println(worker);
        ws.insertWorker(worker);
        return new Result(null ,"添加成功",Code.INSERT_OK);
    }

}
