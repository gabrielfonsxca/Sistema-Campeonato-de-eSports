package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;


public class CSV {

    public static void exportarEquipes(Campeonato campeonato) {

        try {
            FileWriter arquivo = new FileWriter("equipes.csv");

            arquivo.write("Equipe,Tag,Pais,Nickname,Nacionalidade,Funcao\n");

            for (Equipe equipe : campeonato.getEquipes()) {

                for (Jogador jogador : equipe.getJogadores()) {

                    arquivo.write(
                            equipe.getNome() + "," +
                                    equipe.getTag() + "," +
                                    equipe.getPais() + "," +
                                    jogador.getNickname() + "," +
                                    jogador.getNacionalidade() + "," +
                                    jogador.getFuncao() + "\n"
                    );
                }
            }

            arquivo.close();

            System.out.println("CSV criado com sucesso!");

        } catch (IOException e) {
            System.out.println("Erro ao criar o CSV.");
        }
    }

    public static void exportarPartidas(Campeonato campeonato) {

        try {
            FileWriter arquivo = new FileWriter("partidas.csv");

            DateTimeFormatter formato =
                    DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

            arquivo.write("Equipe1,Equipe2,DataHora,Mapa\n");

            for (Partida partida : campeonato.getPartidas()) {

                String equipe1 = partida.getEquipes().get(0).getNome();
                String equipe2 = partida.getEquipes().get(1).getNome();

                String dataHora =
                        partida.getDataHora().format(formato);

                for (Mapa mapa : partida.getMapas()) {

                    arquivo.write(
                            equipe1 + "," +
                                    equipe2 + "," +
                                    dataHora + "," +
                                    mapa.getNome() + "\n"
                    );
                }
            }

            arquivo.close();

            System.out.println("CSV de partidas criado com sucesso!");

        } catch (IOException e) {
            System.out.println("Erro ao criar o CSV de partidas.");
        }
    }
}
