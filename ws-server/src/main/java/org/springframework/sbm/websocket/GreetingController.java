package org.springframework.sbm.websocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.util.HtmlUtils;

@Controller
public class GreetingController {

  @MessageMapping("/scan")
  @SendTo("/topic/applicableRecipes")
  public String greeting(String message) throws Exception {
    Thread.sleep(1000); // simulated delay
    System.out.println("scanning " + message);
    return "applicableRceipes: " + "some list of recipes"; //new Greeting("Hello, " + HtmlUtils.htmlEscape(message) + "!");
  }

  @SubscribeMapping("/topic/hello")
  public String sendOneTimeMessage() {
    return "server one-time message via the application";
  }

}