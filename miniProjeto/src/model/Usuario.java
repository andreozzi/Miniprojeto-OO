package model;

public abstract class Usuario {
    protected String nome;
    protected Integer idade;
    protected String usuario;
    protected String email;

    public Usuario() {
    }

    public Usuario(String nome, Integer idade, String usuario, String email) {
        this.nome = nome;
        this.idade = idade;
        this.usuario = usuario;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}

