package futureandexecutorservice.diffexectime;

import futureandexecutorservice.completablefuture.ParallelTasks;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 *
 * @author raitis
 */
public class WebLoadingByCompletableFuture {

    private final static int THREADS_COUNT= 10;
    
    private static final String[] SITES= {
        "www.google.com", "www.youtube.com", "www.yahoo.com", "www.msn.com",
        "www.wikipedia.org", "www.baidu.com", "www.microsoft.com", "www.qq.com",
        "www.bing.com", "www.ask.com", "www.adobe.com", "www.taobao.com",
        "www.youku.com", "www.soso.com", "www.wordpress.com", "www.sohu.com",
        "www.windows.com", "www.163.com", "www.tudou.com", "www.amazon.com"
        };
    
    public static void main(String[] args) {
            
       
	ExecutorService executor = Executors.newFixedThreadPool(THREADS_COUNT);
        ParallelTasks paralelTasks = new ParallelTasks(executor);
        
        Set<WebContent> webContents = paralelTasks.getWebs(new HashSet<>(Arrays.asList(SITES)));
        
        executor.shutdown();
        
        webContents.forEach(wc -> 
                System.out.println(
                        String.format("Web-> %s\nContent->%s", wc.url,wc.content)
                    )
                );
        
    }
}
