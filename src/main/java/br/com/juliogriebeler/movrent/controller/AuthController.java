package br.com.juliogriebeler.movrent.controller;

import br.com.juliogriebeler.movrent.controller.request.LoginRequest;
import br.com.juliogriebeler.movrent.controller.request.RegisterRequest;
import br.com.juliogriebeler.movrent.entity.Customer;
import br.com.juliogriebeler.movrent.repository.CustomerRepository;
import br.com.juliogriebeler.movrent.service.CustomerDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthController {

    private CustomerDetailsService customerDetailsService;
    private CustomerRepository customerRepository;

    @Autowired
    public AuthController(CustomerDetailsService customerDetailsService, CustomerRepository customerRepository) {
        this.customerDetailsService = customerDetailsService;
        this.customerRepository = customerRepository;
    }

    @PostMapping("/login")
    public ResponseEntity login(@Valid @RequestBody LoginRequest loginRequest) {
        return this.customerDetailsService.login(loginRequest);
    }

    @PostMapping("/register")
    //@PreAuthorize("hasRole('ADMIN') or hasRole('APPLICATION')")
    public ResponseEntity register(@Valid @RequestBody RegisterRequest registerRequest) {
        return this.customerDetailsService.register(registerRequest);
    }

}
