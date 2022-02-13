package br.com.gedev.models;

import br.com.gedev.enums.RacaRebelde;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Rebelde {
    @NotNull(message = "O nome do rebelde é obrigatório.")
    @Min(value = 1, message = "O nome do rebelde não pode ser vazio.")
    @Max(value = 10, message = "Resuma o nome desse rebelde.")
    private String nome;

    @Min(value = 0, message = "Não existe idade negativa, meu patrão!")
    private int idade;

    @NotNull(message = "A raça do rebelde é obrigatória.")
    private RacaRebelde raca;
}
