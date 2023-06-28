package view;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

public class OpcoesCriarDel implements ActionListener, ListSelectionListener{

	private JFrame janela;
	private JLabel titulo;
	private JLabel tituloOrigem;
	private JLabel tituloDest;
	private JLabel dataPassagem;
	private JButton continuar;
	private JList<String> listaOrigem;
	private JList<String> listaDest;
	private JList<String> listaPassagemDel;
	private JList<String> listaItinerarios;
	private JList<String> listaItinerarioDel;
	private MaskFormatter mascaraData;
	private JFormattedTextField data;
	private JLabel selItinerario;
	private JButton voltar;
	
	private static int valorCriarIti = 0;
	private static int valorCriarDest = 0;
	private static int qtdSelIti = 0;
	
	private int seletor;
	
	public void opcoes(int op) {
		seletor = op;
		
		switch(op) {//1 = Criar Passagem, 2 = Deletar Passagem, 3 = Criar Itinerario, 4 = Deletar Itinerario;
		case 1://Criar Passagem
			try {
				mascaraData = new MaskFormatter("##/##/####");
				mascaraData.setPlaceholderCharacter('_');
			}
			catch(ParseException excessao) {
				System.err.println("Erro na formatacao" + excessao.getMessage());
				System.exit(-1);
			}
			
			janela = new JFrame("Criar Passagem");
			titulo = new JLabel("Insira os dados da Passagem");
			dataPassagem = new JLabel("Data:");
			selItinerario = new JLabel("Selecione um itinerario:");
			data = new JFormattedTextField(mascaraData);
			listaItinerarios = new JList<String>();
			
			titulo.setFont(new Font("Arial", Font.BOLD, 20));
			titulo.setBounds(65, 10, 350, 30);
			dataPassagem.setBounds(50,40,100,20);
			data.setBounds(150,40,100,20);
			selItinerario.setBounds(50,70,160,20);
			listaItinerarios.setBounds(50, 90, 300, 80);
			
			janela.setLayout(null);
			
			janela.add(titulo);
			janela.add(data);
			janela.add(dataPassagem);
			janela.add(selItinerario);
			janela.add(listaItinerarios);
			
			janela.setSize(400, 250);
			janela.setVisible(true);
			
			listaItinerarios.addListSelectionListener(this);

			break;
		case 2://Deletar Passagem
			janela = new JFrame("Deletar Passagem");
			titulo = new JLabel("Selecione a Passagem");
			listaPassagemDel = new JList<String>();
			
			titulo.setFont(new Font("Arial", Font.BOLD, 20));
			titulo.setBounds(90, 10, 250, 30);
			listaPassagemDel.setBounds(20, 50, 350, 100);
			
			janela.setLayout(null);
			
			janela.add(titulo);
			janela.add(listaPassagemDel);
			
			janela.setSize(400, 250);
			janela.setVisible(true);
			
			listaPassagemDel.addListSelectionListener(this);
			
			break;
		case 3://Criar Itinerario
			janela = new JFrame("Criar Itinerario");
			titulo = new JLabel("Insira os dados da Itinerario");
			tituloOrigem = new JLabel("Origem");
			tituloDest = new JLabel("Destino");
			continuar = new JButton("Continuar");
			listaOrigem = new JList<String>();
			listaDest = new JList<String>();
			
			titulo.setFont(new Font("Arial", Font.BOLD, 20));
			titulo.setBounds(65, 10, 350, 30);
			tituloOrigem.setBounds(90, 60, 125, 20);
			listaOrigem.setBounds(20, 80, 160, 80);
			listaOrigem.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
			listaOrigem.setVisibleRowCount(10);
			tituloDest.setBounds(260, 60, 160, 20);
			listaDest.setBounds(200, 80, 160, 80);
			listaDest.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
			listaDest.setVisibleRowCount(10);
			continuar.setBounds(140, 170, 100, 30);
			
			janela.setLayout(null);
			
			janela.add(titulo);
			janela.add(tituloOrigem);
			janela.add(listaOrigem);
			janela.add(listaDest);
			janela.add(tituloDest);
			janela.add(continuar);
			
			janela.setSize(400, 250);
			janela.setVisible(true);
			
			listaOrigem.addListSelectionListener(this);
			listaDest.addListSelectionListener(this);
			continuar.addActionListener(this);
			
			break;
		case 4://Deletar Itinerario
			janela = new JFrame("Deletar Itinerario");
			titulo = new JLabel("Selecione o Itinerario");
			listaItinerarioDel = new JList<String>();
			
			titulo.setFont(new Font("Arial", Font.BOLD, 20));
			titulo.setBounds(90, 10, 250, 30);
			listaItinerarioDel.setBounds(20, 50, 350, 100);
			
			janela.setLayout(null);
			
			janela.add(titulo);
			janela.add(listaItinerarioDel);
			
			janela.setSize(400, 250);
			janela.setVisible(true);
			
			listaItinerarioDel.addListSelectionListener(this);

			break;
		}
		
		voltar = new JButton("Voltar");
		voltar.setBounds(313, 177, 68, 30);
		janela.add(voltar);
		voltar.addActionListener(this);
		voltar.addActionListener(e-> {
			janela.dispose();
		});
		
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object leitura = e.getSource();
		
		if(leitura == voltar && (seletor == 1 || seletor == 2))
			new Passagem().opcoesPassagem(1);
		if(leitura == voltar && (seletor == 3 || seletor == 4))
			new Itinerario().opcoesItinerario(1);
		
		if(leitura == continuar) {
			if(valorCriarIti == 1 && valorCriarDest == 1) {
				JOptionPane.showMessageDialog(null, "Opcao valida",null, 
						JOptionPane.INFORMATION_MESSAGE);
				valorCriarIti = 0;
				valorCriarDest = 0;
			}
			else
				JOptionPane.showMessageDialog(null, "selecione um local de origem\n"
						+"e um de destino",null, 
						JOptionPane.INFORMATION_MESSAGE);
				valorCriarIti = 0;
				valorCriarDest = 0;
		}
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		Object procura = e.getSource();
		
		if(e.getValueIsAdjusting() && procura == listaOrigem) {
			JOptionPane.showMessageDialog(null, "ainda precisa ser implementada\n"
					+"as funcionalidade para essa opcao\n",null, 
					JOptionPane.INFORMATION_MESSAGE);
			valorCriarIti += 1;
		}
		if(e.getValueIsAdjusting() && procura == listaDest)
			JOptionPane.showMessageDialog(null, "ainda precisa ser implementada\n"
					+"as funcionalidade para essa opcao\n",null, 
					JOptionPane.INFORMATION_MESSAGE);
			valorCriarDest += 1;
		
		if(e.getValueIsAdjusting() && procura == listaPassagemDel)
			JOptionPane.showMessageDialog(null, "ainda precisa ser implementada\n"
					+"as funcionalidade para essa opcao\n",null, 
					JOptionPane.INFORMATION_MESSAGE);
		
		if(e.getValueIsAdjusting() && procura == listaItinerarios)
			JOptionPane.showMessageDialog(null, "ainda precisa ser implementada\n"
					+"as funcionalidade para essa opcao\n",null, 
					JOptionPane.INFORMATION_MESSAGE);
		
		if(e.getValueIsAdjusting() && procura == listaItinerarioDel)
			JOptionPane.showMessageDialog(null, "ainda precisa ser implementada\n"
					+"as funcionalidade para essa opcao\n",null, 
					JOptionPane.INFORMATION_MESSAGE);
	}
}
