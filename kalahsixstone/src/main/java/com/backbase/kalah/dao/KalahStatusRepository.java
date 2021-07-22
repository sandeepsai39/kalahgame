package com.backbase.kalah.dao;

import com.backbase.kalah.entity.KalahStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface KalahStatusRepository extends CrudRepository<KalahStatus,Integer> {

    public KalahStatus save(KalahStatus status);
   //public  List<KalahStatus> saveAll(List<KalahStatus> status);
    public Optional<KalahStatus> findByGameIdAndCurrentPit(int gameId, int pitId);

}
