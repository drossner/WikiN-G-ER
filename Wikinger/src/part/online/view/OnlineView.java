package part.online.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.border.EtchedBorder;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JSeparator;
import java.awt.GridLayout;
import javax.swing.BoxLayout;

public class OnlineView
{

	private JFrame frmWikinerOnlinepart;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;

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
					OnlineView window = new OnlineView();
					window.frmWikinerOnlinepart.setVisible(true);
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
	public OnlineView()
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		frmWikinerOnlinepart = new JFrame();
		frmWikinerOnlinepart.setTitle("Wiki-NER --- OnlinePart");
		frmWikinerOnlinepart.setBounds(100, 100, 450, 322);
		frmWikinerOnlinepart.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblWelcomeToWikiner = new JLabel("Welcome to Wiki-NER Online!");
		lblWelcomeToWikiner.setFont(new Font("Tahoma", Font.PLAIN, 17));
		frmWikinerOnlinepart.getContentPane().add(lblWelcomeToWikiner, BorderLayout.NORTH);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frmWikinerOnlinepart.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("General", null, panel, null);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.insets = new Insets(0, 0, 5, 5);
		gbc_separator.gridx = 1;
		gbc_separator.gridy = 0;
		panel.add(separator, gbc_separator);
		
		JLabel lblInputTextFor = new JLabel("Input Text for Named Entity Recognition");
		lblInputTextFor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblInputTextFor = new GridBagConstraints();
		gbc_lblInputTextFor.insets = new Insets(0, 0, 5, 5);
		gbc_lblInputTextFor.anchor = GridBagConstraints.WEST;
		gbc_lblInputTextFor.gridx = 1;
		gbc_lblInputTextFor.gridy = 1;
		panel.add(lblInputTextFor, gbc_lblInputTextFor);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 2;
		panel.add(textField, gbc_textField);
		textField.setColumns(10);
		
		JButton btnReadFile = new JButton("Read File");
		btnReadFile.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_btnReadFile = new GridBagConstraints();
		gbc_btnReadFile.insets = new Insets(0, 0, 5, 0);
		gbc_btnReadFile.gridx = 2;
		gbc_btnReadFile.gridy = 2;
		panel.add(btnReadFile, gbc_btnReadFile);
		
		JSeparator separator_1 = new JSeparator();
		GridBagConstraints gbc_separator_1 = new GridBagConstraints();
		gbc_separator_1.insets = new Insets(0, 0, 5, 5);
		gbc_separator_1.gridx = 0;
		gbc_separator_1.gridy = 3;
		panel.add(separator_1, gbc_separator_1);
		
		JLabel lblFileReadAnd = new JLabel("File read and processing...");
		lblFileReadAnd.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_lblFileReadAnd = new GridBagConstraints();
		gbc_lblFileReadAnd.insets = new Insets(0, 0, 5, 5);
		gbc_lblFileReadAnd.gridx = 1;
		gbc_lblFileReadAnd.gridy = 4;
		panel.add(lblFileReadAnd, gbc_lblFileReadAnd);
		
		JInternalFrame internalFrame = new JInternalFrame("Result in OpenStreetMap");
		internalFrame.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GridBagConstraints gbc_internalFrame = new GridBagConstraints();
		gbc_internalFrame.gridwidth = 2;
		gbc_internalFrame.fill = GridBagConstraints.HORIZONTAL;
		gbc_internalFrame.gridx = 1;
		gbc_internalFrame.gridy = 5;
		panel.add(internalFrame, gbc_internalFrame);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Settings", null, panel_1, null);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{68, 288, 0};
		gbl_panel_1.rowHeights = new int[]{24, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 0);
		gbc_panel_2.anchor = GridBagConstraints.NORTHWEST;
		gbc_panel_2.gridx = 1;
		gbc_panel_2.gridy = 0;
		panel_1.add(panel_2, gbc_panel_2);
		
		JLabel lblEnterYourSpecific = new JLabel("Enter your specific Configuration for the loaded Classifier:");
		panel_2.add(lblEnterYourSpecific);
		
