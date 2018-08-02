package top.zhkumanage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("auto")
public class AutoController {

    @RequestMapping
    public String onAuto(){
        return "auto";
    }

}
