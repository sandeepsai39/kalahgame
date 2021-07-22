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

    /**
     * save Kalah Game with initial values number of pits, stones in each pit and stones in houses
     * @param kala details with number of pits, stones in each pit and number of stones in each house
     * @return Generated Game ID.
     */
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

    /**
     * Fetch the kalah Game details by ID(Game ID, Number of pits, Number of stones in each pit,
     * Number of stones in Houses
     * @param id
     * @return KalaGame details Empty optional
     */
    public Optional<KalahGame> findByKalahId(Integer id){
        return repository.findById(id);
    }

    /**
     *  Fetch the Game details from DB for given gameId and Pit id
     * @param gameId Game ID
     * @param pitId Pit ID
     * @return Current Kalah Status (pits location,number of stones in pits)
     */
    public Optional<KalahStatus> findByGameIdAndPitId(int gameId, int pitId){
        KalahStatus status = new KalahStatus();
        status.setGameId(gameId);
        status.setCurrentPit(pitId);
        return statusRepository.findByGameIdAndCurrentPit(gameId, pitId);
    }

    /**
     * Return all Kala Game status from DB
     * @return List containing all kalahs(Game ID, Number of stones, Stones in House
     */
    public List<KalahStatus> getAllKalahs(){
        List<KalahStatus> statusList = new ArrayList<>();
        statusRepository.findAll().forEach(statusList::add);
        return statusList;
    }

}
