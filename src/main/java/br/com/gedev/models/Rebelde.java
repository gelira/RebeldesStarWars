package br.com.gedev.models;

import br.com.gedev.enums.RacaRebelde;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Rebelde {
    private String nome;
    private int idade;
    private RacaRebelde raca;
}
