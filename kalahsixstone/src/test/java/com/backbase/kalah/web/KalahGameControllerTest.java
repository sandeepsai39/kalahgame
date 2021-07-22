package com.backbase.kalah.web;


import com.backbase.kalah.entity.KalahGame;
import com.backbase.kalah.entity.KalahStatus;
import com.backbase.kalah.service.KalahGameService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@AutoConfigureMockMvc
@WebMvcTest(KalahGameController.class)
@ContextConfiguration
public class KalahGameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    @Autowired
    private KalahGameService service;

    @Test
    @SneakyThrows
    public void testStartGame(){
        doReturn(1234).when(service).save(any(KalahGame.class));
        mockMvc.perform(MockMvcRequestBuilders.post("/games").
                contentType(MediaType.APPLICATION_JSON_VALUE)).
                andExpect(MockMvcResultMatchers.status().isCreated()).
                andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE));

    }

    @Test
    @SneakyThrows
    public void testMakeMove(){
        List<KalahStatus> kalahStatusList = new ArrayList<>();
        KalahStatus kalahStatus = new KalahStatus();
        kalahStatus.setGameId(1234);
        kalahStatus.setCurrentPit(1);
        kalahStatus.setStonesInPit(6);
        kalahStatusList.add(kalahStatus);
        kalahStatus = new KalahStatus();
        kalahStatus.setGameId(1234);
        kalahStatus.setCurrentPit(2);
        kalahStatus.setStonesInPit(6);
        kalahStatusList.add(kalahStatus);
        kalahStatus = new KalahStatus();
        kalahStatus.setGameId(1234);
        kalahStatus.setCurrentPit(3);
        kalahStatus.setStonesInPit(6);
        kalahStatusList.add(kalahStatus);
        kalahStatus = new KalahStatus();
        kalahStatus.setGameId(1234);
        kalahStatus.setCurrentPit(4);
        kalahStatus.setStonesInPit(6);
        kalahStatusList.add(kalahStatus);
        kalahStatus = new KalahStatus();
        kalahStatus.setGameId(1234);
        kalahStatus.setCurrentPit(5);
        kalahStatus.setStonesInPit(6);
        kalahStatusList.add(kalahStatus);kalahStatus = new KalahStatus();
        kalahStatus.setGameId(1234);
        kalahStatus.setCurrentPit(6);
        kalahStatus.setStonesInPit(6);
        kalahStatusList.add(kalahStatus);
        doReturn(kalahStatusList).when(service).getAllKalahs();
        kalahStatus = new KalahStatus();
        kalahStatus.setGameId(1234);
        kalahStatus.setCurrentPit(1);
        kalahStatus.setStonesInPit(6);
        doReturn(Optional.of(kalahStatus)).when(service).findByGameIdAndPitId(1234,1);
        mockMvc.perform(MockMvcRequestBuilders.put("/games/1234/pits/1")
                .contentType(MediaType.APPLICATION_JSON_VALUE)).
                andExpect(MockMvcResultMatchers.status().isOk()).
                andExpect(MockMvcResultMatchers.content().
                        contentType(MediaType.APPLICATION_JSON_VALUE));
        mockMvc.perform(MockMvcRequestBuilders.put("/games/123/pits/1")
                .contentType(MediaType.APPLICATION_JSON_VALUE)).
                andExpect(MockMvcResultMatchers.status().isNotFound()).
                andExpect(MockMvcResultMatchers.content().
                        contentType(MediaType.APPLICATION_JSON_VALUE));

    }

}
