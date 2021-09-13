package com.numbersexample.phonenumbers.users;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {

    Users findById(int id);
    List<Users> findAll();
}
