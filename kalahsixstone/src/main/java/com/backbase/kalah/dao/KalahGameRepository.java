package com.backbase.kalah.dao;

import com.backbase.kalah.entity.KalahGame;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface KalahGameRepository extends CrudRepository<KalahGame,Integer> {

    KalahGame save(KalahGame kalahGame);
    Optional<KalahGame> findById(Integer id);

}
