package fr.digi_hello.controleurs;

import fr.digi_hello.services.HelloService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloControleur {
    HelloService helloService = new HelloService();

    @GetMapping
    public String sayHello() {
        return helloService.salutation();
    }
}
