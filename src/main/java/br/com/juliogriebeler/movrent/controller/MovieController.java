package br.com.juliogriebeler.movrent.controller;

import br.com.juliogriebeler.movrent.controller.request.MovieRequest;
import br.com.juliogriebeler.movrent.repository.MovieItemRepository;
import br.com.juliogriebeler.movrent.repository.MovieRepository;
import br.com.juliogriebeler.movrent.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Julio Griebeler
 */
@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private MovieRepository movieRepository;
    private MovieItemRepository movieItemRepository;

    private MovieService movieService;

    @Autowired
    MovieController(MovieRepository movieRepository, MovieItemRepository movieItemRepository, MovieService movieService) {
        this.movieRepository = movieRepository;
        this.movieItemRepository = movieItemRepository;
        this.movieService = movieService;
    }

    @GetMapping
    public ResponseEntity findAll() {
        return new ResponseEntity(this.movieRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/available")
    public ResponseEntity findAvailable() {
        return new ResponseEntity(this.movieItemRepository.findDistinctAndAndAvailable(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity findById(@PathVariable Long id) {
        return this.movieRepository.findById(id)
                .map(m -> ResponseEntity.ok().body(m))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(path = {"findByTitle/{title}"})
    public ResponseEntity findByTitle(@PathVariable String title) {
        return ResponseEntity.ok().body(movieRepository.findByTitleLike(title));
    }

    @PostMapping
    public ResponseEntity create(@RequestBody MovieRequest movieRequest) {
        return this.movieService.addMovie(movieRequest);
    }

}
