package com.movies.moviesBokingApplication.controllers;

import com.movies.moviesBokingApplication.model.Movie;
import com.movies.moviesBokingApplication.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

@RestController
@RequestMapping("/threatre")
public class MovieControllers  {
    //dependency injection
    //Field Injection
    @Autowired
    MovieService movieServe;

    //MovieService movieServe;

    //Constructor Injection
   /*@Autowired(required = false)
    public MovieControllers (MovieService movieServ){
        this.movieServe=movieServ;
    }*/

    //Setter Injection
    /*@Autowired()
    public void setServiceBean(MovieService movieServe){
        this.movieServe=movieServe;
    }*/
    @GetMapping("/movies")
    public List<Movie> moviesList(){
        return movieServe.getMovieList();

    }
    @GetMapping ("/moviesName")
    public List<String> getMovieNames(){
        return movieServe.getMovieNames();
    }

    @PostMapping("/uploadMovie")
    public String uploadMovie(@RequestBody Movie movie){
        return movieServe.uploadMovie(movie);
    }


    //1.
    /*To Update the Movie Records based on movie name using request param where movie name will be expected
    endpoint : /updateMovie/{movieName}
    PUT Request
    RequestBody : required Update details either ibmd or genre or both & movie name;*/

    //2.
    //Create a delete API to delete a movie based on movie name
    //Get Request using query parameter
    //endpoint: /deleteMovie/{movieName}

    //3.
    /*Create a Get request to fetch list of movies available on a particular day
    taking date in request param using Prototype bean scope for success or failure message use property file*/


}
