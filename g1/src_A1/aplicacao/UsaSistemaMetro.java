package aplicacao;

import java.util.Scanner;

public class UsaSistemaMetro {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int[] vagao = new int[6];
        int totalPassageiros = 0;

        System.out.println("----------------------------------------------------------");
        System.out.println("~~Embarque de passageiros~~");
        for (int i = 0; i < vagao.length; i++) {
            int numeroVagao = 1 + i;
            System.out.printf("Número de passageiros entrando no vagão %d: ", numeroVagao);
            int entrando = in.nextInt();
            if (entrando >= 0 && entrando <= 250) {
                vagao[i] += entrando;
            } else {
                System.err.println("Valor inserido inválido.");
            }
        }

        System.out.println("----------------------------------------------------------");
        System.out.println("~~Desembarque de passageiros~~");
        for (int i = 0; i < vagao.length; i++) {
            int numeroVagao = 1 + i;
            System.out.printf("Número de passageiros saindo do vagão %d: ", numeroVagao);
            int saindo = in.nextInt();
            if (saindo <= vagao[i]) {
                if (saindo >= 0 && saindo <= 250) {
                    vagao[i] -= saindo;
                } else {
                    System.err.println("Valor inserido inválido.");
                }
            } else {
                System.err.println("Valor inserido inválido:"
                        + "\n(Desembarque de passageiros maior do que o total de passageiros no vagão).");
            }
        }

        System.out.println("----------------------------------------------------------");
        System.out.println("~~Informações da composição~~");
        for (int i = 0; i < vagao.length; i++) {
            int numeroVagao = 1 + i;
            totalPassageiros += vagao[i];
            if (vagao[i] >= 0 && vagao[i] < 51) {
                System.out.printf("Número total de passageiros no vagão %d: %d (Azul).%n", numeroVagao, vagao[i]);
            } else if (vagao[i] >= 51 && vagao[i] < 101) {
                System.out.printf("Número total de passageiros no vagão %d: %d (Amarela).%n", numeroVagao, vagao[i]);
            } else if (vagao[i] >= 101 && vagao[i] < 151) {
                System.out.printf("Número total de passageiros no vagão %d: %d (Laranja).%n", numeroVagao, vagao[i]);
            } else if (vagao[i] >= 150 && vagao[i] < 251) {
                System.out.printf("Número total de passageiros no vagão %d: %d (Vermelha).%n", numeroVagao, vagao[i]);
            }
        }

        System.out.println("Total de passageiros na composição: " + totalPassageiros);
        System.out.println("----------------------------------------------------------");

        in.close();
    }

}
