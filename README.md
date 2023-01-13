# OOP project 2 
# part 1
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
* getNumOfLinesThreads : 2ms 
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
as we can see, using the TreadPool method was the least affective method out of the 3. 
thats because in order to use thread or threadpool you'll need to allocate resources from the operating system, therefore for small projects (as can be seem in the 10 file calculations) its less affective. 
but, for bigger projects (as can be seen in the 100 + files) using the threads to calculate the number of lines was more affective.

## UML


![Picture1](https://user-images.githubusercontent.com/118693941/212293982-9e4fbbdc-43e3-4865-91c3-be4bb659eb38.png)


# part 2
## classes
### TaskType
the enum class "TaskType" has 3 fixed values: 
* COMPUTATIONAL : with priority 1.
* IO : with priority 2. 
* OTHER : with priority 3. <br />
and the following functions as methods : 
* typePriority
* setPriority
* getPriorityValue
* validatePriority

### Task
this class implements the Comparable interface. this is a generic class. <br /> 
It has two fields:
* taskType of type TaskType 
* callable of type Callable<T>. <br /> 
The class has two constructors:
 * one of them takes two parameters Callable<T> and TaskType, 
 * the other takes one parameter Callable<T> and will set the TaskType to OTHER. 

### NewFuture
this class is extending the FutureTask<V> and implements the Comparable<NewFuture> interface. <br /> 
it has 3 methods: 
* constructor : that takes a Callable and an int parameter and pass the Callable to the constructor of the FutureTask super class.
* toString 
* compareTo : this function is an implementation of the compareTo method of the Comparable interface.
  
### CustomExecuter
this class extends ThreadPoolExecutor class. <br /> 

 CustomExecutor uses a PriorityBlockingQueue to hold the MyFuture tasks, this queue is able to hold a collection of tasks and orders them based on the priority of each task. It also keeps track of the number of tasks for each priority using an array priorityCounts.

The class has four constructors: A default constructor that creates a new thread pool with the number of available processors in the system and a default PriorityBlockingQueue and defines some properties such as core pool size, maximum pool size and keep-alive time. It overrides the newTaskFor method from ThreadPoolExecutor to return a MyFuture object for a new task, this method is used to create new instances of MyFuture for the ThreadPoolExecutor to use when a new task is submitted. It also has three methods that are used to submit a task to the thread pool:

submit(Task task): which is used to submit a Task object to the thread pool and increments the priority count
submit (Callable task, TaskType taskType): which is used to submit a Callable task with a TaskType object to the thread pool
submit (Callable task): which is used to submit a Callable task with TaskType object is set to OTHER It also has two methods:
getCurrentMax() which returns the current maximum priority count. gracefullyTerminate() which calls the shutdown() method of the ThreadPoolExecutor to terminate the thread pool. It also overrides the beforeExecute method to decrement the priority count of the task before it is executed. This CustomExecutor allows to schedule and execute the tasks according to the priority assigned to them and it also allows tracking the number of tasks for each priority

## UML
![Picture2](https://user-images.githubusercontent.com/118693941/212294438-40b3f918-21cd-4f3f-83cb-6db0e7687be3.png)

