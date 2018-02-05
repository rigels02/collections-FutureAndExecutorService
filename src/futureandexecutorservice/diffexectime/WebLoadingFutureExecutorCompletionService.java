/**
* ExecutorCompletionService "remembers" all submitted tasks and 
* allows you to wait for the first completed, as opposed to first submitted task.
* Crucial API method is CompletionService.take() that blocks and waits 
* for any underlying Future to complete
*/
package futureandexecutorservice.diffexectime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author raitis
 */
public class WebLoadingFutureExecutorCompletionService {
  
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
        //Use executor completion service
        ExecutorCompletionService<WebContent> completionService = new ExecutorCompletionService<>(executor);
        
		
        
        // invoke tasks
	for (String web : webSites) {
            WebLoadingSimulateTask task = new WebLoadingSimulateTask(web);
            Future<WebContent> future = completionService.submit(task);
          
	}
		
	for (int i=0 ; i< webSites.size(); i++) {
		WebContent loadTaskResult = null;
              
		try {
                      final Future<WebContent> future = completionService.take();
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
