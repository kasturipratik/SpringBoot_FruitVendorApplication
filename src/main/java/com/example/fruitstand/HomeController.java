package com.example.fruitstand;

import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    DaysRepository daysRepository;

    @Autowired
    CloudinaryConfig cloudc;

    @Autowired
    FruitsRepository fruitsRepository;

/*main page that customer can view*/
    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("days", daysRepository.findAll());

        return "index";
    }
/*log in processing*/
    @RequestMapping("/login")
    public String login(Model model){
        model.addAttribute("invalid","");
        return "login";
    }

    @PostMapping("/processLogin")
    public String processLogin(HttpServletRequest request, Model model){
        String admin = request.getParameter("adminName");
        String passcode = request.getParameter("password");
        if(admin.equals("admin") && passcode.equals("password"))
        {
            model.addAttribute("fruitList", fruitsRepository.findAll());
            return "admin";
        }
        else {
            model.addAttribute("invalid", "Invalid log in informaion. Try Again");
            return "login";
        }
    }
 /*adding and posting fruit*/
    @RequestMapping("/addFruits")
    public String addFruits(Model model){
        model.addAttribute("fruit", new Fruits());
        model.addAttribute("days", daysRepository.findAll());
        return "addFruits";
    }

    @PostMapping("/processFruits")
    public String processFruits(@ModelAttribute Fruits fruit, @RequestParam("file")MultipartFile file,Model model) {
        if (file.isEmpty()) {
            return "redirect:/addFruits";
        }
        try {

            Map uploadResult = cloudc.upload(file.getBytes(),
                    ObjectUtils.asMap("resourcetype", "auto"));

            fruit.setImage(uploadResult.get("url").toString());
            fruitsRepository.save(fruit);


        }catch(IOException e){
        e.printStackTrace();
        return "redirect:/addFruits";
    }
        return "redirect:/admin";
    }
/*admin page*/
    @RequestMapping("/admin")
    public String admin(Model model){
        model.addAttribute("fruitList", fruitsRepository.findAll());
        return "admin";
    }
/*update the fruit list*/
    @RequestMapping("/update/{id}")
    public String updateFruits(@PathVariable("id") long id, Model model){
        model.addAttribute("days", daysRepository.findAll());
        model.addAttribute("fruit", fruitsRepository.findById(id));
        return "addFruits";

    }
 /*delete fruit list*/
 @RequestMapping("/delete/{id}")
 public String deleteFruit(@PathVariable("id") long id,Model model){
     fruitsRepository.deleteById(id);
     model.addAttribute("fruitList", fruitsRepository.findAll());
     return "/admin";
 }



    @RequestMapping("/show/{id}")
    public String showList(@PathVariable("id") long id, Model model){
        model.addAttribute("list", fruitsRepository.findByDay_Id(id));
        return "showDetails";
    }

    /*update time for open and close*/
    @RequestMapping("/updateTime")
    public String updateTime( Model model){
        model.addAttribute("updateTimes", daysRepository.findAll());
        return "updateTime";
    }

    @RequestMapping("/display/{id}")
    public String showListIndex(@PathVariable("id") long id, Model model){

        model.addAttribute("listIndex", daysRepository.findById(id));
        return "updateForm";
    }
    @PostMapping("/processUpdate")
    public String processUpdate(@ModelAttribute Days days){
        daysRepository.save(days);
        return "admin";
    }
}
