package fr.digi.digi_spring_j21.controleurs;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloControleur {

    @GetMapping
    public String sayHello() {
        return "Hello";
    }
}
