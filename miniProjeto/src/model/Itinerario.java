package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class  Itinerario {
    private EstadosBrasileiros localOrigem;
    private EstadosBrasileiros localDestino;
    private LocalDate date;


    public Itinerario(EstadosBrasileiros localOrigem, EstadosBrasileiros localDestino, LocalDate date) {
        this.localOrigem = localOrigem;
        this.localDestino = localDestino;
        this.date = date;
    }



    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public EstadosBrasileiros getLocalOrigem() {
        return localOrigem;
    }

    public void setLocalOrigem(EstadosBrasileiros localOrigem) {
        this.localOrigem = localOrigem;
    }

    public EstadosBrasileiros getLocalDestino() {
        return localDestino;
    }

    public void setLocalDestino(EstadosBrasileiros localDestino) {
        this.localDestino = localDestino;
    }
    @Override
    public String toString(){
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataFormatada = date.format(formatador);
        return "Local de Origem: "+localOrigem+"\n"+" "
                +"Local de Destino: "+localDestino+"\n"+" "
                +"Data: "+dataFormatada;
    }
}
