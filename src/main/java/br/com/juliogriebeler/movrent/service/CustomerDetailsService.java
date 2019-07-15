package br.com.juliogriebeler.movrent.service;

import br.com.juliogriebeler.movrent.controller.request.LoginRequest;
import br.com.juliogriebeler.movrent.entity.Customer;
import br.com.juliogriebeler.movrent.entity.Role;
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = Optional.ofNullable(customerRepository.findByUsername(username)).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado."));
        return new org.springframework.security.core.userdetails.User(
                customer.getUsername(), customer.getPassword(), getAuthoritiesByRole(customer));
    }

    public ResponseEntity login(LoginRequest loginRequest) {
        try {
            UserDetails userDetails = loadUserByUsername(loginRequest.getUsername());
            if (userDetails.getPassword().equals(new BCryptPasswordEncoder().encode(loginRequest.getPassword()))) {
                return ResponseEntity.ok().body("Logado com sucesso");
            }
            throw new UsernameNotFoundException("Usuario e/ou senha invalido(s).");
        } catch (UsernameNotFoundException e) {
            LOGGER.error("Erro ao logar: " + e.getMessage());
            return new ResponseEntity("Usuario e/ou senha invalido(s).", HttpStatus.UNAUTHORIZED);
        }
    }

    private List<GrantedAuthority> getAuthoritiesByRole(Customer customer) {
        if (customer.getRole().equals(Role.ADMIN))
            return AuthorityUtils.createAuthorityList("ROLE_USER", "ROLE_ADMIN");
        return AuthorityUtils.createAuthorityList("ROLE_USER");
    }
}
