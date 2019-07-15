package br.com.juliogriebeler.movrent.repository;

import br.com.juliogriebeler.movrent.entity.Movie;
import br.com.juliogriebeler.movrent.entity.MovieItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Julio Griebeler
 */
public interface MovieItemRepository extends JpaRepository<MovieItem, Long> {

    @Query("SELECT DISTINCT movie FROM MovieItem WHERE available=1")
    List<Movie> findDistinctAndAndAvailable();

    List<MovieItem> findAllByIdIsIn(List<Long> ids);

}
