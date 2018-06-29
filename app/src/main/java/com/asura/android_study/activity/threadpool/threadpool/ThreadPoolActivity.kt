package com.asura.android_study.activity.threadpool.threadpool

import android.util.Log
import android.view.View
import com.asura.android_study.R
import com.asura.android_study.activity.base.BaseActivity
import com.asura.android_study.activity.threadpool.callable.MyCallable
import com.asura.android_study.activity.threadpool.runable.MyRunnable
import com.asura.android_study.activity.threadpool.runable.MyRunnableNotSafe
import com.asura.android_study.activity.threadpool.runable.MyRunnableSafe
import com.asura.android_study.activity.threadpool.thread.MyThread
import kotlinx.android.synthetic.main.activity_thrad_pool.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.Future
import java.util.concurrent.TimeUnit

/**
 * @author Created by Asura on 2018/6/29 16:07.
 */
class ThreadPoolActivity : BaseActivity(), View.OnClickListener {

    override fun setLayoutId(): Int {
        return R.layout.activity_thrad_pool
    }

    override fun initView() {
        btn_thread.setOnClickListener(this)
        btn_runnable.setOnClickListener(this)
        btn_callable.setOnClickListener(this)

        btn_multy_thread_not_safe.setOnClickListener(this)
        btn_multy_thread_safe.setOnClickListener(this)

        btn_newFixedThreadPool.setOnClickListener(this)
        btn_newCachedThreadPool.setOnClickListener(this)
        btn_newSingleThreadExecutor.setOnClickListener(this)
        btn_newSingleThreadScheduledExecutor.setOnClickListener(this)
        btn_newScheduledThreadPool.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            btn_thread -> {
                val thread: Thread = MyThread()
                thread.start()
            }

            btn_runnable -> {
                val runnable = MyRunnable()
                Thread(runnable).start()
            }

            btn_callable -> {
                //3、创建线程池对象，调用submit()方法执行MyCallable任务，并返回Future对象
                val pool: ExecutorService = Executors.newSingleThreadExecutor();
                val futrue: Future<Int> = pool.submit(MyCallable())

                //4、调用Future对象的get()方法获取call()方法执行完后的值
                try {
                    Log.d("asura", "sum=" + futrue.get());
                } catch (e: Exception) {

                }
                //关闭线程池
                pool.shutdown();
            }
            btn_multy_thread_not_safe -> {
                val runnable: MyRunnableNotSafe = MyRunnableNotSafe()
                Thread(runnable).start()
                //开两个线程
                Thread(runnable).start()
            }
            btn_multy_thread_safe -> {
                val runnable: MyRunnableSafe = MyRunnableSafe()
                Thread(runnable).start()
                //开两个线程
                Thread(runnable).start()
            }
            btn_newFixedThreadPool -> {
                val executorService = Executors.newFixedThreadPool(3)
                for (i in 0..10) {
                    executorService.submit(object : Runnable {
                        override fun run() {
                            Thread.sleep(1000)
                            Log.d("asura", Thread.currentThread().name + "  i=$i")
                        }
                    })
                }
            }

            btn_newSingleThreadExecutor -> {
                val executorService = Executors.newSingleThreadExecutor()
                for (i in 0..10) {
                    executorService.submit(object : Runnable {
                        override fun run() {
                            Thread.sleep(1000)
                            Log.d("asura", Thread.currentThread().name + "  i=$i")
                        }
                    })
                }
            }

            btn_newCachedThreadPool -> {
                val executorService = Executors.newCachedThreadPool()
                for (i in 0..10) {
                    executorService.submit(object : Runnable {
                        override fun run() {
                            Log.d("asura", Thread.currentThread().name + "  i=$i")
                        }
                    })
                }
            }

            btn_newScheduledThreadPool -> {
                val executorService = Executors.newScheduledThreadPool(2)
                //延时5秒
                executorService.schedule(object : Runnable {
                    override fun run() {
                        Log.d("asura", "定时任务：" + Thread.currentThread().name + "")
                    }
                }, 5, TimeUnit.SECONDS)

//                //延时2秒，等任务执行完后每5秒一次
//                executorService.scheduleWithFixedDelay(object : Runnable {
//                    override fun run() {
//                        Log.d("asura", "周期任务：" + Thread.currentThread().name + "")
//                    }
//                }, 2, 5, TimeUnit.SECONDS)
//
//                //延时2秒，每5秒一次，不管任务是否执行完
//                executorService.scheduleAtFixedRate(object : Runnable {
//                    override fun run() {
//                        Log.d("asura", "周期任务2：" + Thread.currentThread().name + "")
//                    }
//                }, 2, 5, TimeUnit.SECONDS)


                /*参考 https://blog.csdn.net/butingnal/article/details/12775277
                scheduleWithFixedDelay从字面意义上可以理解为就是以固定延迟（时间）来执行线程任务，
                它实际上是不管线程任务的执行时间的，每次都要把任务执行完成后再延迟固定时间后再执行下一次。

                而scheduleFixedRate呢，是以固定频率来执行线程任务，固定频率的含义就是可能设定的固定时间不足以完成线程任务，
                但是它不管，达到设定的延迟时间了就要执行下一次了。*/
//                scheduleAtFixedRate()
                scheduleWithFixedDelay()
            }

            btn_newSingleThreadScheduledExecutor -> {
                val executorService = Executors.newSingleThreadScheduledExecutor()
                //延时5秒
                executorService.schedule(object : Runnable {
                    override fun run() {
                        Log.d("asura", "定时任务：" + Thread.currentThread().name + "")
                    }
                }, 5, TimeUnit.SECONDS)

                //参考 https://blog.csdn.net/butingnal/article/details/12775277
                //延时2秒，等任务执行完后每5秒一次
                executorService.scheduleWithFixedDelay(object : Runnable {
                    override fun run() {
                        Log.d("asura", "周期任务：" + Thread.currentThread().name + "")
                    }
                }, 2, 5, TimeUnit.SECONDS)

                //延时2秒，每5秒一次，不管任务是否执行完
                executorService.scheduleAtFixedRate(object : Runnable {
                    override fun run() {
                        Log.d("asura", "周期任务2：" + Thread.currentThread().name + "")
                    }
                }, 2, 5, TimeUnit.SECONDS)
            }

        }
    }

    fun scheduleWithFixedDelay() {

        val scheduledExecutorService = Executors.newScheduledThreadPool(2);
        // 响铃线程
        val beeper = Runnable() {
            kotlin.run {

                val sf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                var time = (Math.random() * 1000).toLong()
                // 输出线程的名字和使用目标对象及休眠的时间
                Log.d("asura", (sf.format(Date()) + "线程:" + Thread.currentThread().getName() + ":Sleeping" + time + "ms"))
                Thread.sleep(time);
            }
        };
        // 设定执行线程计划,初始10s延迟,每次任务完成后延迟10s再执行一次任务
        val sFuture = scheduledExecutorService.scheduleWithFixedDelay(beeper, 10, 10, TimeUnit.SECONDS);

        // 40s后取消线程任务
        scheduledExecutorService.schedule(object : Runnable {
            override fun run() {
                Log.d("asura", Thread.currentThread().name + "CANCEL...")
                sFuture.cancel(true);
                scheduledExecutorService.shutdown();
            }
        }, 40, TimeUnit.SECONDS);

    }

    fun scheduleAtFixedRate() {
        // 声明线程池
        val scheduledExecutorService = Executors.newScheduledThreadPool(1)
        // 响铃线程
        val beeper = Runnable {
            val sf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            val time = (Math.random() * 1000).toLong()
            // 输出线程的名字和使用目标对象及休眠的时间
            Log.d("asura", sf.format(Date()) + " 线程：" + Thread.currentThread().name + ":Sleeping " + time + "ms")
            try {
                Thread.sleep(time)
            } catch (e: InterruptedException) {
            }
        }
        // 计划响铃，初始延迟10s然后以10s的频率执行响铃
        val beeperHandle = scheduledExecutorService.scheduleAtFixedRate(beeper, 10, 10, TimeUnit.SECONDS)

        // 取消响铃并关闭线程
        val cancelBeeper = Runnable {
            Log.d("asura", Thread.currentThread().name + "CANCEL...")
            beeperHandle.cancel(true)
            scheduledExecutorService.shutdown()
        }
        // 60s后执行scheduleAtFixedRate
        scheduledExecutorService.schedule(cancelBeeper, 40, TimeUnit.SECONDS)
    }

}