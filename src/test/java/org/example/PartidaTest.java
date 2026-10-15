package org.example;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PartidaTest {

    @Test
    void naoDevePermitirMaisDeTresMapas() {

        LocalDateTime horario =
                LocalDateTime.of(2026, 10, 20, 19, 30);

        Partida partida = new Partida(
                horario,
                new ArrayList<>(),
                new ArrayList<>()
        );

        partida.adicionarMapa(new Mapa("Haven"));
        partida.adicionarMapa(new Mapa("Ascent"));
        partida.adicionarMapa(new Mapa("Bind"));

        // Tentativa de adicionar o quarto mapa
        partida.adicionarMapa(new Mapa("Lotus"));

        assertEquals(3, partida.getMapas().size());
    }
}