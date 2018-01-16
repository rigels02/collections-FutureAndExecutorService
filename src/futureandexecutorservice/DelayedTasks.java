package futureandexecutorservice;

import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author raitis
 */
public class DelayedTasks {

    Runnable runnabledelayedTask ;
    Callable<String> callabledelayedTask;
    int count=0;
    
    public DelayedTasks() {
        runnabledelayedTask= new Runnable() {
            @Override
            public void run() {
                count++;
                System.out.println(Thread.currentThread().getName()+" is Running Delayed Task-"+count);
                for(int i = 0; i<10; i++){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(DelayedTasks.class.getName()+": run()").log(Level.SEVERE, null, ex);
                    }
                    System.out.println(Thread.currentThread().getName()+": Delayed Task still running..."+count);   
                }
            }
        };
        
        callabledelayedTask = new Callable() {
            @Override
            public String call() throws Exception {
                 System.out.println(Thread.currentThread().getName()+" is Callable Delayed Task");
                 for(int i = 0; i<10; i++){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(DelayedTasks.class.getName()+": Callable()").log(Level.SEVERE, null, ex);
                    }
                    System.out.println(Thread.currentThread().getName()+": Callable Task still running...");   
                }
                 return Thread.currentThread().getName()+" :Callable task finished.";
            }
        };
    }
    
        
    
        
}
