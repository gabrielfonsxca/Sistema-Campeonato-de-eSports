package org.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;

public class Campeonato {
    private String nome;
    private double premiacao;
    private ArrayList<Equipe> equipes = new ArrayList<>();
    private ArrayList<Partida> partidas = new ArrayList<>();

    public Campeonato(String nome, double premiacao, ArrayList<Equipe> equipes, ArrayList<Partida> partidas) {
        this.nome = nome;
        this.premiacao = premiacao;
        this.equipes = equipes;
        this.partidas = partidas;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public double getPremiacao() {
        return premiacao;
    }
    public void setPremiacao(double premiacao) {
        this.premiacao = premiacao;
    }
    public ArrayList<Equipe> getEquipes() {
        return equipes;
    }
    public void setEquipes(ArrayList<Equipe> equipes) {
        this.equipes = equipes;
    }
    public ArrayList<Partida> getPartidas() {
        return partidas;
    }
    public void setPartidas(ArrayList<Partida> partidas) {
        this.partidas = partidas;
    }

    public void adicionarEquipe(Equipe e){
        if(equipes.size() >= 16){
            System.out.println("O máximo de equipes permitidas é 16");
            return;
        }
        for (Equipe equipe : equipes) {
            if (equipe.getNome().equalsIgnoreCase(e.getNome())) {
                System.out.println("Essa equipe já está inscrita");
                return;
            }
        }
        equipes.add(e);
        System.out.println("Equipe adicionado com sucesso");
    }

    public void removerEquipe(String nomeRemover){
        if(equipes.isEmpty()){
            System.out.println("Não há equipes inscritas ainda");
            return;
        }
        for (Equipe equipe : equipes) {
            if(equipe.getNome().equalsIgnoreCase(nomeRemover)){
                equipes.remove(equipe);
                System.out.println("Equipe removida com sucesso!");
                return;
            }
        }
        System.out.println("Equipe não encontrada");
    }

    public void listarEquipes(){
        if(equipes.isEmpty()){
            System.out.println("Não há equipes inscritas no campeonato");
            return;
        }
        for(Equipe equipe : equipes) {
            System.out.println(equipe.getTag()+" ("+equipe.getNome()+" - "+equipe.getPais()+")");
            for(Jogador jogador : equipe.getJogadores()) {
                System.out.println("Player: "+jogador.getNickname());
            }
            System.out.println("--------------------");
        }
    }

    public boolean jogadorJaInscrito(String nickname) {
        for (Equipe equipe : equipes) {
            for (Jogador jogador : equipe.getJogadores()) {
                if (jogador.getNickname().equalsIgnoreCase(nickname)) {
                    return true;
                }
            }
        }
        return false;
    }

    public Equipe buscarEquipe(String nome) {
        for (Equipe equipe : equipes) {
            if (equipe.getNome().equalsIgnoreCase(nome)) {
                return equipe;
            }
        }

        return null;
    }

    public void adicionarPartida(Partida p) {
        if(!podeGerenciarPartidas()){
            System.out.println("O campeonato precisa ter pelo menos 2 equipes e uma quantidade par inscritas.");
            return;
        }

        for (Partida partida : partidas) {
            if (partida.getDataHora().equals(p.getDataHora())) {
                System.out.println("Já existe uma partida nesse horário.");
                return;
            }
        }
        partidas.add(p);
        System.out.println("Partida cadastrada com sucesso!");
    }

    public void listarPartidas(){
        if(partidas.isEmpty()){
            System.out.println("Ainda não há partidas marcadas");
            return;
        }
        partidas.sort(Comparator.comparing(Partida::getDataHora));

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        for (Partida partida : partidas) {
            System.out.println(partida.getEquipes().get(0).getNome() + " x " + partida.getEquipes().get(1).getNome() + " - " + partida.getDataHora().format(formato));
            System.out.print(partida.getMapas().get(0).getNome()+" ");
            System.out.print(partida.getMapas().get(1).getNome()+" ");
            System.out.print(partida.getMapas().get(2).getNome());
            System.out.println("------------------------");
        }
    }

    public void removerPartida(LocalDateTime dataHoraRemover) {
        if (!podeGerenciarPartidas()) {
            System.out.println(
                    "Não é possível desmarcar partidas. " +
                            "O campeonato precisa ter pelo menos 2 equipes e uma quantidade par."
            );
            return;
        }

        for (int i = 0; i < partidas.size(); i++) {
            if(partidas.isEmpty()){
                System.out.println("Não há partidas marcadas");
            }
            if (partidas.get(i).getDataHora().equals(dataHoraRemover)) {
                partidas.remove(i);
                System.out.println("Partida removida com sucesso!");
                return;
            }
        }
        System.out.println("Nenhuma partida encontrada nessa data e horário.");
    }

    public void listarAbates() {

    Jogador maior = null;
    int quantidade = 0;


    for (int i = 0; i < equipes.size(); i++) {
        for (int j = 0; j < equipes.get(i).getJogadores().size(); j++) {

            if (equipes.get(i).getJogadores().get(j).getEstatisticas() != null) {
                quantidade++;
            }
        }
    }


    for (int x = 0; x < quantidade; x++) {

        maior = null;

        for (int i = 0; i < equipes.size(); i++) {
            for (int j = 0; j < equipes.get(i).getJogadores().size(); j++) {

                Jogador jogador = equipes.get(i).getJogadores().get(j);

                if (jogador.getEstatisticas() == null) {
                    continue;
                }

                int anteriores = 0;

                for (int a = 0; a < equipes.size(); a++) {
                    for (int b = 0; b < equipes.get(a).getJogadores().size(); b++) {

                        Jogador outro = equipes.get(a).getJogadores().get(b);

                        if (outro.getEstatisticas() != null &&
                            (outro.getEstatisticas().getAbates() > jogador.getEstatisticas().getAbates() ||
                            (outro.getEstatisticas().getAbates() == jogador.getEstatisticas().getAbates() &&
                             (a < i || (a == i && b < j))))) {

                            anteriores++;
                        }
                    }
                }

                if (anteriores == x) {
                    maior = jogador;
                }
            }
        }

        if (maior != null) {
            System.out.println(maior.getNickname() + " - " +
                maior.getEstatisticas().getAbates() + " abates");
        }
    }
}
    
    public void listarMortes() {
        
    Jogador maior = null;
    int quantidade = 0;


    for (int i = 0; i < equipes.size(); i++) {
        for (int j = 0; j < equipes.get(i).getJogadores().size(); j++) {

            if (equipes.get(i).getJogadores().get(j).getEstatisticas() != null) {
                quantidade++;
            }
        }
    }


    for (int x = 0; x < quantidade; x++) {

        maior = null;

        for (int i = 0; i < equipes.size(); i++) {
            for (int j = 0; j < equipes.get(i).getJogadores().size(); j++) {

                Jogador jogador = equipes.get(i).getJogadores().get(j);

                if (jogador.getEstatisticas() == null) {
                    continue;
                }

                int anteriores = 0;

                for (int a = 0; a < equipes.size(); a++) {
                    for (int b = 0; b < equipes.get(a).getJogadores().size(); b++) {

                        Jogador outro = equipes.get(a).getJogadores().get(b);

                        if (outro.getEstatisticas() != null &&
                            (outro.getEstatisticas().getMortes() > jogador.getEstatisticas().getMortes() ||
                            (outro.getEstatisticas().getMortes() == jogador.getEstatisticas().getMortes() &&
                             (a < i || (a == i && b < j))))) {

                            anteriores++;
                        }
                    }
                }

                if (anteriores == x) {
                    maior = jogador;
                }
            }
        }

        if (maior != null) {
            System.out.println(maior.getNickname() + " - " +
                maior.getEstatisticas().getMortes() + " mortes");
        }
    }
    
    
    
    

}
public void listarAssistencias() {

    Jogador maior = null;
    int quantidade = 0;


    for (int i = 0; i < equipes.size(); i++) {
        for (int j = 0; j < equipes.get(i).getJogadores().size(); j++) {

            if (equipes.get(i).getJogadores().get(j).getEstatisticas() != null) {
                quantidade++;
            }
        }
    }


    for (int x = 0; x < quantidade; x++) {

        maior = null;

        for (int i = 0; i < equipes.size(); i++) { 
            for (int j = 0; j < equipes.get(i).getJogadores().size(); j++) {

                Jogador jogador = equipes.get(i).getJogadores().get(j);

                if (jogador.getEstatisticas() == null) {
                    continue;
                }

                int anteriores = 0;

                for (int a = 0; a < equipes.size(); a++) {
                    for (int b = 0; b < equipes.get(a).getJogadores().size(); b++) {

                        Jogador outro = equipes.get(a).getJogadores().get(b);

                        if (outro.getEstatisticas() != null &&
                            (outro.getEstatisticas().getAssistencias() > jogador.getEstatisticas().getAssistencias() ||
                            (outro.getEstatisticas().getAssistencias() == jogador.getEstatisticas().getAssistencias() &&
                             (a < i || (a == i && b < j))))) {

                            anteriores++;
                        }
                    }
                }

                if (anteriores == x) {
                    maior = jogador;
                }
            }
        }

        if (maior != null) {
            System.out.println(maior.getNickname() + " - " +
                maior.getEstatisticas().getAssistencias() + " assistências");
        }
    }
}
    public void ListarKdr(){

    Jogador maior = null;
    int quantidade = 0;


    for (int i = 0; i < equipes.size(); i++) {
        for (int j = 0; j < equipes.get(i).getJogadores().size(); j++) {

            if (equipes.get(i).getJogadores().get(j).getEstatisticas() != null) {
                quantidade++;
            }
        }
    }

    // Imprime os jogadores por ordem de abates
    for (int x = 0; x < quantidade; x++) {

        maior = null;

        for (int i = 0; i < equipes.size(); i++) {
            for (int j = 0; j < equipes.get(i).getJogadores().size(); j++) {

                Jogador jogador = equipes.get(i).getJogadores().get(j);

                if (jogador.getEstatisticas() == null) {
                    continue;
                }

                int anteriores = 0;

                for (int a = 0; a < equipes.size(); a++) {
                    for (int b = 0; b < equipes.get(a).getJogadores().size(); b++) {

                        Jogador outro = equipes.get(a).getJogadores().get(b);

                        if (outro.getEstatisticas() != null &&
                            (outro.getEstatisticas().getKdr() > jogador.getEstatisticas().getKdr() ||
                            (outro.getEstatisticas().getKdr() == jogador.getEstatisticas().getKdr() &&
                             (a < i || (a == i && b < j))))) {

                            anteriores++;
                        }
                    }
                }

                if (anteriores == x) {
                    maior = jogador;
                }
            }
        }

        if (maior != null) {
            System.out.println(maior.getNickname() + " - " +
                maior.getEstatisticas().getKdr() + " KDR");
        }
    }

 }

    public boolean podeGerenciarPartidas() {
        return equipes.size() >= 2 && equipes.size() % 2 == 0;
    }

}











