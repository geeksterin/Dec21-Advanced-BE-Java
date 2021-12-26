package in.geekster.dec21advancebe.simplerestapp.controllers.greet;

import in.geekster.dec21advancebe.simplerestapp.models.GreetMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "greetmessages")
@Slf4j
public class GreetController {


    @GetMapping
    public List<GreetMessage> greet() {
        log.info("Coming to Greet");
        final GreetMessage message1 = new GreetMessage();
        message1.setCode("EN");
        message1.setMessage("Hey");

        final GreetMessage message2 = new GreetMessage();
        message2.setCode("FR");
        message2.setMessage("Bonjour");

        final List<GreetMessage> greetMessages = new ArrayList<>();
        greetMessages.add(message1);
        greetMessages.add(message2);

        log.info("All greet messages: {}", greetMessages);
        return greetMessages;
    }


}
