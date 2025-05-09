package tech.insight;

public class Job implements Comparable<Job> {
    private Runnable task;
    private long startTime;
    private long delay;

    public long getDelay() {
        return this.delay;
    }

    public void setDelay(long delay) {
        this.delay = delay;
    }

    public Runnable getTask() {
        return this.task;
    }

    public void setTask(Runnable task) {
        this.task = task;
    }

    public long getStartTime() {
        return this.startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public int compareTo(Job o) {
        return Long.compare(this.startTime, o.startTime);
    }
}
