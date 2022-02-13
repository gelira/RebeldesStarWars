package br.com.gedev.controllers;

import br.com.gedev.enums.RebeldeAttribute;
import br.com.gedev.exceptions.RebeldeReprovadoException;
import br.com.gedev.exceptions.UnhandledRebeldeAttributeException;
import br.com.gedev.models.Rebelde;
import br.com.gedev.views.CadastroRebeldeView;
import br.com.gedev.views.MenuView;
import br.com.gedev.views.OrdenacaoView;
import lombok.Cleanup;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class InteligenciaCentralController {
    private List<Rebelde> rebeldes;
    private boolean relatorioGerado;

    public InteligenciaCentralController() {
        rebeldes = new LinkedList<Rebelde>();
        relatorioGerado = false;
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

            case 2:
                relatorioRebeldes();
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

    private void relatorioRebeldes() {
        RebeldeAttribute attribute = OrdenacaoView.askRebeldeAttribute();
        boolean ordemInversa = OrdenacaoView.askOrdemInversa();

        try {
            Rebelde[] arrayRebeldesOrdenado = ordenarRebeldes(attribute);

            if (ordemInversa) {
                arrayRebeldesOrdenado = ordemInversa(arrayRebeldesOrdenado);
            }

            for (Rebelde rebelde : arrayRebeldesOrdenado) {
                System.out.printf("%s%n%n", rebelde);
            }

            salvarRebeldes(arrayRebeldesOrdenado);
        }
        catch (UnhandledRebeldeAttributeException e) {
            System.out.println(e.getMessage());
        }
    }

    private Rebelde[] ordenarRebeldes(RebeldeAttribute attribute) throws UnhandledRebeldeAttributeException {
        Object[] arrayAux = rebeldes.toArray();

        Rebelde[] arrayRebeldes = new Rebelde[arrayAux.length];
        int arrayMaxIndex = arrayRebeldes.length - 1;

        for (int i = 0; i <= arrayMaxIndex; i ++) {
            arrayRebeldes[i] = (Rebelde) arrayAux[i];
        }

        // Bubble Sort
        for (int i = 0; i < arrayMaxIndex; i ++) {
            for (int j = 0; j < arrayMaxIndex - i; j ++) {
                if (arrayRebeldes[j].greaterThan(arrayRebeldes[j + 1], attribute)) {
                    Rebelde temp = arrayRebeldes[j];
                    arrayRebeldes[j] = arrayRebeldes[j + 1];
                    arrayRebeldes[j + 1] = temp;
                }
            }
        }

        return arrayRebeldes;
    }

    private Rebelde[] ordemInversa(Rebelde[] rebeldes) {
        Stack<Rebelde> stackRebeldes = new Stack();

        for (Rebelde rebelde : rebeldes) {
            stackRebeldes.push(rebelde);
        }

        int c = 0;
        while (!stackRebeldes.isEmpty()) {
            rebeldes[c] = stackRebeldes.pop();
            c ++;
        }

        return rebeldes;
    }

    private void salvarRebeldes() {
        if (!relatorioGerado) {
            salvarRebeldes((Rebelde[]) rebeldes.toArray());
        }
    }

    private void salvarRebeldes(Rebelde[] rebeldes) {
        @Cleanup PrintWriter writer = null;
        try {
            writer = new PrintWriter("rebeldes.txt", "UTF-8");
            for (Rebelde rebelde : rebeldes) {
                writer.printf("%s%n%n", rebelde);
            }
            relatorioGerado = true;
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
