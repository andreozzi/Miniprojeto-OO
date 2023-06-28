package view;

import control.ControleAdmin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PassageiroView {
	private JFrame frame;
	private JLabel titulo;
	private JButton botaoVoltar;
	private JButton botaoPesquisar;
	private JList<String> itinerariosLista;
	 private JScrollPane scrollDaLista;

	public PassageiroView() {
		// Create the main frame
		frame = new JFrame("Passageiro View");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 300);
		frame.setLayout(null);

		// Create the title label
		titulo = new JLabel("Itinerarios");
		titulo.setFont(new Font("Arial", Font.BOLD, 20));
		titulo.setBounds(150, 10, 150, 30);

		// Create the back button
		botaoVoltar = new JButton("Back");
		botaoVoltar.setBounds(300, 210, 80, 30);
		botaoVoltar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new TelaMenu();
				frame.dispose();
			}
		});

		// Create the search button
		botaoPesquisar = new JButton("Buscar");
		botaoPesquisar.setBounds(20, 50, 80, 30);
		botaoPesquisar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showOpcoesDeBusca();
			}
		});

		// Create the list of itinerarios
		DefaultListModel<String> listaItinerariosView = new DefaultListModel<>();
		ControleAdmin c = new ControleAdmin();
		List<String> listaItinerarios = c.getListaItinerarios();
		for(String s : listaItinerarios){
			listaItinerariosView.addElement(s);
		}

		itinerariosLista = new JList<>(listaItinerariosView);
		scrollDaLista = new JScrollPane(itinerariosLista);
		scrollDaLista.setBounds(50, 100, 300, 100);

		// Add components to the frame
		frame.add(titulo);
		frame.add(botaoVoltar);
		frame.add(botaoPesquisar);
		frame.add(scrollDaLista);

		// Make the frame visible
		frame.setVisible(true);
	}

	private void showOpcoesDeBusca() {
		JFrame buscaFrame = new JFrame("Opções de Busca");
		buscaFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		buscaFrame.setSize(300, 200);
		buscaFrame.setLayout(new FlowLayout());

		JButton buscaPorDataButton = new JButton("Busca por Data");
		JButton buscaPorOrigemButton = new JButton("Busca por Origem");
		JButton buscaPorDestinoButton = new JButton("Busca por Destino");
		JButton buscaPorTudoButton = new JButton("Busca por Data, Origem e Destino");

		buscaFrame.add(buscaPorDataButton);
		buscaFrame.add(buscaPorOrigemButton);
		buscaFrame.add(buscaPorDestinoButton);
		buscaFrame.add(buscaPorTudoButton);

		buscaFrame.setVisible(true);
	}


}