package br.com.gedev.models;

import br.com.gedev.enums.RacaRebelde;
import lombok.Data;

@Data
public class Rebelde {
    private String nome;
    private int idade;
    private RacaRebelde raca;
}
