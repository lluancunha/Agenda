package model;

import java.awt.EventQueue;

import javax.swing.JDialog;
import java.awt.Toolkit;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;

public class Sobre extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sobre dialog = new Sobre();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public Sobre() {
		setModal(true);
		setTitle("Sobre");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Sobre.class.getResource("/img/notepad.png")));
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Agenda de contatos");
		lblNewLabel.setFont(lblNewLabel.getFont().deriveFont(lblNewLabel.getFont().getStyle() | Font.BOLD));
		lblNewLabel.setBounds(10, 11, 126, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Autor: Luan Cunha");
		lblNewLabel_1.setBounds(10, 34, 105, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Versão 1.0");
		lblNewLabel_2.setBounds(10, 69, 105, 14);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Sob a licença MIT");
		lblNewLabel_3.setBounds(10, 146, 92, 14);
		getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(Sobre.class.getResource("/img/mit-icon.png")));
		lblNewLabel_4.setBounds(296, 122, 128, 128);
		getContentPane().add(lblNewLabel_4);

	}
}
