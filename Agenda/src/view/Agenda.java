package view;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.DAO;
import model.Sobre;

public class Agenda extends JFrame {

	// criação de objetos (JDBC)
	DAO dao = new DAO();
	private Connection con;
	private PreparedStatement pst;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtID;
	private JTextField txtNome;
	private JTextField txtFone;
	private JTextField txtEmail;
	private JLabel lblStatus;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Agenda frame = new Agenda();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Agenda() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				status();
			}
		});
		setResizable(false);
		setTitle("Agenda de contatos");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Agenda.class.getResource("/img/notepad.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setToolTipText("limpar campos");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("ID:");
		lblNewLabel.setBounds(10, 11, 46, 14);
		contentPane.add(lblNewLabel);

		txtID = new JTextField();
		txtID.setEditable(false);
		txtID.setBounds(50, 8, 69, 20);
		contentPane.add(txtID);
		txtID.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Nome:");
		lblNewLabel_1.setBounds(10, 42, 46, 14);
		contentPane.add(lblNewLabel_1);

		txtNome = new JTextField();
		txtNome.setBounds(50, 39, 199, 20);
		contentPane.add(txtNome);
		txtNome.setColumns(10);

		txtFone = new JTextField();
		txtFone.setBounds(50, 70, 118, 20);
		contentPane.add(txtFone);
		txtFone.setColumns(10);

		txtEmail = new JTextField();
		txtEmail.setBounds(50, 101, 199, 20);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Fone:");
		lblNewLabel_2.setBounds(10, 73, 46, 14);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Email:");
		lblNewLabel_3.setBounds(10, 101, 46, 14);
		contentPane.add(lblNewLabel_3);

		JButton btnCreate = new JButton("");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// botao add contato
				adicionarContato();
			}
		});
		btnCreate.setToolTipText("Salvar contato");
		btnCreate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCreate.setContentAreaFilled(false);
		btnCreate.setBorder(null);
		btnCreate.setIcon(new ImageIcon(Agenda.class.getResource("/img/add.png")));
		btnCreate.setBounds(60, 132, 48, 48);
		contentPane.add(btnCreate);

		lblStatus = new JLabel("");
		lblStatus.setIcon(new ImageIcon(Agenda.class.getResource("/img/off1.png")));
		lblStatus.setBounds(376, 202, 48, 48);
		contentPane.add(lblStatus);

		JButton btnSobre = new JButton("");
		btnSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// as linhas abaixo fazem o link entre o Jframe e o JDialog
				Sobre sobre = new Sobre();
				sobre.setVisible(true);
			}
		});
		btnSobre.setToolTipText("Sobre");
		btnSobre.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSobre.setContentAreaFilled(false);
		btnSobre.setBorderPainted(false);
		btnSobre.setIcon(new ImageIcon(Agenda.class.getResource("/img/sobre.png")));
		btnSobre.setBounds(376, 11, 48, 48);
		contentPane.add(btnSobre);
		
		JButton btnLimpar = new JButton("");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//evento clicar no botão
				limparCampos();
			}
		});
		btnLimpar.setToolTipText("limpar campos");
		btnLimpar.setIcon(new ImageIcon(Agenda.class.getResource("/img/lixeira.png")));
		btnLimpar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLimpar.setContentAreaFilled(false);
		btnLimpar.setBorderPainted(false);
		btnLimpar.setBorder(null);
		btnLimpar.setBounds(8, 132, 48, 48);
		contentPane.add(btnLimpar);
	}// fim do construtor

	/**
	 * metodo para verificar o status de conexão
	 */
	private void status() {
		// System.out.println("teste janela ativada");
		try {
			// abrir a conexão com o banco
			con = dao.conectar();
			if (con == null) {
				// mudar o icone JLabel
				lblStatus.setIcon(new ImageIcon(Agenda.class.getResource("/img/off1.png")));
			} else {
				lblStatus.setIcon(new ImageIcon(Agenda.class.getResource("/img/on2.png")));
				// fechar conexão
				con.close();
			}
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	/**
	 * metodo para adicionar um contato no banco
	 */

	private void adicionarContato() {
		// System.out.println("teste do botao adicionar contato");

		// validação de campos obrogatórios
		if (txtNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "preencha o nome do contato");
			txtNome.requestFocus();

		} else if (txtFone.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "preencha o número do contato");
			txtFone.requestFocus();

		} else {
			//lógica principal
			//a linha abaixo cria uma variavel
			String create = "insert into contatos(nome,fone,email) values (?,?,?)";
			//tratamento de exceção
			try {
				//abrir conexão
				con = dao.conectar();
				
				//uso da class para executar a instrução sql e replicar no banco
				pst = con.prepareStatement(create);
				
				//setar os parametros (?,?,?) de acordo com os preenchimentos das caixas de texto
				pst.setString(1,  txtNome.getText());
				pst.setString(2,  txtFone.getText());
				pst.setString(3,  txtEmail.getText());
				pst.executeUpdate();
				JOptionPane.showMessageDialog(null,"contato adicionado com sucesso");
				
				//limpar campos
				limparCampos();
				
				//fechar conexão
				con.close();
				
			} catch (Exception e) {
				System.out.println(e);
			}
		  }
		} //fim do metodo adicionar contato
	
		/**
		 * metodo responsavel por limpar os campos
		 */
		private void limparCampos() {
			txtID.setText(null);
			txtNome.setText(null);
			txtFone.setText(null);
			txtEmail.setText(null);
			
		} //fim do metodo limpar campos
}// fim do código
