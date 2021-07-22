package com.backbase.kalah.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class KalahGameResponse {
    int id;
    String url;
}
