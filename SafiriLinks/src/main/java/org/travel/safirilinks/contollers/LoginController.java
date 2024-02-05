package org.travel.safirilinks.contollers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.travel.safirilinks.Models.UserLogin;
import org.travel.safirilinks.Models.loginModel;
import org.travel.safirilinks.Repository.AdminRepository;
import org.travel.safirilinks.serviceLayer.UsersService;


import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping()
public class LoginController {

    private final UsersService usersService;
    @Autowired
    private AdminRepository adminRepository;

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        return "login";
    }



    @PostMapping("/processLogin")
    public String processLogin(@RequestParam String username, @RequestParam String password, Model model) {
        Optional<loginModel> userOptional = adminRepository.findByUsername(username);

        if (userOptional.isPresent()) {
            loginModel user = userOptional.get();
            // Directly compare passwords (not recommended for production)
            if (user.getPassword().equals(password)) {
                return "redirect:/admin/addMatatu";
            }
        }

        model.addAttribute("error", "Invalid username or password");
        return "login";
    }
}
