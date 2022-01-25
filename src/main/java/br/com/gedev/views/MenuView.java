package br.com.gedev.views;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuView {
    public static int askOpcao() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("1 - Cadastrar rebelde");
            System.out.println("0 - Sair");
            System.out.print("Escolha a opção: ");

            try {
                int opcao = sc.nextInt();
                return opcao;
            }
            catch (InputMismatchException e) {
                System.out.println("Opção inválida. Tente novamente.");
                sc.nextLine();
            }
        }
    }
}
