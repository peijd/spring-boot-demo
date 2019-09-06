package com.ripjava.auth.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ripjava.auth.model.User;
import com.ripjava.auth.service.SecurityService;
import com.ripjava.auth.service.UserService;
import com.ripjava.auth.validator.UserValidator;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Controller
public class UserController {

    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private LocaleResolver localeFilter;

        @GetMapping("/registration")
        public String registration(Model model) {
            model.addAttribute("userForm", new User());

            return "registration";
        }

        @PostMapping("/registration")
        public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
            userValidator.validate(userForm, bindingResult);

            if (bindingResult.hasErrors()) {
                return "registration";
            }

            userService.save(userForm);

            securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());

            return "redirect:/welcome";
        }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {

        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    @GetMapping({"/", "/welcome"})
    public String welcome(Model model) {
        return "welcome";
    }
}
