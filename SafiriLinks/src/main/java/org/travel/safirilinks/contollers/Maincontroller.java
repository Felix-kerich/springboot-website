package org.travel.safirilinks.contollers;
import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.travel.safirilinks.Models.Matatu;
import org.travel.safirilinks.Repository.MatatuRepository;
import org.travel.safirilinks.serviceLayer.MatatuService;


import java.util.List;

@Controller
@RequestMapping
@AllArgsConstructor
public class Maincontroller {
    private  final MatatuService matatuService;
    private  final MatatuRepository matatuRepository;
      @PostMapping("/book")
        public String Booking(@RequestParam Long numberOfSeatsAvailable ){

        return "redirect:/book";
    }
    @GetMapping("/book")
    public String ShowBookingForm(){
        return "book";
    }
    @GetMapping("/dashboard")
    public String ShowDashboard(Model model){
        List<Matatu> availableMatatu = matatuService.getAllMatatus();
        model.addAttribute("availableMatatu",availableMatatu );
        return "dashboard";
    }



}
