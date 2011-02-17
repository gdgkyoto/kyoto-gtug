package org.kyoto_gtug.savetheworld.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/help")
public class HelpController {

    @RequestMapping(method=RequestMethod.GET)
    public String welcome(Model model) {
        return "help";
    }
}
