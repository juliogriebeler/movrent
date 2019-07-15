package br.com.juliogriebeler.movrent.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Julio Griebeler
 */
@Entity
@Table(name = "director")
public class Director extends AbstractEntity {

    private String name;

    public Director() {
    }

    public Director(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
