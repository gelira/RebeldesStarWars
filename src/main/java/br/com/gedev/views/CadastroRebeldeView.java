package br.com.gedev.views;

import br.com.gedev.enums.RacaRebelde;
import br.com.gedev.models.Rebelde;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.*;

public class CadastroRebeldeView {
    public static Rebelde cadastrar() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        while (true) {
            Rebelde rebelde = Rebelde.builder()
                    .nome(askNomeRebelde())
                    .idade(askIdadeRebelde())
                    .raca(askRacaRebelde())
                    .build();

            Set<ConstraintViolation<Rebelde>> constraintViolations = validator.validate(rebelde);

            if (constraintViolations.isEmpty()) {
                return rebelde;
            }

            showValidationErrors(constraintViolations);
        }
    }

    private static String askNomeRebelde() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Informe o nome do rebelde: ");
        String nome = sc.nextLine();

        return nome;
    }

    private static int askIdadeRebelde() {
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

    private static RacaRebelde askRacaRebelde() {
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

    private static void showValidationErrors(Set<ConstraintViolation<Rebelde>> constraintViolations) {
        Map<String, String> mapErrors = new HashMap<String, String>();

        constraintViolations.forEach(constraintViolation -> {
            String property = constraintViolation.getPropertyPath().toString();
            String message = String.format("%s%n", constraintViolation.getMessage());

            if (mapErrors.containsKey(property)) {
                message = String.format("%s%s", mapErrors.get(property), message);
            }

            mapErrors.put(property, message);
        });

        for (Map.Entry<String, String> errorEntry : mapErrors.entrySet()) {
            System.out.printf("Atributo: %s%n%s%n", errorEntry.getKey(), errorEntry.getValue());
        }
        System.out.println("Tente novamente.");
    }
}
