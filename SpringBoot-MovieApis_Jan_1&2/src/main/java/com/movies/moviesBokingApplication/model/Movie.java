package com.movies.moviesBokingApplication.model;

import org.springframework.stereotype.Component;

@Component("modelComponent")
public class Movie {
    private String movieName;
    private Integer imdbRating;
    private String genre;
    //Add date field

    public Integer getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(Integer imdbRating) {
        this.imdbRating = imdbRating;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }



    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }




}
