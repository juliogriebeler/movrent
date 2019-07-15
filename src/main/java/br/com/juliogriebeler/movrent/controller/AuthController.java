package br.com.juliogriebeler.movrent.controller;

import br.com.juliogriebeler.movrent.controller.request.LoginRequest;
import br.com.juliogriebeler.movrent.controller.request.RegisterRequest;
import br.com.juliogriebeler.movrent.entity.Customer;
import br.com.juliogriebeler.movrent.repository.CustomerRepository;
import br.com.juliogriebeler.movrent.service.CustomerDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    /*
        @PostMapping("/logout")
        public ResponseEntity logout() {
            UserDetails userDetails = this.movRentUserDetailsService.loadUserByUsername(loginRequest.getUsername());
            if(userDetails.getPassword().equals(new BCryptPasswordEncoder().encode(loginRequest.getPassword()))) {
                return ResponseEntity.ok().body("Logado com sucesso");
            }
            return new ResponseEntity("Não autorizado", HttpStatus.UNAUTHORIZED);
        }
    */
    @PostMapping("/register")
    public ResponseEntity register(@Valid @RequestBody RegisterRequest registerRequest) {
        Customer customer = new Customer();
        customer.setFirstName(registerRequest.getFirstName());
        customer.setLastName(registerRequest.getLastName());
        customer.setUsername(registerRequest.getUsername());
        customer.setPassword(new BCryptPasswordEncoder().encode(registerRequest.getPassword()));
        customer.setActive(true);
        customer.setRole(registerRequest.getRole());
        customer.setBirthDate(registerRequest.getBirthDate());
        UserDetails userDetails = this.customerRepository.save(customer);
        if (null != userDetails) {
            return ResponseEntity.ok().body(userDetails);
        }
        return new ResponseEntity("Erro ao registrar", HttpStatus.UNAUTHORIZED);
    }

}
