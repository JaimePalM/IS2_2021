package practica3;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.JTextPane;
import javax.swing.SpinnerDateModel;
import javax.swing.border.TitledBorder;

import java.util.Date;
import java.util.Calendar;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VentanaPrincipal {

	// Variables del JSwing
	private JFrame frmControladorAlarmas;
	private JTextField idAlarma;
	private JTextField idAlarmaMod;
	JSpinner spinner = new JSpinner();
	JTextPane textAlarmasActivas = new JTextPane();
	JTextPane textAlarmasDesactivadas = new JTextPane();
	
	// Controlador que gestiona todas las alarmas
	ControladorAlarma controladorAlarma = new ControladorAlarma();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal window = new VentanaPrincipal();
					window.frmControladorAlarmas.setVisible(true);
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
		frmControladorAlarmas = new JFrame();
		frmControladorAlarmas.setResizable(false);
		frmControladorAlarmas.setTitle("Controlador Alarmas");
		frmControladorAlarmas.setBounds(100, 100, 465, 500);
		frmControladorAlarmas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frmControladorAlarmas.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel lblIdAlarma = new JLabel("Id Alarma");
		lblIdAlarma.setBounds(26, 118, 75, 14);
		panel.add(lblIdAlarma);
		
		idAlarma = new JTextField();
		idAlarma.setBounds(111, 115, 108, 20);
		panel.add(idAlarma);
		idAlarma.setColumns(10);
		
		JLabel lblHoraAlarma = new JLabel("Hora Alarma");
		lblHoraAlarma.setBounds(26, 149, 75, 14);
		panel.add(lblHoraAlarma);
		
		spinner.setModel(new SpinnerDateModel(new Date(1617870060000L), null, null, Calendar.HOUR));
		spinner.setBounds(111, 146, 108, 20);
		panel.add(spinner);
		
		// BOTON NUEVA ALARMA
		JButton btnNuevaAlarma = new JButton("Nueva Alarma");
		btnNuevaAlarma.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String id = idAlarma.getText();
				Date hora = (Date)spinner.getValue();
				controladorAlarma.nuevaAlarma(id, hora);
				refrescaPaneles();
			}
		});
		btnNuevaAlarma.setBounds(48, 181, 159, 23);
		panel.add(btnNuevaAlarma);
		
		JPanel panelCrear = new JPanel();
		panelCrear.setBorder(new TitledBorder(null, "Crear Alarma", 
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelCrear.setBounds(10, 99, 222, 116);
		panel.add(panelCrear);
		
		JLabel lblAlarmasActivas = new JLabel("Alarmas Activas");
		lblAlarmasActivas.setBounds(274, 26, 140, 14);
		panel.add(lblAlarmasActivas);
		
		JLabel lblAlarmasDesactivadas = new JLabel("Alarmas Desactivadas");
		lblAlarmasDesactivadas.setBounds(274, 207, 140, 14);
		panel.add(lblAlarmasDesactivadas);
		
		textAlarmasDesactivadas.setEditable(false);
		textAlarmasDesactivadas.setBounds(274, 232, 140, 135);
		panel.add(textAlarmasDesactivadas);
		
		textAlarmasActivas.setEditable(false);
		textAlarmasActivas.setBounds(274, 49, 140, 135);
		panel.add(textAlarmasActivas);
		
		JLabel lblIdAlarma_1 = new JLabel("Id Alarma");
		lblIdAlarma_1.setBounds(26, 260, 75, 14);
		panel.add(lblIdAlarma_1);
		
		idAlarmaMod = new JTextField();
		idAlarmaMod.setBounds(111, 257, 108, 20);
		panel.add(idAlarmaMod);
		idAlarmaMod.setColumns(10);
		
		JPanel panelMod = new JPanel();
		panelMod.setBorder(new TitledBorder(null, "Modificar Alarma", 
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelMod.setBounds(10, 232, 222, 61);
		panel.add(panelMod);
		
		// BOTON APAGAR
		JButton btnapagar = new JButton("?APAGAR!");
		btnapagar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				controladorAlarma.apagar();
				refrescaPaneles();
			}
		});
		btnapagar.setBounds(66, 324, 116, 37);
		panel.add(btnapagar);
		
		// BOTON ALARMA ON
		JButton btnOn = new JButton("ON");
		btnOn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String id = idAlarmaMod.getText();
				if (id.isEmpty() || controladorAlarma.alarma(id) == null) return;
				controladorAlarma.alarmaOn(id);
				refrescaPaneles();
			}
		});
		btnOn.setBounds(274, 378, 67, 20);
		panel.add(btnOn);
		
		// BOTON ALARMA OFF
		JButton btnOff = new JButton("OFF");
		btnOff.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String id = idAlarmaMod.getText();
				if (id.isEmpty() || controladorAlarma.alarma(id) == null) return;
				controladorAlarma.alarmaOff(id);
				refrescaPaneles();
			}
		});
		btnOff.setBounds(351, 378, 63, 20);
		panel.add(btnOff);
		
		// BOTON ELIMINAR
		JButton btnEliminar = new JButton("ELIMINAR");
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String id = idAlarmaMod.getText();
				if (id.isEmpty() || controladorAlarma.alarma(id) == null) return;
				controladorAlarma.borraAlarma(id);
				refrescaPaneles();
			}
		});
		btnEliminar.setBounds(274, 409, 140, 23);
		panel.add(btnEliminar);		
	}
	
	/**
	 * Metodo auxiliar que refresca la informacion de las alarmas activas y desactivas.
	 */
	public void refrescaPaneles () {
		String alarmasDesactivadas = "";
		for (Alarma a: controladorAlarma.alarmasDesactivadas().values()) {
			alarmasDesactivadas = alarmasDesactivadas + a.getId() + "\n"; 
		}
		textAlarmasDesactivadas.setText(alarmasDesactivadas);
		String alarmasActivas = "";
		for (Alarma a: controladorAlarma.alarmasActivadas()) {
			alarmasActivas = alarmasActivas + a.getId() + "\n"; 
		}
		textAlarmasActivas.setText(alarmasActivas);
	}
}
