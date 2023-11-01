package classes;

public class Lanche {
    private int idRestaurante;
    private String item;
    private double preco;

    Lanche(int idRestaurante, String item, double preco){
        this.idRestaurante = idRestaurante;
        this.item = item;
        this.preco = preco;
    }

    public String getItem(){
        return item;
    }

    public double getPreco(){
        return preco;
    }

    public  int getIdRestaurante(){
        return idRestaurante;
    }
}
