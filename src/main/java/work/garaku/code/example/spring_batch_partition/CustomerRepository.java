package work.garaku.code.example.spring_batch_partition;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Mapper
@Transactional(readOnly = true)
public interface CustomerRepository {
  int takeTotalCount();
  List<Customer> findAll(@Param("limit") int dataSize, @Param("offset") int offset);
}
