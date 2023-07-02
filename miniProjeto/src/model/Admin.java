package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Admin extends Usuario {

    private List<Passagem> passagemLista;
    private List<Itinerario> listaItinerarios;

    public Admin() {
        this.passagemLista = new ArrayList<Passagem>();
        this.listaItinerarios = new ArrayList<Itinerario>();
    }

    public Admin(String nome, Integer idade, String usuario, String email) {
        super(nome, idade, usuario, email);
        this.passagemLista = new ArrayList<Passagem>();
        this.listaItinerarios = new ArrayList<Itinerario>();
    }

    public void criarPassagem(Itinerario itinerario, double preco){
        int idPassagem = passagemLista.size();
        Passagem p = new Passagem(idPassagem,preco,itinerario);
        passagemLista.add(p);

    }

    public void deletarPassagem(int index){
        passagemLista.remove(index);

    }
    public void atualizarPassagem(int index, EstadosBrasileiros origem, EstadosBrasileiros destino,LocalDate date, double preco){
        passagemLista.remove(index);
        passagemLista.add(index, new Passagem(index, preco,new Itinerario(origem,destino,date)));



    }
    public List<String> getPassagens(){
        List<String> passagens = new ArrayList<>();
        for(Passagem p:passagemLista){
            passagens.add(p.toString());
        }
        return passagens;
    }
    public List<String> buscarItinerariosData(LocalDate data){
        List<String> itinerariosFiltrados = new ArrayList<String>();
        for(Itinerario i :listaItinerarios){
            if(i.getDate().equals(data)){
                itinerariosFiltrados.add(i.toString());
            }
        }
        return itinerariosFiltrados;

    }

    public List<String> buscarItinerariosLocalOrigem(EstadosBrasileiros origem){
        List<String> itinerariosFiltrados = new ArrayList<String>();
        for(Itinerario i :listaItinerarios){
            if(i.getLocalOrigem().equals(origem)){
                itinerariosFiltrados.add(i.toString());
            }
        }
        return itinerariosFiltrados;

    }

    public List<String> buscarItinerariosLocalDestino(EstadosBrasileiros destino){
        List<String> itinerariosFiltrados = new ArrayList<String>();
        for(Itinerario i :listaItinerarios){
            if(i.getLocalDestino().equals(destino)){
                itinerariosFiltrados.add(i.toString());
            }
        }
        return itinerariosFiltrados;

    }

    public List<String> buscarItinerariosRefinada(LocalDate data, EstadosBrasileiros origem, EstadosBrasileiros destino){
        List<String> itinerariosFiltrados = new ArrayList<String>();
        for(Itinerario i :listaItinerarios){
            if(i.getDate().equals(data) && i.getLocalOrigem().equals(origem) && i.getLocalDestino().equals(destino)){
                itinerariosFiltrados.add(i.toString());
            }
        }
        return itinerariosFiltrados;

    }

    public void criarItinerario(EstadosBrasileiros origem, EstadosBrasileiros destino, LocalDate date){
        Itinerario itinerario = new Itinerario(origem,destino, date);
        listaItinerarios.add(itinerario);

    }
    public void deletarItinerario(int index){
        listaItinerarios.remove(index);
        for(Passagem p : passagemLista){
            if (listaItinerarios.get(index).equals(p.getItinerario())){
                listaItinerarios.remove(p);
            }
        }


    }

    public void atualizarItinerario(EstadosBrasileiros novaOrigem, EstadosBrasileiros novoDestino, LocalDate novaData, int index){

        listaItinerarios.remove(index);
        Itinerario i = new Itinerario(novaOrigem, novoDestino, novaData);
        listaItinerarios.add(index,i);

    }
    public List<String> getItinerarios(){
        List<String> itinerarios = new ArrayList<>();
        for(Itinerario i:listaItinerarios) {
            itinerarios.add(i.toString());
        }
        return itinerarios;
    }
    public void criarDados(){
        Random random = new Random();
        for (int i = 1; i < 20; i++) {
            int indexOrigem = random.nextInt(EstadosBrasileiros.values().length);
            int indexDestino = random.nextInt(EstadosBrasileiros.values().length);
            EstadosBrasileiros randomOrigem = EstadosBrasileiros.values()[indexOrigem];
            EstadosBrasileiros randomDestino = EstadosBrasileiros.values()[indexDestino];

            LocalDate dataInicial = LocalDate.of(2023, 1, 1);
            LocalDate dataFinal = LocalDate.of(2026, 12, 31);
            long EpochDayInicial = dataInicial.toEpochDay();
            long EpochDayFinal = dataFinal.toEpochDay();
            long randomEpochDay = ThreadLocalRandom.current().nextLong(EpochDayInicial, EpochDayFinal + 1);
            LocalDate randomData = LocalDate.ofEpochDay(randomEpochDay);
            Itinerario itinerario = new Itinerario(randomOrigem, randomDestino,randomData);
            listaItinerarios.add(itinerario);
            double precoMin = 200.00;
            double precoMax = 1500.00;
            double randomValue = precoMin + (precoMax - precoMin) * random.nextDouble();
            Passagem passagem = new Passagem(i,randomValue, itinerario);
            passagemLista.add(passagem);

        }



    }
    public List<Itinerario> getListaItinerarios(){
        return listaItinerarios;
    }

}
