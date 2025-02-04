package com.lrdluffy.approjusermicroservice.Api;


import com.lrdluffy.approjusermicroservice.Model.User;
import com.lrdluffy.approjusermicroservice.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserApi {

    private final UserService userService;

    @Autowired
    public UserApi(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "getUser")
    public User getUser(@RequestParam(required = true) String userName, @RequestParam(required = true) String password) {
        return userService.getUserByUserNameAndPassword(userName, password);
    }


    @PostMapping(path = "saveUser")
    public void saveUser(@RequestParam(required = true) String userName, @RequestParam(required = true) String password) {
        userService.saveUser(userName, password);
    }


    @PostMapping(path = "updateUser")
    public void updateUser(@RequestParam(required = true) String userName,
            @RequestParam(required = true) String password,
            @RequestParam(required = false) Integer releaseYear,
            @RequestParam(required = false) Integer duration,
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) Double rating,
            @RequestParam(required = false) Integer metaScore,
            @RequestParam(required = false) Integer votesNumber,
            @RequestParam(required = false) String directorName,
            @RequestParam(required = false) String cast) {

        releaseYear=(releaseYear==null)?0:releaseYear;
        duration=(duration==null)?0:duration;
        genre=(genre==null || genre.isBlank())?null:genre;
        rating=(rating==null)?0.0:rating;
        metaScore=(metaScore==null)?0:metaScore;
        votesNumber=(votesNumber==null)?0:votesNumber;
        directorName=(directorName==null || directorName.isBlank())?null:directorName;
        cast=(cast==null || cast.isBlank())?null:cast;

        userService.updateUser(userName,password,releaseYear,duration,genre,rating,metaScore, Long.valueOf(votesNumber),directorName,cast);

    }
}
