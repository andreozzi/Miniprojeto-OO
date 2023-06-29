package view;

import control.ControleAdmin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.List;

public class PassageiroView {
	private JFrame frame;
	private JLabel titulo;
	private JButton botaoVoltar;
	private JButton botaoPesquisar;
	private JList<String> itinerariosLista;
	 private JScrollPane scrollDaLista;

	public PassageiroView() {
		// Cria o main frame
		frame = new JFrame("Passageiro View");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 300);
		frame.setLayout(null);

		// Create the title label
		titulo = new JLabel("Itinerarios");
		titulo.setFont(new Font("Arial", Font.BOLD, 20));
		titulo.setBounds(150, 10, 150, 30);

		// Create the back button
		botaoVoltar = new JButton("Voltar");
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

		buscaPorDataButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showBuscaPorData();
				buscaFrame.setVisible(false);
			}
		});

		buscaPorOrigemButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showBuscaPorOrigem();
			}
		});

		buscaPorDestinoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showBuscaPorDestino();
			}
		});

		buscaPorTudoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showBuscaPorDataOrigemDestino();
			}
		});

		buscaFrame.add(buscaPorDataButton);
		buscaFrame.add(buscaPorOrigemButton);
		buscaFrame.add(buscaPorDestinoButton);
		buscaFrame.add(buscaPorTudoButton);

		buscaFrame.setVisible(true);
	}

	private void showBuscaPorData() {
		JFrame buscaPorDataFrame = new JFrame("Busca por Data");
		buscaPorDataFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		buscaPorDataFrame.setSize(400, 250);
		buscaPorDataFrame.setLayout(new BorderLayout());

		JLabel titulo = new JLabel("Busca Por Data");
		titulo.setFont(new Font("Arial", Font.BOLD, 20));
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		buscaPorDataFrame.add(titulo, BorderLayout.NORTH);

		JPanel painelCentral = new JPanel(new BorderLayout());
		buscaPorDataFrame.add(painelCentral, BorderLayout.CENTER);

		JPanel painelData = new JPanel(new FlowLayout(FlowLayout.LEFT));
		painelCentral.add(painelData, BorderLayout.NORTH);

		JLabel dataLabel = new JLabel("Data: ");
		painelData.add(dataLabel);

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		JFormattedTextField dataTextField = new JFormattedTextField(dateFormat);
		dataTextField.setColumns(10);
		painelData.add(dataTextField);

		JButton botaoBuscar = new JButton("Buscar!");
		painelData.add(botaoBuscar);

		DefaultListModel<String> lista = new DefaultListModel<>();
		ControleAdmin c = new ControleAdmin();
		for (String s : c.getListaItinerarios()){lista.addElement(s);}
		JList<String> listaView = new JList<>(lista);
		JScrollPane scrollPane = new JScrollPane(listaView);
		painelCentral.add(scrollPane, BorderLayout.CENTER);

		JButton voltarButton = new JButton("Voltar");
		buscaPorDataFrame.add(voltarButton, BorderLayout.SOUTH);

		buscaPorDataFrame.setVisible(true);




		botaoBuscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Perform the search based on the selected date
				// ...
			}
		});



		voltarButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				buscaPorDataFrame.dispose();
			}

		});


	}

	private void showBuscaPorOrigem() {
		JFrame buscaPorOrigemFrame = new JFrame("Busca por Origem");
		buscaPorOrigemFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		buscaPorOrigemFrame.setSize(200, 150);

		// Add components to the frame
		// ...

		buscaPorOrigemFrame.setVisible(true);
	}

	private void showBuscaPorDestino() {
		JFrame buscaPorDestinoFrame = new JFrame("Busca por Destino");
		buscaPorDestinoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		buscaPorDestinoFrame.setSize(200, 150);

		// Add components to the frame
		// ...

		buscaPorDestinoFrame.setVisible(true);
	}

	private void showBuscaPorDataOrigemDestino() {
		JFrame buscaPorTudoFrame = new JFrame("Busca por Data, Origem e Destino");
		buscaPorTudoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		buscaPorTudoFrame.setSize(200, 150);

		// Add components to the frame
		// ...

		buscaPorTudoFrame.setVisible(true);
	}


}