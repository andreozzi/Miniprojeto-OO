package control;

import model.Admin;
import model.EstadosBrasileiros;
import model.Itinerario;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class ControleAdmin {
    private Admin admin = new Admin();

    public ControleAdmin() {
        admin.criarDados();
    }
    public List<String> getListaItinerarios(){
        return admin.getItinerarios();
    }
    public List<String> getListaPassagens(){
        return admin.getPassagens();
    }
    public List<String> bucarItinerarioData(String data) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy");
        LocalDate dataBusca = sdf.parse(data).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
       return admin.buscarItinerariosData(dataBusca);
    }

    public List<String> bucarItinerarioLocal(EstadosBrasileiros estadoOrigem)  {

        return admin.buscarItinerariosLocalOrigem(estadoOrigem);
    }
    public void criarPassagem(String data, EstadosBrasileiros origem, EstadosBrasileiros destino, Double preco) {
        DateTimeFormatter d = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataConvertida = LocalDate.parse(data,d);
        Itinerario itinerario = new Itinerario(origem,destino, dataConvertida);
        admin.criarPassagem(itinerario,preco);

    }

    public void criarItinerario(String data, EstadosBrasileiros origem, EstadosBrasileiros destino) {
        DateTimeFormatter d = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataConvertida = LocalDate.parse(data,d);


        admin.criarItinerario(origem,destino,dataConvertida);

    }
    public void atualizarItinerario(String data, EstadosBrasileiros origem, EstadosBrasileiros destino, int index){
        DateTimeFormatter d = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataConvertida = LocalDate.parse(data,d);

        admin.atualizarItinerario(origem,destino, dataConvertida, index);
    }
    public void deletarItinerario(int index){
        admin.deletarItinerario(index);
    }
    public void deletarPassagem(int index){
        admin.deletarPassagem(index);
    }
    public void atualizarPassagem(int index, EstadosBrasileiros origem, EstadosBrasileiros destino, String dataString, double preco){
        DateTimeFormatter d = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        admin.atualizarPassagem(index,origem,destino,LocalDate.parse(dataString,d),preco);

    }
    public List<String> buscaItinerariosDestino(EstadosBrasileiros destino){
        return admin.buscarItinerariosLocalOrigem(destino);

    }
    public List<String> buscarItinerariosRefinado(String data, EstadosBrasileiros origem, EstadosBrasileiros destino){
        DateTimeFormatter d = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataConvertida = LocalDate.parse(data,d);
        return admin.buscarItinerariosRefinada(dataConvertida,origem,destino);

    }
}
