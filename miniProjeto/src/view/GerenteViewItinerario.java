package view;

import control.ControleAdmin;
import model.EstadosBrasileiros;
import model.Itinerario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class GerenteViewItinerario {
    private JFrame janela;
    private JLabel titulo;
    private JButton criarButton;
    private JButton atualizarButton;
    private JButton visualizarButton;
    private JButton deletarButton;
    private JButton voltarButton;
    private JPanel painelBotoes;
    private JLabel infoLabel;
    private JLabel dataLabel;
    private JFormattedTextField dataTextField;
    private JLabel origemLabel;
    private JComboBox<EstadosBrasileiros> origemComboBox;
    private JLabel destinoLabel;
    private DefaultListModel listModel;
    private JComboBox<EstadosBrasileiros> destinoComboBox;
    private JLabel precoLabel;
    private JTextField precoTextField;
    private static ControleAdmin controleAdmin;


    public GerenteViewItinerario(ControleAdmin controleAdmin){
        this.controleAdmin = controleAdmin;
        janela = new JFrame("CRUD Itinerarios");
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(5, 0, 5, 5);

        infoLabel = new JLabel("Insira as informações:");
        dataLabel = new JLabel("Data:");
        dataTextField = new JFormattedTextField("__/__/____");
        origemLabel = new JLabel("Origem:");
        origemComboBox = new JComboBox<>(EstadosBrasileiros.values());
        destinoLabel = new JLabel("Destino:");
        destinoComboBox = new JComboBox<>(EstadosBrasileiros.values());
        listModel = new DefaultListModel<>();
        for(String s : controleAdmin.getListaItinerarios()){listModel.addElement(s);}
        JList<String> list = new JList<>(listModel);
        JScrollPane scroll = new JScrollPane(list);

        criarButton = new JButton("Criar");
        atualizarButton = new JButton("Atualizar");
        deletarButton = new JButton("Deletar");
        voltarButton = new JButton("Voltar");

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        formPanel.add(dataLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        formPanel.add(dataTextField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        formPanel.add(origemLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        formPanel.add(origemComboBox, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        formPanel.add(destinoLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        formPanel.add(destinoComboBox, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        formPanel.add(new JLabel("Para atualizar ou deletar um itinerario, selecione um Itinerario:"), constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        formPanel.add(scroll, constraints);

        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonsPanel.add(criarButton);
        buttonsPanel.add(atualizarButton);
        buttonsPanel.add(deletarButton);

        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.gridwidth = 2;
        formPanel.add(buttonsPanel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 6;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.EAST;
        constraints.insets = new Insets(10, 0, 0, 0);
        formPanel.add(voltarButton, constraints);

        panel.add(infoLabel, BorderLayout.NORTH);
        panel.add(formPanel, BorderLayout.CENTER);
        janela.getContentPane().add(panel);
        janela.pack();
        janela.setVisible(true);

        // Add action listeners for buttons as needed

        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                janela.dispose();
                new PassagemOuItinerario(controleAdmin);
            }
        });
        criarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String dataString = dataTextField.getText();
                DateTimeFormatter d = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate dataConvetida = LocalDate.parse(dataString,d);
                EstadosBrasileiros origem = (EstadosBrasileiros) origemComboBox.getSelectedItem();
                EstadosBrasileiros destino = (EstadosBrasileiros) destinoComboBox.getSelectedItem();
                controleAdmin.criarItinerario(dataString,origem,destino);
                dataTextField.setText("");
                origemComboBox.setSelectedIndex(0);
                destinoComboBox.setSelectedIndex(0);
                Itinerario i = new Itinerario(origem,destino,dataConvetida);
                listModel.addElement(i.toString());

            }
        });
        atualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String dataString = dataTextField.getText();
                DateTimeFormatter d = DateTimeFormatter.ofPattern("dd/MM/yyy");
                LocalDate dataConvetida = LocalDate.parse(dataString,d);

                EstadosBrasileiros origem = (EstadosBrasileiros) origemComboBox.getSelectedItem();
                EstadosBrasileiros destino = (EstadosBrasileiros) destinoComboBox.getSelectedItem();

                int index = list.getSelectedIndex();
                listModel.remove(index);

                Itinerario i = new Itinerario(origem,destino,dataConvetida);
                listModel.add(index,i.toString());
                controleAdmin.atualizarItinerario(dataString, origem,destino,index);
                dataTextField.setText("");
                origemComboBox.setSelectedIndex(0);
                destinoComboBox.setSelectedIndex(0);


            }
        });
        deletarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = list.getSelectedIndex();
                listModel.remove(index);
                controleAdmin.deletarItinerario(index);
            }
        });
    }
    }
