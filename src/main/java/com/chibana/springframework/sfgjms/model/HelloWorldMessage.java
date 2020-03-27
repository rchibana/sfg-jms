package com.chibana.springframework.sfgjms.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Created by Rodrigo Chibana
 * Date: 27/03/2020
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HelloWorldMessage {

    private UUID id;
    private String message;

}
