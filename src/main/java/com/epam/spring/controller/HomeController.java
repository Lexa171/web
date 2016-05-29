package com.epam.spring.controller;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

import javax.mail.MessagingException;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.epam.spring.service.UserService;


@Controller
public class HomeController extends GlobalExceptionController {
    @Autowired
    UserService userService;

    @RequestMapping(value = {"/",""}, method = RequestMethod.GET)
    public String news () {
        return "welcome";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        return "login";
    }

    @RequestMapping(value = "/my/{name}", method = RequestMethod.GET)
    public String getMyPage(@PathVariable("name") String name, Model model) {
        return "redirect:/users/" + userService.getUserByName(name).getUserId();
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(Model model) {
        return "redirect:/login";
    }

    @RequestMapping(value = "/error")
    public ModelAndView error(@ModelAttribute("errCode") String errCode, @ModelAttribute("errMsg") String errMsg,
                              @ModelAttribute("errStackTrace") String errStackTrace) {
        ModelAndView model = new ModelAndView("error");
        model.addObject("errCode", errCode);
        model.addObject("errMsg", errMsg);
        model.addObject("errStackTrace", errStackTrace);
        return model;
    }
}
