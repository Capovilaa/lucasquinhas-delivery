package classes;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Vector;

public class Pedido {
    ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
    ArrayList<String> auxUsers = new ArrayList<>();

    private String item;
    private double preco;
    private int quantidade;
    private double total;

    public double getTotal() {
        return total;
    }

    Pedido (String item, double preco, int quantidade, double total){
        this.item = item;
        this.preco = preco;
        this.quantidade = quantidade;
        this.total = total;
    }

    Pedido (){}

    JLabel valorTotal = new JLabel("Valor total: R$ ");

    public void telaPedido(ArrayList<Usuario> arrayUsuario){
        auxUsers.clear();

        for (Usuario usu: arrayUsuario) {
            auxUsers.add(usu.getNome());
            System.out.println(usu.getNome());
        }

        Vector vetUsuarios = new Vector(this.auxUsers);

        JFrame pedidoScreen = new JFrame();
        pedidoScreen.setBounds(150,150,390,844);
        pedidoScreen.setLayout(null);

        // ComboBox usuários
        JComboBox cbUsuarios = new JComboBox(vetUsuarios);
        cbUsuarios.setBounds(70,240,252,50);


        // Texto valor total
        valorTotal.setBounds(70,350,252,50);
        valorTotal.setText(valorTotal.getText() + calcularValorTotal());

        // Botão finalizar
        JButton btFinalizar = new JButton("Finalizar");
        btFinalizar.setSize(215,50);
        btFinalizar.setLocation(90,730);
        btFinalizar.setOpaque(false);

        // Background
        ImageIcon imagemDeFundo = new ImageIcon("src/img/finalizar.png");
        JLabel background = new JLabel(imagemDeFundo);
        background.setBounds(0,0,390,844);

        // OnClick
        btFinalizar.addActionListener(e -> this.concluirPedido());

        pedidoScreen.add(valorTotal);
        pedidoScreen.add(cbUsuarios);
        pedidoScreen.add(background);
        pedidoScreen.add(btFinalizar);

        pedidoScreen.setVisible(true);
    }

    void fazerPedido(String item, double preco, int quantidade){
        pedidos.add(new Pedido(item, preco, quantidade, preco * quantidade));
        System.out.println("Pedido "+pedidos);
    }

    double calcularValorTotal(){
        double finalTotal = 0;
        for (Pedido pedido: pedidos) {
            finalTotal += pedido.getTotal();
        }
        return finalTotal;
    }

    void concluirPedido(){
        pedidos.clear();
        valorTotal.setText("R$ ");
    }

    @Override
    public String toString(){
        return Double.toString(this.total);
    }
}
