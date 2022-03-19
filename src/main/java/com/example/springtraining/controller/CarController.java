package com.example.springtraining.controller;

import com.example.springtraining.entities.Car;
import com.example.springtraining.services.VerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/car")
public class CarController {

        @Autowired
        VerificationService verficationService;

        @PostMapping
        public boolean addCar(@RequestBody Car car){

                if(verficationService.isStoled(car.getMatricule())){
                        return false;
                }else{
                        return true;
                }
        }

}