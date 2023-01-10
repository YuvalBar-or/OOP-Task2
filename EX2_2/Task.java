package EX2_2;

import java.util.concurrent.*;
/**
 * A class that represent a Task that can be submitted to a ThreadPoolExecutor.
 * The class includes a callable which is a task that a thread can execute it and a priority.
 */
public class Task {
    private Callable callable;
    private TaskType priority;
    /**A function that creates a new task from a callable and a priority.
     * @param callable
     * @param priority
     * @param <T>
     */
    private Task(Callable callable, TaskType priority) {
        this.callable = callable;
        this.priority = priority;
    }
    /**
     * @return priority of a task
     */
    public TaskType getPriority() {
        return priority;
    }
    /** A function that returns a new task which uses the factory pattern.
     * @param callable
     * @return a new task
     * @param <T>
     */
    public static <T> Task createTask(Callable<T> callable) {
        return new Task(callable, TaskType.OTHER);
    }
    /** A function that returns a new task which uses the factory pattern.
     * @param callable
     * @param priority
     * @return a new task
     * @param <T>
     */
    public static <T> Task createTask(Callable<T> callable, TaskType priority) {
        return new Task(callable, priority);
    }
    /**
     * @return the callable
     */
    public <T> Callable<T> getCallable() {
        return this.callable;
    }
}
