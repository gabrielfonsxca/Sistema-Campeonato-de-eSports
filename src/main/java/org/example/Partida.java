package org.example;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Partida {
    private LocalDateTime dataHora;
    private ArrayList<Mapa> mapas = new ArrayList<>();
    private ArrayList<Equipe> equipes = new ArrayList<>();

    public Partida(LocalDateTime dataHora, ArrayList<Mapa> mapas, ArrayList<Equipe> equipes) {
        this.dataHora = dataHora;
        this.mapas = mapas;
        this.equipes = equipes;
    }
    public LocalDateTime getDataHora() {
        return dataHora;
    }
    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
    public ArrayList<Mapa> getMapas() {
        return mapas;
    }
    public void setMapas(ArrayList<Mapa> mapas) {
        this.mapas = mapas;
    }
    public ArrayList<Equipe> getEquipes() {
        return equipes;
    }
    public void setEquipes(ArrayList<Equipe> equipes) {
        this.equipes = equipes;
    }

    public void adicionarEquipe(Equipe equipe) {
        if (equipes.size() >= 2) {
            System.out.println("A partida já possui duas equipes.");
            return;
        }
        if (equipes.contains(equipe)) {
            System.out.println("Essa equipe já está na partida.");
            return;
        }

        equipes.add(equipe);
    }

    public void adicionarMapa(Mapa mapa) {
        if (mapas.size() >= 3) {
            System.out.println("A partida já possui 3 mapas.");
            return;
        }

        mapas.add(mapa);
    }




}
