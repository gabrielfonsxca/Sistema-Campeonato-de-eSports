package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EquipeTest {

    @Test
    void naoDevePermitirMaisDeCincoJogadores() {

        Equipe equipe = new Equipe(
                "LOUD",
                "LOUD",
                "Brasil",
                new ArrayList<>()
        );

        equipe.adicionarJogador(
                new Jogador("Jogador1", "Brasil", "Duelista", null)
        );

        equipe.adicionarJogador(
                new Jogador("Jogador2", "Brasil", "Iniciador", null)
        );

        equipe.adicionarJogador(
                new Jogador("Jogador3", "Brasil", "Sentinela", null)
        );

        equipe.adicionarJogador(
                new Jogador("Jogador4", "Brasil", "Controlador", null)
        );

        equipe.adicionarJogador(
                new Jogador("Jogador5", "Brasil", "Duelista", null)
        );

        // Tentamos adicionar um sexto jogador
        equipe.adicionarJogador(
                new Jogador("Jogador6", "Brasil", "Iniciador", null)
        );

        // Mesmo depois da tentativa, devem existir somente 5
        assertEquals(5, equipe.getJogadores().size());
    }
}