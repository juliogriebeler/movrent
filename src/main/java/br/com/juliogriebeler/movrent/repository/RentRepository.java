package br.com.juliogriebeler.movrent.repository;

import br.com.juliogriebeler.movrent.entity.Rent;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Julio Griebeler
 */
public interface RentRepository extends JpaRepository<Rent, Long> {
}
