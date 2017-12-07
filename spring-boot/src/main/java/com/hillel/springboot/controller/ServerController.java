package com.hillel.springboot.controller;

import com.hillel.springboot.dao.ServerDao;
import com.hillel.springboot.model.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Locale;

/**
 * Created by eugen on 11/21/17.
 */
@Controller
@RequestMapping("server")
public class ServerController {

    @Autowired
    private ServerDao serverDao ;

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "", method = RequestMethod.GET)
    //@GetMapping("get")
    public String index(Model model, Locale locale) {
        model.addAttribute("name", messageSource.getMessage("ivan",null,locale));
        model.addAttribute("servers",serverDao.findAll());

        return "index";
    }

    @RequestMapping(value = "/{server_id}", method = RequestMethod.GET)
    public ModelAndView getServer(@PathVariable("server_id") Integer id) {
        ModelAndView maw = new ModelAndView();
        maw.setViewName("server");
        maw.addObject("server", serverDao.findOne(id));
        return maw;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String getAddServerPage(Model model) {
        model.addAttribute("server", new Server());
        return "addServer";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String addServer(@ModelAttribute @Valid Server server, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "addServer";
        }
        serverDao.save(server);
        return "redirect:/server/";
    }


}
