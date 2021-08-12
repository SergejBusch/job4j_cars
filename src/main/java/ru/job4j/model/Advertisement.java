package ru.job4j.model;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Advertisement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private String bodyType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @Column(nullable = false)
    private boolean sold;

    @Type(type="org.hibernate.type.BinaryType")
    private byte[] image;


}
