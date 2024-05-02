package com.projects.formare;

import com.projects.formare.model.Persoana;
import com.projects.formare.repository1.PersoanaRepository;
import com.projects.formare.services.CursServices;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class FormareApplication {

    public static void main(String[] args) {
        SpringApplication.run(FormareApplication.class, args);
    }

//	@Bean
//	CommandLineRunner commandLineRunner(PersoanaRepository persoanaRepository, CursServices cursServices){
//		return args -> {
//                List<Persoana> lista=cursServices.getAllWithoutAnyHQL();
//            for (Persoana p :lista){
//                System.out.println(p.toString());
//            }
//		};
//	}
}
