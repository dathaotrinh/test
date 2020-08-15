package com.admin.jira.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.admin.jira.entity.User;

@Repository
//@CrossOrigin("http://localhost:4200")
public interface UserRepo extends JpaRepository<User, Integer> {

}
