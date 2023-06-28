package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import java.util.ArrayList;

public class Itinerario  implements ActionListener, ListSelectionListener{
	private JFrame janela;
	private JLabel tituloLista;
	private JButton criarItinerario;
	private JButton deletarItinerario;
	private JList<String> listaItinerarios;
	private ArrayList<String> itinerarios;
	private JButton voltar;
	
	private int seletor;
	
	public void opcoesItinerario(int op) {
		seletor = op;
		
		switch(op) {// 1 = GerenteView, 2 = PassageiroView
		case 1:
			janela = new JFrame("Opcoes de Itinerario");
			tituloLista = new JLabel("Selecione um Itinerario");
			itinerarios = new ArrayList<String>();
			itinerarios.add("Sao Paulo");
			listaItinerarios = new JList<String>();
			criarItinerario = new JButton("Criar");
			deletarItinerario = new JButton("Deletar");
			
			tituloLista.setFont(new Font("Arial", Font.BOLD, 20));
			tituloLista.setBounds(90, 10, 250, 30);
			listaItinerarios.setBounds(20, 50, 350, 100);
			listaItinerarios.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
			listaItinerarios.setVisibleRowCount(10);
			criarItinerario.setBounds(90,155,100,50);
			deletarItinerario.setBounds(210,155,100,50);
			
			janela.setLayout(null);
			
			janela.add(listaItinerarios);
			janela.add(tituloLista);
			janela.add(criarItinerario);
			janela.add(deletarItinerario);
			
			janela.setSize(400,250);
			janela.setVisible(true);
			
			listaItinerarios.addListSelectionListener(this);
			criarItinerario.addActionListener(this);
			criarItinerario.addActionListener(e -> {
				janela.dispose();
			});
			deletarItinerario.addActionListener(this);
			deletarItinerario.addActionListener(e -> {
				janela.dispose();
			});
			
			break;
			
		case 2:
			janela = new JFrame("Opcoes de Itinerario");
			tituloLista = new JLabel("Selecione um Itinerario");
			itinerarios = new ArrayList<String>();
			itinerarios.add("Sao Paulo");
			listaItinerarios = new JList<String>();
			
			tituloLista.setFont(new Font("Arial", Font.BOLD, 20));
			tituloLista.setBounds(90, 10, 250, 30);
			listaItinerarios.setBounds(20, 50, 350, 100);
			listaItinerarios.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
			listaItinerarios.setVisibleRowCount(10);
			
			janela.setLayout(null);
			
			janela.add(listaItinerarios);
			janela.add(tituloLista);
			
			janela.setSize(400,250);
			janela.setVisible(true);
			
			listaItinerarios.addListSelectionListener(this);
			
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
		
		if(leitura == voltar && seletor == 1)
			new GerenteView().MostrarDados();
		if(leitura == voltar && seletor == 2)
			new PassageiroView();
		
		if(leitura == criarItinerario) {
			new OpcoesCriarDel().opcoes(3); 
			/*JOptionPane.showMessageDialog(null, "ainda precisa ser implementada\n"
					+"as funcionalidade para essa opcao\n",null, 
					JOptionPane.INFORMATION_MESSAGE);*/
		}
		if(leitura == deletarItinerario) {
			new OpcoesCriarDel().opcoes(4);
			/*JOptionPane.showMessageDialog(null, "ainda precisa ser implementada\n"
					+"as funcionalidade para essa opcao\n",null, 
					JOptionPane.INFORMATION_MESSAGE);*/
		}
		
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		Object procura = e.getSource();
		
		if(e.getValueIsAdjusting() && procura == listaItinerarios)
			JOptionPane.showMessageDialog(null, "ainda precisa ser implementada\n"
					+"as funcionalidade para essa opcao\n",null, 
					JOptionPane.INFORMATION_MESSAGE);
	}
}
