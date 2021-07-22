package com.backbase.kalah.service;

import com.backbase.kalah.dao.KalahGameRepository;
import com.backbase.kalah.dao.KalahStatusRepository;
import com.backbase.kalah.entity.KalahGame;
import com.backbase.kalah.entity.KalahStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyIterable;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class KalahGameServiceTest {

    @Mock
    KalahGameRepository gameRepository;

    @Mock
    KalahStatusRepository statusRepository;

    @InjectMocks
    @Spy
    KalahGameService service;

    @Test
    public void testSave(){
        KalahGame kalahGame = new KalahGame();
        kalahGame.setPits(6);
        kalahGame.setStones(6);
        kalahGame.setStonesInHouse7(0);
        kalahGame.setStonesInHouse14(0);

        KalahGame returnedGame = new KalahGame();
        returnedGame.setId(1234);
        doReturn(returnedGame).when(gameRepository).save(any(KalahGame.class));
        doReturn(new ArrayList<KalahStatus>()).when(statusRepository).saveAll(anyIterable());
        Integer returnedValue = service.save(kalahGame);
        assertEquals(returnedValue,1234);
    }

    @Test
    public void testFindByKalahId(){
        KalahGame kalahGame = new KalahGame();
        kalahGame.setPits(6);
        kalahGame.setStones(6);
        kalahGame.setStonesInHouse7(0);
        kalahGame.setStonesInHouse14(0);
        kalahGame.setId(1234);

        doReturn(Optional.of(kalahGame)).when(gameRepository).findById(1234);
        Optional<KalahGame> returnedVal = service.findByKalahId(1234);
        Assertions.assertNotNull(returnedVal.get());
    }

    @Test
    public void testFindByGameIdAndPitId(){
        KalahStatus status = new KalahStatus();
        status.setStonesInPit(6);
        status.setGameId(1234);
        status.setCurrentPit(2);
        doReturn(Optional.of(status)).when(statusRepository).findByGameIdAndCurrentPit(2,1234);
        Optional<KalahStatus> returnval = service.findByGameIdAndPitId(2,1234);
        assertEquals(returnval.get().getGameId(),status.getGameId());
        assertEquals(returnval.get().getCurrentPit(),status.getCurrentPit());

    }

    @Test
    public void testGetAllKalahs(){
        List<KalahStatus> statusList = new ArrayList<>();
        KalahStatus status = new KalahStatus();
        status.setId(1234);
        statusList.add(status);
        status = new KalahStatus();
        status.setId(4567);
        statusList.add(status);
        status = new KalahStatus();
        status.setId(8900);
        statusList.add(status);
        doReturn(statusList).when(statusRepository).findAll();
        List<KalahStatus> returnList = service.getAllKalahs();
        assertEquals(returnList.size(),statusList.size());
    }
}
