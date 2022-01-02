package com.movies.moviesBokingApplication.services;

import com.movies.moviesBokingApplication.model.Movie;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("prototype")
public class MovieScopeServices {

    public MovieScopeServices(){
        System.out.println("Service obj created");
    }

    public String getMoviesByIMDB(Integer rating){
        Movie m=new Movie();
        m.setMovieName("Avengers");
        m.setGenre("Thriller");
        m.setImdbRating(8);
        if(rating>=m.getImdbRating()){
            return m.getMovieName();
        }
        else{
            return "No movie found";
        }
    }
}
