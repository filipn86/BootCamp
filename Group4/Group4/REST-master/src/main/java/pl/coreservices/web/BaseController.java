package pl.coreservices.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/base")
public class BaseController {
    @RequestMapping(method = RequestMethod.GET, value = "/sayHello")
    public String sayHello(@RequestParam(value = "name") String name) {
        return "HELLO "+name;
    }
}
