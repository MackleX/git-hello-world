package com.example.springtraining;


import com.example.springtraining.model.StudentDTO;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Service
@Scope("singleton")
public class studentsService {

        private List<StudentDTO> students = new ArrayList<>();

        studentsService()
        {


                StudentDTO studentOne = new StudentDTO(1L, "badr", "INPT",true);
                StudentDTO studentTwo = new StudentDTO(2L, "badr", "INPT",true);
                StudentDTO studentThree = new StudentDTO(3L, "badr", "INPT",true);


                this.students.add(studentOne);
                this.students.add(studentTwo);
                this.students.add(studentThree);


        }

        public Collection<StudentDTO> getAllStudents() {
            return students;
        }


        public StudentDTO getStudent(int id) {
            return students.get(id);
        }
}


