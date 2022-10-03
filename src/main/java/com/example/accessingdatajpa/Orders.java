package com.example.accessingdatajpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.StringJoiner;

@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orders_id_seq")
    private long id;

    private String name;

    public Orders() {
    }

    public Orders(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Orders.class.getSimpleName() + "{", "}")
                .add("id=" + id)
                .add("name='" + name + "'")
                .toString();
    }
}
