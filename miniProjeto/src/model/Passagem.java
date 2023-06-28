package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Passagem  {
    private int idPassagem;
    private double preco;
    private Itinerario itinerario;


    public Passagem(int idPassagem, double preco, Itinerario itinerario) {
        this.idPassagem = idPassagem;
        this.preco = preco;
        this.itinerario = itinerario;

    }





    public Itinerario getItinerario() {
        return itinerario;
    }

    public void setItinerario(Itinerario itinerario) {
        this.itinerario = itinerario;
    }

    public int getIdPassagem() {
        return idPassagem;
    }

    public void setIdPassagem(int idPassagem) {
        this.idPassagem = idPassagem;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public String toString(){

        return "Passagem #"+idPassagem+"\n"+" "
                +"Id da Passagem: "+idPassagem+"\n"+" "
                +"Itinerario: "+itinerario.toString();

    }
}