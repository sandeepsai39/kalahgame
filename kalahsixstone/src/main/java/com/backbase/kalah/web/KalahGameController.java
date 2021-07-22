package com.backbase.kalah.web;

import com.backbase.kalah.dto.KalahGameResponse;
import com.backbase.kalah.dto.MakeMoveResponse;
import com.backbase.kalah.entity.KalahGame;
import com.backbase.kalah.entity.KalahStatus;
import com.backbase.kalah.service.KalahGameService;
import lombok.SneakyThrows;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
public class KalahGameController {

    final KalahGameService service;

    public KalahGameController(KalahGameService service){
        this.service = service;
    }

    /**
     * Create a new Game and returns Game ID
     * @return HTTP Status code, JSON representation of data contains game id, game URI
     * or errorCode and errorMessage for negative scenarios.
     */
    @PostMapping(path = "/games", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<KalahGameResponse> startGame(){
        KalahGame kalahGame = new KalahGame();
        kalahGame.setPits(6);
        kalahGame.setStones(6);
        kalahGame.setStonesInHouse7(0);
        kalahGame.setStonesInHouse14(0);
        Integer gameId = service.save(kalahGame);
        URI selfUri = MvcUriComponentsBuilder.fromController(getClass()).
                path("/{id}").
                buildAndExpand(gameId).toUri();
        return ResponseEntity.created(selfUri).body(KalahGameResponse.builder().
                id(gameId).url(selfUri.toString()).build());
    }

    /**
     * Make a move for the gameId and pitId path variables.
     * @param gameId Id of created game
     * @param pitId  pitId where user wants to start game
     * @return Response with HTTP Status, with JSON representation of data containing game GameId, Game URL
     * status: json object key-value, where key is the pitId and value is the number of stones in the pit or
     * errorCode and errorMessage in negative scenarios.
     * @throws KalahGameException
     */
    @PutMapping(path = "/games/{gameId}/pits/{pitId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @SneakyThrows
    public ResponseEntity<MakeMoveResponse> makeMove(@PathVariable("gameId") Integer gameId, @PathVariable("pitId") Integer pitId){
        //Load Kalah Game position ,initially with 6 stones in each pit and zero stones the houses
        Map<Integer,Integer>  gamePosition = service.getAllKalahs().stream().collect(HashMap::new,
                (map, value) -> {
                  map.put(value.getCurrentPit(), value.getStonesInPit());
                }, (map, map2) -> map2.putAll(map));

        //Load the position for pit id, replace it with initial values
        Optional<KalahStatus> gamestatus = service.findByGameIdAndPitId(gameId,pitId);
        gamestatus.map(s -> gamePosition.put(s.getCurrentPit(),s.getStonesInPit())).
                orElseThrow(() -> new KalahGameException(404,"Game not Created","Web"));

        //gamePosition.entrySet().
        int numberofStonesinPit  = gamePosition.get(pitId);
        gamePosition.put(pitId,0);

        URI selfUri = MvcUriComponentsBuilder.fromController(getClass()).
                path("/{id}").
                buildAndExpand(gameId).toUri();
        return ResponseEntity.ok(MakeMoveResponse.builder().
                id(gameId).
                url(selfUri.toString()).
                status(gamePosition)
                .build());
    }

}
