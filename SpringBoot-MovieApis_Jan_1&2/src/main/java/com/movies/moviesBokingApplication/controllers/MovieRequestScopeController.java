package com.movies.moviesBokingApplication.controllers;

import com.movies.moviesBokingApplication.services.MovieRequestScopeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class MovieRequestScopeController {
    @Autowired
    WebApplicationContext context;

    @GetMapping("/getMovie")
    public void getMovieByGenre(HttpServletResponse response) throws IOException {
       //asking for a bean from IOC container
        MovieRequestScopeService scope=context.getBean("movieRequestScopeService", MovieRequestScopeService.class);
        MovieRequestScopeService scope1=context.getBean("movieRequestScopeService", MovieRequestScopeService.class);

        System.out.println(scope +" "+scope1);

        String movieName=scope.getMovieByGenre("Thriller");
        response.getWriter().write(movieName);
    }
}
