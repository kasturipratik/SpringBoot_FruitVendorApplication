package com.example.fruitstand;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Days {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String dayList;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name ="time_id")
    private Time time;


    @OneToMany(mappedBy = "day",orphanRemoval = true,
            cascade = CascadeType.REMOVE)
    @Fetch(value = FetchMode.SUBSELECT)
    private Set<Fruits> fruits;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDayList() {
        return dayList;
    }

    public void setDayList(String dayList) {
        this.dayList = dayList;
    }



    public Set<Fruits> getFruits() {
        return fruits;
    }

    public void setFruits(Set<Fruits> fruits) {
        this.fruits = fruits;
    }
}
