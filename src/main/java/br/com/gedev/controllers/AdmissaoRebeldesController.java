package br.com.gedev.controllers;

import br.com.gedev.exceptions.RebeldeReprovadoException;
import br.com.gedev.models.Rebelde;

import java.util.Random;

public class AdmissaoRebeldesController {
    public static void analisarAdmissaoRebelde(Rebelde rebelde) throws RebeldeReprovadoException {
        /*
        Foi necessário criar um controlador específico para concentrar a lógica de análise
        da admissão dos rebeldes devido à extrema complexidade dos critérios envolvidos
        no algoritmo que auxilia na tomada de decisão.
        */
        Random analise = new Random();

        if (!analise.nextBoolean()) {
            throw new RebeldeReprovadoException(String.format("O rebelde %s não foi aprovado ao passar pelo crivo " +
                    "dos nossos rigorosíssimos critérios de avaliação!", rebelde.getNome()));
        }
    }
}
