package futureandexecutorservice.jpa.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author raitis
 */
@Entity
public class TaskRecord implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeRecord;
    private int taskId;
    private long threadId;
    private String threadName;
    private int taskSeqNum;

    public TaskRecord() {
    }

    public TaskRecord(Date timeRecord, int taskId, long threadId, String threadName, int taskSeqNum) {
        this.timeRecord = timeRecord;
        this.taskId = taskId;
        this.threadId = threadId;
        this.threadName = threadName;
        this.taskSeqNum = taskSeqNum;
    }

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public Date getTimeRecord() {
        return timeRecord;
    }

    public void setTimeRecord(Date timeRecord) {
        this.timeRecord = timeRecord;
    }

   

    public long getThreadId() {
        return threadId;
    }

    public void setThreadId(long threadId) {
        this.threadId = threadId;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public int getTaskSeqNum() {
        return taskSeqNum;
    }

    public void setTaskSeqNum(int taskSeqNum) {
        this.taskSeqNum = taskSeqNum;
    }

    @Override
    public String toString() {
        return "TaskRecord{" + "id=" + id + ", timeRecord=" + timeRecord + ", taskId=" + taskId + ", threadId=" + threadId + ", threadName=" + threadName + ", taskSeqNum=" + taskSeqNum + '}';
    }

    
    
    
}
