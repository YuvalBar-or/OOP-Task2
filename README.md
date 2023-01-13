# OOP project 2 
# part 1
## about the project 
in this part of the project, we are using verious functions to work with text files, and calculate the number of lines in each file. 

## the used mothods 
* createTextFiles 
* getNumOfLines
* getNumOfLinesThreads
* getNumOfLinesThreadPool

### explanation 
createTextFiles : method used to make the textfiles. 
getNumOfLines : normal method.
getNumOfLinesThreads : method with the use of threads. 
getNumOfLinesThreadPool : method with the use of threadpool. 

## classes
### ThreadHelper
this class, extends the "Thread" class. this class has 3 methods : 
* a constructor which gets a String name as a parameter and passes it to the super constructor. 
* run method: this method reads a file and counts the number of lines in it. 
* getcount: getter for count. 

### ThreadPoolHelper
this class, implements the "Callable" interface. this class has 2 method : 
* a constructor which gets a String name as a parameter.
* call method : this method reads a file and counts the number of lines in it. 

## time measures 
### 10 files 
number of lines : 34
* getNumOfLines : 1ms
* getNumOfLinesThreads : 1ms 
* getNumOfLinesThreadPool :3 ms 

### 100 files 
number of lines : 5672
* getNumOfLines : 5ms
* getNumOfLinesThreads : 4 ms
* getNumOfLinesThreadPool : 34 ms 

### 1000 files 
number of lines : 526701
* getNumOfLines : 47 ms 
* getNumOfLinesThreads : 27 ms
* getNumOfLinesThreadPool : 136 ms

### 2000 files 
number of lines : 1982659
* getNumOfLines : 89 ms 
* getNumOfLinesThreads : 68 ms  
* getNumOfLinesThreadPool : 270 ms 

### explanation 


























