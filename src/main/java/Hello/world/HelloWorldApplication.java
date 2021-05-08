package Hello.world;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableBatchProcessing
public class HelloWorldApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloWorldApplication.class, args);
		System.out.println("HEllo jayaram how are you ..........");
	}
	@Autowired(required=true)
	private JobBuilderFactory jobs;
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	@Autowired
	private HWJobExecutionListner hwJobExecutionListner;
	@Autowired
	private HWStepExecutionListner hwStepExecutionListner;

	public Step step1(){

		return stepBuilderFactory.get("step1").
				tasklet(helloWorldtasklet()).
				listener(hwStepExecutionListner).
				build();
	}

	@Bean
	public Job job() {
		return jobs
				.get("taskletsJob")
				.listener(hwJobExecutionListner)
				.start(step1())
				.build();
	}
	public Tasklet helloWorldtasklet(){

		return (new Tasklet() {
			@Override
			public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
				System.out.println("Hello world ..............");
				return RepeatStatus.FINISHED;
			}
		});

	}
}