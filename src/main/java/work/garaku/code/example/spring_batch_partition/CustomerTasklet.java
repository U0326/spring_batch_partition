package work.garaku.code.example.spring_batch_partition;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class CustomerTasklet implements Tasklet {
  @Autowired private CustomerRepository repository;

  @Override
  public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext)
      throws Exception {
    log.debug("tasklet started.");
    Map<String, Object> context = chunkContext.getStepContext().getStepExecutionContext();
    List<Customer> customers =
        repository.findAll((int) context.get("limit"), (int) context.get("offset"));
    customers.forEach(System.out::println);
    return RepeatStatus.FINISHED;
  }
}
