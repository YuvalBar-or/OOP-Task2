package EX2_1;
import java.awt.*;
import java.io.*;
import java.util.Random;
import java.util.concurrent.*;

public class EX2_1 {
    // first question

    /**
     * this function runs on the files array and for each file it creates a text file.
     * furthermore, we opened a try and catch for exceptions.
     * @param n     number of files
     * @param seed  random
     * @param bound the max number of lines in files.
     * @return array of file names.
     */
    public static String[] createTextFiles(int n, int seed, int bound) {
        String[] files = new String[n];
        Random rand = new Random(seed);
        for (int i = 0; i < n; i++) {
            files[i] = "file_" + (i + 1) + ".txt";
            File filenew = new File(files[i]);
            try {
                filenew.createNewFile();
                FileWriter writer = new FileWriter(files[i]);
                int counter = rand.nextInt(bound);
                for (int k = 0; k < counter; k++) {
                    writer.write("Hello World" + "\n");
                }
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return files;

    }

    // second question

    /**
     * this function runs through each file in fileNames and reads the amount of lines in it, while the number of lines is not null we add one to or counter.
     *
     * @param fileNames the string of files
     * @return the number of lines in fileNames
     */
    public static int getNumOfLines(String[] fileNames) {
        int lines = 0;
        for (String file : fileNames) {
            try {
                BufferedReader read = new BufferedReader(new FileReader(file));
                while (read.readLine() != null) {
                    lines++;
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return lines;
    }

    //third question

    /**
     * this function runs once through filenames and second through each thread in the specific file and counts it.
     * the first for: for each file in filename we put it in the threadhelper array and start the thread.
     * the second for: while using try and catch we use the join function and for each thread we get the count and add that to our counter for lines (line)
     *
     * @param fileNames array of file names
     * @return the number of lines in fileNames
     */
    public int getNumOfLinesThreads(String[] fileNames) {
        int line = 0;
        ThreadHelper[] counterThreads = new ThreadHelper[fileNames.length];
        for (int i = 0; i < fileNames.length; i++) {
            counterThreads[i] = new ThreadHelper(fileNames[i]);
            counterThreads[i].start();
        }
        for (ThreadHelper thread : counterThreads) {
            try {
                thread.join();
                line += thread.getCount();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return line;
    }

    //forth question

    /**
     * this function has ine main for and 2 try and catches.
     * the main for: for each file in filename we put the file in our ThreadPoolHelper array.
     *the first try: in this try we used our ThreadPoolExecuter and Futures to count the number of lines.
     * the second try:we terminate the executer
     * @param fileNames the array of file names
     * @return the amount of lines in filenames
     */
    public static int getNumOfLinesThreadPool(String[] fileNames) {
        int line = 0;
        ThreadPoolExecutor ex = (ThreadPoolExecutor) Executors.newFixedThreadPool(fileNames.length);
        ThreadPoolHelper[] counterThreadPool = new ThreadPoolHelper[fileNames.length];
        Future<Integer>[] futures = new Future[fileNames.length];

        for (int i = 0; i < fileNames.length; i++) {
            counterThreadPool[i] = new ThreadPoolHelper(fileNames[i]);
        }
        try {
            for (int i = 0; i < fileNames.length; i++) {
                futures[i] = ex.submit(counterThreadPool[i]);
            }
            for (Future<Integer> future : futures) {
                line += future.get();
            }

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ex.shutdown();
        try {
            ex.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return line;
    }

    public static void main(String[] args) {
        long start, end;
        int numberOfFiles = 1000;
        int maxNumberOfLines = 999;
        String[] fileNames = createTextFiles(numberOfFiles, 1, maxNumberOfLines);
        System.out.println("Checking " + numberOfFiles + " files with " + maxNumberOfLines + " maximum lines each:");
        System.out.println("---------------------------------------------------------");
        start = System.currentTimeMillis();
        int numLines = getNumOfLines(fileNames);
        end = System.currentTimeMillis();
        System.out.println("The number of lines :" + numLines +
                " The time is took : " + (end - start) + " ms");
        System.out.println("---------------------------------------------------------");

        // Measure the run time of getNumOfLinesThreads
        start = System.currentTimeMillis();
        numLines = getNumOfLines(fileNames);
        end = System.currentTimeMillis();
        System.out.println("The number of lines using Threads:" + numLines +
                " The time is took : " + (end - start) + " ms");
        System.out.println("---------------------------------------------------------");

        // Measure the run time of getNumOfLinesThreadPool
        start = System.currentTimeMillis();
        numLines = getNumOfLinesThreadPool(fileNames);
        end = System.currentTimeMillis();
        System.out.println("The number of lines using ThreadPool :" + numLines +
                " The time is took : " + (end - start) + " ms");

    }

}

