package br.com.juliogriebeler.movrent.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author Julio Griebeler
 */
@Entity
@Table(name = "movie_item")
public class MovieItem extends AbstractEntity {

    @Column(name = "available")
    private boolean available;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = Movie.class)
    private Movie movie;

    public MovieItem() {
    }

    public MovieItem(@NotNull Movie movie) {
        this.available = true;
        this.movie = movie;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
