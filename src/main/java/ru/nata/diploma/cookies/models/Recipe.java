package ru.nata.diploma.cookies.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

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
}