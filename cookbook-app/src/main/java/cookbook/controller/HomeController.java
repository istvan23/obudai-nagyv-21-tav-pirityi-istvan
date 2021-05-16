package cookbook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;

@Controller
public class HomeController {

    @GetMapping({"/", "/login"})
    public String getIndex(Model model){
        return "login";
    }
}
