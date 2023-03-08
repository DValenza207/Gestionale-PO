package demo_backend.entities;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "counters")
public class Counters {
    @Id
    @Column(name = "object")
    private String object;
    @NotNull @Version
    @Column(name="counter")
    public Integer counter;

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public Integer getCounter() {
        return counter;
    }

    public void setCounter(Integer counter) {
        this.counter=counter;
    }
}
