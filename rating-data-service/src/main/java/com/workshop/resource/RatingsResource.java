package com.workshop.resource;

import com.workshop.exception.CustomException;
import com.workshop.model.Rating;
import com.workshop.model.UserRating;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ratingsdata")
public class RatingsResource {

    @RequestMapping("{movieId}")
    public Rating getRating(@PathVariable("movieId") String movieId )throws CustomException{
        Rating rating;
        if(movieId.equalsIgnoreCase("sachin")){
            throw  new CustomException("testing exception");
        }else{
            rating=new Rating(movieId,4);
        }

        return rating;
    }


    @RequestMapping("users/{userId}")
    public UserRating getUserRating(@PathVariable("userId") String userId){
        List<Rating> ratingList=Arrays.asList(
                new Rating("1234",4),
                new Rating("5678",3)
        );
        UserRating userRating=new UserRating();
        userRating.setRatingList(ratingList);
        return userRating;
    }


}
