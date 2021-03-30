package practica3;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class VentanaPrincipal {

	private JFrame frame;
	private JTextField idAlarma;
	private JTextField horaAlarma;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal window = new VentanaPrincipal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 400, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblIdAlarma = new JLabel("Id Alarma");
		lblIdAlarma.setBounds(26, 163, 46, 14);
		panel.add(lblIdAlarma);
		
		idAlarma = new JTextField();
		idAlarma.setBounds(99, 160, 86, 20);
		panel.add(idAlarma);
		idAlarma.setColumns(10);
		
		horaAlarma = new JTextField();
		horaAlarma.setBounds(99, 191, 86, 20);
		panel.add(horaAlarma);
		horaAlarma.setColumns(10);
		
		JLabel lblHoraAlarma = new JLabel("Hora Alarma");
		lblHoraAlarma.setBounds(26, 194, 63, 14);
		panel.add(lblHoraAlarma);
		
		JButton btnNuevaAlarma = new JButton("Nueva Alarma");
		btnNuevaAlarma.setBounds(26, 222, 159, 23);
		panel.add(btnNuevaAlarma);
		
		JButton btnapagar = new JButton("¡APAGAR!");
		btnapagar.setBounds(48, 260, 116, 37);
		panel.add(btnapagar);
	}
}
