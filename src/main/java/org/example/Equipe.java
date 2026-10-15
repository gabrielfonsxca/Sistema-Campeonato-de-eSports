package org.example;

import java.util.ArrayList;

public class Equipe {
    private String nome;
    private String tag;
    private String pais;
    private ArrayList<Jogador> jogadores = new ArrayList<>();

    public Equipe(String nome, String tag, String pais, ArrayList<Jogador> jogadores) {
        this.nome = nome;
        this.tag = tag;
        this.pais = pais;
        this.jogadores = jogadores;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getTag() {
        return tag;
    }
    public void setTag(String tag) {
        this.tag = tag;
    }
    public String getPais() {
        return pais;
    }
    public void setPais(String pais) {
        this.pais = pais;
    }
    public ArrayList<Jogador> getJogadores() {
        return jogadores;
    }
    public void setJogadores(ArrayList<Jogador> jogadores) {
        this.jogadores = jogadores;
    }

    public void adicionarJogador(Jogador j) {
        if (jogadores.size() >= 5) {
            System.out.println("A equipe já possui 5 jogadores.");
            return;
        }
        for(Jogador jogador : jogadores) {
            if (jogador.getNickname().equalsIgnoreCase(j.getNickname())) {
                System.out.println("Esse jogador já está em uma equipe");
                return;
            }
        }
        jogadores.add(j);
    }

    public boolean contemJogador(String nickname) {
        for (Jogador jogador : jogadores) {
            if (jogador.getNickname().equalsIgnoreCase(nickname)) {
                return true;
            }
        }

        return false;
    }

    public void removerJogador(String nickname) {
        for (int i = 0; i < jogadores.size(); i++) {

            if (jogadores.get(i).getNickname().equalsIgnoreCase(nickname)) {
                jogadores.remove(i);
                System.out.println("Jogador removido com sucesso!");
                return;
            }
        }
        System.out.println("Jogador não encontrado nessa equipe.");
    }



}
