package fr.digi_hello.services;

import org.springframework.stereotype.Service;

@Service
public class HelloService {
    public String salutation(){
        return "Je suis la classe de service et je vous dis Bonjour";
    }
}
