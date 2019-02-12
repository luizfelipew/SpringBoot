package br.com.dev;


import br.com.dev.model.Student;
import br.com.dev.repository.StudentRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Rule
    public ExpectedException thrown = ExpectedException.none();


    @Test
    public void createShouldPersistData(){
        Student student = new Student("Luiz Felipe", "teste@email.com");
        this.studentRepository.save(student);
        assertThat(student.getId()).isNotNull();
        assertThat(student.getName()).isEqualTo("Luiz Felipe");
        assertThat(student.getEmail()).isEqualTo("teste@email.com");
    }


    @Test
    public void deleteShouldRemoveData(){
        Student student = new Student("Luiz Felipe", "teste@email.com");
        this.studentRepository.save(student);
        studentRepository.delete(student);
        assertThat(studentRepository.findOne(student.getId())).isNull();
    }

    @Test
    public void updateShouldChangeAndPersistData(){
        Student student = new Student("Luiz Felipe", "teste@email.com");
        this.studentRepository.save(student);
        student.setName("Luiz Felipe222");
        student.setEmail("teste@email.com222");
//        student = this.studentRepository.save(student);
        this.studentRepository.save(student);
        student = this.studentRepository.findOne(student.getId());
        assertThat(student.getName()).isEqualTo("Luiz Felipe222");
        assertThat(student.getEmail()).isEqualTo("teste@email.com222");
    }

    @Test
    public void findByNameIgnoreCaseContainingShouldIgnoreCase(){
        Student student = new Student("LuizFelipe", "teste@email.com");
        Student student2 = new Student("luizfelipe", "teste222@email.com");
        this.studentRepository.save(student);
        this.studentRepository.save(student2);
        List<Student> studentList = studentRepository.findByNameIgnoreCaseContaining("luizfelipe");
        assertThat(studentList.size()).isEqualTo(2);

    }

    @Test
    public void createWhenNameIsNullShouldThrowConstraintViolationException() {
        thrown.expect(ConstraintViolationException.class);
        thrown.expectMessage("O campo nome do estudante é obrigatório");
        this.studentRepository.save(new Student());
    }

    @Test
    public void createWhenEmailIsNullShouldThrowConstraintViolationException() {
        thrown.expect(ConstraintViolationException.class);
        Student student = new Student();
        student.setName("Luiz Felipe");
        this.studentRepository.save(student);
    }

    @Test
    public void createWhenEmailIsNotValidShouldThrowConstraintViolationException() {
        thrown.expect(ConstraintViolationException.class);
        thrown.expectMessage("Digite um email válido");
        Student student = new Student();
        student.setName("Luiz Felipe");
        student.setEmail("Luiz Felipe");
        this.studentRepository.save(student);
    }


}
