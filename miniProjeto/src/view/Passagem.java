package view;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.*;

public class Passagem implements ActionListener, ListSelectionListener{
	private JFrame janela;
	private JLabel tituloLista;
	private JButton criarPassagem;
	private JButton deletarPassagem;
	private JList<String> listaPassagem;
	private ArrayList<String> passagem;
	private JButton voltar;
	
	private int seletor;
	
	public void opcoesPassagem(int op) {
		seletor = op;
		
		switch(op) {// 1 = GerenteView, 2 = PassageiroView
		case 1:
			janela = new JFrame("Opcoes de Passagens");
			tituloLista = new JLabel("Selecione uma Passagem");
			passagem = new ArrayList<String>();
			passagem.add("Sao Paulo");
			listaPassagem = new JList<String>();
			criarPassagem = new JButton("Criar");
			deletarPassagem = new JButton("Deletar");
			
			tituloLista.setFont(new Font("Arial", Font.BOLD, 20));
			tituloLista.setBounds(90, 10, 250, 30);
			listaPassagem.setBounds(20, 50, 350, 100);
			listaPassagem.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
			listaPassagem.setVisibleRowCount(10);
			criarPassagem.setBounds(90,155,100,50);
			deletarPassagem.setBounds(210,155,100,50);
			
			janela.setLayout(null);
			
			janela.add(listaPassagem);
			janela.add(tituloLista);
			janela.add(criarPassagem);
			janela.add(deletarPassagem);
			
			janela.setSize(400,250);
			janela.setVisible(true);
			
			listaPassagem.addListSelectionListener(this);	
			criarPassagem.addActionListener(this);
			criarPassagem.addActionListener(e -> {
				janela.dispose();
			});
			deletarPassagem.addActionListener(this);
			deletarPassagem.addActionListener(e -> {
				janela.dispose();
			});
			
			break;
		case 2:
			janela = new JFrame("Opcoes de Passagens");
			tituloLista = new JLabel("Selecione uma Passagem");
			passagem = new ArrayList<String>();
			passagem.add("Sao Paulo");
			listaPassagem = new JList<String>();
			
			tituloLista.setFont(new Font("Arial", Font.BOLD, 20));
			tituloLista.setBounds(90, 10, 250, 30);
			listaPassagem.setBounds(20, 50, 350, 100);
			listaPassagem.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
			listaPassagem.setVisibleRowCount(10);
			
			janela.setLayout(null);
			
			janela.add(listaPassagem);
			janela.add(tituloLista);
			
			janela.setSize(400,250);
			janela.setVisible(true);
			
			listaPassagem.addListSelectionListener(this);
			
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
		
		if(leitura == criarPassagem) {
			new OpcoesCriarDel().opcoes(1); 
			/*JOptionPane.showMessageDialog(null, "ainda precisa ser implementada\n"
					+"as funcionalidade para essa opcao\n",null, 
					JOptionPane.INFORMATION_MESSAGE);*/
		}
		if(leitura == deletarPassagem) {
			new OpcoesCriarDel().opcoes(2);
			/*JOptionPane.showMessageDialog(null, "ainda precisa ser implementada\n"
					+"as funcionalidade para essa opcao\n",null, 
					JOptionPane.INFORMATION_MESSAGE);*/
		}
		
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		Object procura = e.getSource();
		
		if(e.getValueIsAdjusting() && procura == listaPassagem)
			JOptionPane.showMessageDialog(null, "ainda precisa ser implementada\n"
					+"as funcionalidade para essa opcao\n",null, 
					JOptionPane.INFORMATION_MESSAGE);
	}
	
}