		JLabel lblLocation = new JLabel("Location");
		GridBagConstraints gbc_lblLocation = new GridBagConstraints();
		gbc_lblLocation.anchor = GridBagConstraints.EAST;
		gbc_lblLocation.insets = new Insets(0, 0, 5, 5);
		gbc_lblLocation.gridx = 0;
		gbc_lblLocation.gridy = 1;
		panel_1.add(lblLocation, gbc_lblLocation);
		
		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 0);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 1;
		panel_1.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		JLabel lblPerson = new JLabel("Person");
		GridBagConstraints gbc_lblPerson = new GridBagConstraints();
		gbc_lblPerson.anchor = GridBagConstraints.EAST;
		gbc_lblPerson.insets = new Insets(0, 0, 5, 5);
		gbc_lblPerson.gridx = 0;
		gbc_lblPerson.gridy = 2;
		panel_1.add(lblPerson, gbc_lblPerson);
		
		textField_2 = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 0);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 1;
		gbc_textField_2.gridy = 2;
		panel_1.add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);
		
		JLabel lblOrganization = new JLabel("Organization");
		GridBagConstraints gbc_lblOrganization = new GridBagConstraints();
		gbc_lblOrganization.anchor = GridBagConstraints.EAST;
		gbc_lblOrganization.insets = new Insets(0, 0, 5, 5);
		gbc_lblOrganization.gridx = 0;
		gbc_lblOrganization.gridy = 3;
		panel_1.add(lblOrganization, gbc_lblOrganization);
		
		textField_3 = new JTextField();
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.insets = new Insets(0, 0, 5, 0);
		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.gridx = 1;
		gbc_textField_3.gridy = 3;
		panel_1.add(textField_3, gbc_textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Date");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 4;
		panel_1.add(lblNewLabel, gbc_lblNewLabel);
		
		textField_4 = new JTextField();
		GridBagConstraints gbc_textField_4 = new GridBagConstraints();
		gbc_textField_4.insets = new Insets(0, 0, 5, 0);
		gbc_textField_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_4.gridx = 1;
		gbc_textField_4.gridy = 4;
		panel_1.add(textField_4, gbc_textField_4);
		textField_4.setColumns(10);
		
		JLabel lblMoney = new JLabel("Money");
		GridBagConstraints gbc_lblMoney = new GridBagConstraints();
		gbc_lblMoney.anchor = GridBagConstraints.EAST;
		gbc_lblMoney.insets = new Insets(0, 0, 5, 5);
		gbc_lblMoney.gridx = 0;
		gbc_lblMoney.gridy = 5;
		panel_1.add(lblMoney, gbc_lblMoney);
		
		textField_5 = new JTextField();
		GridBagConstraints gbc_textField_5 = new GridBagConstraints();
		gbc_textField_5.insets = new Insets(0, 0, 5, 0);
		gbc_textField_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_5.gridx = 1;
		gbc_textField_5.gridy = 5;
		panel_1.add(textField_5, gbc_textField_5);
		textField_5.setColumns(10);
		
		JLabel lblMeal = new JLabel("Meal");
		GridBagConstraints gbc_lblMeal = new GridBagConstraints();
		gbc_lblMeal.anchor = GridBagConstraints.EAST;
		gbc_lblMeal.insets = new Insets(0, 0, 5, 5);
		gbc_lblMeal.gridx = 0;
		gbc_lblMeal.gridy = 6;
		panel_1.add(lblMeal, gbc_lblMeal);
		
		textField_6 = new JTextField();
		GridBagConstraints gbc_textField_6 = new GridBagConstraints();
		gbc_textField_6.insets = new Insets(0, 0, 5, 0);
		gbc_textField_6.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_6.gridx = 1;
		gbc_textField_6.gridy = 6;
		panel_1.add(textField_6, gbc_textField_6);
		textField_6.setColumns(10);
		
		JLabel lblTime = new JLabel("Time");
		GridBagConstraints gbc_lblTime = new GridBagConstraints();
		gbc_lblTime.anchor = GridBagConstraints.EAST;
		gbc_lblTime.insets = new Insets(0, 0, 5, 5);
		gbc_lblTime.gridx = 0;
		gbc_lblTime.gridy = 7;
		panel_1.add(lblTime, gbc_lblTime);
		
		textField_7 = new JTextField();
		GridBagConstraints gbc_textField_7 = new GridBagConstraints();
		gbc_textField_7.insets = new Insets(0, 0, 5, 0);
		gbc_textField_7.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_7.gridx = 1;
		gbc_textField_7.gridy = 7;
		panel_1.add(textField_7, gbc_textField_7);
		textField_7.setColumns(10);
		
		JButton btnSubmit = new JButton("Submit Values");
		btnSubmit.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_btnSubmit = new GridBagConstraints();
		gbc_btnSubmit.gridx = 1;
		gbc_btnSubmit.gridy = 8;
		panel_1.add(btnSubmit, gbc_btnSubmit);
		
		internalFrame.setVisible(true);
		
	}

}
