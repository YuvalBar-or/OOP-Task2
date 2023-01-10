package EX2_2;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import java.util.concurrent.*;
public class Tests {
    public static final Logger logger = LoggerFactory.getLogger(Tests.class);

    @Test
    public void partialTest() throws Exception {
        CustomExecutor customExecutor = new CustomExecutor();
        customExecutor.setCorePoolSize(1);
        customExecutor.setMaximumPoolSize(1);
        for (int i = 0; i < 8; i++) {
            Callable<String> testIO = () -> {
                StringBuilder sb = new StringBuilder("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
                System.out.println("hi");
                return sb.reverse().toString();
            };

            var reverseTask1 = customExecutor.submit(testIO, TaskType.IO);
            var task = Task.createTask(()->{
                int sum = 0;
                for (int j = 1; j <= 10; j++) {

                    sum += j;
                }
                System.out.println(sum);
                return sum;
            }, TaskType.COMPUTATIONAL);
            var sumTask = customExecutor.submit(task);
            logger.info(()-> "Current maximum priority = " +
                    customExecutor.getCurrentMax());

            var testMath = customExecutor.submit(() -> {

                return 1000 * Math.pow(1.02, 5);
            }, TaskType.OTHER);

            Callable<String> testIO2 = () -> {
                StringBuilder sb = new StringBuilder("ABCDEFGHIJKLMNOPQRSTUVWXYZ");

                return sb.reverse().toString();
            };

            var testt = customExecutor.submit(testIO2, TaskType.IO);
            logger.info(()-> "Current maximum priority = " +
                    customExecutor.getCurrentMax());

            final String get1;
            final double get2;
            final int get3;

            try {
                get1 = testt.get();
                get2 = testMath.get();
                get3 = (int) sumTask.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
            logger.info(()-> "Reversed String = " + get1);
            logger.info(()->String.valueOf("Total Price = " + get2));
            logger.info(()-> "Current maximum priority = " +
                    customExecutor.getCurrentMax());

        }
        customExecutor.gracefullyTerminate();
    }
}