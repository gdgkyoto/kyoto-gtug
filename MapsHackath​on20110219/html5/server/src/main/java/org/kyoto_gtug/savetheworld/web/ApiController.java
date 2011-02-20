package org.kyoto_gtug.savetheworld.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kyoto_gtug.savetheworld.dao.HelpDao;
import org.kyoto_gtug.savetheworld.domain.Help;
import org.kyoto_gtug.savetheworld.ws.HelperManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/*")
public class ApiController {
    
	private static Logger logger = LoggerFactory.getLogger(ApiController.class);
    private static Map<String, Object> OK;
    private static Map<String, Object> NG;
    
    static {
        OK = new HashMap<String, Object>();
        OK.put("result", "OK");
        NG = new HashMap<String, Object>();
        NG.put("result", "NG");
    }
    
    @Autowired
    private HelpDao helpDao;

    @RequestMapping(value="helps", method=RequestMethod.GET)
    @ResponseBody
    public List<Help> getRecentHelps(Model model) {
        List<Help> helps = helpDao.getRecentHelps();
        for (Help help : helps) {
            help.escapeHtml();
        }
        return helps;
    }

    @RequestMapping(value="help", method=RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> postHelp(Help help) {
        try {
            if (help.getDate() == null) {
                help.setDate(new Date().getTime());
            }
            helpDao.save(help);
            Help savedHelp = helpDao.getById(help.getId());
            HelperManager.getInstance().notifyHelp(savedHelp);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return OK;
    }

    @RequestMapping(value="accept", method=RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> acceptHelp(Long id) {
    	logger.info("Help " + id + " was accepted.");
        return OK;
    }

}
