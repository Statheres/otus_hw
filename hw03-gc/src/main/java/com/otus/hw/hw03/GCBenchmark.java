package com.otus.hw.hw03;

import com.sun.management.GarbageCollectionNotificationInfo;

import javax.management.NotificationEmitter;
import javax.management.openmbean.CompositeData;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class GCBenchmark implements Runnable, GCBenchmarkMBean {
    private static final int DEFAULT_OLD_GENERATION_INCREMENT = 10000;
    private static final int DEFAULT_OLD_GENERATION_DECREMENT = 1000;
    private static final int DEFAULT_NEW_GENERATION_SIZE = 1000;
    private static final int DEFAULT_TIMEOUT = 2;

    private ScheduledThreadPoolExecutor executor;

    private int oldGenerationIncrement = DEFAULT_OLD_GENERATION_INCREMENT;
    private int oldGenerationDecrement = DEFAULT_OLD_GENERATION_DECREMENT;
    private int newGenerationSize = DEFAULT_NEW_GENERATION_SIZE;
    private int timeout = DEFAULT_TIMEOUT;

    private List<String> oldGenerationData = new ArrayList<>();

    private AtomicBoolean isRunning = new AtomicBoolean();

    public GCBenchmark(ScheduledThreadPoolExecutor executor) {
        this.executor = executor;
    }

    public void start() {
        startGCMonitoring();

        isRunning.set(true);

        executor.execute(this);
    }

    @Override
    public int getOldGenerationIncrement() {
        return oldGenerationIncrement;
    }

    @Override
    public int getOldGenerationDecrement() {
        return oldGenerationDecrement;
    }

    @Override
    public int getNewGenerationSize() {
        return newGenerationSize;
    }

    @Override
    public int getTimeout() {
        return timeout;
    }

    @Override
    public void stopBenchmark() {
        isRunning.set(false);
    }

    @Override
    public void setOldGenerationIncrement(int oldGenerationIncrement) {
        this.oldGenerationIncrement = oldGenerationIncrement;
    }

    @Override
    public void setOldGenerationDecrement(int oldGenerationDecrement) {
        this.oldGenerationDecrement = oldGenerationDecrement;
    }

    @Override
    public void setNewGenerationSize(int newGenerationSize) {
        this.newGenerationSize = newGenerationSize;
    }

    @Override
    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    @Override
    public void run() {
        try {
            int oldDataDeleteCount = oldGenerationDecrement;
            while (oldDataDeleteCount-- > 0 && !oldGenerationData.isEmpty()) {
                oldGenerationData.remove(oldGenerationData.size() - 1);
            }

            for (int i = 0; i < oldGenerationIncrement; ++i) {
                oldGenerationData.add(new String("Some old string"));
            }

            List<String> newGenerationData = new ArrayList<>();
            for (int i = 0; i < newGenerationSize; ++i) {
                newGenerationData.add(new String("Some new string"));
            }

            Thread.sleep(timeout);
        } catch (Exception e) {
            System.out.println(e);
        }

        if (isRunning.get()) {
            executor.schedule(this, 1, TimeUnit.SECONDS);
        }
    }

    private void startGCMonitoring() {
        List<GarbageCollectorMXBean> garbageCollectorMXBeans = ManagementFactory.getGarbageCollectorMXBeans();
        for (GarbageCollectorMXBean mxBean : garbageCollectorMXBeans) {
            System.out.println(String.format("Start monitoring for %s gc...", mxBean.getName()));
            NotificationEmitter emitter = (NotificationEmitter) mxBean;
            emitter.addNotificationListener(
                    (notification, data) ->
                    {
                        GarbageCollectionNotificationInfo info = GarbageCollectionNotificationInfo.from((CompositeData) notification.getUserData());
                        String gcName = info.getGcName();
                        String gcAction = info.getGcAction();
                        String gcCause = info.getGcCause();

                        long startTime = info.getGcInfo().getStartTime();
                        long duration = info.getGcInfo().getDuration();

                        System.out.println("start:" + startTime + " Name:" + gcName + ", action:" + gcAction + ", gcCause:" + gcCause + "(" + duration + " ms)");
                    },
                    notification -> notification.getType().equals(GarbageCollectionNotificationInfo.GARBAGE_COLLECTION_NOTIFICATION),
                    null
            );
        }
    }
}
