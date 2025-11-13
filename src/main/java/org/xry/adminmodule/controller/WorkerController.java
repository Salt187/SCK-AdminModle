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

    //----已弃用---------------------------------------------------
    //一次性查询员工
    @PostMapping("/findAll")
    public Result getWorkers(@RequestBody Map<String, String> map) {
        System.out.println(map.get("name"));
        return new Result(ws.selectAllWorker(map),"已查询数据", Code.SELECT_OK);
    }

    //---------------------------------------------------------------

    //添加员工
    @PostMapping("/insertWorker")
    public Result InsertWorker(@RequestBody Worker worker) {
        System.out.println(worker);
        ws.insertWorker(worker);
        return new Result(null ,"添加成功",Code.INSERT_OK);
    }

    //查询员工数量
    @GetMapping("/selectWorkerNum")
    public Result SelectWorkerNum() {
        return new Result(ws.selectWorkerNum(),"预查询员工数量成功",Code.SELECT_OK);
    }

    //分页查询员工
    @PostMapping("/selectWorker")
    public Result SelectWorker(@RequestBody Map<String, String> map) {
        return new Result(ws.selectWorker(map),"分页查询成功",Code.SELECT_OK);
    }

}
