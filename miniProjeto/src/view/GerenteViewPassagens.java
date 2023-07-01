package view;

import control.ControleAdmin;
import model.EstadosBrasileiros;
import model.Itinerario;
import model.Passagem;

import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.*;
import javax.swing.text.MaskFormatter;

public class GerenteViewPassagens{
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
	private JComboBox<EstadosBrasileiros> destinoComboBox;
	private JLabel precoLabel;
	private JTextField precoTextField;
	private static ControleAdmin controleAdmin;


	public GerenteViewPassagens(ControleAdmin controleAdmin){
		this.controleAdmin = controleAdmin;
		janela = new JFrame("CRUD Passagens");
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
		precoLabel = new JLabel("Preço:");
		precoTextField = new JTextField();

		DefaultListModel<String> listModel = new DefaultListModel<>();
		for (String s : controleAdmin.getListaPassagens()) {
			listModel.addElement(s);
		}
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
		constraints.gridwidth = 1;
		formPanel.add(precoLabel, constraints);

		constraints.gridx = 1;
		constraints.gridy = 3;
		constraints.gridwidth = 1;
		formPanel.add(precoTextField, constraints);

		constraints.gridx = 0;
		constraints.gridy = 4;
		constraints.gridwidth = 2;
		formPanel.add(new JLabel("Para atualizar ou deletar uma passagem, selecione uma passagem:"), constraints);

		constraints.gridx = 0;
		constraints.gridy = 5;
		constraints.gridwidth = 2;
		formPanel.add(scroll, constraints);

		JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		buttonsPanel.add(criarButton);
		buttonsPanel.add(atualizarButton);
		buttonsPanel.add(deletarButton);

		constraints.gridx = 0;
		constraints.gridy = 6;
		constraints.gridwidth = 2;
		formPanel.add(buttonsPanel, constraints);

		constraints.gridx = 1;
		constraints.gridy = 7;
		constraints.gridwidth = 1;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets = new Insets(10, 0, 0, 0);
		formPanel.add(voltarButton, constraints);

		panel.add(infoLabel, BorderLayout.NORTH);
		panel.add(formPanel, BorderLayout.CENTER);

		janela.getContentPane().add(panel);
		janela.pack();
		janela.setVisible(true);





		atualizarButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String dataString = dataTextField.getText();
				DateTimeFormatter d = DateTimeFormatter.ofPattern("dd/MM/yyy");
				LocalDate dataConvetida = LocalDate.parse(dataString,d);
				double preco = Double.parseDouble(precoTextField.getText());

				EstadosBrasileiros origem = (EstadosBrasileiros) origemComboBox.getSelectedItem();
				EstadosBrasileiros destino = (EstadosBrasileiros) destinoComboBox.getSelectedItem();

				int index = list.getSelectedIndex();
				listModel.remove(index);

				Itinerario i = new Itinerario(origem,destino,dataConvetida);
				Passagem p = new Passagem(index,preco,i);
				listModel.add(index,p.toString());
				controleAdmin.atualizarPassagem(index,origem,destino,dataString,preco);
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
				controleAdmin.deletarPassagem(index);
			}
		});

		criarButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DateTimeFormatter d = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				String dataDigitada = dataTextField.getText();
				EstadosBrasileiros origem = (EstadosBrasileiros) origemComboBox.getSelectedItem();
				EstadosBrasileiros destino = (EstadosBrasileiros) destinoComboBox.getSelectedItem();
				Double preco = Double.parseDouble(precoTextField.getText());
				controleAdmin.criarPassagem(dataDigitada,origem,destino,preco);
				LocalDate dataConvertida = LocalDate.parse(dataDigitada,d);
				Itinerario i = new Itinerario(origem,destino,dataConvertida);
				Passagem p = new Passagem(controleAdmin.getListaPassagens().size(),preco,i);
				dataTextField.setText("");
				origemComboBox.setSelectedIndex(0);
				destinoComboBox.setSelectedIndex(0);
				precoTextField.setText("");
				listModel.addElement(p.toString());


			}
		});
		voltarButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				janela.dispose();
				new PassagemOuItinerario(controleAdmin);
			}
		});

	}






}
	
