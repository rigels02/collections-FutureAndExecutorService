package futureandexecutorservice.jpa;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author raitis
 */
public class JpaDBTaskExecutorService {
    private final static Logger Log= Logger.getLogger(JpaDBTaskExecutorService.class.getName());
    
    private final static int THREADS_COUNT= 10;
    
   
    public static void main(String[] args) {
      
        ExecutorService executor = Executors.newFixedThreadPool(THREADS_COUNT);
      List<String> resultMsgs= new ArrayList<>();
      List<Future<String>> futuresList = new ArrayList<>();
     
        int[] ids={1,2,3,4,5,6,7,8,9,10}; 
       
        for (int id : ids) {
            JpaDBTask task = new JpaDBTask(id);
            Future<String> future = executor.submit(task);
            futuresList.add(future);
        }
        
        for (Future<String> future : futuresList) {
            String fresult=null;
            try {
                fresult = future.get();
            } catch (InterruptedException | ExecutionException ex) {
                Logger.getLogger(JpaDBTaskExecutorService.class.getName()).log(Level.SEVERE, null, ex);
            }
            resultMsgs.add(fresult);
        }
        executor.shutdown();
        
        //Results
        resultMsgs.forEach(m -> System.out.println("Result Msg: "+m));
    }
}
