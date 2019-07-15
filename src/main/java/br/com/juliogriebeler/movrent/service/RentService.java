package br.com.juliogriebeler.movrent.service;

import br.com.juliogriebeler.movrent.controller.request.RentRequest;
import br.com.juliogriebeler.movrent.controller.request.RentUpdateRequest;
import br.com.juliogriebeler.movrent.entity.Rent;
import br.com.juliogriebeler.movrent.repository.CustomerRepository;
import br.com.juliogriebeler.movrent.repository.MovieItemRepository;
import br.com.juliogriebeler.movrent.repository.MovieRepository;
import br.com.juliogriebeler.movrent.repository.RentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Calendar;

/**
 * @author Julio Griebeler
 */
@Service
public class RentService {

    private RentRepository rentRepository;
    private CustomerRepository customerRepository;
    private MovieRepository movieRepository;
    private MovieItemRepository movieItemRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(RentService.class);

    @Autowired
    public RentService(RentRepository rentRepository, CustomerRepository customerRepository, MovieRepository movieRepository, MovieItemRepository movieItemRepository) {
        this.rentRepository = rentRepository;
        this.customerRepository = customerRepository;
        this.movieRepository = movieRepository;
        this.movieItemRepository = movieItemRepository;
    }

    public ResponseEntity save(RentRequest rentRequest) {
        try {
            Rent rent = new Rent();
            rent.setDaysQuantity(rentRequest.getDaysQuantity());
            rent.setCustomer(this.customerRepository.getOne(rentRequest.getCustomerId()));
            rent.setMovies(this.movieItemRepository.findAllByIdIsIn(rentRequest.getMovieIds()));
            rent.setReturned(false);
            rent.setLastUpdate(Calendar.getInstance().getTime());
            rent.setPullOutDate(Calendar.getInstance().getTime());
            return ResponseEntity.ok().body(this.rentRepository.save(rent));
        } catch (Exception e) {
            LOGGER.error("Erro ao salvar locação " + rentRequest + " : " + e.getMessage());
            return ResponseEntity.badRequest().body("Erro ao salvar locação " + rentRequest);
        }
    }

    public ResponseEntity update(RentUpdateRequest rentUpdateRequest) {
        try {
            Rent rent = this.rentRepository.getOne(rentUpdateRequest.getId());
            rent.setReturned(rentUpdateRequest.isReturned());
            rent.setLastUpdate(Calendar.getInstance().getTime());
            return ResponseEntity.ok().body(this.rentRepository.save(rent));
        } catch (Exception e) {
            LOGGER.error("Erro ao atualizar locação " + rentUpdateRequest + " : " + e.getMessage());
            return ResponseEntity.badRequest().body("Erro ao atualizar locação " + rentUpdateRequest);
        }
    }
}