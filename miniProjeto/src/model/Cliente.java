package model;

public class Cliente extends Usuario{
    private Double carteira;

    public Cliente(String nome, Integer idade, String usuario, String email, Double carteira) {
        super(nome, idade, usuario, email);
        this.carteira = carteira;
    }

    public Double getCarteira() {
        return carteira;
    }

    public void setCarteira(Double carteira) {
        this.carteira = carteira;
    }
}
