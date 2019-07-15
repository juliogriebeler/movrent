package br.com.juliogriebeler.movrent.controller.request;

/**
 * @author Julio Griebeler
 */
public class RentUpdateRequest {
    private Long id;
    private boolean returned;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }
}
