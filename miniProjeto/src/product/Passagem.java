package product;

import java.time.LocalDate;
import java.util.Date;

public class Passagem extends Itinerario{
    private int idPassagem;
    private double preco;

    public Passagem(String localOrigem, String localDestino, LocalDate date, int idPassagem, double preco) {
        super(localOrigem, localDestino, date);
        this.idPassagem = idPassagem;
        this.preco = preco;
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
        return "Passagem #"+idPassagem+"\n"
        +"Data: " +date+"\n"
                +"Local de Origem: "+localOrigem+"\n"+
                "Local de Destino: "+localDestino;
    }
}
