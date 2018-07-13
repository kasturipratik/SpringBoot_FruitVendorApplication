package com.example.fruitstand;

import javax.persistence.*;

@Entity
public class Fruits {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String fruitName;
    private String image;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name ="days_id")
    private Days day;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFruitName() {
        return fruitName;
    }

    public void setFruitName(String fruitName) {
        this.fruitName = fruitName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Days getDay() {
        return day;
    }

    public void setDay(Days day) {
        this.day = day;
    }
}
