package ma.rightman.openapidemo.services;

import ma.rightman.openapidemo.entity.StudentEntity;
import ma.rightman.openapidemo.model.Student;
import ma.rightman.openapidemo.model.StudentCreateObject;
import ma.rightman.openapidemo.repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class StudentsServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    ModelMapper modelMapper = new ModelMapper();

    @Override
    public ResponseEntity<List<Student>> listStudents() {

        List<Student> students =  modelMapper.map(studentRepository.findAll(), new TypeToken<List<Student>>() {}.getType());
        return ResponseEntity.ok(students);
    }

    @Override
    public ResponseEntity<Student> showStudentsById(Integer studentId) {
        Student student = modelMapper.map(studentRepository.getById(studentId), Student.class);
        return ResponseEntity.ok(student);
    }

    @Override
    public ResponseEntity<Void> createStudents(StudentCreateObject studentCreateObject) {

        if((studentCreateObject.getTag() != null && !studentCreateObject.getTag().isEmpty())
        && (studentCreateObject.getName() != null && !studentCreateObject.getName().isEmpty())
        && (studentCreateObject.getAge() != null && !studentCreateObject.getAge().equals(0))){
            StudentEntity studentEntity = modelMapper.map(studentCreateObject, StudentEntity.class);
            StudentEntity s = studentRepository.save(studentEntity);
            return new ResponseEntity<Void>(HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Void> deleteStudentsById(Integer studentId) {
        if(studentRepository.existsById(studentId)){
            studentRepository.deleteById(studentId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Void> modifyStudents(Student student) {
        if(studentRepository.existsById(student.getId())){
            StudentEntity studentEntity = studentRepository.findById(student.getId()).get();
            studentEntity.setAge(student.getAge());
            studentEntity.setName(student.getName());
            studentEntity.setTag(student.getTag());
            studentRepository.save(studentEntity);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
