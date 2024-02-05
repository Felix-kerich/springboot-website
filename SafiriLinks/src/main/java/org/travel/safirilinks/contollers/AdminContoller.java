package org.travel.safirilinks.contollers;



import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.travel.safirilinks.Models.Matatu;
import org.travel.safirilinks.serviceLayer.MatatuService;

import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminContoller {


    private final MatatuService matatuService;
              @GetMapping("/addMatatu")
        public String showAddMatatuForm(Model model) {
            model.addAttribute("matatu", new Matatu());
              List<Matatu> availableMatatu = matatuService.getAllMatatus();
              model.addAttribute("availableMatatu",availableMatatu );
            return "addMatatu";
        }

//    @GetMapping("/dashboard")
//    publidjango-testc String ShowDashboard(Model model){
//        List<Matatu> availableMatatu = matatuService.getAllMatatus();
//        model.addAttribute("availableMatatu",availableMatatu );
//        return "dashboard";
//    }


    @PostMapping("/addMatatu")
        public String processAddMatatuForm(@ModelAttribute("matatu") Matatu matatu) {
           matatuService.saveMatatu(matatu);

           return "redirect:/admin/addMatatu";
        }

    @PostMapping("/deleteMatatu")
    public String deleteMatatu(@RequestParam Long matatuId) {
        matatuService.deleteMatatu(matatuId);
        return "redirect:/admin/addMatatu"; // Redirect to the admin dashboard or any other page
    }


}
