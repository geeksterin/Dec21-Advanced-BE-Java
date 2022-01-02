package com.movies.moviesBokingApplication.services;

import com.movies.moviesBokingApplication.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.annotation.RequestScope;

import java.util.ArrayList;
import java.util.List;

@Service("movieService")
public class MovieService {
    @Autowired
    @Qualifier("modelComponent") // Top avoid ambiguity while creating bean
    Movie movie; ///asking object of Movie from IOC container

    public List<Movie> getMovieList(){
        List<Movie> movies=new ArrayList<Movie>();
        movie.setMovieName("Spider Home Coming");
        movie.setImdbRating(6);
        movie.setGenre("Thriller");
        movies.add(movie);
        return movies;
    }

    public  List<String> getMovieNames(){
        List<String> moviesName=new ArrayList<>();
        Movie m= new Movie();
        m.setMovieName("Spider man");
        Movie m1= new Movie();
        m1.setMovieName("Avengers");
        moviesName.add(m.getMovieName());
        moviesName.add(m1.getMovieName());
        return moviesName;
    }

    public String uploadMovie(Movie m){
        String dataSaved="";
        if(m.getMovieName()!=null && m.getGenre()!=null && m.getImdbRating()!=null){
            dataSaved="Movie saved successfully";
        }
        else
            dataSaved="Movie could not be saved!";
        return dataSaved;
    }
}
