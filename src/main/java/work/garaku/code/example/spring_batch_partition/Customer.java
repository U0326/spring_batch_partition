package work.garaku.code.example.spring_batch_partition;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Customer {
  private int id;
  private String name;
}
