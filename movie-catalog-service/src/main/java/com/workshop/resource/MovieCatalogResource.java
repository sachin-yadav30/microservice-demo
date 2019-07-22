package com.workshop.resource;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.workshop.model.CatalogItem;
import com.workshop.model.Movie;
import com.workshop.model.Rating;
import com.workshop.model.UserRating;
import com.workshop.services.MovieInfo;
import com.workshop.services.UserDataRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {


    @Autowired
    RestTemplate restTemplate;

    @Autowired
    MovieInfo movieInfo;

    @Autowired
    UserDataRating userDataRating;

    @RequestMapping("/{userId}")
    //@HystrixCommand(fallbackMethod = "getFallbackCatalog")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){


     UserRating userRating=userDataRating.getUserRating(userId);

        return  userRating.getRatingList().stream().map(rating ->{
                    return movieInfo.getCatalogItem(rating);
        }

        ).collect(Collectors.toList());
        //For each movie Id , call movie info servie and get data

        //put them together
//
//        return Collections.singletonList(
//                new CatalogItem("GOT","testing4",5)
//        );
    }

    public List<CatalogItem> getFallbackCatalog(@PathVariable("userId") String userId){
        return Arrays.asList(new CatalogItem("No Moviee","",0));
    }



}
