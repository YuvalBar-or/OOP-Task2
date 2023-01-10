package EX2_2;

import java.util.concurrent.*;

/**
 * A class that helps us to compare between tasks priorities
 * @param <V>
 */
public class NewFuture<V> extends FutureTask<V> implements Comparable<NewFuture>{
    private final int priority;

    /**
     * The constructor for the NewFuture class
     * @param callable
     * @param p
     */
    public NewFuture(Callable<V> callable, int p) {
        super(callable);
        this.priority = p;
    }

    /**
     * @return the string of the comparable
     */
    @Override
    public String toString() {
        return super.toString() + " Priority: "+priority;
    }
    /**A function that compares between two tasks priority and returns who is the bigger priority task
     * @param o the second object to be compared.
     * @return a value greater than zero if this.priority has a bigger priority. 0 if they are equal.a value smaller
     * than zero if o has a bigger priority
     */
    @Override
    public int compareTo(NewFuture o) {
        return Integer.compare(this.priority,o.priority);
    }
}
