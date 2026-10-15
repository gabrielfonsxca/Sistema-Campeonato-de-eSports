package org.example;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        Campeonato champions = new Campeonato("Champions Brasil", 2000000, new ArrayList<>(), new ArrayList<>());

        System.out.println("------ BEM VINDO AO CHAMPIONS BRASIL ------");
        System.out.println("O champions é o maior e mais disputado campeonato de Valorant, e esse ano \nserá sediado no Brasil!");
        int opcao = 0;

        do {
            System.out.println("------ MENU ------");
            System.out.println("1. Gerenciar Equipes");
            System.out.println("2. Gerenciar Partidas");
            System.out.println("3. Gerenciar Estatisticas");
            System.out.println("4. Exportar equipes para CSV");
            System.out.println("5. Exportar partidas para CSV");
            System.out.println("6. Listar Equipes do campeonato");
            System.out.println("7. Listar Partidas do campeonato");
            System.out.println("8. Listar Estatisticas dos Jogadores");
            System.out.println("0. Sair");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1: // TERMINEI
                    System.out.println("1. Adicionar Equipe ao campeonato");
                    System.out.println("2. Remover Equipe do campeonato");
                    System.out.println("3. Remover jogador da equipe");
                    System.out.println("4. Adicionar jogador a equipe");

                    switch (scanner.nextInt()) {
                        case 1:
                            scanner.nextLine();
                            System.out.print("Nome da equipe: ");
                            String nome = scanner.nextLine();

                            System.out.print("Tag da equipe: ");
                            String tag = scanner.nextLine();

                            System.out.print("País da equipe: ");
                            String pais = scanner.nextLine();

                            Equipe equipe = new Equipe(nome, tag, pais, new ArrayList<>());
                            for (int i = 1; i <= 5; i++) {
                                System.out.println("\n--- Jogador " + i + " ---");

                                System.out.print("Nickname: ");
                                String nickname = scanner.nextLine();

                                if (champions.jogadorJaInscrito(nickname) || equipe.contemJogador(nickname)) {
                                    System.out.println("Esse jogador já está inscrito em uma equipe!");
                                    i--;
                                    continue;
                                }

                                System.out.print("Nacionalidade: ");
                                String nacionalidade = scanner.nextLine();

                                System.out.print("Função: ");
                                String funcao = scanner.nextLine();

                                Jogador jogador = new Jogador(nickname, nacionalidade, funcao, null);

                                equipe.adicionarJogador(jogador);
                            }

                            champions.adicionarEquipe(equipe);

                            break;
                        case 2:
                            System.out.println("Nome da equipe a ser removida: ");
                            scanner.nextLine();
                            String nomeRemover = scanner.nextLine();
                            champions.removerEquipe(nomeRemover);
                            break;
                        case 3:
                            scanner.nextLine();
                            System.out.print("Digite o nome da equipe: ");
                            String nomeEquipeRemover = scanner.nextLine();

                            Equipe equipeRemoverJogador = champions.buscarEquipe(nomeEquipeRemover);

                            if (equipeRemoverJogador == null) {
                                System.out.println("Equipe não encontrada.");
                            } else {

                                System.out.print("Digite o nickname do jogador: ");
                                String nickname = scanner.nextLine();

                                equipeRemoverJogador.removerJogador(nickname);
                            }

                            break;
                        case 4:
                            scanner.nextLine();
                            System.out.print("Deseja adicionar em qual equipe?: ");
                            String nomeEquipeAdicionar = scanner.nextLine();

                            Equipe equipeAdicionar = champions.buscarEquipe(nomeEquipeAdicionar);
                            if (equipeAdicionar == null) {
                                System.out.println("Equipe não encontrada.");
                            } else {

                                System.out.print("Nickname: ");
                                String nickname = scanner.nextLine();

                                System.out.print("Nacionalidade: ");
                                String nacionalidade = scanner.nextLine();

                                System.out.print("Função: ");
                                String funcao = scanner.nextLine();

                                Jogador jogador = new Jogador(nickname, nacionalidade, funcao, null);

                                equipeAdicionar.adicionarJogador(jogador);
                            }


                        default:
                            System.out.println("Opção inválida");
                    }
                    break;

                case 2: // TERMINEI
                    System.out.println("1. Programar Partida");
                    System.out.println("2. Desmarcar Partida");

                    switch (scanner.nextInt()) {
                        case 1:

                            if (!champions.podeGerenciarPartidas()) {
                                System.out.println(
                                        "É necessário ter pelo menos 2 equipes e uma quantidade par de equipes inscritas."
                                );
                                break;
                            }

                            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                            scanner.nextLine();
                            LocalDateTime dataHora = null;

                            while (dataHora == null) {

                                System.out.print("Data e horário da partida (dd/MM/yyyy HH:mm): ");
                                String entrada = scanner.nextLine();

                                try {
                                    dataHora = LocalDateTime.parse(entrada, formato);
                                    if (dataHora.isBefore(LocalDateTime.now())) {
                                        System.out.println("A partida não pode ser marcada no passado.");
                                        dataHora = null;
                                    }
                                } catch (DateTimeParseException erro) {
                                    System.out.println("Data inválida.");
                                }
                            }

                            System.out.println("Quais equipes vão se enfrentar?:");
                            Equipe equipe1 = null;
                            do {
                                System.out.print("Digite o nome da primeira equipe: ");
                                String nomeEquipe1 = scanner.nextLine();

                                equipe1 = champions.buscarEquipe(nomeEquipe1);

                                if (equipe1 == null) {
                                    System.out.println("Equipe não inscrita.");
                                }

                            } while (equipe1 == null);

                            Equipe equipe2 = null;

                            do {
                                System.out.print("Digite o nome da segunda equipe: ");
                                String nomeEquipe2 = scanner.nextLine();

                                equipe2 = champions.buscarEquipe(nomeEquipe2);

                                if (equipe2 == null) {
                                    System.out.println("Equipe não inscrita.");
                                } else if (equipe2.getNome().equalsIgnoreCase(equipe1.getNome())) {
                                    System.out.println("Escolha uma equipe diferente.");
                                    equipe2 = null;
                                }

                            } while (equipe2 == null);

                            Partida p = new Partida(dataHora, new ArrayList<>(), new ArrayList<>());

                            p.adicionarEquipe(equipe1);
                            p.adicionarEquipe(equipe2);

                            while (p.getMapas().size() < 3) {

                                System.out.print(
                                        "Digite o nome do mapa "
                                                + (p.getMapas().size() + 1)
                                                + ": "
                                );

                                String nomeMapa = scanner.nextLine();

                                boolean mapaRepetido = false;

                                for (Mapa mapaAtual : p.getMapas()) {
                                    if (mapaAtual.getNome().equalsIgnoreCase(nomeMapa)) {
                                        mapaRepetido = true;
                                        break;
                                    }
                                }

                                if (mapaRepetido) {
                                    System.out.println("Esse mapa já foi escolhido.");
                                    continue;
                                }

                                Mapa mapa = new Mapa(nomeMapa);

                                p.adicionarMapa(mapa);
                            }


                            champions.adicionarPartida(p);

                            break;


                        case 2:

                            if (!champions.podeGerenciarPartidas()) {
                                System.out.println(
                                        "É necessário ter pelo menos 2 equipes e uma quantidade par de equipes inscritas."
                                );
                                break;
                            }

                            DateTimeFormatter formatoRemover = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

                            System.out.print("Digite a data e horário da partida (dd/MM/yyyy HH:mm): ");
                            String entrada = scanner.nextLine();

                            try {
                                LocalDateTime dataHoraRemover = LocalDateTime.parse(entrada, formatoRemover);

                                champions.removerPartida(dataHoraRemover);

                            } catch (DateTimeParseException e) {
                                System.out.println("Data ou horário inválido!");
                            }

                            break;


                        default:
                            System.out.println("Opção inválida");

                    }

                    break;

                case 3:  // EDUARDO
                    // SETAR AS ESTATISTICAS DE CADA UM DOS JOGADORES DO CAMPEONATO, NO CASO OS ABATES, MORTES E ASSISTENCIAS
                    System.out.println("Cadastre as estatísticas dos jogadores abaixo:");

                    boolean temJogador = false;

                    for (Equipe equipe : champions.getEquipes()) {

                        if (!equipe.getJogadores().isEmpty()) {
                            temJogador = true;
                            break;
                        }
                    }

                    if (!temJogador) {
                        System.out.println("Nenhum jogador cadastrado!");
                        break;
                    }


                    for (Equipe equipe : champions.getEquipes()) {
                        System.out.println("Equipe: " + equipe.getNome());
                        for (Jogador jogador : equipe.getJogadores()) {
                            System.out.println("Jogador: " + jogador.getNickname());

                            if (jogador.getEstatisticas() != null) {

                                System.out.println("Esse jogador já possui estatísticas!");
                                String resposta;

                                do {
                                    System.out.println("Deseja cadastrar novamente? (S/N)");

                                    resposta = scanner.next();


                                } while (!resposta.equalsIgnoreCase("S") && !resposta.equalsIgnoreCase("N"));
                                if (resposta.equalsIgnoreCase("N")) {
                                    continue;

                                }
                            }

                            System.out.print("Abates: ");
                            int abates = scanner.nextInt();

                            System.out.print("Mortes: ");
                            int mortes = scanner.nextInt();

                            System.out.print("Assistências: ");
                            int assistencias = scanner.nextInt();

                            Estatistica estatistica = new Estatistica(abates, mortes, assistencias);
                            jogador.setEstatisticas(estatistica);
                        }
                    }

                    break;

                case 4:
                    CSV.exportarEquipes(champions);
                    break;

                case 5:

                    CSV.exportarPartidas(champions);
                    break;

                case 6: // TERMINEI
                    System.out.println("Equipes participantes:");
                    champions.listarEquipes();
                    break;

                case 7: // TERMINEI
                    System.out.println("Partidas marcadas:");
                    champions.listarPartidas();
                    break;

                case 8: // EDUARDO
                    System.out.println("1. Listar por ordem decrescente de Abates");
                    System.out.println("2. Listar por ordem decrescente de Mortes");
                    System.out.println("3. Listar por ordem decrescente de Assistências");
                    System.out.println("4. Listar por ordem decrescente de KDR");

                    switch (scanner.nextInt()) {
                        case 1:


                            champions.listarAbates();


                            break;

                        case 2:
                            champions.listarMortes();
                            break;

                        case 3:
                            champions.listarAssistencias();
                            break;

                        case 4:
                            champions.ListarKdr();
                            break;

                        default:
                            System.out.println("Opção inválida");


                    }
                    break;
                case 0:
                    System.out.println("Encerrando...");
                    break;

                default:
                    System.out.println("Opção inválida");
            }


        } while (opcao != 0);


    }

}
