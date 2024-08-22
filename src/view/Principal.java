package view;

import controller.ThreadController;
import javax.swing.JOptionPane;

public class Principal {

	public static void main(String[] args) {
		String nome;
		for(int i=0; i<3; i++) {
			nome = JOptionPane.showInputDialog("Digite o endereço do servidor: ");
			ThreadController tc = new ThreadController(nome);
			tc.start();
		}

	}

}
