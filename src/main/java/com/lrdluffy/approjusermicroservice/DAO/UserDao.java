package com.lrdluffy.approjusermicroservice.DAO;

import com.lrdluffy.approjusermicroservice.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User,Long> {

    /**
     * Finds a user by username and password.
     */
    Optional<User> findByUserNameAndPassword(String userName, String password);


    /**
     * Finds a user by username.
     */
    Optional<User> findByUserName(String userName);

}
