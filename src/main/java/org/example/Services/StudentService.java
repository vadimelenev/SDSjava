package org.example.Services;

import org.example.Dto.StudentDto;
import org.example.Entity.Student;
import org.example.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(String id) {
        return studentRepository.findById(id);
    }

    public Student createStudent(StudentDto studentDto) {
        Student student = new Student();
        student.setSurname(studentDto.getSurname());
        student.setName(studentDto.getName());
        student.setPatronymic(studentDto.getPatronymic());
        student.setGroup(studentDto.getGroup());
        student.setAverageGrade(studentDto.getAverageGrade());
        return studentRepository.save(student);
    }

    public Student updateStudent(String id, StudentDto studentDto) {
        return studentRepository.findById(id)
                .map(student -> {
                    student.setSurname(studentDto.getSurname());
                    student.setName(studentDto.getName());
                    student.setPatronymic(studentDto.getPatronymic());
                    student.setGroup(studentDto.getGroup());
                    student.setAverageGrade(studentDto.getAverageGrade());
                    return studentRepository.save(student);
                }).orElseThrow(() -> new RuntimeException("Student not found"));
    }

    public void deleteStudent(String id) {
        studentRepository.deleteById(id);
    }
}
