package br.com.gedev.controllers;

import br.com.gedev.enums.RacaRebelde;
import br.com.gedev.exceptions.RebeldeReprovadoException;
import br.com.gedev.models.Rebelde;
import br.com.gedev.views.CadastroRebeldeView;

import java.util.ArrayList;
import java.util.List;

public class InteligenciaCentralController {
    private List<Rebelde> rebeldes;

    public InteligenciaCentralController() {
        rebeldes = new ArrayList<Rebelde>();
    }

    public void cadastrarRebelde() {
        String nome = CadastroRebeldeView.askNomeRebelde();
        int idade = CadastroRebeldeView.askIdadeRebelde();
        RacaRebelde raca = CadastroRebeldeView.askRacaRebelde();

        Rebelde rebelde = Rebelde.builder()
                .nome(nome)
                .idade(idade)
                .raca(raca)
                .build();

        try {
            AdmissaoRebeldesController.analisarAdmissaoRebelde(rebelde);
            rebeldes.add(rebelde);
        }
        catch (RebeldeReprovadoException e) {
            System.out.println(e.getMessage());
        }
    }
}
