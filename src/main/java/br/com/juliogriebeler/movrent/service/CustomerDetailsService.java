package br.com.juliogriebeler.movrent.service;

import br.com.juliogriebeler.movrent.controller.request.LoginRequest;
import br.com.juliogriebeler.movrent.controller.request.RegisterRequest;
import br.com.juliogriebeler.movrent.entity.Customer;
import br.com.juliogriebeler.movrent.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * @author Julio Griebeler
 */
@Component
public class CustomerDetailsService implements UserDetailsService {
    private final CustomerRepository customerRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);

    @Autowired
    public CustomerDetailsService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public ResponseEntity register(RegisterRequest registerRequest) {
        if (null == customerRepository.findByEmail(registerRequest.getEmail())) {
            Customer customer = new Customer();
            customer.setFirstName(registerRequest.getFirstName());
            customer.setLastName(registerRequest.getLastName());
            customer.setEmail(registerRequest.getEmail());
            customer.setPassword(new BCryptPasswordEncoder().encode(registerRequest.getPassword()));
            customer.setActive(true);
            customer.setRole(registerRequest.getRole());
            customer.setBirthDate(registerRequest.getBirthDate());
            return ResponseEntity.ok().body(this.customerRepository.save(customer));
        } else {
            return ResponseEntity.badRequest().body("Usuário já existente.");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = Optional.ofNullable(customerRepository.findByEmail(username)).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado."));
        return new org.springframework.security.core.userdetails.User(
                customer.getEmail(), customer.getPassword(), getAuthoritiesByCustomer(customer));
    }

    public ResponseEntity login(LoginRequest loginRequest) {
        try {
            UserDetails user = this.loadUserByUsername(loginRequest.getEmail());
            if (null != user)
                return ResponseEntity.ok().body("Logado com sucesso.");
            else
                throw new UsernameNotFoundException("Usuário não encontrado e/ou senha/email inválidos.");
        } catch (UsernameNotFoundException e) {
            LOGGER.error("Erro ao logar: " + e.getMessage());
            return new ResponseEntity(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

    private List<GrantedAuthority> getAuthoritiesByCustomer(Customer customer) {
        return AuthorityUtils.commaSeparatedStringToAuthorityList(customer.getRole().toString());
    }
}