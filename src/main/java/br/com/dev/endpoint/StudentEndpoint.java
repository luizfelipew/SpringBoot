package br.com.dev.endpoint;


import br.com.dev.error.ResourceNotFoundException;
import br.com.dev.model.Student;
import br.com.dev.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * Created by FelipeWendt on 07/09/17.
 */
@RestController
@RequestMapping("v1")
public class StudentEndpoint {
    //private final DateUtil dateUtil;

    private final StudentRepository studentDAO;

    @Autowired
    public StudentEndpoint(StudentRepository studentDAO) {
        this.studentDAO = studentDAO;
    }

    @GetMapping(path = "admin/students")
    public ResponseEntity<?> listAll(Pageable pageable) {
        return new ResponseEntity<>(studentDAO.findAll(pageable), HttpStatus.OK);

    }

    @GetMapping(path = "protected/students/{id}")
    public ResponseEntity<?> getStudentsbyId(@PathVariable("id") Long id, Authentication authentication) {

        System.out.println(authentication);
        verifyStudentExists(id);
        Student student = studentDAO.findOne(id);
        return new ResponseEntity<>(student, HttpStatus.OK);

    }

    @GetMapping(path = "protected/students/findByName/{name}")
    public ResponseEntity<?> findStudentsByName(@PathVariable String name){
        return new ResponseEntity<>(studentDAO.findByNameIgnoreCaseContaining(name), HttpStatus.OK);
    }

    @PostMapping(path = "admin/students")
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> save(@Valid @RequestBody Student student) {

        return new ResponseEntity<>(studentDAO.save(student), HttpStatus.CREATED);
    }


    @DeleteMapping(path = "admin/students/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        verifyStudentExists(id);
        studentDAO.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(path = "admin/students")
    public ResponseEntity<?> update(@RequestBody Student student) {
        verifyStudentExists(student.getId());
        studentDAO.save(student);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void verifyStudentExists(Long id){
        if (studentDAO.findOne(id) == null)
            throw new ResourceNotFoundException("Student not found for ID: " + id);

    }
}
