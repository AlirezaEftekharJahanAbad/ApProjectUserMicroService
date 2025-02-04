package com.lrdluffy.approjusermicroservice.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String userName;
    private String password;
    private Integer minReleaseYear;
    private Integer minDuration;
    private String preferredGenre;
    private Double minImdbRating;
    private Integer minMetaScore;
    private Long minVotesNumber;
    private String preferredDirector;
    private String preferredCast;

    public User(String userName,String password){
        this.userName=userName;
        this.password=password;
    }

}
