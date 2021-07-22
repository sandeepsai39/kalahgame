package com.backbase.kalah.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "kalahstatus")
@Data
public class KalahStatus implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    @Column(name = "gameId")
    private Integer gameId;
    @Column(name = "current_pit")
    private Integer currentPit;
    @Column(name = "stones_in_pit")
    private Integer stonesInPit;

}
