package org.example.paytrack.model;

import jakarta.persistence.*;

@Entity
@Table(name = "paymentType")
public class PaymentType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "payment_type_id")
    private long id;

    @Column(name = "type_name")
    private String name;

    public PaymentType() {

    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
