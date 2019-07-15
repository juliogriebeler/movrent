package br.com.juliogriebeler.movrent.controller.request;

import javax.validation.constraints.NotNull;

/**
 * @author Julio Griebeler
 */
public class MovieRequest {
    @NotNull
    private String movieTitle;
    @NotNull
    private String movieDirector;

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getMovieDirector() {
        return movieDirector;
    }

    public void setMovieDirector(String movieDirector) {
        this.movieDirector = movieDirector;
    }
}
