package classes;

public class Usuario {
    private String nome;
    private int cpf;
    private int cep;

    Usuario(String nome, int cpf,int cep){
        this.nome = nome;
        this.cpf = cpf;
        this.cep = cep;
    }

    Usuario(){}

    public String getNome(){
        return nome;
    }

    public int getCpf(){
        return cpf;
    }

    public int getCep(){
        return cep;
    }

    @Override
    public String toString(){
        return this.nome;
    }
}
