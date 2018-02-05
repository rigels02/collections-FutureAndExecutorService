package futureandexecutorservice.diffexectime;

import java.util.ArrayList;
import java.util.Arrays;
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
public class WebLoadingFutureAndExecutorService {

 private final static Logger Log= Logger.getLogger
        ( WebLoadingFutureAndExecutorService.class.getName());
    
    private final static int THREADS_COUNT= 10;
    
    private static String[] sites= {
        "www.google.com", "www.youtube.com", "www.yahoo.com", "www.msn.com",
        "www.wikipedia.org", "www.baidu.com", "www.microsoft.com", "www.qq.com",
        "www.bing.com", "www.ask.com", "www.adobe.com", "www.taobao.com",
        "www.youku.com", "www.soso.com", "www.wordpress.com", "www.sohu.com",
        "www.windows.com", "www.163.com", "www.tudou.com", "www.amazon.com"
        };
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ExecutionException {
        
        List<String> webSites = Arrays.asList(sites);
        List<WebContent> webContents = new ArrayList<>();
	ExecutorService executor = Executors.newFixedThreadPool(THREADS_COUNT);
        List<Future<WebContent>> futuresList = new ArrayList<>();
		
        
        // invoke tasks
	for (String web : webSites) {
            WebLoadingSimulateTask task = new WebLoadingSimulateTask(web);
            Future<WebContent> future = executor.submit(task);
            futuresList.add(future);
	}
		
	for (Future<WebContent> future : futuresList) {
		WebContent loadTaskResult = null;
		try {
                    /**
                     * Blocking problem (loosing performance case): The 1st submitted
                     * task future is taken and waiting for this task completion...
                     * But if the last submitted task is completed before the 1st submitted task??
                     * For such scenario the ExecutorCompletionService is useful
                     */
			loadTaskResult = future.get();
                    System.out.println("Result got for: "+loadTaskResult.url);    
		} catch (InterruptedException | ExecutionException e) {
			Log.log(Level.SEVERE, e.getMessage());
                } 
		webContents.add(loadTaskResult);
	}
        
        executor.shutdown();
        //Results
        webContents.forEach(r-> System.out.println("Result ->"+r.content));
       
   }

}