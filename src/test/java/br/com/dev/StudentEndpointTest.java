package br.com.dev;

import br.com.dev.model.Student;
import br.com.dev.repository.StudentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class StudentEndpointTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @MockBean
    private StudentRepository studentRepository;

    @Autowired
    private MockMvc mockMvc;

    @TestConfiguration
    static class config {

        @Bean
        public RestTemplateBuilder restTemplateBuilder(){
            return new RestTemplateBuilder().basicAuthorization("toyo", "devdojo");
        }
    }

    @Test
    public void listStudentsWhenUsernameAndPasswordAreIncorrectShouldReturnStatusCode401(){
        System.out.println(port);
        restTemplate = restTemplate.withBasicAuth("1", "1");
        ResponseEntity<String> response = restTemplate.getForEntity("/v1/protected/students/", String.class);
        assertThat(response.getStatusCodeValue()).isEqualTo(401);
    }

    @Test
    public void getStudentsByIdWhenUsernameAndPasswordAreIncorrectShouldReturnStatusCode401(){
        System.out.println(port);
        restTemplate = restTemplate.withBasicAuth("1", "1");
        ResponseEntity<String> response = restTemplate.getForEntity("/v1/protected/students/1", String.class);
        assertThat(response.getStatusCodeValue()).isEqualTo(401);
    }

    @Test
    public void listStudentsWhenUsernameAndPasswordAreCorrectShouldReturnStatusCode200(){
        List<Student> studentList = asList(new Student(1L, "Legolas", "legolas@lotr.com"),
                new Student(2L, "Aragorn", "Aragorn@lotr.com"));
        BDDMockito.when(studentRepository.findAll()).thenReturn(studentList);
        ResponseEntity<String> response = restTemplate.getForEntity("/v1/protected/students/", String.class);
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    public void getStudentsByIdWhenUsernameAndPasswordAreCorrectShouldReturnStatusCode200(){
        Student student = new Student(1L, "Legolas", "legolas@lotr.com");
        BDDMockito.when(studentRepository.findOne(student.getId())).thenReturn(student);
        ResponseEntity<String> response = restTemplate.getForEntity("/v1/protected/students/{id}", String.class, student.getId());
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    public void getStudentsByIdWhenUsernameAndStudentDoNotExistPasswordAreCorrectShouldReturnStatusCode404(){
        ResponseEntity<String> response = restTemplate.getForEntity("/v1/protected/students/{id}", String.class, -1);
        assertThat(response.getStatusCodeValue()).isEqualTo(404);
    }
}
