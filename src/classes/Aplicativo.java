package classes;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Vector;

public class Aplicativo extends JFrame {
    Pedido pedido = new Pedido();

    ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
    ArrayList<Restaurantes> restaurantes = new ArrayList<Restaurantes>();

    Restaurantes rest = new Restaurantes();
    ArrayList<String> auxRestaurantes = new ArrayList<>();

    JTextField nomeRestaurante = new JTextField();
    JTextField cnpjRestaurante = new JTextField();
    JTextField localizacaoRestaurante = new JTextField();

    JTextField nome = new JTextField("");
    JTextField cpf = new JTextField("");
    JTextField cep = new JTextField("");

    public Aplicativo(){
        setBounds(150,150,390,844);
        setDefaultCloseOperation(Aplicativo.EXIT_ON_CLOSE);
        setLayout(null);

        // Botão cadastrar restaurante
        JButton btCadRest = new JButton("CADASTRAR");
        btCadRest.setSize(260,70);
        btCadRest.setLocation(65,280);
        btCadRest.setOpaque(false);

        // Botão cadastrar usuário
        JButton btCadUser = new JButton("CADASTRAR");
        btCadUser.setSize(260,70);
        btCadUser.setLocation(65,430);
        btCadUser.setOpaque(false);

        // Botão fazer pedido
        JButton btFazerPedido = new JButton("CADASTRAR");
        btFazerPedido.setSize(260,70);
        btFazerPedido.setLocation(65,570);
        btFazerPedido.setOpaque(false);

        // Background
        ImageIcon imagemDeFundo = new ImageIcon("src/img/home.png");
        JLabel background = new JLabel(imagemDeFundo);
        background.setBounds(0,0,390,844);

        // OnClick
        btCadRest.addActionListener(e -> this.telaCadastrarRest());
        btCadUser.addActionListener(e ->  this.telaCadastrarUser());
        btFazerPedido.addActionListener(e -> this.telaRestaurantes());

        getContentPane().add(background);
        getContentPane().add(btCadRest);
        getContentPane().add(btCadUser);
        getContentPane().add(btFazerPedido);

        setVisible(true);
    }

    public void telaCadastrarRest(){
        JFrame cadastrarRestScreen = new JFrame();
        cadastrarRestScreen.setBounds(150,150,390,844);
        cadastrarRestScreen.setLayout(null);

        // Input nome restaurante
        nomeRestaurante.setSize(252,50);
        nomeRestaurante.setLocation(70,350);

        // Input cnpj restaurante
        cnpjRestaurante.setSize(252,50);
        cnpjRestaurante.setLocation(70,450);

        // Input localização restaurante
        localizacaoRestaurante.setSize(252,50);
        localizacaoRestaurante.setLocation(70,550);

        // Botão cadastrar
        JButton btCadRest = new JButton("CADASTRAR");
        btCadRest.setSize(215,50);
        btCadRest.setLocation(90,730);
        btCadRest.setOpaque(false);

        // Background
        ImageIcon imagemDeFundo = new ImageIcon("src/img/cadrestaurante.png");
        JLabel background = new JLabel(imagemDeFundo);
        background.setBounds(0,0,390,844);

        // OnClick
        btCadRest.addActionListener(e -> this.cadastrarRestaurante(nomeRestaurante.getText(),
                Integer.parseInt(cnpjRestaurante.getText()),
                Integer.parseInt(localizacaoRestaurante.getText())));

        cadastrarRestScreen.add(background);
        cadastrarRestScreen.add(nomeRestaurante);
        cadastrarRestScreen.add(cnpjRestaurante);
        cadastrarRestScreen.add(localizacaoRestaurante);
        cadastrarRestScreen.add(btCadRest);

        cadastrarRestScreen.setVisible(true);
    }

