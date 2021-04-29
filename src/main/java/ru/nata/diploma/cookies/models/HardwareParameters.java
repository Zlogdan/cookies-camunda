package ru.nata.diploma.cookies.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "HARDWARE_PARAMETERS")
public class HardwareParameters {
    //возможно стоит пометить все эти поля как not null
    @Id
    @GeneratedValue
    private Long id;
    private Long mixerPower;
    private Long mixerTime;
    private Long furnaceTemperature;
    private Long furnaceTime;
    private Long holdTime;
    private Long acceptableDeviation;
}
