package org.xry.adminmodule.Service;

import org.xry.adminmodule.pojo.Worker;

import java.util.List;

public interface WorkerService {
    List<Worker> selectAllWorker();

    List<Worker> selectAllWorkerByName(String workerName);

    void insertWorker(Worker worker);
}
