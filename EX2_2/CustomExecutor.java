package EX2_2;

import java.util.Comparator;
import java.util.concurrent.*;

/**
 *A class that extends ThreadPoolExecutor and implements Comparator.
 * The class will create a custom thread pool with a priority for each task that is submitted to a thread.
 * The ThreadPoolExecutor holds a PriorityBlockingQueue that is comparing the tasks and organizing the queue such as
 * the lowest priority will be executed first.
 */
public class CustomExecutor extends ThreadPoolExecutor {
    private int maxPriority=0;
    private boolean isShutdown = false;
    /**
     * The constructor for the customExecutor which creates a new ThreadPoolExecutor.
     */
    public CustomExecutor() {
        super(Runtime.getRuntime().availableProcessors() / 2
                , Runtime.getRuntime().availableProcessors() - 1
                , 300L, TimeUnit.MILLISECONDS, new PriorityBlockingQueue<>());
    }

    /**
     * @return returns the max priority that was in the queue
     */
    public int getCurrentMax() {
        return this.maxPriority;
    }

    /**
     * @param task
     * @return returns a new future for a task
     * @param <T>
     */
    protected <T> NewFuture<T> newTask(Task task) {
        return new NewFuture<T>(task.getCallable(), task.getPriority().getPriorityValue());
    }
    /**
     * A function that is used to submit tasks to the ThreadPoolExecutor.
     * The function is used only in this class so that's the reason that it's private.
     * @param task
     * @return submits the task to the ThreadPoolExecutor.
     */
    private  <T> Future<T> submitTask(Task task) {
        if (isShutdown) {
            System.out.println("Thread pool has already been shut down. Cannot submit task");
            return null;
       }
        if (task.getPriority().getPriorityValue()>maxPriority)
         maxPriority=task.getPriority().getPriorityValue();
        NewFuture<T> futureTask = newTask(task);
        execute(futureTask);
        return futureTask;
    }
    /**
     *A function that Submits a task to the ThreadPoolExecutor.
     * @return submits the task to the ThreadPoolExecuter
     * @param <T>
     */
    public <T> Future<T> submit(Task task) {
        return submitTask(task);
    }
    /**
     *A function that Submits a callable with priority to the ThreadPoolExecutor.
     * The function creates a Task from the callable and priority and activate the submitTask on them.
     * @param callable
     * @param priority
     * @return submits the task to the ThreadPoolExecuter
     * @param <T>
     */
    public <T> Future<T> submit(Callable<T> callable, TaskType priority) {
        return submitTask(Task.createTask(callable, priority));
    }
    /**A function that Submits a callable with priority to the ThreadPoolExecutor.
     * The function creates a Task from the callable and  default priority which is OTHER(3) and activate the submitTask on them.
     * @param callable the task to submit
     * @return submits the task to the ThreadPoolExecuter
     * @param <T>
     */
    //OTHER is default because it has the worst priority which means it will be executed last.
    public <T> Future<T> submit(Callable<T> callable) {
        return submitTask(Task.createTask(callable));
    }
    /**A function that shuts down the ThreadPoolExecutor and making the isShutdown flag true so that you cant submit
     * more tasks to the ThreadPoolExecutor.
     */
    public void gracefullyTerminate() {
          isShutdown = true;
          super.shutdown();
    }
}

