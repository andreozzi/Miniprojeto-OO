package entities;

import product.Passagem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Cliente {
    private String nome;
    private String email;
    private String usuario;
    private int idCliente;
    private List<Passagem> minhasPassagens;

    public Cliente(String nome, String email, String usuario, int idCliente) {
        this.nome = nome;
        this.email = email;
        this.usuario = usuario;
        this.idCliente = idCliente;
        List<Passagem> minhasPassagens = new ArrayList<Passagem>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }





}
