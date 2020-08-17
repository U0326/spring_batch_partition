package work.garaku.code.example.spring_batch_partition;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class SamplePartitioner implements Partitioner {
  @Autowired
  private CustomerRepository repository;

  @Override
  public Map<String, ExecutionContext> partition(int gridSize) {
    int totalCount = repository.takeTotalCount();
    int limit = (totalCount / gridSize) + 1;
    int offset = 0;
    Map<String, ExecutionContext> map = new HashMap<>();

    for (int i = 0; i < gridSize; i++) {
      ExecutionContext context = new ExecutionContext();
      context.put("limit", limit);
      context.put("offset", offset);
      map.put(String.valueOf(i), context);
      offset += limit;
    }
    return map;
  }
}
