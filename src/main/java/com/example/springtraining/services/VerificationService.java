package com.example.springtraining.services;


import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VerificationService {
    ArrayList<String> matricules = new ArrayList<String>(List.of("A650110", "A650115","A650120"));

    public boolean isStoled(String matricule){
        for(String mat:matricules){
            if(matricule == mat){
                return true;
            }
        }
        return false;
    }


}
