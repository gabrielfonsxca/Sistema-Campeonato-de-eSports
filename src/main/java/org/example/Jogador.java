package org.example;
import java.util.ArrayList;
public class Jogador {
    private String nickname;
    private String nacionalidade;
    private String funcao;
    
    private Estatistica estatisticas;

    public Jogador(String nickname, String nacionalidade, String funcao, Estatistica estatisticas) {
        this.nickname = nickname;
        this.nacionalidade = nacionalidade;
        this.funcao = funcao;
        this.estatisticas = estatisticas;
    }
    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public String getNacionalidade() {
        return nacionalidade;
    }
    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }
    public String getFuncao() {
        return funcao;
    }
    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }
    public void setEstatisticas(Estatistica estatisticas) {
    this.estatisticas = estatisticas;
}
    
    public Estatistica getEstatisticas() {
        return estatisticas;
    }



}
