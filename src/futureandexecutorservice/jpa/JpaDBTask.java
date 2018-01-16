package futureandexecutorservice.jpa;

import futureandexecutorservice.jpa.model.TaskRecord;
import java.util.Date;
import java.util.concurrent.Callable;
import javax.persistence.EntityManager;

/**
 *
 * @author raitis
 */
public class JpaDBTask implements Callable<String>{

    private int taskId;

    public JpaDBTask(int taskId) {
        this.taskId = taskId;
    }
    
    @Override
    public String call() throws Exception {

        System.out.println("Task started for taskId: " + taskId);
       EntityManager em = EntityManagerHelper.getEntityManager();
       EntityManagerHelper.beginTransaction();
        //Simulate long time calc
        for (int i = 0; i < 10; i++) {
            TaskRecord record = new TaskRecord(
                    new Date(), taskId, Thread.currentThread().getId(),
                    Thread.currentThread().getName(), i);
            
            
            em.persist(record);
            
            System.out.println(">Task Id:" + taskId + " running by threadId: "+Thread.currentThread().getId());
            Thread.sleep(1000L);
        }
        EntityManagerHelper.commit();
        EntityManagerHelper.closeEntityManager();
        
        return "10 records added by Task: "+taskId;
    }
}
