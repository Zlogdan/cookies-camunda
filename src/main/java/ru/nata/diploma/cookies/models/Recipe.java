package ru.nata.diploma.cookies.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "RECIPE")
public class Recipe {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Long flour;//мука
    private Long water;
    private Long bakingPowder;
    private Long ferment;
    private String additive;
    private Long additiveCount;
    private Long sugar;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "hardware_parameters_id", referencedColumnName = "id")
    private HardwareParameters hardwareParameters;
}