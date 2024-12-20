package fr.digi_hello.controleurs;

import fr.digi_hello.services.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/hello")
public class HelloControleur {
    @Autowired
    HelloService helloService;

    @GetMapping
    public String sayHello() {
        return helloService.salutation();
    }
}
