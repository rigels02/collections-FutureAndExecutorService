package futureandexecutorservice.completablefuture;

import futureandexecutorservice.diffexectime.WebContent;
import futureandexecutorservice.diffexectime.WebLoadingSimulateTask;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author raitis
 */
public class ParallelTasks {
    
    ExecutorService service;

    public ParallelTasks(ExecutorService service) {
        this.service = service;
    }
    
    public Set<WebContent> getWebs(Set<String> url){

        List<CompletableFuture<WebContent>> futures =
                url.stream()
                          .map(webUrl -> getWebAsync(webUrl))
                          .collect(Collectors.toList());

        Set<WebContent> result =
                futures.stream()
                        .map(CompletableFuture::join)
                        .collect(Collectors.toSet());

        return result;
    }


    CompletableFuture<WebContent> getWebAsync(String url){

        CompletableFuture<WebContent> future = CompletableFuture.supplyAsync(new Supplier<WebContent>() {
            @Override
            public WebContent get() {
                WebContent content=null;
                try {
                    content = new WebLoadingSimulateTask(url).call();
                } catch (Exception ex) {
                    Logger.getLogger(ParallelTasks.class.getName()).log(Level.SEVERE, null, ex);
                }

                return content;
            }
        }, service);

        return future;
    }
}
