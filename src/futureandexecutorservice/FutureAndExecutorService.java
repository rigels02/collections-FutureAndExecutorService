
package futureandexecutorservice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
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
public class FutureAndExecutorService {

    private final static Logger Log= Logger.getLogger
        ( FutureAndExecutorService.class.getName());
    
    private final static int THREADS_COUNT= 10;
    private static BigDecimal[] ids= new BigDecimal[]{
        new BigDecimal(1),new BigDecimal(2),new BigDecimal(3),new BigDecimal(4),
        new BigDecimal(5),new BigDecimal(6),new BigDecimal(7),new BigDecimal(8),
        new BigDecimal(9),new BigDecimal(10)
    };
    private static List<BigDecimal> invoiceIDs;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        invoiceIDs= Arrays.asList(ids);
        List<BigDecimal> invoiceCalculationsResult = new ArrayList<BigDecimal>();
	ExecutorService executor = Executors.newFixedThreadPool(THREADS_COUNT);
        List<Future<BigDecimal>> futuresList = new ArrayList<>();
		
        
        // invoiceIDs is a collection of Invoice IDs
	for (BigDecimal invoiceID : invoiceIDs) {
		Callable<BigDecimal> task = new CalculationTask(invoiceID);
		Future<BigDecimal> future = executor.submit(task);
		futuresList.add(future);
	}
		
	for (Future<BigDecimal> future : futuresList) {
		BigDecimal calculationResult = null;
		try {
			calculationResult = future.get();
		} catch (InterruptedException | ExecutionException e) {
			Log.log(Level.SEVERE, e.getMessage());
                } 
		invoiceCalculationsResult.add(calculationResult);
	}
        
        executor.shutdown();
        //Results
        invoiceCalculationsResult.forEach(r-> System.out.println("Result ->"+r.toPlainString()));
        
        /***
        
        ScheduledExecutorService scheduledPool = Executors.newScheduledThreadPool(4);
        ScheduledFuture<?> schedFuture = scheduledPool.scheduleWithFixedDelay(runnabledelayedTask, 1, 1, TimeUnit.SECONDS);
        ***/
        
    }
    
}
