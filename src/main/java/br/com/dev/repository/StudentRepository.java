package br.com.dev.repository;

import br.com.dev.model.Student;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by FelipeWendt on 13/09/17.
 */
public interface StudentRepository extends PagingAndSortingRepository<Student, Long> {
    List<Student> findByNameIgnoreCaseContaining(String name);
}
