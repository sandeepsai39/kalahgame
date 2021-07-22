package com.backbase.kalah.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class MakeMoveResponse {
    Integer id;
    String url;
    Map<Integer,Integer> status;
}
