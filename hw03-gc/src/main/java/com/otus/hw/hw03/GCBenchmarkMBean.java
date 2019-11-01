package com.otus.hw.hw03;

public interface GCBenchmarkMBean {
    void setOldGenerationIncrement(int oldGenerationIncrement);
    int getOldGenerationIncrement();

    void setOldGenerationDecrement(int oldGenerationDecrement);
    int getOldGenerationDecrement();

    void setNewGenerationSize(int newGenerationSize);
    int getNewGenerationSize();

    void setTimeout(int timeout);
    int getTimeout();

    void stopBenchmark();
}
