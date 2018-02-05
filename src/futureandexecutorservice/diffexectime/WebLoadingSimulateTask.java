/*
*
*/
package futureandexecutorservice.diffexectime;

import java.util.Random;
import java.util.concurrent.Callable;

class Content{
    final static String HTMLTMPL="<!DOCTYPE html>\n" +
"<html>\n" +
"    <head>\n" +
"        <title>title</title>\n" +
"        <meta charset=\"UTF-8\">\n" +
"        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
"    </head>\n" +
"    <body>\n" +
"        <h1>%s</h1>\n" +
"        <div>%s</div>\n" +
"    </body>\n" +
"</html>";
}
/**
 *
 * @author raitis
 */
public class WebLoadingSimulateTask implements Callable<WebContent>{

    private final String web;

    
        public WebLoadingSimulateTask(String webUrl) {
		this.web = webUrl;
	}
        
    @Override
    public WebContent call() throws Exception {
        WebContent result = new WebContent();
       Random ran = new Random();
        int tim = 200+ran.nextInt(1000-200+1); //generate int between 200 - 1000
        
       System.out.println("Task started for web url: "+web+" ("+tim+") ms");
        //Simulate long time calc
       for(int i=0; i<10; i++){
          // System.out.println("> url: "+web+" still running...");
           
           Thread.sleep(tim);
       }
       //Thread.sleep(10000L);
       result.url = web;
       result.content=String.format(Content.HTMLTMPL, web,"Generated html content");
        System.out.println("> url: "+web+" completed...("+tim+") ms");
       return result;
    }
    
}
