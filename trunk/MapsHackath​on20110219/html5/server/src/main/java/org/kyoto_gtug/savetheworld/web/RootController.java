package org.kyoto_gtug.savetheworld.web;

import java.util.Date;
import java.util.List;

import org.kyoto_gtug.savetheworld.dao.HelpDao;
import org.kyoto_gtug.savetheworld.domain.Help;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class RootController {

    @Autowired
    private HelpDao helpDao;
    
    @RequestMapping(method=RequestMethod.GET)
    public String welcome(Model model) {
        Date today = new Date();
        model.addAttribute("today", today);
        model.addAttribute("hello", "Hello world");
        
        List<Help> helps = helpDao.getRecentHelps();
        model.addAttribute("helps", helps);
        
        return "main";
    }
}
