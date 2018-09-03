package com.xiyoukeji.zhitingyun.task;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;



public class TaskQueue {
    private BlockingQueue<ITask> mTaskBlockingQueue;
    private TaskExecutor[] mTaskExecutors;


    public TaskQueue(int size) {
        mTaskBlockingQueue = new LinkedBlockingQueue<>();
        mTaskExecutors = new TaskExecutor[size];
    }

    public void start() {
        stop();
        for(int i = 0; i <mTaskExecutors.length; i++) {
            mTaskExecutors[i] = new TaskExecutor(mTaskBlockingQueue);
            mTaskExecutors[i].start();
        }
    }

    public void stop() {
        if (mTaskExecutors != null) {
            for (TaskExecutor taskExecutor: mTaskExecutors) {
                if (taskExecutor != null) {
                    taskExecutor.quit();
                }
            }
        }
    }

    public <T extends ITask> int add(T task) {
        if (mTaskBlockingQueue == null) {
            throw new IllegalArgumentException("taskqueque is not initialize");
        }
        if (!mTaskBlockingQueue.contains(task)) {
            mTaskBlockingQueue.add(task);
        }
        return mTaskBlockingQueue.size();
    }
}
