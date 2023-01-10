package EX2_1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.Callable;

public class ThreadPoolHelper implements Callable<Integer> {

    private String name;

    /**
     * constructor
     * @param name the name
     */
    public ThreadPoolHelper (String name){
        this.name = name;
    }

    /**
     * overridng the Callable method
     * @return the number of lines
     * @throws Exception
     */
    @Override
    public Integer call() throws Exception {
        int count =0;
        String name = this.name;
        try{
            BufferedReader read = new BufferedReader(new FileReader(this.name));
            while (read.readLine() != null){
                count++;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return count;
    }






}