    public void telaCadastrarUser(){
        JFrame cadastrarUserScreen = new JFrame();
        cadastrarUserScreen.setBounds(150,150,390,844);
        cadastrarUserScreen.setLayout(null);

        // Input nome
        nome.setSize(252,50);
        nome.setLocation(70,350);

        // Input CPF
        cpf.setSize(252,50);
        cpf.setLocation(70,450);

        // Input CEP
        cep.setSize(252,50);
        cep.setLocation(70,550);

        // Botão cadastrar
        JButton btCadUser = new JButton("CADASTRAR");
        btCadUser.setSize(215,50);
        btCadUser.setLocation(90,730);
        btCadUser.setOpaque(false);

        // Background
        ImageIcon imagemDeFundo = new ImageIcon("src/img/caduser.png");
        JLabel background = new JLabel(imagemDeFundo);
        background.setBounds(0,0,390,844);

        // OnClick
        btCadUser.addActionListener(e -> this.cadastrarUsuario(nome.getText(),
                Integer.parseInt(cpf.getText()),
                Integer.parseInt(cep.getText())));

        cadastrarUserScreen.add(background);
        cadastrarUserScreen.add(nome);
        cadastrarUserScreen.add(cpf);
        cadastrarUserScreen.add(cep);
        cadastrarUserScreen.add(btCadUser);

        cadastrarUserScreen.setVisible(true);
    }

    public void telaRestaurantes(){
        auxRestaurantes.clear();

        for (Restaurantes rest: this.restaurantes) {
            auxRestaurantes.add(rest.getNome());
            System.out.println(rest.getNome());
        }

        Vector vetRestaurantes = new Vector(this.auxRestaurantes);

        JFrame restaurantesScreen = new JFrame();
        restaurantesScreen.setBounds(150,150,390,844);
        restaurantesScreen.setLayout(null);

        // ComboBox restaurantes
        JComboBox cbRestaurantes = new JComboBox(vetRestaurantes);
        cbRestaurantes.setBounds(70,350,252,50);

        // Botão admin
        JButton btAdmin = new JButton("");
        btAdmin.setSize(215,50);
        btAdmin.setLocation(90,635);
        btAdmin.setOpaque(false);

        // Botão ver cardápio
        JButton btCardapio = new JButton("");
        btCardapio.setSize(215,50);
        btCardapio.setLocation(90,730);
        btCardapio.setOpaque(false);

        // Background
        ImageIcon imagemDeFundo = new ImageIcon("src/img/restaurantes.png");
        JLabel background = new JLabel(imagemDeFundo);
        background.setBounds(0,0,390,844);

        // OnClick
        btAdmin.addActionListener(e ->rest.telaAddProduto(cbRestaurantes.getSelectedIndex() + 1));
        btCardapio.addActionListener(e -> rest.telaCardapio(cbRestaurantes.getSelectedIndex() + 1, usuarios));

        restaurantesScreen.add(cbRestaurantes);
        restaurantesScreen.add(background);
        restaurantesScreen.add(btCardapio);
        restaurantesScreen.add(btAdmin);

        restaurantesScreen.setVisible(true);
    }


    void cadastrarRestaurante(String nome, int cnpj, int localizacao){
        boolean aux = false;
        for (Restaurantes rest: this.restaurantes) {
            if (nome.equalsIgnoreCase(rest.getNome())){
                System.out.println("Restaurante já existe");
                aux = true;
            }
        }
        if (!aux){
            restaurantes.add(new Restaurantes(nome, cnpj, localizacao));
            System.out.println(restaurantes);
        }
        this.nomeRestaurante.setText("");
        this.cnpjRestaurante.setText("");
        this.localizacaoRestaurante.setText("");
    }

    void cadastrarUsuario(String nome, int cpf, int cep){
        boolean aux = false;
        for (Usuario user: this.usuarios) {
            if (cpf == user.getCpf()){
                System.out.println("Usuário já existe");
                aux = true;
            }
        }
        if (!aux){
            usuarios.add(new Usuario(nome, cpf, cep));
            System.out.println("Usuário "+usuarios);
        }
        this.nome.setText("");
        this.cpf.setText("");
        this.cep.setText("");
    }
}
