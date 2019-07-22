package com.workshop.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.workshop.model.CatalogItem;
import com.workshop.model.Movie;
import com.workshop.model.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieInfo {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getFallbackCatalogRating")
    public CatalogItem getCatalogItem(Rating rating){
        Movie movie=restTemplate.getForObject("http://movie-info-service/movies/"+rating.getMovieId(), Movie.class);
        return new CatalogItem(movie.getName(),"testing4",rating.getRating());
    }

    public CatalogItem getFallbackCatalogRating(Rating rating){
        return new CatalogItem("Test1","Test",rating.getRating());
    }
}
