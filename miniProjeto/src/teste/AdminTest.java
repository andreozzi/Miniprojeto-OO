package teste;

import model.Admin;
import model.EstadosBrasileiros;
import model.Itinerario;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AdminTest {

    @Test
    void criarItinerario() {
        Admin admin = new Admin();
        admin.criarDados();
        int qtdItinerariosInicial = admin.getItinerarios().size();
        admin.criarItinerario(EstadosBrasileiros.ESPIRITOSANTO, EstadosBrasileiros.ALAGOAS, LocalDate.now());
        int qtdItinerariosAposCriarPassagens = admin.getItinerarios().size();
        assertTrue(qtdItinerariosAposCriarPassagens-qtdItinerariosInicial==1);
    }

    @Test
    void deletarItinerario() {
        Admin admin = new Admin();
        admin.criarDados();
        int qtdItinerariosInicial = admin.getItinerarios().size();
        admin.deletarItinerario(0);
        int qtdItinerariosAposDeletar = admin.getItinerarios().size();
        assertTrue(qtdItinerariosInicial-qtdItinerariosAposDeletar==1);
    }

    @Test
    void atualizarItinerario() {
        Admin admin = new Admin();
        admin.criarDados();
        Itinerario itinerarioInicial = admin.getListaItinerarios().get(0);
        admin.atualizarItinerario(EstadosBrasileiros.ESPIRITOSANTO, EstadosBrasileiros.ALAGOAS, LocalDate.now(),0);
        Itinerario itinerarioatualizado = admin.getListaItinerarios().get(0);
        assertFalse(itinerarioInicial.equals(itinerarioatualizado));

    }
}