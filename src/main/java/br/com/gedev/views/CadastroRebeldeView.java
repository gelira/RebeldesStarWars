package br.com.gedev.views;

import br.com.gedev.enums.RacaRebelde;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CadastroRebeldeView {
    public static String askNomeRebelde() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Informe o nome do rebelde: ");
        String nome = sc.nextLine();

        return nome;
    }

    public static int askIdadeRebelde() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("Informe a idade do rebelde: ");
            try {
                int idade = sc.nextInt();
                return idade;
            }
            catch (InputMismatchException e) {
                System.out.println("Idade inválida. Tente novamente.");
            }
        }
    }

    public static RacaRebelde askRacaRebelde() {
        RacaRebelde[] racas = RacaRebelde.values();
        int indiceMax = racas.length - 1;

        Scanner sc = new Scanner(System.in);

        while (true) {
            for (RacaRebelde raca : racas) {
                System.out.printf("%d - %s%n", raca.ordinal(), raca);
            }

            System.out.printf("Informe a raça do rebelde [0 - %d]: ", indiceMax);

            try {
                int racaIndex = sc.nextInt();
                RacaRebelde raca = racas[racaIndex];
                return raca;
            }
            catch (InputMismatchException e) {
                System.out.println("Índice inválido. Tente novamente.");
                sc.nextLine();
            }
            catch (ArrayIndexOutOfBoundsException e) {
                System.out.printf("Informe um índice entre 0 e %d%n", indiceMax);
            }
        }
    }
}
