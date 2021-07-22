package com.backbase.kalah.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "KALAH")
@Data
public class KalahGame implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "PITS")
    private Integer pits;
    @Column(name = "STONES")
    private Integer stones;
    @Column(name = "stonesinhouse7")
    private Integer stonesInHouse7;
    @Column(name = "stonesinhouse14")
    private Integer stonesInHouse14;
}
