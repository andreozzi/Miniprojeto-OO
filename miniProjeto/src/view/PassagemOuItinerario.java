package view;

import control.ControleAdmin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PassagemOuItinerario {
    JFrame frame;
    JButton itinerario;
    JButton passagem;
    JButton voltarButton;
    private static ControleAdmin controleAdmin;

    public PassagemOuItinerario(ControleAdmin controleAdmin) {
        this.controleAdmin = controleAdmin;
        frame = new JFrame("Escolha uma Opção:");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        itinerario = new JButton("Itinerario");
        passagem = new JButton("Passagem");
        voltarButton = new JButton("Voltar");

        JLabel escolhaOpcao = new JLabel("Escolha uma opção:");
        escolhaOpcao.setFont(new Font("Arial", Font.BOLD, 16));
        JPanel escolhaPainel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        escolhaPainel.add(escolhaOpcao);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttonPanel.add(itinerario);
        buttonPanel.add(passagem);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(escolhaPainel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.CENTER);

        JPanel painelBaixo = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        painelBaixo.add(voltarButton);
        panel.add(painelBaixo, BorderLayout.SOUTH);

        frame.getContentPane().add(panel);
        frame.setSize(400, 250);
        frame.setVisible(true);

        itinerario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new GerenteViewItinerario(controleAdmin);
            }
        });
        passagem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new GerenteViewPassagens(controleAdmin);
            }
        });

        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new TelaMenu();
            }
        });

    }
}
