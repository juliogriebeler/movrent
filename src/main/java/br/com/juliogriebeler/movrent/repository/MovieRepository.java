package br.com.juliogriebeler.movrent.repository;

import br.com.juliogriebeler.movrent.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Julio Griebeler
 */
public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findByTitleLike(String title);

    Movie findByTitleEqualsAndAndDirector_Name(String title, String directorName);

    List<Movie> findAllByIdIsIn(List<Long> ids);
}
