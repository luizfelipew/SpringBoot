package br.com.dev.javaclient;

import br.com.dev.handler.RestResponseExceptionHandler;
import br.com.dev.model.PagebleResponse;
import br.com.dev.model.Student;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class JavaClientDAO {
    private RestTemplate restTemplate = new RestTemplateBuilder()
            .rootUri("http://localhost:8080/v1/protected/students")
            .basicAuthorization("toyo", "devdojo")
            .errorHandler(new RestResponseExceptionHandler())
            .build();
    private RestTemplate restTemplateAdmin = new RestTemplateBuilder()
            .rootUri("http://localhost:8080/v1/admin/students")
            .basicAuthorization("toyo", "devdojo")
            .errorHandler(new RestResponseExceptionHandler())
            .build();


    public Student findbyId(long id){
        return restTemplate.getForObject("/{id}", Student.class, id);
//        ResponseEntity<Student> forEntity = restTemplate.getForEntity("/{id}", Student.class, 1);

    }

    public List<Student> listAll(){
        ResponseEntity<PagebleResponse<Student>> exchange = restTemplate.exchange("/", HttpMethod.GET, null,
                new ParameterizedTypeReference<PagebleResponse<Student>>() {
                });

//        Student studentPostForObject = restTemplateAdmin.postForObject("/", studentPost, Student.class);
//        ResponseEntity<Student> studentPostForEntity = restTemplateAdmin.postForEntity("/", studentPost, Student.class);

        return exchange.getBody().getContent();
    }

    public Student save(Student student){
        ResponseEntity<Student> exchangePost = restTemplateAdmin.exchange("/",
                HttpMethod.POST, new HttpEntity<>(student, createJSONHeader()), Student.class);
        return exchangePost.getBody();
    }

    public void update(Student student){
        restTemplateAdmin.put("/", student);
    }

    public void delete(long id){
        restTemplateAdmin.delete("/{id}",id);
    }

    private static HttpHeaders createJSONHeader(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
