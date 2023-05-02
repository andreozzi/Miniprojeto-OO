package entities;

import product.Passagem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Admin {
    private String userName;
    private List<Passagem> passagemLista;

    public Admin(String userName) {
        this.userName = userName;
        this.passagemLista = new ArrayList<Passagem>();
    }

    public void criarPassagem(String localOrigem, String localDestino, LocalDate date, int idPassagem, double preco){
        Passagem p = new Passagem(localOrigem, localDestino, date,  idPassagem,  preco);
        passagemLista.add(p);

    }
    public void deletarPassagem(int idPassagem){
        for (Passagem p:passagemLista) {
            if(p.getIdPassagem() == idPassagem){
                passagemLista.remove(p);
            }

        }
    }
    public void atualizarPassagem(int idPassagemAntigo, String localOrigem, String localDestino, LocalDate date, int idPassagem, double preco){
        for(Passagem passagem:passagemLista){
            if(passagem.getIdPassagem()==idPassagemAntigo){
                passagemLista.remove(passagem);
                Passagem p = new Passagem(localOrigem, localDestino, date, idPassagem, preco);
                passagemLista.add(p);
            }
        }


    }
    public void mostrarPassagens(){
        for(Passagem p:passagemLista){
            System.out.println(p.toString());
        }
    }
    public void buscarPassagensData(LocalDate data){
        List<Passagem> passagensFiltradas = new ArrayList<Passagem>();
        for(Passagem p:passagemLista){
            if(p.getDate() == data){
                passagensFiltradas.add(p);
            }
        }
        for(Passagem p:passagensFiltradas){
            System.out.println(p.toString());
        }
    }
    public void agendarPassagem(int idPassagem){
        for(Passagem p:passagemLista){
            if(p.getIdPassagem()== idPassagem){
                System.out.println("Você escolheu a passagem: "+p.toString());
            }
        }
    }
}
