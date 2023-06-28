package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TelaMenu implements ActionListener{
	private static JFrame janelaInic = new JFrame("Tela Inicial");
	private static JLabel tituloInic = new JLabel("Menu Inicial");
	private static JButton gerente = new JButton("Gerente");
	private static JButton passageiro = new JButton("Passageiro");
	
	public TelaMenu() {
		tituloInic.setFont(new Font("SansSerif", Font.BOLD, 20));
		tituloInic.setBounds(153, 20, 120, 30);
		gerente.setFont(new Font("Arial", Font.BOLD, 12));
		gerente.setBounds(210, 80, 100, 50);
		passageiro.setFont(new Font("Arial", Font.BOLD, 12));
		passageiro.setBounds(100, 80, 100, 50);
		
		janelaInic.setLayout(null);
		
		janelaInic.add(tituloInic);
		janelaInic.add(gerente);
		janelaInic.add(passageiro);
		
		janelaInic.setSize(400,250);
		janelaInic.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janelaInic.setVisible(true);
	}
	
	public static void main(String[] args) {
		TelaMenu inicio = new TelaMenu();
		
		gerente.addActionListener(inicio);
		gerente.addActionListener(e ->{
			janelaInic.dispose();
		});
		passageiro.addActionListener(inicio);
		passageiro.addActionListener(e ->{
			janelaInic.dispose();
		});
	}
	
	public void actionPerformed(ActionEvent e) {
		Object origemEvento = e.getSource();
		
		if (origemEvento == gerente)
			new GerenteView().MostrarDados();
			
			/*JOptionPane.showMessageDialog(null, "ainda precisa ser implementada\n"
					+"as funcionalidades voltadas a\n"
					+"Gerente",null, 
					JOptionPane.INFORMATION_MESSAGE);*/
		if(origemEvento == passageiro)
			new PassageiroView();
			/*JOptionPane.showMessageDialog(null, "ainda precisa ser implementada\n"
					+"as funcionalidades voltadas a\n"
					+"Passageiro",null, 
					JOptionPane.INFORMATION_MESSAGE);*/
	}
	
}
