package visao;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controle.UsuarioDAO;
import mensagens.CadastroSucesso;
import mensagens.LoginErro;
import mensagens.LoginErro1;
import modelo.Usuario;
import utilidades.RoundButton;
import utilidades.RoundedPanel;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtEmail;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel;
	private RoundButton btnNewButton;
	private JPasswordField txtSenha;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_6;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/imagem/logoampliada.png")));
		setExtendedState(MAXIMIZED_BOTH);
		setTitle("Entrar");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 2000, 1400);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 102, 102));
		contentPane.setForeground(new Color(0, 102, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		RoundedPanel panel1 = new RoundedPanel();
		panel1.setBounds(761, 215, 443, 542);
		panel1.setBorder(null);
		panel1.setBackground(SystemColor.inactiveCaptionBorder);
		panel1.setForeground(new Color(204, 204, 204));
		contentPane.add(panel1);
		panel1.setLayout(null);

		btnNewButton = new RoundButton("Entrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Usuario usuario = new Usuario();
				String email = txtEmail.getText();
				String senha = txtSenha.getText();
				usuario.setEmail(email);
				usuario.setSenha(senha);

				UsuarioDAO usuarioDAO = UsuarioDAO.getInstancia();
				Usuario retorno = usuarioDAO.consultarLogin(usuario);

				if (!email.isEmpty() && !senha.isEmpty()) {

					if (retorno != null) {
						if (retorno.getCargo().equalsIgnoreCase("administrador")) {
							MenuAdmin telaPrincipal = new MenuAdmin(retorno);
							telaPrincipal.setLocationRelativeTo(null);
							telaPrincipal.setVisible(true);
							CadastroSucesso sucesso = new CadastroSucesso("Você entrou com sucesso!");
							sucesso.setVisible(true);
							sucesso.setLocationRelativeTo(null);
							dispose();
						} else {
							MenuFuncionario telaFuncionario = new MenuFuncionario(retorno);
							telaFuncionario.setLocationRelativeTo(null);
							telaFuncionario.setVisible(true);
							CadastroSucesso sucesso = new CadastroSucesso("Você entrou com sucesso!");
							sucesso.setVisible(true);
							sucesso.setLocationRelativeTo(null);
							dispose();
						}

					} else {
						LoginErro janelaErro = new LoginErro("Senha ou E-mail Incorretos!");
						janelaErro.setLocationRelativeTo(null);
						janelaErro.setVisible(true);
					}

				} else {
					LoginErro1 janelaErro1 = new LoginErro1("Senha ou E-mail não Preenchidos!");
					janelaErro1.setLocationRelativeTo(null);
					janelaErro1.setVisible(true);
				}
			}
		});
		btnNewButton.setBounds(120, 414, 219, 42);
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(0, 128, 128));
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 18));
		panel1.add(btnNewButton);

		txtEmail = new JTextField();
		txtEmail.setBounds(127, 258, 212, 31);
		panel1.add(txtEmail);
		txtEmail.setBackground(new Color(255, 255, 255));
		txtEmail.setForeground(new Color(0, 0, 0));
		txtEmail.setFont(new Font("Dialog", Font.BOLD, 13));
		txtEmail.setColumns(10);

		lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Login.class.getResource("/imagem/titulo2.png")));
		lblNewLabel_2.setBounds(-362, 76, 734, 114);
		panel1.add(lblNewLabel_2);

		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("/imagem/logo.png")));
		lblNewLabel.setBounds(-362, 61, 916, 150);
		panel1.add(lblNewLabel);

		txtSenha = new JPasswordField();
		txtSenha.setBackground(new Color(255, 255, 255));
		txtSenha.setBounds(127, 319, 212, 31);
		panel1.add(txtSenha);

		JLabel lblNewLabel_4 = new JLabel("E-mail:");
		lblNewLabel_4.setFont(new Font("Dialog", Font.BOLD, 13));
		lblNewLabel_4.setBounds(128, 239, 56, 14);
		panel1.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon(Login.class.getResource("/imagem/icone email.png")));
		lblNewLabel_5.setBounds(76, 234, 66, 55);
		panel1.add(lblNewLabel_5);

		lblNewLabel_3 = new JLabel("Senha:");
		lblNewLabel_3.setFont(new Font("Dialog", Font.BOLD, 13));
		lblNewLabel_3.setBounds(128, 300, 56, 14);
		panel1.add(lblNewLabel_3);

		lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setIcon(new ImageIcon(Login.class.getResource("/imagem/icone senha.png")));
		lblNewLabel_6.setBounds(72, 295, 56, 55);
		panel1.add(lblNewLabel_6);

		JCheckBox showPasswordCheckBox = new JCheckBox("Mostrar senha");
		showPasswordCheckBox.setBounds(126, 357, 132, 15);
		panel1.add(showPasswordCheckBox);
		showPasswordCheckBox.setBackground(Color.WHITE);
		showPasswordCheckBox.setFont(new Font("Dialog", Font.PLAIN, 11));

		showPasswordCheckBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (showPasswordCheckBox.isSelected()) {
					txtSenha.setEchoChar((char) 0);
				} else {
					txtSenha.setEchoChar('\u2022');
				}
			}
		});

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(0, 0, 2040, 1072);
		lblNewLabel_1.setIcon(new ImageIcon(Login.class.getResource("/imagem/fundo.png")));
		contentPane.add(lblNewLabel_1);
	}
}