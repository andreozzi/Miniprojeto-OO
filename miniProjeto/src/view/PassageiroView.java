package view;

import control.ControleAdmin;
import model.EstadosBrasileiros;
import model.Itinerario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class PassageiroView {
	private JFrame frame;
	private JLabel titulo;
	private JButton botaoVoltar;
	private JButton botaoPesquisar;
	private JList<String> itinerariosLista;
	 private JScrollPane scrollDaLista;
	 private static ControleAdmin controleAdmin;

	public PassageiroView(ControleAdmin controleAdmin) {
		this.controleAdmin = controleAdmin;

		frame = new JFrame("Passageiro View");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 300);
		frame.setLayout(null);


		titulo = new JLabel("Itinerarios");
		titulo.setFont(new Font("Arial", Font.BOLD, 20));
		titulo.setBounds(150, 10, 150, 30);


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

		List<String> listaItinerarios = controleAdmin.getListaItinerarios();
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
				frame.dispose();
				showBuscaPorData(controleAdmin);
				buscaFrame.setVisible(false);
			}
		});

		buscaPorOrigemButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showBuscaPorOrigem(controleAdmin);
				frame.dispose();
				buscaFrame.setVisible(false);
			}
		});

		buscaPorDestinoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showBuscaPorDestino(controleAdmin);
				frame.dispose();
				buscaFrame.setVisible(false);
			}
		});

		buscaPorTudoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showBuscaPorDataOrigemDestino(controleAdmin);
				frame.dispose();
				buscaFrame.setVisible(false);
			}
		});

		buscaFrame.add(buscaPorDataButton);
		buscaFrame.add(buscaPorOrigemButton);
		buscaFrame.add(buscaPorDestinoButton);
		buscaFrame.add(buscaPorTudoButton);

		buscaFrame.setVisible(true);
	}

	private void showBuscaPorData(ControleAdmin controleAdmin) {
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


		JFormattedTextField dataTextField = new JFormattedTextField("__/__/____");
		dataTextField.setColumns(10);
		painelData.add(dataTextField);

		JButton botaoBuscar = new JButton("Buscar!");
		painelData.add(botaoBuscar);

		DefaultListModel<String> lista = new DefaultListModel<>();

		for (String s : controleAdmin.getListaItinerarios()){lista.addElement(s);}
		JList<String> listaView = new JList<>(lista);
		JScrollPane scrollPane = new JScrollPane(listaView);
		painelCentral.add(scrollPane, BorderLayout.CENTER);

		JButton voltarButton = new JButton("Voltar");
		buscaPorDataFrame.add(voltarButton, BorderLayout.SOUTH);

		buscaPorDataFrame.setVisible(true);




		botaoBuscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String dataString = dataTextField.getText();
				try {
					List<String> itinerariosFiltrados = controleAdmin.bucarItinerarioData(dataString);
					lista.clear();
					for(String s:itinerariosFiltrados){
						lista.addElement(s);
					}

				} catch (ParseException ex) {
					throw new RuntimeException(ex);
				}


			}
		});



		voltarButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				buscaPorDataFrame.dispose();
				new PassageiroView(controleAdmin);
			}

		});


	}

	private void showBuscaPorOrigem(ControleAdmin controleAdmin) {
		JFrame buscaPorOrigemFrame = new JFrame("Busca por Origem");
		buscaPorOrigemFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		buscaPorOrigemFrame.setSize(400, 300);

		JLabel titulo = new JLabel("Busca Por Origem:");
		titulo.setFont(new Font("Arial", Font.BOLD, 20));
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		buscaPorOrigemFrame.add(titulo, BorderLayout.NORTH);

		JPanel painelCentral = new JPanel(new BorderLayout());
		buscaPorOrigemFrame.add(painelCentral, BorderLayout.CENTER);

		JPanel painelComboBox = new JPanel(new FlowLayout(FlowLayout.LEFT));
		painelCentral.add(painelComboBox, BorderLayout.NORTH);

		JLabel comboBoxLabel = new JLabel("Origem: ");
		painelComboBox.add(comboBoxLabel);

		JComboBox<EstadosBrasileiros> origens = new JComboBox<>(EstadosBrasileiros.values());
		painelComboBox.add(origens);

		JButton botaoBuscar = new JButton("Buscar!");
		painelComboBox.add(botaoBuscar);

		DefaultListModel<String> lista = new DefaultListModel<>();

		for (String s : controleAdmin.getListaItinerarios()) {
			lista.addElement(s);
		}
		JList<String> listaView = new JList<>(lista);
		JScrollPane scrollPane = new JScrollPane(listaView);
		painelCentral.add(scrollPane, BorderLayout.CENTER);

		JButton voltarButton = new JButton("Voltar");
		buscaPorOrigemFrame.add(voltarButton, BorderLayout.SOUTH);

		buscaPorOrigemFrame.setVisible(true);

		botaoBuscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				EstadosBrasileiros estadoSelecionado = (EstadosBrasileiros) origens.getSelectedItem();
				List<String> itinerariosFiltrados = controleAdmin.bucarItinerarioLocal(estadoSelecionado);
				lista.clear();
				for(String s:itinerariosFiltrados){
					lista.addElement(s);
				}
			}
		});

		voltarButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				buscaPorOrigemFrame.dispose();
				new PassageiroView(controleAdmin);
			}

		});


	}

	private void showBuscaPorDestino(ControleAdmin controleAdmin) {
		JFrame buscaPorDestinoFrame = new JFrame("Busca por Origem");
		buscaPorDestinoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		buscaPorDestinoFrame.setSize(400, 300);

		JLabel titulo = new JLabel("Busca Por Destino:");
		titulo.setFont(new Font("Arial", Font.BOLD, 20));
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		buscaPorDestinoFrame.add(titulo, BorderLayout.NORTH);

		JPanel painelCentral = new JPanel(new BorderLayout());
		buscaPorDestinoFrame.add(painelCentral, BorderLayout.CENTER);

		JPanel painelComboBox = new JPanel(new FlowLayout(FlowLayout.LEFT));
		painelCentral.add(painelComboBox, BorderLayout.NORTH);

		JLabel comboBoxLabel = new JLabel("Destino: ");
		painelComboBox.add(comboBoxLabel);

		JComboBox<EstadosBrasileiros> destinos = new JComboBox<>(EstadosBrasileiros.values());
		painelComboBox.add(destinos);

		JButton botaoBuscar = new JButton("Buscar!");
		painelComboBox.add(botaoBuscar);

		DefaultListModel<String> lista = new DefaultListModel<>();

		for (String s : controleAdmin.getListaItinerarios()) {
			lista.addElement(s);
		}
		JList<String> listaView = new JList<>(lista);
		JScrollPane scrollPane = new JScrollPane(listaView);
		painelCentral.add(scrollPane, BorderLayout.CENTER);

		JButton voltarButton = new JButton("Voltar");
		buscaPorDestinoFrame.add(voltarButton, BorderLayout.SOUTH);

		buscaPorDestinoFrame.setVisible(true);

		botaoBuscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				EstadosBrasileiros estadoSelecionado = (EstadosBrasileiros) destinos.getSelectedItem();
				List<String> itinerariosFiltrados = controleAdmin.bucarItinerarioLocal(estadoSelecionado);
				lista.clear();
				for(String s:itinerariosFiltrados){
					lista.addElement(s);
				}
			}
		});
		voltarButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				buscaPorDestinoFrame.dispose();
				new PassageiroView(controleAdmin);

			}
		});
	}

	private void showBuscaPorDataOrigemDestino(ControleAdmin controleAdmin) {
		JFrame buscaPorTudoFrame = new JFrame("Busca por Data, Origem e Destino");
		buscaPorTudoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		buscaPorTudoFrame.setLayout(new BorderLayout());
		buscaPorTudoFrame.setSize(600, 400);

		JLabel titulo = new JLabel("Busca Por Data, Origem e Destino:");
		titulo.setFont(new Font("Arial", Font.BOLD, 20));
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		buscaPorTudoFrame.add(titulo, BorderLayout.NORTH);

		JPanel formPanel = new JPanel(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(5, 5, 5, 5);

		JLabel dataLabel = new JLabel("Data:");
		constraints.gridx = 0;
		constraints.gridy = 0;
		formPanel.add(dataLabel, constraints);

		JFormattedTextField dataTextField = new JFormattedTextField("__/__/____");
		dataTextField.setPreferredSize(new Dimension(120, 25));
		constraints.gridx = 1;
		formPanel.add(dataTextField, constraints);

		JLabel origemLabel = new JLabel("Origem:");
		constraints.gridx = 0;
		constraints.gridy = 1;
		formPanel.add(origemLabel, constraints);

		JComboBox<EstadosBrasileiros> origemComboBox = new JComboBox<>(EstadosBrasileiros.values());
		origemComboBox.setPreferredSize(new Dimension(120, 25));
		constraints.gridx = 1;
		formPanel.add(origemComboBox, constraints);

		JLabel destinoLabel = new JLabel("Destino:");
		constraints.gridx = 0;
		constraints.gridy = 2;
		formPanel.add(destinoLabel, constraints);

		JComboBox<EstadosBrasileiros> destinos = new JComboBox<>(EstadosBrasileiros.values());
		destinos.setPreferredSize(new Dimension(120, 25));
		constraints.gridx = 1;
		formPanel.add(destinos, constraints);




		DefaultListModel<String> lista = new DefaultListModel<>();
		for (String s : controleAdmin.getListaItinerarios()) {
			lista.addElement(s);
		}
		JList<String> listaView = new JList<>(lista);
		JScrollPane scrollPane = new JScrollPane(listaView);
		constraints.gridx = 0;
		constraints.gridy = 4;
		constraints.gridwidth = 2;
		constraints.weightx = 1.0;
		constraints.weighty = 1.0;
		constraints.fill = GridBagConstraints.BOTH;
		formPanel.add(scrollPane, constraints);

		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JButton buscarButton = new JButton("Buscar");
		buttonPanel.add(buscarButton);
		JButton voltarButton = new JButton("Voltar");
		buttonPanel.add(voltarButton);
		buscaPorTudoFrame.add(buttonPanel, BorderLayout.SOUTH);

		buscaPorTudoFrame.add(formPanel, BorderLayout.CENTER);
		buscaPorTudoFrame.setVisible(true);
		buscarButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DateTimeFormatter d = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				String dataString = dataTextField.getText();
				EstadosBrasileiros origem = (EstadosBrasileiros) origemComboBox.getSelectedItem();
				EstadosBrasileiros destino = (EstadosBrasileiros) destinos.getSelectedItem();
				List<String> itinerariosFiltrados = controleAdmin.buscarItinerariosRefinado(dataString, origem, destino);
				lista.clear();
				for(String s:itinerariosFiltrados){
					lista.addElement(s);
				}
			}
		});
		voltarButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				buscaPorTudoFrame.dispose();
				new PassageiroView(controleAdmin);

			}
		});
		buscaPorTudoFrame.setVisible(true);
	}


}