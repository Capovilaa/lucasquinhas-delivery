package classes;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Vector;

public class Restaurantes {
    public static ArrayList<Lanche> cardapio = new ArrayList<Lanche>();
    Pedido pedido = new Pedido();

    ArrayList<String> auxCardapio = new ArrayList<>();

    private String nome;
    private int cnpj;
    private int localizacao;

    JTextField produto = new JTextField();
    JTextField valorProduto = new JTextField();

    Restaurantes(String nome, int cnpj, int localizacao) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.localizacao = localizacao;
    }

    Restaurantes(){}
    public String getNome(){
        return nome;
    }

    public void telaCardapio(int idRestaurante, ArrayList<Usuario> arrayUsuario){
        auxCardapio.clear();

        for (Lanche lanche: cardapio) {
            if (lanche.getIdRestaurante() == idRestaurante){
                auxCardapio.add(lanche.getItem());
                System.out.println(lanche.getItem());
            }
        }

        Vector vetCardapio = new Vector(this.auxCardapio);

        JFrame cardapioScreen = new JFrame();
        cardapioScreen.setBounds(150,150,390,844);
        cardapioScreen.setLayout(null);

        // ComboBox cardápio do restaurante
        JComboBox cbCardapio = new JComboBox<>(vetCardapio);
        cbCardapio.setBounds(70,350,252,50);

        // Texto
        JLabel informaQuantidade = new JLabel("Quantidade:");
        informaQuantidade.setBounds(70,510,252,50);

        // Input quantidade
        JTextField quantidade = new JTextField();
        quantidade.setSize(252,50);
        quantidade.setLocation(70,550);

        // Botão comprar
        JButton btComprar = new JButton("");
        btComprar.setSize(215,50);
        btComprar.setLocation(90,730);
        btComprar.setOpaque(false);

        // Background
        ImageIcon imagemDeFundo = new ImageIcon("src/img/cardapio.png");
        JLabel background = new JLabel(imagemDeFundo);
        background.setBounds(0,0,390,844);

        // OnClick
        btComprar.addActionListener(e -> this.realizarPedido(cbCardapio.getSelectedItem().toString(),
                buscaPreco((String) cbCardapio.getSelectedItem()),Integer.parseInt(quantidade.getText()), arrayUsuario));

        cardapioScreen.add(informaQuantidade);
        cardapioScreen.add(quantidade);
        cardapioScreen.add(background);
        cardapioScreen.add(btComprar);
        cardapioScreen.add(cbCardapio);

        cardapioScreen.setVisible(true);
    }

    public void telaAddProduto(int idRestaurante){
        JFrame addProdutoScreen = new JFrame();
        addProdutoScreen.setBounds(150,150,390,844);
        addProdutoScreen.setLayout(null);

        // Input produto
        produto.setSize(252,50);
        produto.setLocation(70,350);

        // Input preco produto
        valorProduto.setSize(252,50);
        valorProduto.setLocation(70,450);

        // Botão remover produto
        JButton btRemover = new JButton("Remover");
        btRemover.setSize(215,50);
        btRemover.setLocation(90,630);
        btRemover.setOpaque(false);

        // Botão cadastrar produto
        JButton btCadProd = new JButton("");
        btCadProd.setSize(215,50);
        btCadProd.setLocation(90,730);
        btCadProd.setOpaque(false);

        // Background
        ImageIcon imagemDeFundo = new ImageIcon("src/img/cadprod.png");
        JLabel background = new JLabel(imagemDeFundo);
        background.setBounds(0,0,390,844);

        // OnClick
        btCadProd.addActionListener(e -> this.adicionarProduto(idRestaurante,
                produto.getText(),
                Double.parseDouble(valorProduto.getText())));
        btRemover.addActionListener(e -> this.removerProduto(idRestaurante, produto.getText()));

        addProdutoScreen.add(produto);
        addProdutoScreen.add(valorProduto);
        addProdutoScreen.add(background);
        addProdutoScreen.add(btCadProd);
        addProdutoScreen.add(btRemover);

        addProdutoScreen.setVisible(true);
    }

    ArrayList<String> imprimirCardapio(int idRestaurante){
        ArrayList<String> aux = new ArrayList<String>();
        for (Lanche item: cardapio) {
            if (item.getIdRestaurante() == idRestaurante){
                aux.add(item.getItem());
            }
        }
        return aux;
    }

    void adicionarProduto(int idRestaurante, String item, double preco){
        cardapio.add(new Lanche(idRestaurante, item, preco));
        System.out.println(this.imprimirCardapio(idRestaurante));

        this.produto.setText("");
        this.valorProduto.setText("");
    }

    void removerProduto(int idRestaurante, String nome){
        int auxCount = 0;
        if(cardapio.size() == 1){
            auxCount = 0;
        }else{
            for (Lanche item: cardapio) {
                if (item.getIdRestaurante() == idRestaurante){
                    if (nome.equalsIgnoreCase(item.getItem())){
                        auxCount +=1;
                    }
                }
            }
        }
        cardapio.remove(auxCount);

        this.produto.setText("");
        this.valorProduto.setText("");
    }

    Double buscaPreco(String item){
        double preco = 0;
        for (Lanche lanche: cardapio) {
            if (lanche.getItem().equalsIgnoreCase(item)){
                preco = lanche.getPreco();
                System.out.println(lanche.getPreco());
            }
        }
        return preco;
    }

    void realizarPedido(String item, double preco, int quantidade , ArrayList<Usuario> arrayUsuario) {
        pedido.fazerPedido(item,preco,quantidade);
        pedido.telaPedido(arrayUsuario);
    }
}
