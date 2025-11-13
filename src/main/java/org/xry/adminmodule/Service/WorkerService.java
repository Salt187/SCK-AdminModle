package org.xry.adminmodule.Service;

import org.xry.adminmodule.pojo.Worker;

import java.util.List;
import java.util.Map;

public interface WorkerService {
    List<Worker> selectAllWorker(Map<String, String > positionMap);

    List<Worker> selectWorker(Map<String, String > map);

    void insertWorker(Worker worker);

    Integer selectWorkerNum();
}
