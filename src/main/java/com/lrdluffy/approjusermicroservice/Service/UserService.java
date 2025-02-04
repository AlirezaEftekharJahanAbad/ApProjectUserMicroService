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


    public User getUserByUserNameAndPassword(String userName, String password) {
        return userDao.findByUserNameAndPassword(userName, password).orElseThrow(
                () -> {
                    throw new IllegalStateException("User Not Found!!!");
                }
        );
    }

    public void saveUser(String userName, String password) {
        Optional<User> userOptional = userDao.findByUserName(userName);

        if (userOptional.isPresent()) {
            throw new IllegalStateException("User with this Username alreay exists!!!");
        } else {
            userDao.save(new User(userName, password));
        }
    }

    public void updateUser(String userName, String password, Integer minReleaseYear
            , Integer minDuration, String preferredGenre, Double minImdbRating,
                           Integer minMetaScore, Long minVotesNumber, String preferredDirector,
                           String preferredCast) {

        Optional<User> userOptional = userDao.findByUserNameAndPassword(userName, password);

        User userToUpdate = userOptional.get();

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
