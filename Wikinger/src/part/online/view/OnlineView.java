package part.online.view;

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
import javax.swing.JSeparator;

public class OnlineView
{

	private JFrame frmWikinerOnlinepart;
	private JTextField textField;
	private JTextField setLocationText;
	private JTextField setPersonText;
	private JTextField setOrganizationText;
	private JTextField setDateText;
	private JTextField setMoneyText;
	private JTextField setMealText;
	private JTextField setTimeText;
	private JTextField classifierTextField;

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
		frmWikinerOnlinepart.setVisible(true);
		
		JLabel lblWelcomeToWikiner = new JLabel("Welcome to Wiki-NER Online!");
		lblWelcomeToWikiner.setFont(new Font("Tahoma", Font.PLAIN, 17));
		frmWikinerOnlinepart.getContentPane().add(lblWelcomeToWikiner, BorderLayout.NORTH);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frmWikinerOnlinepart.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("General", null, panel, null);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
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
		
		setTextField(new JTextField());
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 2;
		panel.add(getTextField(), gbc_textField);
		getTextField().setColumns(10);
		
		JButton btnBrowse = new JButton("Browse");
		btnBrowse.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_btnBrowse = new GridBagConstraints();
		gbc_btnBrowse.insets = new Insets(0, 0, 5, 0);
		gbc_btnBrowse.gridx = 2;
		gbc_btnBrowse.gridy = 2;
		panel.add(btnBrowse, gbc_btnBrowse);
		
		//Add action to btnBrowse
		btnBrowse.setActionCommand("browse");
		FileOpener open = new FileOpener(textField);
		btnBrowse.addActionListener(open);
		FileReaderAction read = new FileReaderAction(this);
		
		classifierTextField = new JTextField();
		GridBagConstraints gbc_classifierTextField = new GridBagConstraints();
		gbc_classifierTextField.insets = new Insets(0, 0, 5, 5);
		gbc_classifierTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_classifierTextField.gridx = 1;
		gbc_classifierTextField.gridy = 3;
		panel.add(classifierTextField, gbc_classifierTextField);
		classifierTextField.setColumns(10);
		FileOpener openClf = new FileOpener(classifierTextField);
		
		JButton btnReadClassifier = new JButton("Read Classifier");
		btnReadClassifier.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_btnReadClassifier = new GridBagConstraints();
		gbc_btnReadClassifier.insets = new Insets(0, 0, 5, 0);
		gbc_btnReadClassifier.gridx = 2;
		gbc_btnReadClassifier.gridy = 3;
		panel.add(btnReadClassifier, gbc_btnReadClassifier);
		
		btnReadClassifier.setActionCommand("readclassifier");
		btnReadClassifier.addActionListener(openClf);
		
		JSeparator separator_1 = new JSeparator();
		GridBagConstraints gbc_separator_1 = new GridBagConstraints();
		gbc_separator_1.insets = new Insets(0, 0, 5, 5);
		gbc_separator_1.gridx = 0;
		gbc_separator_1.gridy = 4;
		panel.add(separator_1, gbc_separator_1);
		
		JLabel lblFileReadAnd = new JLabel("File read and processing...");
		lblFileReadAnd.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_lblFileReadAnd = new GridBagConstraints();
		gbc_lblFileReadAnd.insets = new Insets(0, 0, 5, 5);
		gbc_lblFileReadAnd.gridx = 1;
		gbc_lblFileReadAnd.gridy = 5;
		panel.add(lblFileReadAnd, gbc_lblFileReadAnd);
		
		JButton btnReadFile = new JButton("Start Process");
		btnReadFile.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_btnReadFile = new GridBagConstraints();
		gbc_btnReadFile.insets = new Insets(0, 0, 5, 0);
		gbc_btnReadFile.gridx = 2;
		gbc_btnReadFile.gridy = 5;
		panel.add(btnReadFile, gbc_btnReadFile);
		
		//adding action to btnReadFile
		btnReadFile.setActionCommand("read");
		btnReadFile.addActionListener(read);
		
		JInternalFrame internalFrame = new JInternalFrame("Result in OpenStreetMap");
		internalFrame.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GridBagConstraints gbc_internalFrame = new GridBagConstraints();
		gbc_internalFrame.gridwidth = 2;
		gbc_internalFrame.fill = GridBagConstraints.HORIZONTAL;
		gbc_internalFrame.gridx = 1;
		gbc_internalFrame.gridy = 8;
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
		
		setLocationText = new JTextField();
		GridBagConstraints gbc_setLocationText = new GridBagConstraints();
		gbc_setLocationText.insets = new Insets(0, 0, 5, 0);
		gbc_setLocationText.fill = GridBagConstraints.HORIZONTAL;
		gbc_setLocationText.gridx = 1;
		gbc_setLocationText.gridy = 1;
		panel_1.add(setLocationText, gbc_setLocationText);
		setLocationText.setColumns(10);
		
		JLabel lblPerson = new JLabel("Person");
		GridBagConstraints gbc_lblPerson = new GridBagConstraints();
		gbc_lblPerson.anchor = GridBagConstraints.EAST;
		gbc_lblPerson.insets = new Insets(0, 0, 5, 5);
		gbc_lblPerson.gridx = 0;
		gbc_lblPerson.gridy = 2;
		panel_1.add(lblPerson, gbc_lblPerson);
		
