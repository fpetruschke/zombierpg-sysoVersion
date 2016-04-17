package zombieRPG;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Component;

import javax.swing.SpringLayout;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Mainmenu {

	private JFrame frmZombierpg;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Mainmenu window = new Mainmenu();
					window.frmZombierpg.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Mainmenu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmZombierpg = new JFrame();
		frmZombierpg.getContentPane().setBackground(Color.DARK_GRAY);
		frmZombierpg.setTitle("ZombieRPG");
		frmZombierpg.setBounds(100, 100, 450, 300);
		frmZombierpg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frmZombierpg.getContentPane().setLayout(springLayout);
		
		JLabel lblZombieRpg = new JLabel("Zombie RPG");
		springLayout.putConstraint(SpringLayout.WEST, lblZombieRpg, 123, SpringLayout.WEST, frmZombierpg.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lblZombieRpg, -191, SpringLayout.SOUTH, frmZombierpg.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblZombieRpg, 300, SpringLayout.WEST, frmZombierpg.getContentPane());
		lblZombieRpg.setFont(new Font("Chiller", Font.BOLD, 40));
		lblZombieRpg.setForeground(Color.RED);
		frmZombierpg.getContentPane().add(lblZombieRpg);
		
		JButton btnSpielStarten = new JButton("Spiel starten");
		springLayout.putConstraint(SpringLayout.NORTH, btnSpielStarten, 25, SpringLayout.SOUTH, lblZombieRpg);
		springLayout.putConstraint(SpringLayout.WEST, btnSpielStarten, 160, SpringLayout.WEST, frmZombierpg.getContentPane());
		btnSpielStarten.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
			}
		});
		frmZombierpg.getContentPane().add(btnSpielStarten);
		
		JButton btnCredits = new JButton("Credits");
		springLayout.putConstraint(SpringLayout.WEST, btnCredits, 160, SpringLayout.WEST, frmZombierpg.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnCredits, 1, SpringLayout.EAST, btnSpielStarten);
		btnCredits.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnCredits, 16, SpringLayout.SOUTH, btnSpielStarten);
		frmZombierpg.getContentPane().add(btnCredits);
		
		JButton btnBeenden = new JButton("Beenden");
		springLayout.putConstraint(SpringLayout.EAST, btnBeenden, 1, SpringLayout.EAST, btnSpielStarten);
		btnBeenden.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnBeenden, 18, SpringLayout.SOUTH, btnCredits);
		springLayout.putConstraint(SpringLayout.WEST, btnBeenden, 0, SpringLayout.WEST, btnSpielStarten);
		frmZombierpg.getContentPane().add(btnBeenden);
	}
}
