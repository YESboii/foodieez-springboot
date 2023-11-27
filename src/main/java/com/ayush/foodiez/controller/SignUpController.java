package com.ayush.foodiez.controller;
import com.ayush.foodiez.dto.UserSignUpDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SignUpController {

    @RequestMapping("/signup")
    public String returnSignUpPage(Model model){
        model.addAttribute("userSignUpDto",new UserSignUpDto());
        return "signup";
    }

    @PostMapping("/signup")
    public String signUp(@Valid @ModelAttribute UserSignUpDto userSignUpDto, BindingResult bindingResult){
        System.out.println(userSignUpDto);
//        System.out.println(rePass);
        if(bindingResult.hasErrors()){
            return "signup";
            }
        return "login";
        }
    }

