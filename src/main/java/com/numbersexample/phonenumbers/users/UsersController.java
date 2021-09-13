package com.numbersexample.phonenumbers.users;

import com.numbersexample.phonenumbers.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UsersController {

    @Autowired
    private UsersRepository users_repo;
    @Autowired
    private UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    // Home Page
    @GetMapping("/")
    public String welcome() {
        //return "<html><body>"
        //       + "<h1>WELCOME TO PHONE NUMBER VALIDATION</h1>"
        //       + "</body></html>";
        return "index";
    }

    // Get All Notes
    @GetMapping("/users")
    public List<Users> getAllNotes() {
        return users_repo.findAll();
    }

    // Get the users details by ID
    @GetMapping("/users/{id}")
    public Users getCompanyById(
            @PathVariable(value = "id") int id) {
        return users_repo.findById(id);
    }

    @GetMapping("/phoneNumbers")
    public String getPhoneNumbersFilters(Model model) {
        List<Users> phoneNumbers = usersService.getUsersNumberByParams("UGANDA", "VALID");
        model.addAttribute("phoneNumbers",phoneNumbers);
        //model.addAttribute("name", "testing");

        return "phoneNumbers";
    }

}
