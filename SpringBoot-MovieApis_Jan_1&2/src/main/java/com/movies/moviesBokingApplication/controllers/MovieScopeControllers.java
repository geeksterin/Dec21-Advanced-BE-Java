package com.movies.moviesBokingApplication.controllers;

import com.movies.moviesBokingApplication.services.MovieScopeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieScopeControllers {
    @Value("${api.success.message}")
    String successMessage;
    @Value("${api.failure.message}")
    String failureMessage;

    //method injection
   @Lookup
    public MovieScopeServices getMovieScopeServices(){
        //dynamically create bean
        return null;
    }
    @GetMapping("/getMovie/{rating}")
    public String getMovieByRating(@PathVariable Integer rating){
       try{
           System.out.println(successMessage);
           return getMovieScopeServices().getMoviesByIMDB(rating);
       }
       catch (Exception e){
           System.out.println(failureMessage);
           e.printStackTrace();
       }
       return getMovieScopeServices().getMoviesByIMDB(rating);
   }
}
