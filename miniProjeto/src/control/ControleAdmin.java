package control;

import model.Admin;

import java.util.List;

public class ControleAdmin {
    private Admin admin = new Admin();

    public ControleAdmin() {
        admin.criarDados();
    }
    public List<String> getListaItinerarios(){
        return admin.mostrarItinerarios();
    }
    public List<String> getListaPassagens(){
        return admin.mostrarPassagens();
    }
}
