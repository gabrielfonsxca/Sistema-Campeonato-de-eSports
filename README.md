# Sistema de Gerenciamento de Campeonato de eSports

## Integrantes

- Gabriel Fonseca - Desenvolvimento do sistema, regras de negócio, testes automatizados e integração das funcionalidades.
- Eduardo do Amaral - Desenvolvimento do sistema.
- Diego Viana - Testes com JUnit.

---

## Descrição do Problema

A organização de campeonatos de eSports envolve o gerenciamento de diversas informações, como equipes, jogadores, partidas, mapas, horários e estatísticas.

O sistema foi desenvolvido com o objetivo de facilitar o gerenciamento de um campeonato de eSports, só que mais pensado no Valorant. Porém, ele funciona para qualquer outro FPS, permitindo cadastrar equipes e jogadores, organizar partidas, controlar os mapas utilizados e acompanhar informações relacionadas ao campeonato.

Os possíveis usuários do sistema são organizadores e administradores de campeonatos de eSports.

---

## Funcionalidades

O sistema possui funcionalidades como:

- Cadastro de equipes;
- Remoção de equipes;
- Consulta de equipes participantes;
- Cadastro de jogadores;
- Remoção de jogadores;
- Associação de jogadores às equipes;
- Programação de partidas;
- Desmarcação de partidas;
- Consulta das partidas programadas;
- Ordenação das partidas por data e horário;
- Associação de equipes às partidas;
- Associação de mapas às partidas;
- Controle das estatísticas dos jogadores;
- Exportação dos dados das equipes e jogadores para CSV;
- Exportação das partidas para CSV;
- Testes automatizados das regras de negócio utilizando JUnit.

---

## Principais Classes

### Campeonato

Responsável pelo gerenciamento geral do campeonato.

Armazena as equipes e partidas cadastradas e possui métodos responsáveis por operações como cadastro, remoção, consulta e validação das regras relacionadas ao campeonato.

### Equipe

Representa uma equipe participante do campeonato.

Possui informações como:

- Nome;
- Tag;
- País;
- Lista de jogadores.

Também é responsável pelo gerenciamento dos jogadores pertencentes à equipe.

### Jogador

Representa um jogador participante do campeonato.

Possui informações como:

- Nickname;
- Nacionalidade;
- Função;
- Estatísticas.

### Partida

Representa uma partida do campeonato.

Possui informações como:

- Data e horário;
- Equipes participantes;
- Mapas da partida.

Cada partida possui duas equipes participantes e até três mapas.

### Mapa

Representa um mapa utilizado em uma partida.

Os objetos dessa classe são associados às partidas do campeonato.

### Estatistica

Responsável por armazenar informações relacionadas ao desempenho dos jogadores.

Pode armazenar informações como:

- Abates;
- Mortes;
- Assistências;
- KDR.

### CSV

Responsável pela exportação dos dados do sistema para arquivos no formato CSV.

O sistema gera arquivos contendo informações das equipes, jogadores e partidas.

---

## Regras de Negócio

O sistema possui diversas regras de negócio, entre elas:

1. Cada equipe só pode ter 5 jogadores
2. Não é possível criar duas equipes com o mesmo nome
3. O campeonato deve ter no máximo 16 equipes
4. Um jogador só pode estar em uma equipe
5. Não é possível agendar duas partidas no mesmo horário
6. Cada partida só pode ter 3 mapas
7. Só é possível criar ou desmarcar uma partida caso haja no mínimo 2 equipes E um número par de equipes inscritas no campeonato
8. Calcular KDR (Abates / Mortes)
9. Adicionar E remover uma equipe no campeonato com base no nome
10. Listar os jogadores em ordem decrescente de Abates, Mortes, Assistência e KDR
11. É possível desmarcar uma partida com base no horário


---

## Tecnologias Utilizadas

- Java;
- Gradle;
- JUnit;
- CSV;

---

## Ferramentas de Desenvolvimento

- IntelliJ IDEA;
- Git;
- GitHub.

---

## Configuração

Para executar o projeto é necessário possuir:

- Java JDK instalado;
- Git instalado, caso seja necessário clonar o repositório;
- Uma IDE com suporte a Java, como IntelliJ IDEA.

O projeto utiliza Gradle para gerenciamento e organização.

O Gradle Wrapper já está incluído no projeto, através dos arquivos:

- `gradlew`
- `gradlew.bat`

Portanto, não é necessário instalar o Gradle manualmente.

---

## Execução

### IntelliJ IDEA

1. Clone ou baixe este repositório.
2. Abra a pasta do projeto no IntelliJ IDEA.
3. Aguarde o Gradle carregar as dependências.
4. Localize a classe principal do sistema.
5. Execute o método `main`.
6. O menu interativo será exibido no terminal.

---

## Testes Automatizados

O projeto utiliza JUnit para testar automaticamente algumas das regras de negócio.

Entre os testes realizados estão:

- Limite máximo de 5 jogadores por equipe;
- Limite máximo de 16 equipes no campeonato;
- Impedimento de equipes com nomes repetidos;
- Verificação de jogadores já inscritos;
- Impedimento de duas partidas no mesmo horário;
- Limite de 3 mapas por partida;
- Impedimento de criação de partidas quando a quantidade de equipes inscritas é inválida.

## Utilização de IA

Durante o desenvolvimento do projeto, ferramentas de Inteligência Artificial foram utilizadas como apoio ao aprendizado, desenvolvimento, revisão e documentação do sistema.

| Ferramenta | Objetivo | Resumo do uso | Revisão pelo Dev |
|---|---|---|---|
| ChatGPT | Apoio ao desenvolvimento e aprendizado. Documentação e README | Foi utilizado para auxiliar na compreensão de conceitos de Java e Programação Orientada a Objetos, organização das classes, uso de ArrayList, LocalDateTime, Gradle, CSV, JUnit, Git e GitHub. Também auxiliou na elaboração e análise das regras de negócio, criação de testes automatizados. Auxiliou na estruturação, formatação e redação inicial do ficheiro README.md e guias de instalaçã. | As sugestões fornecidas pela IA foram analisadas pelos desenvolvedores antes de serem utilizadas. O código foi executado e testado manualmente e com JUnit, sendo feitas adaptações conforme a estrutura e as regras do projeto. |

### Executar os testes no Windows

```powershell
.\gradlew.bat test