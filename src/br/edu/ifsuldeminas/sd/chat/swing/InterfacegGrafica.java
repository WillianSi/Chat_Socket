package br.edu.ifsuldeminas.sd.chat.swing;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import br.edu.ifsuldeminas.sd.chat.ChatException;
import br.edu.ifsuldeminas.sd.chat.ChatFactory;
import br.edu.ifsuldeminas.sd.chat.MessageContainer;
import br.edu.ifsuldeminas.sd.chat.Sender;

public class InterfacegGrafica implements MessageContainer {
	
	private static InterfacegGrafica window;

	private JFrame frmOla;
	private JTextField textLocal;
	private JTextField textRemota;
	private JTextField textName;
	private JTextField msg_text;
	public JTextArea textRecebimento;
	
	private int localPort;
	private int serverPort;
	private String from;
	private Sender sender;
	
	public static int RECEIVER_BUFFER_SIZE = 10;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new InterfacegGrafica();
					window.frmOla.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InterfacegGrafica() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frmOla = new JFrame();
		frmOla.setForeground(Color.WHITE);
		frmOla.setFont(new Font("Impact", Font.BOLD, 12));
		frmOla.setIconImage(Toolkit.getDefaultToolkit().getImage(InterfacegGrafica.class.getResource("/Img/enviado.png")));
		frmOla.setTitle("Chat Socket");
		frmOla.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 11));
		frmOla.setBackground(new Color(204, 153, 255));
		frmOla.getContentPane().setBackground(new Color(204, 204, 255));
		frmOla.setBounds(100, 100, 732, 519);
		frmOla.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmOla.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Porta local:");
		lblNewLabel.setBackground(Color.LIGHT_GRAY);
		lblNewLabel.setForeground(Color.DARK_GRAY);
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblNewLabel.setBounds(10, 30, 86, 21);
		frmOla.getContentPane().add(lblNewLabel);
		
		JLabel lblPortaRemota = new JLabel("Porta remota:");
		lblPortaRemota.setForeground(Color.DARK_GRAY);
		lblPortaRemota.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblPortaRemota.setBounds(249, 30, 106, 21);
		frmOla.getContentPane().add(lblPortaRemota);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setForeground(Color.DARK_GRAY);
		lblNome.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblNome.setBounds(506, 30, 57, 21);
		frmOla.getContentPane().add(lblNome);
		
		textLocal = new JTextField();
		textLocal.setForeground(Color.DARK_GRAY);
		textLocal.setBackground(Color.WHITE);
		textLocal.setFont(new Font("Segoe UI", Font.BOLD, 15));
		textLocal.setBounds(95, 31, 144, 19);
		frmOla.getContentPane().add(textLocal);
		textLocal.setColumns(10);
		
		textRemota = new JTextField();
		textRemota.setForeground(Color.DARK_GRAY);
		textRemota.setBackground(Color.WHITE);
		textRemota.setFont(new Font("Segoe UI", Font.BOLD, 15));
		textRemota.setColumns(10);
		textRemota.setBounds(352, 31, 144, 19);
		frmOla.getContentPane().add(textRemota);
		
		textName = new JTextField();
		textName.setForeground(Color.DARK_GRAY);
		textName.setBackground(Color.WHITE);
		textName.setFont(new Font("Segoe UI", Font.BOLD, 15));
		textName.setColumns(10);
		textName.setBounds(564, 31, 144, 19);
		frmOla.getContentPane().add(textName);
		
		JButton btnConectar = new JButton("");
		btnConectar.setIcon(new ImageIcon(InterfacegGrafica.class.getResource("/Img/chat-de-video.png")));
		btnConectar.setBackground(Color.WHITE);
		btnConectar.setForeground(Color.WHITE);
		btnConectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				localPort = Integer.parseInt(textLocal.getText());
				serverPort = Integer.parseInt(textRemota.getText());
				from = textName.getText();
				
				try {
					sender = ChatFactory.build("localhost", serverPort,localPort, window);
					} catch (ChatException chatException) {
					System.err.printf(String.format("Houve algum erro na conação. Mensagem do erro: %s",chatException.getCause().getMessage()));
				}
			}
		});
		
		
		btnConectar.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnConectar.setBounds(11, 61, 70, 41);
		frmOla.getContentPane().add(btnConectar);
		
		JButton msg_send = new JButton("");
		msg_send.setIcon(new ImageIcon(InterfacegGrafica.class.getResource("/Img/enviar-correio (1).png")));
		msg_send.setBackground(new Color(255, 255, 255));
		msg_send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enviarMensagem();
			}
		});
		msg_send.setFont(new Font("Segoe UI", Font.BOLD, 15));
		msg_send.setBounds(597, 364, 57, 47);
		frmOla.getContentPane().add(msg_send);
		
		msg_text = new JTextField();
		msg_text.setForeground(new Color(0, 0, 0));
		msg_text.setBackground(Color.WHITE);
		msg_text.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		msg_text.setColumns(10);
		msg_text.setBounds(10, 364, 577, 47);
		frmOla.getContentPane().add(msg_text);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 133, 698, 221);
		frmOla.getContentPane().add(scrollPane);
		
		textRecebimento = new JTextArea();
		scrollPane.setViewportView(textRecebimento);
		textRecebimento.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		textRecebimento.setBackground(Color.WHITE);
		textRecebimento.setForeground(Color.BLACK);
		textRecebimento.setEditable(false);
		
		JButton btnLimpar = new JButton("");
		btnLimpar.setIcon(new ImageIcon(InterfacegGrafica.class.getResource("/Img/vassoura-voadora (1).png")));
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textRecebimento.setText("");
			}
		});
		btnLimpar.setForeground(Color.DARK_GRAY);
		btnLimpar.setFont(new Font("Segoe UI", Font.BOLD, 10));
		btnLimpar.setBackground(Color.WHITE);
		btnLimpar.setBounds(659, 82, 49, 41);
		frmOla.getContentPane().add(btnLimpar);
		
	}
	
    private void enviarMensagem() {
    	try {
			String message = "";
			String conversa = "";
			message = msg_text.getText();
			conversa = String.format("%s: %s \n",from ,message);
			textRecebimento.append(conversa);
			message = String.format("%s%s%s", message, MessageContainer.FROM, from);
			sender.send(message);
			msg_text.setText("");
			msg_text.requestFocus();
			
		} catch (ChatException chatException) {
			System.err.printf(String.format("Houve algum erro no chat. Mensagem do erro: %s",chatException.getCause().getMessage()));
		}
    }

	@Override
	public void newMessage(String message) {
		if (message == null || message.equals(""))
			return;
		String[] messageParts = message.split(MessageContainer.FROM);
		String msgra = String.format("%s: %s \n", messageParts[1],messageParts[0]);
		textRecebimento.append(msgra);
	}  
}