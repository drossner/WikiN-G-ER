package part.offline.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JSplitPane;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.CardLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import net.miginfocom.swing.MigLayout;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import java.awt.Color;

public class OfflinePartGui
{

	private JFrame frmWikiner;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable() {
			public void run()
			{
				try
				{
					OfflinePartGui window = new OfflinePartGui();
					window.frmWikiner.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public OfflinePartGui()
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		frmWikiner = new JFrame();
		frmWikiner.setTitle("Wiki-Ner");
		frmWikiner.setBounds(100, 100, 573, 359);
		frmWikiner.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frmWikiner.getContentPane().add(panel, BorderLayout.NORTH);
		
		JLabel lblWelcomeToWikiner = new JLabel("Welcome to Wiki-NER --- Offline Part");
		lblWelcomeToWikiner.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel.add(lblWelcomeToWikiner);
		
		JPanel panel_2 = new JPanel();
		frmWikiner.getContentPane().add(panel_2, BorderLayout.CENTER);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel_2.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		JLabel lblEnterDbname = new JLabel("Enter DB-Name:");
		lblEnterDbname.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblEnterDbname = new GridBagConstraints();
		gbc_lblEnterDbname.insets = new Insets(0, 0, 5, 5);
		gbc_lblEnterDbname.gridx = 1;
		gbc_lblEnterDbname.gridy = 0;
		panel_2.add(lblEnterDbname, gbc_lblEnterDbname);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.anchor = GridBagConstraints.WEST;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 0;
		panel_2.add(textField, gbc_textField);
		textField.setColumns(10);
		
		JLabel label = new JLabel("");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 3;
		gbc_label.gridy = 0;
		panel_2.add(label, gbc_label);
		
		JLabel lblEnterUsername = new JLabel("Enter Username:");
		lblEnterUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblEnterUsername = new GridBagConstraints();
		gbc_lblEnterUsername.insets = new Insets(0, 0, 5, 5);
		gbc_lblEnterUsername.gridx = 1;
		gbc_lblEnterUsername.gridy = 1;
		panel_2.add(lblEnterUsername, gbc_lblEnterUsername);
		
		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.anchor = GridBagConstraints.WEST;
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.gridx = 2;
		gbc_textField_1.gridy = 1;
		panel_2.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		JLabel lblEnterHostname = new JLabel("Enter Host:");
		lblEnterHostname.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblEnterHostname = new GridBagConstraints();
		gbc_lblEnterHostname.insets = new Insets(0, 0, 5, 5);
		gbc_lblEnterHostname.gridx = 1;
		gbc_lblEnterHostname.gridy = 2;
		panel_2.add(lblEnterHostname, gbc_lblEnterHostname);
		
		textField_2 = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.anchor = GridBagConstraints.WEST;
		gbc_textField_2.insets = new Insets(0, 0, 5, 5);
		gbc_textField_2.gridx = 2;
		gbc_textField_2.gridy = 2;
		panel_2.add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);
		
		JLabel lblEnterPortnumber = new JLabel("Enter Portnumber:");
		lblEnterPortnumber.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblEnterPortnumber = new GridBagConstraints();
		gbc_lblEnterPortnumber.insets = new Insets(0, 0, 5, 5);
		gbc_lblEnterPortnumber.gridx = 1;
		gbc_lblEnterPortnumber.gridy = 3;
		panel_2.add(lblEnterPortnumber, gbc_lblEnterPortnumber);
		
		textField_3 = new JTextField();
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.anchor = GridBagConstraints.WEST;
		gbc_textField_3.insets = new Insets(0, 0, 5, 5);
		gbc_textField_3.gridx = 2;
		gbc_textField_3.gridy = 3;
		panel_2.add(textField_3, gbc_textField_3);
		textField_3.setColumns(10);
		
		JLabel lblEnterPassword = new JLabel("Enter Password:");
		lblEnterPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblEnterPassword = new GridBagConstraints();
		gbc_lblEnterPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblEnterPassword.gridx = 1;
		gbc_lblEnterPassword.gridy = 4;
		panel_2.add(lblEnterPassword, gbc_lblEnterPassword);
		
		textField_4 = new JTextField();
		GridBagConstraints gbc_textField_4 = new GridBagConstraints();
		gbc_textField_4.anchor = GridBagConstraints.WEST;
		gbc_textField_4.insets = new Insets(0, 0, 5, 5);
		gbc_textField_4.gridx = 2;
		gbc_textField_4.gridy = 4;
		panel_2.add(textField_4, gbc_textField_4);
		textField_4.setColumns(10);
		
		JLabel lblEnterMaxThreadnumber = new JLabel("Enter Max Thread_Number:");
		lblEnterMaxThreadnumber.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblEnterMaxThreadnumber = new GridBagConstraints();
		gbc_lblEnterMaxThreadnumber.insets = new Insets(0, 0, 5, 5);
		gbc_lblEnterMaxThreadnumber.gridx = 1;
		gbc_lblEnterMaxThreadnumber.gridy = 5;
		panel_2.add(lblEnterMaxThreadnumber, gbc_lblEnterMaxThreadnumber);
		
		textField_5 = new JTextField();
		GridBagConstraints gbc_textField_5 = new GridBagConstraints();
		gbc_textField_5.insets = new Insets(0, 0, 5, 5);
		gbc_textField_5.anchor = GridBagConstraints.WEST;
		gbc_textField_5.gridx = 2;
		gbc_textField_5.gridy = 5;
		panel_2.add(textField_5, gbc_textField_5);
		textField_5.setColumns(10);
		
		JButton btnStartProcess = new JButton("Start Process");
		GridBagConstraints gbc_btnStartProcess = new GridBagConstraints();
		gbc_btnStartProcess.insets = new Insets(0, 0, 5, 5);
		gbc_btnStartProcess.gridx = 2;
		gbc_btnStartProcess.gridy = 7;
		panel_2.add(btnStartProcess, gbc_btnStartProcess);
		
		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.insets = new Insets(0, 0, 5, 0);
		gbc_separator.gridx = 4;
		gbc_separator.gridy = 8;
		panel_2.add(separator, gbc_separator);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setToolTipText("Progress Offline-Part");
		progressBar.setStringPainted(true);
		progressBar.setForeground(Color.GREEN);
		progressBar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_progressBar = new GridBagConstraints();
		gbc_progressBar.gridwidth = 4;
		gbc_progressBar.fill = GridBagConstraints.HORIZONTAL;
		gbc_progressBar.insets = new Insets(0, 0, 5, 0);
		gbc_progressBar.gridx = 1;
		gbc_progressBar.gridy = 9;
		panel_2.add(progressBar, gbc_progressBar);
		
		JLabel lblProtocol = new JLabel("Protocol:");
		lblProtocol.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblProtocol = new GridBagConstraints();
		gbc_lblProtocol.anchor = GridBagConstraints.WEST;
		gbc_lblProtocol.insets = new Insets(0, 0, 5, 5);
		gbc_lblProtocol.gridx = 1;
		gbc_lblProtocol.gridy = 10;
		panel_2.add(lblProtocol, gbc_lblProtocol);
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
		textArea.setEditable(false);
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.gridwidth = 3;
		gbc_textArea.insets = new Insets(0, 0, 0, 5);
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 1;
		gbc_textArea.gridy = 11;
		panel_2.add(textArea, gbc_textArea);
	}
}
