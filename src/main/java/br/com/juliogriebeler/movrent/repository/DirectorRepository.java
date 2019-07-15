package br.com.juliogriebeler.movrent.repository;

import br.com.juliogriebeler.movrent.entity.Director;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Julio Griebeler
 */
public interface DirectorRepository extends JpaRepository<Director, Long> {

    Director findDirectorByName(String name);

}
