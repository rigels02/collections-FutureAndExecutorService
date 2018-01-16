
package futureandexecutorservice;

import java.math.BigDecimal;
import java.util.concurrent.Callable;

/**
 *
 * @author raitis
 */
public class CalculationTask implements Callable<BigDecimal>{

    	private final BigDecimal invoiceId;
 
        public CalculationTask(BigDecimal invoiceId) {
		this.invoiceId = invoiceId;
	}
        
    @Override
    public BigDecimal call() throws Exception {
       BigDecimal result;
        System.out.println("Task started for InvoiceId: "+invoiceId);
       //Simulate long time calc
       for(int i=0; i<10; i++){
           System.out.println("> Id:"+invoiceId+" still running...");
           Thread.sleep(1000L);
       }
       //Thread.sleep(10000L);
       result = new BigDecimal(100L).add(invoiceId);
       return result;
    }
    
}
