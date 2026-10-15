package org.example;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CampeonatoTest {

    @Test
    void naoDevePermitirMaisDeDezesseisEquipes() {

        Campeonato campeonato = new Campeonato("Champions", 2000000, new ArrayList<>(), new ArrayList<>());

        for (int i = 1; i <= 17; i++) {

            Equipe equipe = new Equipe(
                    "Equipe " + i,
                    "TAG" + i,
                    "Brasil",
                    new ArrayList<>()
            );

            campeonato.adicionarEquipe(equipe);
        }

        assertEquals(16, campeonato.getEquipes().size());
    }

    @Test
    void naoDevePermitirEquipesComMesmoNome() {

        Campeonato campeonato = new Campeonato("Champions", 2000000, new ArrayList<>(), new ArrayList<>());

        Equipe equipe1 = new Equipe(
                "LOUD",
                "LOUD",
                "Brasil",
                new ArrayList<>()
        );

        Equipe equipe2 = new Equipe(
                "LOUD",
                "LDD",
                "Brasil",
                new ArrayList<>()
        );

        campeonato.adicionarEquipe(equipe1);
        campeonato.adicionarEquipe(equipe2);

        assertEquals(1, campeonato.getEquipes().size());
    }

    @Test
    void deveIdentificarJogadorJaInscritoEmOutraEquipe() {

        Campeonato campeonato = new Campeonato("Champions", 2000000, new ArrayList<>(), new ArrayList<>());

        Equipe equipe1 = new Equipe(
                "LOUD",
                "LOUD",
                "Brasil",
                new ArrayList<>()
        );

        Jogador jogador = new Jogador(
                "Gabriel",
                "Brasil",
                "Duelista",
                null
        );

        equipe1.adicionarJogador(jogador);

        campeonato.adicionarEquipe(equipe1);

        assertTrue(campeonato.jogadorJaInscrito("Gabriel"));
    }

    @Test
    void naoDevePermitirDuasPartidasNoMesmoHorario() {

        Campeonato campeonato = new Campeonato("Champions", 2000000, new ArrayList<>(), new ArrayList<>());

        // Precisamos de pelo menos 2 equipes para poder criar partidas
        Equipe equipe1 = new Equipe(
                "LOUD",
                "LOUD",
                "Brasil",
                new ArrayList<>()
        );

        Equipe equipe2 = new Equipe(
                "FURIA",
                "FUR",
                "Brasil",
                new ArrayList<>()
        );

        campeonato.adicionarEquipe(equipe1);
        campeonato.adicionarEquipe(equipe2);

        LocalDateTime horario =
                LocalDateTime.of(2026, 10, 20, 19, 30);

        Partida partida1 = new Partida(horario, new ArrayList<>(), new ArrayList<>());
        Partida partida2 = new Partida(horario, new ArrayList<>(), new ArrayList<>());

        campeonato.adicionarPartida(partida1);
        campeonato.adicionarPartida(partida2);

        assertEquals(1, campeonato.getPartidas().size());
    }

    @Test
    void naoDevePermitirPartidaComQuantidadeImparDeEquipes() {

        Campeonato campeonato = new Campeonato("Champions", 2000000, new ArrayList<>(), new ArrayList<>());

        campeonato.adicionarEquipe(
                new Equipe("LOUD", "LOUD", "Brasil", new ArrayList<>())
        );

        campeonato.adicionarEquipe(
                new Equipe("FURIA", "FUR", "Brasil", new ArrayList<>())
        );

        campeonato.adicionarEquipe(
                new Equipe("MIBR", "MIBR", "Brasil", new ArrayList<>())
        );

        LocalDateTime horario =
                LocalDateTime.of(2026, 10, 25, 19, 30);

        Partida partida = new Partida(
                horario,
                new ArrayList<>(),
                new ArrayList<>()
        );

        campeonato.adicionarPartida(partida);

        assertEquals(0, campeonato.getPartidas().size());
    }
}