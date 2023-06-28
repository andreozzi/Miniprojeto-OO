package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GerenteView implements ActionListener{
	private JFrame janela;
	private JLabel titulo;
	private JButton passagem;
	private JButton itinerario;
	private JButton voltar;
	
	public void MostrarDados() {
		
			janela = new JFrame("Opcoes para Gerente");
			titulo = new JLabel("Selecione uma opcao");
			passagem = new JButton("Passagem");
			itinerario = new JButton("Itinerario");
			
			
			titulo.setFont(new Font("Arial", Font.BOLD, 20));
			titulo.setBounds(90, 10, 250, 30);
			
			passagem.setBounds(100, 80, 100, 50);
			itinerario.setBounds(210, 80, 100, 50);
			
			janela.setLayout(null);
			
			janela.add(titulo);
			janela.add(passagem);
			janela.add(itinerario);
			
			janela.setSize(400,250);
			janela.setVisible(true);
			
			passagem.addActionListener(this);
			passagem.addActionListener(e -> {
				janela.dispose();
			});
			itinerario.addActionListener(this);
			itinerario.addActionListener(e -> {
				janela.dispose();
			});
			
			voltar = new JButton("Voltar");
			voltar.setBounds(313, 177, 68, 30);
			janela.add(voltar);
			voltar.addActionListener(this);
			voltar.addActionListener(e-> {
				janela.dispose();
			});
			
			janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void actionPerformed(ActionEvent e) {
		Object procura = e.getSource();
		
		if(procura == voltar)
			new TelaMenu();
		
		if(procura == passagem)
			new Passagem().opcoesPassagem(1);
			/*JOptionPane.showMessageDialog(null, "ainda precisa ser implementada\n"
					+"as funcionalidades voltadas a\n"
					+"criar passagem",null, 
					JOptionPane.INFORMATION_MESSAGE);*/
		
		if(procura == itinerario) {
			new Itinerario().opcoesItinerario(1);
			
		}
	}
}
	
