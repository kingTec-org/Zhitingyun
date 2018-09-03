package com.xiyoukeji.zhitingyun.task;

import java.util.concurrent.BlockingQueue;



public class TaskExecutor extends Thread {

    private BlockingQueue<ITask> mBlockingQueue;
    private boolean mIsRunning = true;

    public TaskExecutor(BlockingQueue<ITask> blockingQueue) {
        mBlockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        while (mIsRunning) {
            ITask task;
            try {
                task = mBlockingQueue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
                if (!mIsRunning) {
                    break;
                } else {
                    continue;
                }
            }
            task.run();
        }
    }

    public void quit() {
        if (mIsRunning) {
            interrupt();
        }
    }
}
