package br.com.juliogriebeler.movrent.controller;

import br.com.juliogriebeler.movrent.controller.request.RentRequest;
import br.com.juliogriebeler.movrent.controller.request.RentUpdateRequest;
import br.com.juliogriebeler.movrent.entity.Rent;
import br.com.juliogriebeler.movrent.repository.RentRepository;
import br.com.juliogriebeler.movrent.service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Julio Griebeler
 */
@RestController
@RequestMapping("/api/rents")
public class RentController {

    private RentRepository rentRepository;
    private RentService rentService;

    @Autowired
    RentController(RentRepository rentRepository, RentService rentService) {
        this.rentRepository = rentRepository;
        this.rentService = rentService;
    }

    @GetMapping
    public ResponseEntity findAll() {
        return ResponseEntity.ok().body(this.rentRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@Valid @PathVariable Long id) {
        return ResponseEntity.ok().body(this.rentRepository.findById(id));
    }

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody RentRequest rentRequest) {
        return this.rentService.save(rentRequest);
    }

    @PutMapping
    public ResponseEntity update(@Valid @RequestBody RentUpdateRequest rentUpdateRequest) {
        return this.rentService.update(rentUpdateRequest);
    }

}
