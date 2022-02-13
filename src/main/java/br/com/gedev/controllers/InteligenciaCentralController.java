package br.com.gedev.controllers;

import br.com.gedev.exceptions.RebeldeReprovadoException;
import br.com.gedev.models.Rebelde;
import br.com.gedev.views.CadastroRebeldeView;
import br.com.gedev.views.MenuView;
import lombok.Cleanup;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;

public class InteligenciaCentralController {
    private List<Rebelde> rebeldes;

    public InteligenciaCentralController() {
        rebeldes = new LinkedList<Rebelde>();
    }

    public void iniciar() {
        while (true) {
            menu();
        }
    }

    private void menu() {
        switch (MenuView.askOpcao()) {
            case 1:
                cadastrarRebelde();
                break;

            case 0:
                salvarRebeldes();
                System.exit(0);

            default:
                System.out.println("Opção não encontrada.");
                break;
        }
    }

    private void cadastrarRebelde() {
        Rebelde rebelde = CadastroRebeldeView.cadastrar();

        try {
            AdmissaoRebeldesController.analisarAdmissaoRebelde(rebelde);

            System.out.printf("Rebelde %s admitido%n", rebelde.getNome());
            rebeldes.add(rebelde);
        }
        catch (RebeldeReprovadoException e) {
            System.out.println(e.getMessage());
        }
    }

    private void salvarRebeldes() {
        @Cleanup PrintWriter writer = null;
        try {
            writer = new PrintWriter("rebeldes.txt", "UTF-8");
            for (Rebelde rebelde : rebeldes) {
                writer.println(rebelde);
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