		setPersonText = new JTextField();
		GridBagConstraints gbc_setPersonText = new GridBagConstraints();
		gbc_setPersonText.insets = new Insets(0, 0, 5, 0);
		gbc_setPersonText.fill = GridBagConstraints.HORIZONTAL;
		gbc_setPersonText.gridx = 1;
		gbc_setPersonText.gridy = 2;
		panel_1.add(setPersonText, gbc_setPersonText);
		setPersonText.setColumns(10);
		
		JLabel lblOrganization = new JLabel("Organization");
		GridBagConstraints gbc_lblOrganization = new GridBagConstraints();
		gbc_lblOrganization.anchor = GridBagConstraints.EAST;
		gbc_lblOrganization.insets = new Insets(0, 0, 5, 5);
		gbc_lblOrganization.gridx = 0;
		gbc_lblOrganization.gridy = 3;
		panel_1.add(lblOrganization, gbc_lblOrganization);
		
		setOrganizationText = new JTextField();
		GridBagConstraints gbc_setOrganizationText = new GridBagConstraints();
		gbc_setOrganizationText.insets = new Insets(0, 0, 5, 0);
		gbc_setOrganizationText.fill = GridBagConstraints.HORIZONTAL;
		gbc_setOrganizationText.gridx = 1;
		gbc_setOrganizationText.gridy = 3;
		panel_1.add(setOrganizationText, gbc_setOrganizationText);
		setOrganizationText.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Date");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 4;
		panel_1.add(lblNewLabel, gbc_lblNewLabel);
		
		setDateText = new JTextField();
		GridBagConstraints gbc_setDateText = new GridBagConstraints();
		gbc_setDateText.insets = new Insets(0, 0, 5, 0);
		gbc_setDateText.fill = GridBagConstraints.HORIZONTAL;
		gbc_setDateText.gridx = 1;
		gbc_setDateText.gridy = 4;
		panel_1.add(setDateText, gbc_setDateText);
		setDateText.setColumns(10);
		
		JLabel lblMoney = new JLabel("Money");
		GridBagConstraints gbc_lblMoney = new GridBagConstraints();
		gbc_lblMoney.anchor = GridBagConstraints.EAST;
		gbc_lblMoney.insets = new Insets(0, 0, 5, 5);
		gbc_lblMoney.gridx = 0;
		gbc_lblMoney.gridy = 5;
		panel_1.add(lblMoney, gbc_lblMoney);
		
		setMoneyText = new JTextField();
		GridBagConstraints gbc_setMoneyText = new GridBagConstraints();
		gbc_setMoneyText.insets = new Insets(0, 0, 5, 0);
		gbc_setMoneyText.fill = GridBagConstraints.HORIZONTAL;
		gbc_setMoneyText.gridx = 1;
		gbc_setMoneyText.gridy = 5;
		panel_1.add(setMoneyText, gbc_setMoneyText);
		setMoneyText.setColumns(10);
		
		JLabel lblMeal = new JLabel("Meal");
		GridBagConstraints gbc_lblMeal = new GridBagConstraints();
		gbc_lblMeal.anchor = GridBagConstraints.EAST;
		gbc_lblMeal.insets = new Insets(0, 0, 5, 5);
		gbc_lblMeal.gridx = 0;
		gbc_lblMeal.gridy = 6;
		panel_1.add(lblMeal, gbc_lblMeal);
		
		setMealText = new JTextField();
		GridBagConstraints gbc_setMealText = new GridBagConstraints();
		gbc_setMealText.insets = new Insets(0, 0, 5, 0);
		gbc_setMealText.fill = GridBagConstraints.HORIZONTAL;
		gbc_setMealText.gridx = 1;
		gbc_setMealText.gridy = 6;
		panel_1.add(setMealText, gbc_setMealText);
		setMealText.setColumns(10);
		
		JLabel lblTime = new JLabel("Time");
		GridBagConstraints gbc_lblTime = new GridBagConstraints();
		gbc_lblTime.anchor = GridBagConstraints.EAST;
		gbc_lblTime.insets = new Insets(0, 0, 5, 5);
		gbc_lblTime.gridx = 0;
		gbc_lblTime.gridy = 7;
		panel_1.add(lblTime, gbc_lblTime);
		
		setTimeText = new JTextField();
		GridBagConstraints gbc_setTimeText = new GridBagConstraints();
		gbc_setTimeText.insets = new Insets(0, 0, 5, 0);
		gbc_setTimeText.fill = GridBagConstraints.HORIZONTAL;
		gbc_setTimeText.gridx = 1;
		gbc_setTimeText.gridy = 7;
		panel_1.add(setTimeText, gbc_setTimeText);
		setTimeText.setColumns(10);
		
		JButton btnSubmit = new JButton("Submit Values");
		btnSubmit.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_btnSubmit = new GridBagConstraints();
		gbc_btnSubmit.gridx = 1;
		gbc_btnSubmit.gridy = 8;
		panel_1.add(btnSubmit, gbc_btnSubmit);
		
		internalFrame.setVisible(true);
		
	}

	public JTextField getClassifierTextField(){
		return classifierTextField;
	}

	public void setClassifierTextField(JTextField classifierTextField){
		this.classifierTextField = classifierTextField;
	}

	public JTextField getTextField(){
		return textField;
	}

	public void setTextField(JTextField textField){
		this.textField = textField;
	}

}
