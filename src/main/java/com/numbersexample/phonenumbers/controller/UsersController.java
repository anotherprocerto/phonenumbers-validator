package com.numbersexample.phonenumbers.controller;

import com.numbersexample.phonenumbers.repository.UsersRepository;
import com.numbersexample.phonenumbers.services.UsersService;
import com.numbersexample.phonenumbers.users.Users;
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

    @GetMapping("/phoneNumbers/all")
    public String getPhoneNumbersFiltersAll(Model model) {

        List<Users> phoneNumbers = usersService.getUsersNumberByParams(null, null);
        model.addAttribute("phoneNumbers",phoneNumbers);

        return "phoneNumbers";
    }

    @GetMapping("/phoneNumbers/{country}/{state}")
    public String getPhoneNumbersFilters(Model model,@PathVariable(value = "country") int country, @PathVariable(value = "state") int state) {

        String countrySTR = usersService.updateStrCodeCountry(country);
        String stateSTR = usersService.updateStrCodeState(state);

        List<Users> phoneNumbers = usersService.getUsersNumberByParams(countrySTR, stateSTR);

        model.addAttribute("phoneNumbers",phoneNumbers);

        return "phoneNumbers";
    }

}
