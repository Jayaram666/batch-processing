package Hello.world;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class HWJobExecutionListner implements JobExecutionListener {


    @Override
    public void beforeJob(JobExecution jobExecution) {
     System.out.println("Before executing the job ............");
     System.out.println("The job name is "+jobExecution.getJobInstance().getJobName());
     jobExecution.getExecutionContext().put("name", "Jayaram");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        System.out.println("After executing the job ............");
        System.out.println("The job execution context details are  "+jobExecution.getExecutionContext());

    }
}
