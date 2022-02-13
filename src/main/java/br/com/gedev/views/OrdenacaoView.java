package br.com.gedev.views;

import br.com.gedev.enums.RebeldeAttribute;

import java.util.InputMismatchException;
import java.util.Scanner;

public class OrdenacaoView {
    public static RebeldeAttribute askRebeldeAttribute() {
        RebeldeAttribute[] attributes = RebeldeAttribute.values();
        int indiceMax = attributes.length - 1;

        Scanner sc = new Scanner(System.in);

        while (true) {
            for (RebeldeAttribute attribute : attributes) {
                System.out.printf("%d - %s%n", attribute.ordinal(), attribute);
            }

            System.out.printf("Informe o atributo de rebelde pelo qual fazer a ordenação [0 - %d]: ", indiceMax);

            try {
                int attributeIndex = sc.nextInt();
                RebeldeAttribute attribute = attributes[attributeIndex];
                return attribute;
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

    public static boolean askOrdemInversa() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Ordenar inversamente? [S - Sim, N - Não] ");
        String op = sc.nextLine();

        return op.trim().equalsIgnoreCase("S");
    }
}
