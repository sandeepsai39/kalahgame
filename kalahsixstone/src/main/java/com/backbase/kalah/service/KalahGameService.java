package com.backbase.kalah.service;

import com.backbase.kalah.dao.KalahGameRepository;
import com.backbase.kalah.dao.KalahStatusRepository;
import com.backbase.kalah.entity.KalahGame;
import com.backbase.kalah.entity.KalahStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.IntStream;

@Service
public class KalahGameService {

    @Autowired
    KalahGameRepository repository;
    @Autowired
    KalahStatusRepository statusRepository;

    public Integer save(KalahGame kala){
        KalahGame kalahGame = repository.save(kala);
        Integer gameId = kalahGame.getId();
        List<KalahStatus> statusList = new ArrayList<>();
        IntStream.range(0,14).forEach(
                value -> {
                    KalahStatus gameStatus = new KalahStatus();
                    gameStatus.setGameId(gameId);
                    gameStatus.setStonesInPit(6);
                    gameStatus.setCurrentPit(value);
                    statusList.add(gameStatus);
                });
        statusRepository.saveAll(statusList);
        return gameId;
    }

    public Optional<KalahGame> findByKalahId(Integer id){
        return repository.findById(id);
    }

    public Optional<KalahStatus> findByGameIdAndPitId(int gameId, int pitId){
        KalahStatus status = new KalahStatus();
        status.setGameId(gameId);
        status.setCurrentPit(pitId);
        return statusRepository.findByGameIdAndCurrentPit(gameId, pitId);
    }

    public List<KalahStatus> getAllKalahs(){
        List<KalahStatus> statusList = new ArrayList<>();
        statusRepository.findAll().forEach(statusList::add);
        return statusList;
    }

}
