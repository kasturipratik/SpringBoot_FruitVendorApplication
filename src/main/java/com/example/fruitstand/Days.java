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
    private String openTime;
    private String closeTime;


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

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }

    public Set<Fruits> getFruits() {
        return fruits;
    }

    public void setFruits(Set<Fruits> fruits) {
        this.fruits = fruits;
    }
}
