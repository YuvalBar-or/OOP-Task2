package EX2_1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ThreadHelper extends Thread {
    private int count=0;
    private String name;

    /**
     * constructor
     * @param name the name
     */
    public ThreadHelper (String name){
        super(name);
        this.name= name;
    }

    /**
     * overriding a Threads method
     */
    @Override
    public void run(){
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
    }

    /**
     * getter for count
      * @return count
     */
     public int getCount(){
        return this.count;
     }

    }



