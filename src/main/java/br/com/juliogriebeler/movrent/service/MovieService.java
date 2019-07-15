package br.com.juliogriebeler.movrent.service;

import br.com.juliogriebeler.movrent.controller.request.MovieRequest;
import br.com.juliogriebeler.movrent.entity.Director;
import br.com.juliogriebeler.movrent.entity.Movie;
import br.com.juliogriebeler.movrent.entity.MovieItem;
import br.com.juliogriebeler.movrent.repository.DirectorRepository;
import br.com.juliogriebeler.movrent.repository.MovieItemRepository;
import br.com.juliogriebeler.movrent.repository.MovieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Julio Griebeler
 */
@Service
public class MovieService {

    private DirectorRepository directorRepository;
    private MovieRepository movieRepository;
    private MovieItemRepository movieItemRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(MovieService.class);

    @Autowired
    public MovieService(DirectorRepository directorRepository, MovieRepository movieRepository, MovieItemRepository movieItemRepository) {
        this.directorRepository = directorRepository;
        this.movieRepository = movieRepository;
        this.movieItemRepository = movieItemRepository;
    }

    @Transactional
    public ResponseEntity addMovie(MovieRequest movieRequest) {
        try {
            MovieItem movieItem = new MovieItem(getOrCreateMovie(movieRequest.getMovieTitle(), getOrCreateDirector(movieRequest.getMovieDirector())));
            this.movieItemRepository.saveAndFlush(movieItem);
            return ResponseEntity.ok().body(movieItem);
        } catch (Exception e) {
            LOGGER.error("Erro ao salvar filme " + movieRequest + ": " + e.getMessage());
            return ResponseEntity.badRequest().body("Erro ao salvar filme " + movieRequest);
        }
    }

    private Director getOrCreateDirector(String directorName) {
        Director director = this.directorRepository.findDirectorByName(directorName);
        if (null == director)
            director = this.directorRepository.save(new Director(directorName));
        return director;
    }

    private Movie getOrCreateMovie(String movieTitle, Director director) {
        Movie movie = this.movieRepository.findByTitleEqualsAndAndDirector_Name(movieTitle, director.getName());
        if (null == movie)
            movie = new Movie(movieTitle, director);
        return movie;
    }
}