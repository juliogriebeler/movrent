package br.com.juliogriebeler.movrent.service;

import br.com.juliogriebeler.movrent.controller.request.CustomerRequest;
import br.com.juliogriebeler.movrent.entity.Customer;
import br.com.juliogriebeler.movrent.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author Julio Griebeler
 */
@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public ResponseEntity save(CustomerRequest customerRequest) {
        try {
            Customer customer = new Customer();
            return ResponseEntity.ok().body(this.customerRepository.save(customer));
        } catch (Exception e) {
            LOGGER.error("Erro ao salvar cliente " + customerRequest + " : " + e.getMessage());
            return ResponseEntity.badRequest().body("Erro ao salvar cliente " + customerRequest);
        }
    }

    public ResponseEntity update(CustomerRequest customerRequest) {
        try {
            Customer customer = new Customer();
            // this.customerRepository.getOne(customerRequest.get);
            return ResponseEntity.ok().body(this.customerRepository.save(customer));
        } catch (Exception e) {
            LOGGER.error("Erro ao atualizar cliente " + customerRequest + " : " + e.getMessage());
            return ResponseEntity.badRequest().body("Erro ao atualizar cliente " + customerRequest);
        }
    }
}