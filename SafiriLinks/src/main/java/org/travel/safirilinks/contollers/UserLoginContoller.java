package org.travel.safirilinks.contollers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.travel.safirilinks.Models.UserLogin;
import org.travel.safirilinks.Repository.UserRepository;
import org.travel.safirilinks.serviceLayer.UsersService;

import java.util.Optional;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class UserLoginContoller {
    private final UsersService usersService;
    @Autowired
    private UserRepository userRepository;
    @GetMapping("/userLogin")
    public String showUserLogin(){
        return"userLogin";
    }
    @PostMapping("/userLogin")
    public String UserLogin(@RequestParam String username, @RequestParam String password, Model model) {
        Optional<UserLogin> userOptional = userRepository.findByUsername(username);

        if (userOptional.isPresent()) {
            UserLogin user = userOptional.get();
            // Directly compare passwords (not recommended for production)
            if (user.getPassword().equals(password)) {
                return "redirect:/dashboard";
            }
        }

        model.addAttribute("error", "Invalid username or password");
        return "userLogin";
    }


    @PostMapping("/Register")
    public String saveRegistrationForm(@ModelAttribute("userLogin")UserLogin userLogin, Model model){
        if (userRepository.existsByUsername(userLogin.getUsername())) {
            // Username already exists, add an error message to the model
            model.addAttribute("error", "Username already taken. Please choose a different one.");
            return "userRegistration";  // Return to the registration form
        }
        else {
            usersService.saveUser(userLogin);
            return "redirect:/userLogin";
        }
    }

    @GetMapping("/Register")
    public String ShowRegistrationForm(){
        return "userRegistration";
    }


}
