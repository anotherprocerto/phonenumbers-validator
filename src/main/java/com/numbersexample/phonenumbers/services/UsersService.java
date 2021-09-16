package com.numbersexample.phonenumbers.services;

import com.numbersexample.phonenumbers.users.Users;
import org.springframework.stereotype.Service;
import com.numbersexample.phonenumbers.repository.UsersRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UsersService {

    private UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public List<Users> getUsersNumberByParams(String country, String state) {
        List<Users> users = usersRepository.findAll();

        if (country == null && state == null){
            for(Users user: users) {
                validateParams(user);
            }
            return users;
        }
        return filterUsersCountryState(users,country,state);
    }

    private List<Users> filterUsersCountryState(List<Users> users, String country, String state) {

        List<Users> filterUsers = new ArrayList<>();

        for(Users user: users) {
            validateParams(user);

            if (country == null || country == user.getCountry()) {
                if (state == null || state == user.getState()) {
                    filterUsers.add(user);
                }
            }
        }
        return filterUsers;
    }

    private void validateParams(Users user) {
        String phoneNumber = user.getPhone();
        String codeCountry = phoneNumber.substring(1, 4).trim();
        String country = validateCountry(codeCountry);

        user.setCountry(country);

        if (countryIsValid(country,phoneNumber)){
            user.setState("VALID");
        }
        else {
            user.setState("NOTVALID");
        }
    }

    private boolean countryIsValid(String country, String phoneNumber) {

        if (country != null && validateRegexCountry(country,phoneNumber)) {
            return true;
        }
        return false;
    }

    private boolean validateRegexCountry(String country, String phoneNumber) {
        String regex;
        switch(country) {
            case "CAMEROON":
                regex = "\\(237\\)\\ ?[2368]\\d{7,8}$";
                break;
            case "ETHIOPIA":
                regex = "\\(251\\)\\ ?[1-59]\\d{8}$";
                break;
            case "MAROCCO":
                regex = "\\(212\\)\\ ?[5-9]\\d{8}$\n";
                break;
            case "MOZAMBIQUE":
                regex = "\\(258\\)\\ ?[28]\\d{7,8}$";
                break;
            case "UGANDA":
                regex = "\\(256\\)\\ ?\\d{9}$";
                break;
            default:
                regex = null;
        }

        Pattern p = Pattern.compile(regex);

        if (regex == null) {
            return false;
        }

        Matcher m = p.matcher(phoneNumber);

        return m.matches();
    }

    private String validateCountry(String codeCountry) {

        String country;
        switch(codeCountry) {
            case "237":
                country = "CAMEROON";
                break;
            case "251":
                country = "ETHIOPIA";
                break;
            case "212":
                country = "MAROCCO";
                break;
            case "258":
                country = "MOZAMBIQUE";
                break;
            case "256":
                country = "UGANDA";
                break;
            default:
                country = null;
        }
        return country;
    }

    public String updateStrCodeCountry(int country) {

        String countrySTR;

        switch(country) {
            case 1:
                countrySTR = "CAMEROON";
                break;
            case 2:
                countrySTR = "ETHIOPIA";
                break;
            case 3:
                countrySTR = "MAROCCO";
                break;
            case 4:
                countrySTR = "MOZAMBIQUE";
                break;
            case 5:
                countrySTR = "UGANDA";
                break;
            default:
                countrySTR = null;
        }
     return countrySTR;
    }

    public String updateStrCodeState(int state) {

        String stateStr;

        if (state == 1){
            stateStr = "VALID";
        }
        else if (state == 2){
            stateStr = "NOTVALID";
        }
        else {
            stateStr = null;
        }
        return stateStr;
    }
}
