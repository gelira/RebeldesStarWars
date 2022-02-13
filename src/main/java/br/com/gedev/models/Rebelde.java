package br.com.gedev.models;

import br.com.gedev.enums.RacaRebelde;
import br.com.gedev.enums.RebeldeAttribute;
import br.com.gedev.exceptions.UnhandledRebeldeAttributeException;
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

    public boolean greaterThan(Rebelde rebelde, RebeldeAttribute attribute) throws UnhandledRebeldeAttributeException {
        if (RebeldeAttribute.NOME.equals(attribute)) {
            return nomeGreaterThan(rebelde);
        }

        if (RebeldeAttribute.IDADE.equals(attribute)) {
            return idadeGreaterThan(rebelde);
        }

        if (RebeldeAttribute.RACA.equals(attribute)) {
            return racaGreaterThan(rebelde);
        }

        throw new UnhandledRebeldeAttributeException();
    }

    private boolean nomeGreaterThan(Rebelde rebelde) {
        return nome.compareTo(rebelde.getNome()) > 0;
    }

    private boolean idadeGreaterThan(Rebelde rebelde) {
        return idade > rebelde.getIdade();
    }

    private boolean racaGreaterThan(Rebelde rebelde) {
        return raca.toString().compareTo(rebelde.getRaca().toString()) > 0;
    }
}
