package com.asura.android_study.ui.threadpool.step1;

import android.util.Log;

import com.asura.android_study.ui.threadpool.step1.runable.WriteCommandRunnable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Liuxd on 2016/10/21 22:20.
 */

public class ThreadPoolUtil {
    private ExecutorService executorService;
    private static ThreadPoolUtil poolInstance;

    private ThreadPoolUtil() {
    }

    public static ThreadPoolUtil getPoolInstance() {
        if (poolInstance == null) {
            synchronized (ThreadPoolUtil.class) {
                if (poolInstance == null) {
                    poolInstance = new ThreadPoolUtil();
                }
            }
        }
        return poolInstance;
    }

    /**
     * 添加一个任务到线程池执行
     *
     * @param task
     */
    public void executeTask(WriteCommandRunnable task) {
        if (executorService == null) {
            executorService = Executors.newSingleThreadExecutor();
        }
        if (!executorService.isShutdown())
            executorService.execute(task);
    }

    /**
     * 退出线程池
     */
    public void exitAllThread() {
        executorService.shutdown();
        Log.e("lxd", "退出线程池：" + executorService.isShutdown());
    }
}
