package org.example.payticketcinema.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.payticketcinema.model.dto.UserAuthDto;
import org.example.payticketcinema.model.dto.UserForLkDto;
import org.example.payticketcinema.model.entity.Ticket;
import org.example.payticketcinema.model.entity.User;
import org.example.payticketcinema.service.TicketService;
import org.example.payticketcinema.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final TicketService ticketService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping(value = "/login")
    public String login (Model model) {
//
//        if (error != null) {
//            model.addAttribute("message","ваш аккаунт не найден или забанен админом");
//        }

        model.addAttribute("user", new UserAuthDto());
        return "login";
    }


//    @PostMapping(value = "/login")
//    public String loginPost(
//            @Valid  @ModelAttribute UserAuthDto userAuthDto,
//            BindingResult bindingResult,
//            Model model
//    ) {
//
//        if (bindingResult.hasErrors()){
//            model.addAttribute("user", new UserAuthDto());
//            return "login";
//        }
//
//        return "redirect:/process-login";
//    }

    @GetMapping(value = "/register")
    public String register() {
        return "register";
    }

    @PostMapping(value = "/register")
    public String registerPost(
            @RequestParam String name,
            @RequestParam String phone,
            @RequestParam String city,
            @RequestParam String password,
            @RequestParam String confirm_password,
            Model model
    ) {
        Ticket ticket = new Ticket();
        User user = new User();
        user.setCity(city);
        user.setUsername(name);
        user.setPhone(phone);
        if (!password.equals(confirm_password)) {
            model.addAttribute("message", "пароли не совпадают");
            return "register";
        }
        user.setPassword(passwordEncoder.encode(password));


        ticketService.saveTicket(ticket, user);
        userService.saveUser(user);


        model.addAttribute("message", "вы успешно зарегистрировались");
        return "register";

    }

    @GetMapping("/lk")
    public String lk(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        UserForLkDto userForLkDto = userService.getUserByUsername(username);
        model.addAttribute("userForLk", userForLkDto);
        return "menu";
    }


    @GetMapping("/lk/my-ticket")
    public String myTicket(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        UserForLkDto userForLkDto = userService.getUserByUsername(username);
        model.addAttribute("userForLk", userForLkDto);
        return "my-ticket";
    }
}