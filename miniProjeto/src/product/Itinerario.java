package product;

import java.time.LocalDate;
import java.util.Date;

public abstract class  Itinerario {
    protected String localOrigem;
    protected String localDestino;
    protected LocalDate date;

    public Itinerario(String localOrigem, String localDestino, LocalDate date) {
        this.localOrigem = localOrigem;
        this.localDestino = localDestino;
        this.date = date;
    }

    public String getLocalOrigem() {
        return localOrigem;
    }

    public void setLocalOrigem(String localOrigem) {
        this.localOrigem = localOrigem;
    }

    public String getLocalDestino() {
        return localDestino;
    }

    public void setLocalDestino(String localDestino) {
        this.localDestino = localDestino;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
