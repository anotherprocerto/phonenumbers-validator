package com.numbersexample.phonenumbers;


import com.numbersexample.phonenumbers.repository.UsersRepository;
import com.numbersexample.phonenumbers.services.UsersService;
import com.numbersexample.phonenumbers.users.Users;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static javafx.beans.binding.Bindings.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;


@SpringBootTest
public class ServiceTest {

    @Autowired
    private UsersService usersService;

    @MockBean
    private UsersRepository userRepository;

    @Test
    public void testGetCustomers_AllUsers(){
        List<Users> customersPhoneNumbers = usersService.getUsersNumberByParams(null, null);
        assertThat(customersPhoneNumbers.size(), is(41));
    }

    @Test
    public void testGetCustomers_AllUsersMorocco(){
        List<Users> customersPhoneNumbers = usersService.getUsersNumberByParams("1", null);
        assertThat(customersPhoneNumbers.size(), is(10));
    }

    @Test
    public void testGetCustomers_AllUsersEthiopiaValid(){
        List<Users> customersPhoneNumbers = usersService.getUsersNumberByParams("2", "1");
        assertThat(customersPhoneNumbers.size(), is(7));
    }

    @Test
    public void testGetCustomers_AllUsersEthiopiaNotValid(){
        List<Users> customersPhoneNumbers = usersService.getUsersNumberByParams("2", "2");
        assertThat(customersPhoneNumbers.size(), is(7));
    }

    @Test
    public void testGetCustomers_AllUsersMaroccoNotValid(){
        List<Users> customersPhoneNumbers = usersService.getUsersNumberByParams("3", "2");
        assertThat(customersPhoneNumbers.size(), is(7));
    }

    @Test
    public void testGetCustomers_AllUsersMozambiqueValid(){
        List<Users> customersPhoneNumbers = usersService.getUsersNumberByParams("4", "1");
        assertThat(customersPhoneNumbers.size(), is(6));
    }
}
