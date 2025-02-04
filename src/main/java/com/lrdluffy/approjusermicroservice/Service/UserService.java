package com.lrdluffy.approjusermicroservice.Service;

import com.lrdluffy.approjusermicroservice.DAO.UserDao;
import com.lrdluffy.approjusermicroservice.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }


    /**
     * Retrieves a user by username and password.
     * Throws an exception if the user is not found.
     */
    public User getUserByUserNameAndPassword(String userName, String password) {
        return userDao.findByUserNameAndPassword(userName, password).orElseThrow(
                () -> {
                    throw new IllegalStateException("User Not Found!!!");
                }
        );
    }


    /**
     * Saves a new user if the username is unique.
     * Throws an exception if the username already exists.
     */
    public void saveUser(String userName, String password) {
        if (userDao.findByUserName(userName).isPresent()) {
            throw new IllegalStateException("User with this username already exists!");
        }
        userDao.save(new User(userName, password));
    }


    /**
     * Updates user preferences if the user exists.
     * Throws an exception if the user is not found.
     */
    public void updateUser(String userName, String password, Integer minReleaseYear
            , Integer minDuration, String preferredGenre, Double minImdbRating,
                           Integer minMetaScore, Long minVotesNumber, String preferredDirector,
                           String preferredCast) {

        User userToUpdate = userDao.findByUserNameAndPassword(userName, password)
                .orElseThrow(() -> new IllegalStateException("User not found!"));

        // Update user preferences
        userToUpdate.setMinReleaseYear(minReleaseYear);
        userToUpdate.setMinDuration(minDuration);
        userToUpdate.setPreferredGenre(preferredGenre);
        userToUpdate.setMinImdbRating(minImdbRating);
        userToUpdate.setMinMetaScore(minMetaScore);
        userToUpdate.setMinVotesNumber(minVotesNumber);
        userToUpdate.setPreferredDirector(preferredDirector);
        userToUpdate.setPreferredCast(preferredCast);

        userDao.save(userToUpdate);
    }
}
