package org.example.payticketcinema.controller;

import lombok.RequiredArgsConstructor;
import org.example.payticketcinema.model.dto.UserForLk;
import org.example.payticketcinema.model.entity.Ticket;
import org.example.payticketcinema.model.entity.User;
import org.example.payticketcinema.service.TicketService;
import org.example.payticketcinema.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final TicketService ticketService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

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
        user.setPassword(password);

        ticketService.saveTicket(ticket, user);
        userService.saveUser(user);


        model.addAttribute("message", "вы успешно зарегистрировались");
        return "register";

    }

//    @PostMapping(value = "/login")
//    public String loginPost(
//            @RequestParam String username,
//            @RequestParam String password,
//            Model model
//    ) {
//
//        User user = new User();
//        user.setUsername(username);
//        user.setPassword(password);
//
//        boolean isLogin = userService.loginUser(user);
//
//        if (!isLogin) {
//            model.addAttribute("message", "логин или пароль не верны");
//            return "login";
//        }
//        return "redirect:/lk?username=" + username;
//    }

    @GetMapping("/lk")
    public String lk(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        UserForLk userForLk = userService.getUserByUsername(username);
        model.addAttribute("userForLk", userForLk);
        return "menu";
    }


    @GetMapping("/lk/my-ticket")
    public String myTicket(@RequestParam(name = "username") String username, Model model) {
        UserForLk userForLk = userService.getUserByUsername(username);
        model.addAttribute("userForLk", userForLk);
        return "my-ticket";
    }
}