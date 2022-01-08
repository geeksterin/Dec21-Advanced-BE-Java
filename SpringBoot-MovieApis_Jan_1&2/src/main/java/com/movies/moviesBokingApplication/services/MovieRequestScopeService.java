package com.movies.moviesBokingApplication.services;

import com.movies.moviesBokingApplication.model.Movie;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

@Service("movieRequestScopeService")
//@Scope(value = WebApplicationContext.SCOPE_REQUEST)
//@RequestScope
//@SessionScope
public class MovieRequestScopeService {
    public MovieRequestScopeService(){
        System.out.println("Object created ....");
    }

    public String getMovieByGenre(String genre){
        Movie m=new Movie();
        m.setMovieName("Avengers");
        m.setGenre("Thriller");
        m.setImdbRating(8);
        if(genre.equals(m.getGenre())){
            return m.getMovieName();
        }
        else{
            return "No movie found";
        }
    }
}
