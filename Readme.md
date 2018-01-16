ThreadPool and ScheduledThreadPool with Executor Example
===================================
References:
-----------
https://examples.javacodegeeks.com/core-java/util/concurrent/scheduledthreadpoolexecutor/java-util-concurrent-scheduledthreadpoolexecutor-example/?utm_content=buffer78170&utm_medium=social&utm_source=facebook.com&utm_campaign=buffer

Timer
-----

Timer task is used also used to schedule activities. 
However, the Timer object can run only Task at a time. 
As a result of which, if the previous task is taking a more to time to execute, 
the consequent tasks get delayed.

ScheduledThreadPoolExecutor to the rescue!
------------------------------------------
To avoid the above problem, we use the ScheduledThreadPoolExecutor Class 
which can run more than one task in parallel. The minimum number of Threads
(i.e. corepoolsize) that can be passed at the time of creation of the ScheduledThreadPoolExecutor.

The scheduleXXXX methods of the ScheduledThreadPoolExecutor accept instance 
of Runnable or Callable. We can get the result of computation of the Callable 
instance via get() method of ScheduledFuture.

We will have a look at an example, to see how we can get the ScheduledThreadPoolExecutor 
Class to schedule the delayed activities.

JPA and DB update by ThreadPoolExecutor 
--------------------------------------
1) JavaSE application has to manage EnityManager itself
2) EntityManager is mutable in multi-thread application.
To ensure thread safety the EntityManagerHelper class has been implemented in such a way that every task thread has unique EntityManager. 