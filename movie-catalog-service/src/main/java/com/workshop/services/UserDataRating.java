package com.workshop.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.workshop.model.Rating;
import com.workshop.model.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class UserDataRating {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getFallbackCatalogRating")
    public UserRating getUserRating(@PathVariable("userId") String userId){
        return restTemplate.getForObject("http://ratings-data-service/ratingsdata/users/"+userId,UserRating.class);
    }

    public UserRating getFallbackCatalogRating(@PathVariable("userId") String userId){
        UserRating userRating=new UserRating();
        userRating.setRatingList(Arrays.asList(new Rating("GOT",10)));
        return userRating;
    }
}
