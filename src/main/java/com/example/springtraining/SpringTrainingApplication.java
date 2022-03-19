package com.example.springtraining;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.config.EnableHypermediaSupport;


/**
 * <h1>Hi folks</h1>
 * The ReflectionApiTutorial program implements an application that
 * make use of JAVA reflection API to implement an elegant PATCH methode in spring boot
 * note that this application follow best practices thus a bit of complexity
 * but keep focused on classes discussed on the Attached topic
 *
 * <p>I used the <strong>@</strong></p>
 * @author  El batouri badr-eddine
 * @author
 * @version 1.0
 * @since   2022-03-1
 */
@SpringBootApplication
//@Configuration
//@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL_FORMS)
public class SpringTrainingApplication {

    /**
     * This is the main method which bootsrap our springboot application.
     * @param args Unused.
     * @return Nothing.
     */
    public static void main(String[] args) {
        SpringApplication.run(SpringTrainingApplication.class, args);
    }

}
