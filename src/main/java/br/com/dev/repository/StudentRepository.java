package br.com.dev.repository;

import br.com.dev.model.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by FelipeWendt on 13/09/17.
 */
public interface StudentRepository extends CrudRepository<Student, Long> {
    List<Student> findByNameIgnoreCaseContaining(String name);
}
