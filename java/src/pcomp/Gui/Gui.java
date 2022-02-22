package pcomp.Gui;

import java.awt.EventQueue;
import pcomp.IO.*;
import pcomp.prolog.parser.PrologParser;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.JTextPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;

import java.awt.TextArea;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class Gui {
	private static String[] argss;
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		argss=args;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui window = new Gui();
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
	public Gui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 866, 586);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(0, 0, 862, 25);
		frame.getContentPane().add(toolBar);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 23, 862, 536);
		frame.getContentPane().add(tabbedPane);
		
		JPanel acceuil = new JPanel();
		tabbedPane.addTab("Accueil", null, acceuil, null);
		acceuil.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("TODO");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(385, 183, 49, 14);
		acceuil.add(lblNewLabel);
		
		JPanel interprete = new JPanel();
		tabbedPane.addTab("Interprete", null, interprete, null);
		interprete.setLayout(null);
		
		TextArea text = new TextArea();
		text.setBounds(0, 0, 857, 291);
		interprete.add(text);
		
		TextArea outtext = new TextArea();
		outtext.setEditable(false);
		outtext.setBounds(0, 342, 857, 150);
		interprete.add(outtext);
		
		JLabel labcon = new JLabel("OutPut");
		labcon.setFont(new Font("Tahoma", Font.PLAIN, 14));
		labcon.setBounds(51, 322, 55, 14);
		interprete.add(labcon);
		
		JButton gob = new JButton("Interpreter");
		gob.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (text.getText().equals("")) {
					outtext.setText("Veuillez ecrire du code dans la zone de texte au dessus!\n");
				}else {
					outtext.setText(PrologParser.parseString(text.getText()).toString());
				}
			}
		});
		gob.setBounds(359, 297, 131, 35);
		interprete.add(gob);
	JButton save = new JButton(new ImageIcon( "./data/save.png" ));
	save.setToolTipText("Sauvegarder");
	save.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if ((text.getText().equals(""))||(outtext.getText().equals(""))) {
				Question.warn("Aucun Text n'a été interprété pour le moment!\n Merci de lancer une interpretation avant de save!\n");
				return;
			}
			else {
				String path=Question.getrep("Entrée le path a enregistrer! (a default ce sera ./Test.proji)\n", "./Test.proji", "Interface Temporaire de Save de Fichier");
				try {
					String pathi=OutputParser.writer(path, text.getText());
					Question.info("Fichier ecrit avec succes sur : "+pathi);
				} catch (IOException e1) {
					Question.warn("Une erreur est survenue a l'écriture! \n"+e1.getMessage());
				}
			}
		}
	})
	;
	JButton importe = new JButton(new ImageIcon( "data/in.png" ));
	importe.setToolTipText("Importer");
	importe.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			try {
				text.setText(InputParser.gettext(frame));
				Question.info("Fichier chargé avec Succes!");
			} catch (IOException e1) {
				Question.warn("Erreur: "+e1.getMessage()+" \n Veuillez recommencer");
			}
		}
	})
	;
	/*JButton newb = new JButton(new ImageIcon( "./data/new.png" ));
	newb.setToolTipText("Nouvelle Fenetre");
	newb.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			Question.info("Trouve un moyen de cree un new ");
			try {
				Runtime.getRuntime().exec("java -jar "+argss[0]+".jar");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				Question.warn("Erreur : "+e1.getMessage());
			}
			
		}
	})
	;
	toolBar.add(newb);*/
	toolBar.add(importe);
	toolBar.add(save);
	}
}
