package br.com.juliogriebeler.movrent.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author Julio Griebeler
 */
@Entity
@Table(name = "rent")
public class Rent extends AbstractEntity {

    @NotNull
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = MovieItem.class)
    private List<MovieItem> movies;
    @NotNull
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @NotNull
    private Date pullOutDate;
    @NotNull
    private int daysQuantity;
    private boolean returned;
    @NotNull
    private Date lastUpdate;

    public Rent() {
    }

    public List<MovieItem> getMovies() {
        return movies;
    }

    public void setMovies(List<MovieItem> movies) {
        this.movies = movies;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getPullOutDate() {
        return pullOutDate;
    }

    public void setPullOutDate(Date pullOutDate) {
        this.pullOutDate = pullOutDate;
    }

    public int getDaysQuantity() {
        return daysQuantity;
    }

    public void setDaysQuantity(int daysQuantity) {
        this.daysQuantity = daysQuantity;
    }

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rent)) return false;
        if (!super.equals(o)) return false;
        Rent rent = (Rent) o;
        return getDaysQuantity() == rent.getDaysQuantity() &&
                isReturned() == rent.isReturned() &&
                Objects.equals(getMovies(), rent.getMovies()) &&
                Objects.equals(getCustomer(), rent.getCustomer()) &&
                Objects.equals(getPullOutDate(), rent.getPullOutDate()) &&
                Objects.equals(getLastUpdate(), rent.getLastUpdate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getMovies(), getCustomer(), getPullOutDate(), getDaysQuantity(), isReturned(), getLastUpdate());
    }
}
