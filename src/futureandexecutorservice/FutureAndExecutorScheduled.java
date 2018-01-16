package futureandexecutorservice;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author raitis
 */
public class FutureAndExecutorScheduled {


    public static void main(String[] args) {
        DelayedTasks tasks = new DelayedTasks();
        
        ScheduledExecutorService scheduledPool = Executors.newScheduledThreadPool(4);
        //runnabledelayedTask is scheduled to run for ever...
        ScheduledFuture<?> ffeatures = scheduledPool.scheduleWithFixedDelay(tasks.runnabledelayedTask, 1, 1, TimeUnit.SECONDS);
        ScheduledFuture<String> sfeatures = scheduledPool.schedule(tasks.callabledelayedTask, 1,TimeUnit.SECONDS);
        try {
            int count=1;
            //max 5 times wait termination : 6*5 = 30 seconds
            while( !scheduledPool.awaitTermination(6, TimeUnit.SECONDS) && count < 5){
            
                System.out.println("runnabledelayedTask is finished ?:"+ffeatures.isDone());
                System.out.println("callabledelayedTask is finished ?:"+sfeatures.isDone());
                if(sfeatures.isDone()){
                    System.out.println("callabledelayedTask result: "+sfeatures.get());
                }
              count++;
              if(count==5){
                  //cancel runnabledelayedTask, but not interrupt it
                  System.out.println("runnabledelayedTask is canceled? :"+ffeatures.cancel(false));
               }
            }
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(FutureAndExecutorScheduled.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("runnabledelayedTask is finished ?:"+ffeatures.isDone());
        System.out.println("callabledelayedTask is finished ?:"+sfeatures.isDone());
        try {
            System.out.println("callabledelayedTask result: "+sfeatures.get());
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(FutureAndExecutorScheduled.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Shutdown ....");
        scheduledPool.shutdown();
        System.out.println("Shutdown Done?:"+scheduledPool.isShutdown());
    }
    
}
