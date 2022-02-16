package com.example.springtraining.controller;

import com.example.springtraining.model.StudentDTO;
import com.example.springtraining.studentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.*;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.beans.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


@RestController("/students")
public class StudentController {


    @Autowired
    studentsService studentService;

    @GetMapping()
    HttpEntity<Collection<StudentDTO>> showAll() {
        return new HttpEntity<>(studentService.getAllStudents());
    }


    @GetMapping(value = "/{student}")
    HttpEntity<EntityModel<StudentDTO>> show(@PathVariable int student) throws IntrospectionException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        StudentDTO studentObject = studentService.getStudent(student);
        Link findOnelink = linkTo(StudentController.class).slash(studentObject.getStudentID()).withSelfRel();
        return new HttpEntity<>(EntityModel.of(studentObject, findOnelink.andAffordance(afford(methodOn(StudentController.class).updatePartially(student, null)))));
    }


    @PatchMapping(value = "/{student}")
    HttpEntity<StudentDTO> updatePartially(@PathVariable int student, @RequestBody StudentDTO body) throws IntrospectionException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        StudentDTO studentObject = studentService.getStudent(student);
        for (PropertyDescriptor pd : Introspector.getBeanInfo(StudentDTO.class, RepresentationModel.class).getPropertyDescriptors()) {
            if (pd.getReadMethod() != null && !"class".equals(pd.getName())) {
                Method getterMethod = pd.getReadMethod();
                Method setterMethod = pd.getWriteMethod();
                if (getterMethod.invoke(body) != null) {
                    setterMethod.invoke(studentObject, getterMethod.invoke(body));
                }
            }
            ;
        }
        return new HttpEntity<>(studentObject);
    }


//    @PostMapping(value = "/{student}")
//    HttpEntity<StudentDTO> updatePartially(@RequestBody StudentDTO body, @PathVariable int student){
//
//        return
//    }


//    @PatchMapping(value = "/{student}")
//    HttpEntity<StudentDTO> updatePartially(@PathVariable int studentID, @RequestBody StudentDTO body) throws IntrospectionException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {
//
//        //we start by getting the studentObject we want to update.
//        StudentDTO studentObject = studentService.getStudent(studentID);
//        //we use the PropretyDescriptor, now the proprety descriptor will hold informations about each field in our StudentDTO class thus being able to call getters/setters methode on StudentDTO instances in runtime without knowing them.
//        for (PropertyDescriptor pd : Introspector.getBeanInfo(StudentDTO.class, RepresentationModel.class).getPropertyDescriptors()) {
//        //we hold a reference to the getter/setter Methods for the current field, keep in mind we should use the conventional method naming on our StudentDTO class in order for the propertyDescriptor to detect setters/getters (for me, I use LOMBOK to do so otherwise you can write them manually).
//            Method getterMethod = pd.getReadMethod();
//            Method setterMethod = pd.getWriteMethod();
//            //we check if the method exists and if it's a method that is a part of our class
//
//            if (getterMethod != null && !"class".equals(pd.getName())) {
//                //now we query if the current field is null on our request body
//                if (getterMethod.invoke(body) != null) {
//                    //if the field is not null on our request body, we invoke the getter on the body (request body) to get its value, and we invoke the setter on the instance of StudentDTO that we want to change
//                    setterMethod.invoke(studentObject, getterMethod.invoke(body));
//                }
//            }
//        //then we persist our modified student into our database.
//        }
//        //we return the modified object to ensure the client that the object have been modified
//        return new HttpEntity<>(studentObject);
//    }


}