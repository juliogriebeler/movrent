package br.com.juliogriebeler.movrent.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * @author Julio Griebeler
 */
@Entity
@Table(name = "movie")
public class Movie extends AbstractEntity {

    @NotNull
    @Column(name = "title")
    private String title;
    @NotNull
    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, targetEntity = Director.class)
    private Director director;

    public Movie() {
    }

    public Movie(@NotNull String title, @NotNull Director director) {
        this.title = title;
        this.director = director;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Movie)) return false;
        Movie movie = (Movie) o;
        return getId() == movie.getId() &&
                Objects.equals(getTitle(), movie.getTitle()) &&
                Objects.equals(getDirector(), movie.getDirector());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getDirector());
    }
}
