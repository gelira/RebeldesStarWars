package br.com.gedev.models;

import br.com.gedev.enums.RacaRebelde;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Rebelde {
    @NotNull(message = "O nome do rebelde é obrigatório.")
    @Size(min = 1, max = 20, message = "O nome do rebelde deve ter entre 1 e 20 caracteres.")
    private String nome;

    @Min(value = 0, message = "Não existe idade negativa, meu patrão!")
    private int idade;

    @NotNull(message = "A raça do rebelde é obrigatória.")
    private RacaRebelde raca;
}
