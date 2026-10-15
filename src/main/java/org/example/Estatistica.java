package org.example;

public class Estatistica {
    private double abates;
    private double mortes;
    private double assistencias;

    public Estatistica(double abates, double mortes, double assistencias) {
       
       if(abates  >= 0){
        this.abates = abates;
       }
       if(mortes >= 0){
        this.mortes = mortes;
       }
         if(assistencias >= 0){
        this.assistencias = assistencias;
        }
    }
    public double getAbates() {
        return abates;
    }
    public void setAbates(double abates) {
        this.abates = abates;
    }
    public double getMortes() {
        return mortes;
    }
    public void setMortes(double mortes) {
        this.mortes = mortes;
    }
    public double getAssistencias() {
        return assistencias;
    }
    public void setAssistencias(double assistencias) {
        this.assistencias = assistencias;
    }

    private double calcularKdr(){
        if (mortes == 0) {
        return abates;
    }
        
        return abates / mortes;
        }

    public double getKdr() {
             return calcularKdr();
        }
}
