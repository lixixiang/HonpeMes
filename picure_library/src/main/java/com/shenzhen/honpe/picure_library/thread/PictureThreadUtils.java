package com.shenzhen.honpe.picure_library.thread;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.CallSuper;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author：luck
 * @date：2020/10/30 10:56 AM
 * @describe：ThreadPool
 */
public final class PictureThreadUtils {


    private static final Handler HANDLER = new Handler(Looper.getMainLooper());

    private static final Map<Integer, Map<Integer, ExecutorService>> TYPE_PRIORITY_POOLS = new HashMap<>();

    private static final Map<Task, ExecutorService> TASK_POOL_MAP = new ConcurrentHashMap<>();

    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();

    private static final byte TYPE_IO = -4;

    private static Executor sDeliver;


    public static void runOnUiThread(final Runnable runnable) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            runnable.run();
        } else {
            HANDLER.post(runnable);
        }
    }


    /**
     * Return a thread pool that creates (2 * CPU_COUNT + 1) threads
     * operating off a queue which size is 128.
     *
     * @return a IO thread pool
     */
    public static ExecutorService getIoPool() {
        return getPoolByTypeAndPriority();
    }

    /**
     * Return a thread pool that creates (2 * CPU_COUNT + 1) threads
     * operating off a queue which size is 128.
     *
     * @param priority The priority of thread in the poll.
     * @return a IO thread pool
     */
    public static ExecutorService getIoPool(@IntRange(from = 1, to = 10) final int priority) {
        return getPoolByTypeAndPriority(priority);
    }


    /**
     * Executes the given task in an IO thread pool.
     *
     * @param task The task to execute.
     * @param <T>  The type of the task's result.
     */
    public static <T> void executeByIo(final Task<T> task) {
        execute(getPoolByTypeAndPriority(), task);
    }

    /**
     * Cancel the given task.
     *
     * @param task The task to cancel.
     */
    public static void cancel(final Task task) {
        if (task == null) return;
        task.cancel();
    }

    /**
     * Cancel the given tasks.
     *
     * @param tasks The tasks to cancel.
     */
    public static void cancel(final Task... tasks) {
        if (tasks == null || tasks.length == 0) return;
        for (Task task : tasks) {
            if (task == null) continue;
            task.cancel();
        }
    }

    /**
     * Cancel the given tasks.
     *
     * @param tasks The tasks to cancel.
     */
    public static void cancel(final List<Task> tasks) {
        if (tasks == null || tasks.size() == 0) return;
        for (Task task : tasks) {
            if (task == null) continue;
            task.cancel();
        }
    }

    /**
     * Cancel the tasks in pool.
     *
     * @param executorService The pool.
     */
    public static void cancel(ExecutorService executorService) {
        if (executorService instanceof ThreadPoolExecutor4Util) {
            for (Map.Entry<Task, ExecutorService> taskTaskInfoEntry : TASK_POOL_MAP.entrySet()) {
                if (taskTaskInfoEntry.getValue() == executorService) {
                    cancel(taskTaskInfoEntry.getKey());
                }
            }
        } else {
            Log.e("ThreadUtils", "The executorService is not ThreadUtils's pool.");
        }
    }

    /**
     * Set the deliver.
     *
     * @param deliver The deliver.
     */
    public static void setDeliver(final Executor deliver) {
        sDeliver = deliver;
    }

    private static <T> void execute(final ExecutorService pool, final Task<T> task) {
        execute(pool, task, null);
    }

    private static <T> void execute(final ExecutorService pool, final Task<T> task,
                                    final TimeUnit unit) {
        synchronized (TASK_POOL_MAP) {
            if (TASK_POOL_MAP.get(task) != null) {
                Log.e("ThreadUtils", "Task can only be executed once.");
                return;
            }
            TASK_POOL_MAP.put(task, pool);
        }
        pool.execute(task);
    }

    private static ExecutorService getPoolByTypeAndPriority() {
        return getPoolByTypeAndPriority(Thread.NORM_PRIORITY);
    }

    private static ExecutorService getPoolByTypeAndPriority(final int priority) {
        synchronized (TYPE_PRIORITY_POOLS) {
            ExecutorService pool;
            Map<Integer, ExecutorService> priorityPools = TYPE_PRIORITY_POOLS.get((int) PictureThreadUtils.TYPE_IO);
            if (priorityPools == null) {
                priorityPools = new ConcurrentHashMap<>();
                pool = ThreadPoolExecutor4Util.createPool(priority);
                priorityPools.put(priority, pool);
                TYPE_PRIORITY_POOLS.put((int) PictureThreadUtils.TYPE_IO, priorityPools);
            } else {
                pool = priorityPools.get(priority);
                if (pool == null) {
                    pool = ThreadPoolExecutor4Util.createPool(priority);
                    priorityPools.put(priority, pool);
                }
            }
            return pool;
        }
    }

    static final class ThreadPoolExecutor4Util extends ThreadPoolExecutor {

        private static ExecutorService createPool(final int priority) {
            return new ThreadPoolExecutor4Util(2 * CPU_COUNT + 1, 2 * CPU_COUNT + 1,
                        30, TimeUnit.SECONDS,
                        new LinkedBlockingQueue4Util(),
                        new UtilsThreadFactory("io", priority)
                );
        }

        private final AtomicInteger mSubmittedCount = new AtomicInteger();

        private LinkedBlockingQueue4Util mWorkQueue;

        ThreadPoolExecutor4Util(int corePoolSize, int maximumPoolSize,
                                long keepAliveTime, TimeUnit unit,
                                LinkedBlockingQueue4Util workQueue,
                                ThreadFactory threadFactory) {
            super(corePoolSize, maximumPoolSize,
                    keepAliveTime, unit,
                    workQueue,
                    threadFactory
            );
            workQueue.mPool = this;
            mWorkQueue = workQueue;
        }

        private int getSubmittedCount() {
            return mSubmittedCount.get();
        }

        @Override
        protected void afterExecute(Runnable r, Throwable t) {
            mSubmittedCount.decrementAndGet();
            super.afterExecute(r, t);
        }

        @Override
        public void execute(@NonNull Runnable command) {
            if (this.isShutdown()) return;
            mSubmittedCount.incrementAndGet();
            try {
                super.execute(command);
            } catch (RejectedExecutionException ignore) {
                Log.e("ThreadUtils", "This will not happen!");
                mWorkQueue.offer(command);
            } catch (Throwable t) {
                mSubmittedCount.decrementAndGet();
            }
        }
    }

    private static final class LinkedBlockingQueue4Util extends LinkedBlockingQueue<Runnable> {

        private volatile ThreadPoolExecutor4Util mPool;

        private int mCapacity = Integer.MAX_VALUE;

        LinkedBlockingQueue4Util() {
            super();
        }

        LinkedBlockingQueue4Util(boolean isAddSubThreadFirstThenAddQueue) {
            super();
            if (isAddSubThreadFirstThenAddQueue) {
                mCapacity = 0;
            }
        }

        LinkedBlockingQueue4Util(int capacity) {
            super();
            mCapacity = capacity;
        }

        @Override
        public boolean offer(@NonNull Runnable runnable) {
            if (mCapacity <= size() &&
                    mPool != null && mPool.getPoolSize() < mPool.getMaximumPoolSize()) {
                // create a non-core thread
                return false;
            }
            return super.offer(runnable);
        }
    }

    static final class UtilsThreadFactory extends AtomicLong
            implements ThreadFactory {
        private static final AtomicInteger POOL_NUMBER = new AtomicInteger(1);
        private static final long serialVersionUID = -9209200509960368598L;
        private final String namePrefix;
        private final int priority;
        private final boolean isDaemon;

        UtilsThreadFactory(String prefix, int priority) {
            this(prefix, priority, false);
        }

        UtilsThreadFactory(String prefix, int priority, boolean isDaemon) {
            namePrefix = prefix + "-pool-" +
                    POOL_NUMBER.getAndIncrement() +
                    "-thread-";
            this.priority = priority;
            this.isDaemon = isDaemon;
        }

        @Override
        public Thread newThread(@NonNull Runnable r) {
            Thread t = new Thread(r, namePrefix + getAndIncrement()) {
                @Override
                public void run() {
                    try {
                        super.run();
                    } catch (Throwable t) {
                        Log.e("ThreadUtils", "Request threw uncaught throwable", t);
                    }
                }
            };
            t.setDaemon(isDaemon);
            t.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
                @Override
                public void uncaughtException(Thread t, Throwable e) {
                    System.out.println(e);
                }
            });
            t.setPriority(priority);
            return t;
        }
    }

    public abstract static class SimpleTask<T> extends Task<T> {

        @Override
        public void onCancel() {
            Log.e("ThreadUtils", "onCancel: " + Thread.currentThread());
        }

        @Override
        public void onFail(Throwable t) {
            Log.e("ThreadUtils", "onFail: ", t);
        }

    }

    public abstract static class Task<T> implements Runnable {

        private static final int NEW = 0;
        private static final int RUNNING = 1;
        private static final int EXCEPTIONAL = 2;
        private static final int COMPLETING = 3;
        private static final int CANCELLED = 4;
        private static final int INTERRUPTED = 5;
        private static final int TIMEOUT = 6;

        private final AtomicInteger state = new AtomicInteger(NEW);

        private volatile boolean isSchedule;
        private volatile Thread runner;

        private Timer mTimer;
        private long mTimeoutMillis;
        private OnTimeoutListener mTimeoutListener;

        private Executor deliver;

        public abstract T doInBackground() throws Throwable;

        public abstract void onSuccess(T result);

        public abstract void onCancel();

        public abstract void onFail(Throwable t);

        @Override
        public void run() {
            if (isSchedule) {
                if (runner == null) {
                    if (!state.compareAndSet(NEW, RUNNING)) return;
                    runner = Thread.currentThread();
                    if (mTimeoutListener != null) {
                        Log.w("ThreadUtils", "Scheduled task doesn't support timeout.");
                    }
                } else {
                    if (state.get() != RUNNING) return;
                }
            } else {
                if (!state.compareAndSet(NEW, RUNNING)) return;
                runner = Thread.currentThread();
                if (mTimeoutListener != null) {
                    mTimer = new Timer();
                    mTimer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            if (!isDone() && mTimeoutListener != null) {
                                timeout();
                                mTimeoutListener.onTimeout();
                            }
                        }
                    }, mTimeoutMillis);
                }
            }
            try {
                final T result = doInBackground();
                if (isSchedule) {
                    if (state.get() != RUNNING) return;
                    getDeliver().execute(new Runnable() {
                        @Override
                        public void run() {
                            onSuccess(result);
                        }
                    });
                } else {
                    if (!state.compareAndSet(RUNNING, COMPLETING)) return;
                    getDeliver().execute(new Runnable() {
                        @Override
                        public void run() {
                            onSuccess(result);
                            onDone();
                        }
                    });
                }
            } catch (InterruptedException ignore) {
                state.compareAndSet(CANCELLED, INTERRUPTED);
            } catch (final Throwable throwable) {
                if (!state.compareAndSet(RUNNING, EXCEPTIONAL)) return;
                getDeliver().execute(new Runnable() {
                    @Override
                    public void run() {
                        onFail(throwable);
                        onDone();
                    }
                });
            }
        }

        public void cancel() {
            cancel(true);
        }

        public void cancel(boolean mayInterruptIfRunning) {
            synchronized (state) {
                if (state.get() > RUNNING) return;
                state.set(CANCELLED);
            }
            if (mayInterruptIfRunning) {
                if (runner != null) {
                    runner.interrupt();
                }
            }

            getDeliver().execute(new Runnable() {
                @Override
                public void run() {
                    onCancel();
                    onDone();
                }
            });
        }

        private void timeout() {
            synchronized (state) {
                if (state.get() > RUNNING) return;
                state.set(TIMEOUT);
            }
            if (runner != null) {
                runner.interrupt();
            }
            onDone();
        }


        public boolean isCanceled() {
            return state.get() >= CANCELLED;
        }

        public boolean isDone() {
            return state.get() > RUNNING;
        }

        public Task<T> setDeliver(Executor deliver) {
            this.deliver = deliver;
            return this;
        }

        /**
         * Scheduled task doesn't support timeout.
         */
        public Task<T> setTimeout(final long timeoutMillis, final OnTimeoutListener listener) {
            mTimeoutMillis = timeoutMillis;
            mTimeoutListener = listener;
            return this;
        }

        private void setSchedule(boolean isSchedule) {
            this.isSchedule = isSchedule;
        }

        private Executor getDeliver() {
            if (deliver == null) {
                return getGlobalDeliver();
            }
            return deliver;
        }

        @CallSuper
        protected void onDone() {
            TASK_POOL_MAP.remove(this);
            if (mTimer != null) {
                mTimer.cancel();
                mTimer = null;
                mTimeoutListener = null;
            }
        }

        public interface OnTimeoutListener {
            void onTimeout();
        }
    }

    private static Executor getGlobalDeliver() {
        if (sDeliver == null) {
            sDeliver = new Executor() {
                @Override
                public void execute(@NonNull Runnable command) {
                    runOnUiThread(command);
                }
            };
        }
        return sDeliver;
    }
}
