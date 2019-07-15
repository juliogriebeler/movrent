package br.com.juliogriebeler.movrent.controller.request;

import java.util.List;

/**
 * @author Julio Griebeler
 */
public class RentRequest {
    private Long customerId;
    private int daysQuantity;
    private List<Long> movieIds;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public int getDaysQuantity() {
        return daysQuantity;
    }

    public void setDaysQuantity(int daysQuantity) {
        this.daysQuantity = daysQuantity;
    }

    public List<Long> getMovieIds() {
        return movieIds;
    }

    public void setMovieIds(List<Long> movieIds) {
        this.movieIds = movieIds;
    }
}
