package part.offline.view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JSeparator;

import java.util.concurrent.TimeUnit;

import javax.swing.BoxLayout;

import part.offline.constants.DBInformations;
import part.offline.control.Status;
import part.online.view.FileOpener;

public class OfflineGui extends JFrame {

	private JPanel contentPane;
	private JButton startButton;
	private JButton chooseClassifier;
	private JProgressBar progressBar;
	private JLabel varlblExpectedTime;
	private JLabel varlblElapsedTime;
	private boolean isAborted = false;
	private JTextField[] textfields;
	private OfflineGUIListener listener;


	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		
		OfflineGui frame = new OfflineGui();
		
		frame.setVisible(true);
	}

	public OfflineGui() {
		super("Wiki-NER");
		textfields = new JTextField[7];
		initiateFrame();
		setActionlistener();
	}

	private void setActionlistener(){
		listener = new OfflineGUIListener(this);
		listener.setUpdater(new Updater(this));
		startButton.addActionListener(listener);
	}

	private void initiateFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 715, 358);

		contentPane = new JPanel();

		JPanel panel_1 = new JPanel();
		JPanel mainPanel = new JPanel();
		JPanel panelNorth = new JPanel();

		JLabel lblWelcomeToWiki = new JLabel(
				"Welcome To Wiki - NER --- Offline Part");
		JLabel lblDbName = new JLabel("DB Name:");
		JLabel lblPort = new JLabel("Port:");
		JLabel lblUsername = new JLabel("Username:");
		JLabel lblHostname = new JLabel("Hostname:");
		JLabel lblProgress = new JLabel("Progress:");
		JLabel lblProtocol = new JLabel("Protocol:");
		varlblElapsedTime = new JLabel(getTime(0));
		varlblExpectedTime = new JLabel(getTime(0));

		textfields[DBInformations.DB_NAME] = new JTextField();
		textfields[DBInformations.PORT] = new JTextField();
		textfields[DBInformations.USERNAME] = new JTextField();	
		textfields[DBInformations.PASSWD] = new JTextField();
		textfields[DBInformations.HOSTNAME] = new JTextField(); 
		textfields[DBInformations.CLASSIFIER] = new JTextField(); //classifier
		textfields[DBInformations.MAX_THREADS] = new JTextField();

		textfields[DBInformations.PORT].addKeyListener(new NumberListener());
		textfields[DBInformations.MAX_THREADS].addKeyListener(new NumberListener());
		
		
		startButton = new JButton("Start Process");
		chooseClassifier = new JButton("Choose Classifier");
		progressBar = new JProgressBar(0, 1000);
		progressBar.setStringPainted(true);

		JScrollPane scrollPane = new JScrollPane();

		JTextPane textPane = new JTextPane();

		startButton.setActionCommand("start");
		chooseClassifier.setActionCommand("readclassifier");
		chooseClassifier.addActionListener(new FileOpener(textfields[DBInformations.CLASSIFIER])); 
		chooseClassifier.setEnabled(false);
		textfields[DBInformations.CLASSIFIER].setText("Standard Classifier verwendet");
		textfields[DBInformations.CLASSIFIER].setEnabled(false);
		
		
		scrollPane.setViewportView(textPane);

		lblWelcomeToWiki.setFont(new Font("Tahoma", Font.PLAIN, 18));

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(lblWelcomeToWiki, BorderLayout.NORTH);
		contentPane.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new BorderLayout(0, 0));

		contentPane.add(mainPanel, BorderLayout.CENTER);
		GridBagLayout gbl_mainPanel = new GridBagLayout();
		gbl_mainPanel.columnWidths = new int[] { 0, 0, 0, 0 };
		gbl_mainPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_mainPanel.columnWeights = new double[] { 1.0, 0.0, 1.0,
				Double.MIN_VALUE };
		gbl_mainPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0,
				Double.MIN_VALUE };
		mainPanel.setLayout(gbl_mainPanel);

		GridBagConstraints gbc_panelNorth = new GridBagConstraints();
		gbc_panelNorth.gridwidth = 3;
		gbc_panelNorth.insets = new Insets(0, 0, 5, 0);
		gbc_panelNorth.fill = GridBagConstraints.BOTH;
		gbc_panelNorth.gridx = 0;
		gbc_panelNorth.gridy = 0;
		mainPanel.add(panelNorth, gbc_panelNorth);
		GridBagLayout gbl_panelNorth = new GridBagLayout();
		gbl_panelNorth.columnWidths = new int[] { 0, 0, 0, 0, 0 };
		gbl_panelNorth.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gbl_panelNorth.columnWeights = new double[] { 0.0, 1.0, 0.0, 1.0,
				Double.MIN_VALUE };
		gbl_panelNorth.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		panelNorth.setLayout(gbl_panelNorth);

		GridBagConstraints gbc_lblDbName = new GridBagConstraints();
		gbc_lblDbName.anchor = GridBagConstraints.WEST;
		gbc_lblDbName.insets = new Insets(0, 0, 5, 5);
		gbc_lblDbName.gridx = 0;
		gbc_lblDbName.gridy = 0;
		panelNorth.add(lblDbName, gbc_lblDbName);

		GridBagConstraints gbc_textFieldDBName = new GridBagConstraints();
		gbc_textFieldDBName.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldDBName.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldDBName.gridx = 1;
		gbc_textFieldDBName.gridy = 0;
		panelNorth.add(textfields[DBInformations.DB_NAME], gbc_textFieldDBName);
		textfields[DBInformations.DB_NAME].setColumns(10);

		GridBagConstraints gbc_lblPort = new GridBagConstraints();
		gbc_lblPort.anchor = GridBagConstraints.WEST;
		gbc_lblPort.insets = new Insets(0, 0, 5, 5);
		gbc_lblPort.gridx = 2;
		gbc_lblPort.gridy = 0;
		panelNorth.add(lblPort, gbc_lblPort);

		GridBagConstraints gbc_textFieldPort = new GridBagConstraints();
		gbc_textFieldPort.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldPort.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldPort.gridx = 3;
		gbc_textFieldPort.gridy = 0;
		panelNorth.add(textfields[DBInformations.PORT], gbc_textFieldPort);
		textfields[DBInformations.PORT].setColumns(10);

		GridBagConstraints gbc_lblUsername = new GridBagConstraints();
		gbc_lblUsername.anchor = GridBagConstraints.WEST;
		gbc_lblUsername.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsername.gridx = 0;
		gbc_lblUsername.gridy = 1;
		panelNorth.add(lblUsername, gbc_lblUsername);

		GridBagConstraints gbc_textFieldUsername = new GridBagConstraints();
		gbc_textFieldUsername.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldUsername.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldUsername.gridx = 1;
		gbc_textFieldUsername.gridy = 1;
		panelNorth.add(textfields[DBInformations.USERNAME], gbc_textFieldUsername);
		textfields[DBInformations.USERNAME].setColumns(10);

		GridBagConstraints gbc_lblHostname = new GridBagConstraints();
		gbc_lblHostname.anchor = GridBagConstraints.WEST;
		gbc_lblHostname.insets = new Insets(0, 0, 5, 5);
		gbc_lblHostname.gridx = 2;
		gbc_lblHostname.gridy = 1;
		panelNorth.add(lblHostname, gbc_lblHostname);

		GridBagConstraints gbc_textFieldHostname = new GridBagConstraints();
		gbc_textFieldHostname.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldHostname.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldHostname.gridx = 3;
		gbc_textFieldHostname.gridy = 1;
		panelNorth.add(textfields[DBInformations.HOSTNAME], gbc_textFieldHostname);
		textfields[DBInformations.HOSTNAME].setColumns(10);
		JLabel lblPassword = new JLabel("Password:");

		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.anchor = GridBagConstraints.WEST;
		gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassword.gridx = 0;
		gbc_lblPassword.gridy = 2;
		panelNorth.add(lblPassword, gbc_lblPassword);
		

		GridBagConstraints gbc_textFieldPassword = new GridBagConstraints();
		gbc_textFieldPassword.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldPassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldPassword.gridx = 1;
		gbc_textFieldPassword.gridy = 2;
		panelNorth.add(textfields[DBInformations.PASSWD], gbc_textFieldPassword);
		textfields[DBInformations.PASSWD].setColumns(10);
		JLabel lblMaxThreads = new JLabel("Max. Threads");

		GridBagConstraints gbc_lblMaxThreads = new GridBagConstraints();
		gbc_lblMaxThreads.anchor = GridBagConstraints.WEST;
		gbc_lblMaxThreads.insets = new Insets(0, 0, 5, 5);
		gbc_lblMaxThreads.gridx = 2;
		gbc_lblMaxThreads.gridy = 2;
		panelNorth.add(lblMaxThreads, gbc_lblMaxThreads);
	

		GridBagConstraints gbc_textFieldMaxThreads = new GridBagConstraints();
		gbc_textFieldMaxThreads.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldMaxThreads.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldMaxThreads.gridx = 3;
		gbc_textFieldMaxThreads.gridy = 2;
		panelNorth.add(textfields[DBInformations.MAX_THREADS], gbc_textFieldMaxThreads);
		textfields[DBInformations.MAX_THREADS].setColumns(10);

		GridBagConstraints gbc_chooseClassifier = new GridBagConstraints();
		gbc_chooseClassifier.insets = new Insets(0, 0, 0, 5);
		gbc_chooseClassifier.gridx = 0;
		gbc_chooseClassifier.gridy = 3;
		panelNorth.add(chooseClassifier, gbc_chooseClassifier);

		GridBagConstraints gbc_classifierTextfield = new GridBagConstraints();
		gbc_classifierTextfield.insets = new Insets(0, 0, 0, 5);
		gbc_classifierTextfield.fill = GridBagConstraints.HORIZONTAL;
		gbc_classifierTextfield.gridx = 1;
		gbc_classifierTextfield.gridy = 3;
		panelNorth.add(textfields[DBInformations.CLASSIFIER], gbc_classifierTextfield);
		textfields[DBInformations.CLASSIFIER].setColumns(10);

		GridBagConstraints gbc_StartButton = new GridBagConstraints();
		gbc_StartButton.insets = new Insets(0, 0, 5, 5);
		gbc_StartButton.gridx = 1;
		gbc_StartButton.gridy = 1;
		mainPanel.add(startButton, gbc_StartButton);

		JPanel panel_3 = new JPanel();
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.gridwidth = 3;
		gbc_panel_3.insets = new Insets(0, 0, 5, 0);
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 0;
		gbc_panel_3.gridy = 2;
		mainPanel.add(panel_3, gbc_panel_3);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.X_AXIS));

		JSeparator separator = new JSeparator();
		panel_3.add(separator);

		JPanel panel_5 = new JPanel();
		panel_3.add(panel_5);
		panel_5.setLayout(new BorderLayout(0, 0));

		panel_5.add(progressBar, BorderLayout.NORTH);

		JSeparator separator_1 = new JSeparator();
		GridBagConstraints gbc_separator_1 = new GridBagConstraints();
		gbc_separator_1.insets = new Insets(0, 0, 5, 0);
		gbc_separator_1.gridx = 2;
		gbc_separator_1.gridy = 3;
		mainPanel.add(separator_1, gbc_separator_1);

		JPanel panel_4 = new JPanel();
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.gridwidth = 3;
		gbc_panel_4.fill = GridBagConstraints.BOTH;
		gbc_panel_4.gridx = 0;
		gbc_panel_4.gridy = 4;
		mainPanel.add(panel_4, gbc_panel_4);
		GridBagLayout gbl_panel_4 = new GridBagLayout();
		gbl_panel_4.columnWidths = new int[] { 0, 0, 0 };
		gbl_panel_4.rowHeights = new int[] { 0, 0, 0 };
		gbl_panel_4.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_panel_4.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		panel_4.setLayout(gbl_panel_4);

		JPanel panelSouthLeft = new JPanel();
		GridBagConstraints gbc_panelSouthLeft = new GridBagConstraints();
		gbc_panelSouthLeft.gridheight = 2;
		gbc_panelSouthLeft.insets = new Insets(0, 0, 0, 5);
		gbc_panelSouthLeft.fill = GridBagConstraints.BOTH;
		gbc_panelSouthLeft.gridx = 0;
		gbc_panelSouthLeft.gridy = 0;
		panel_4.add(panelSouthLeft, gbc_panelSouthLeft);
		GridBagLayout gbl_panelSouthLeft = new GridBagLayout();
		gbl_panelSouthLeft.columnWidths = new int[] { 0, 0, 0, 0 };
		gbl_panelSouthLeft.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_panelSouthLeft.columnWeights = new double[] { 1.0, 0.0, 1.0,
				Double.MIN_VALUE };
		gbl_panelSouthLeft.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, Double.MIN_VALUE };
		panelSouthLeft.setLayout(gbl_panelSouthLeft);

		GridBagConstraints gbc_lblProgress = new GridBagConstraints();
		gbc_lblProgress.anchor = GridBagConstraints.WEST;
		gbc_lblProgress.insets = new Insets(0, 0, 5, 5);
		gbc_lblProgress.gridx = 0;
		gbc_lblProgress.gridy = 0;
		panelSouthLeft.add(lblProgress, gbc_lblProgress);
		JLabel lblElapsedTime = new JLabel("Elapsed Time:");

		GridBagConstraints gbc_lblElapsedTime = new GridBagConstraints();
		gbc_lblElapsedTime.anchor = GridBagConstraints.WEST;
		gbc_lblElapsedTime.insets = new Insets(0, 0, 5, 5);
		gbc_lblElapsedTime.gridx = 0;
		gbc_lblElapsedTime.gridy = 1;
		panelSouthLeft.add(lblElapsedTime, gbc_lblElapsedTime);

		GridBagConstraints gbc_varlblElapsedTime = new GridBagConstraints();
		gbc_varlblElapsedTime.anchor = GridBagConstraints.WEST;
		gbc_varlblElapsedTime.insets = new Insets(0, 0, 5, 0);
		gbc_varlblElapsedTime.gridx = 2;
		gbc_varlblElapsedTime.gridy = 1;
		panelSouthLeft.add(varlblElapsedTime, gbc_varlblElapsedTime);
		JLabel lblExpectedTime = new JLabel("Expected Time:");

		GridBagConstraints gbc_lblExpectedTime = new GridBagConstraints();
		gbc_lblExpectedTime.anchor = GridBagConstraints.WEST;
		gbc_lblExpectedTime.insets = new Insets(0, 0, 5, 5);
		gbc_lblExpectedTime.gridx = 0;
		gbc_lblExpectedTime.gridy = 2;
		panelSouthLeft.add(lblExpectedTime, gbc_lblExpectedTime);

		GridBagConstraints gbc_varlblExpectedTime = new GridBagConstraints();
		gbc_varlblExpectedTime.insets = new Insets(0, 0, 5, 0);
		gbc_varlblExpectedTime.anchor = GridBagConstraints.WEST;
		gbc_varlblExpectedTime.gridx = 2;
		gbc_varlblExpectedTime.gridy = 2;
		panelSouthLeft.add(varlblExpectedTime, gbc_varlblExpectedTime);

		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.gridwidth = 3;
		gbc_label.gridx = 0;
		gbc_label.gridy = 5;

		JPanel panel_7 = new JPanel();
		GridBagConstraints gbc_panel_7 = new GridBagConstraints();
		gbc_panel_7.gridheight = 2;
		gbc_panel_7.fill = GridBagConstraints.BOTH;
		gbc_panel_7.gridx = 1;
		gbc_panel_7.gridy = 0;
		panel_4.add(panel_7, gbc_panel_7);
		panel_7.setLayout(new BorderLayout(0, 0));

		panel_7.add(lblProtocol, BorderLayout.NORTH);

		panel_7.add(scrollPane, BorderLayout.CENTER);

	}

	private String getTime(long time) {		//TODO: Auslagern in Status
		String s = String.format("%02d:%02d:%02d",
				TimeUnit.MILLISECONDS.toHours(time),
				TimeUnit.MILLISECONDS.toMinutes(time)%60,
				TimeUnit.MILLISECONDS.toSeconds(time)%60);
		return s;
	}

	public void disableFields() {
		isAborted = false;
		for (int i = 0; i < textfields.length; i++) {
			textfields[i].setEditable(false);
		}
		chooseClassifier.setEnabled(false);
		startButton.setText("Abort");
		startButton.setBackground(Color.red);
		startButton.setForeground(Color.white);
		startButton.setActionCommand("abort");
	}

	public void abort() {
		isAborted = true;
		for (int i = 0; i < textfields.length; i++) {
			textfields[i].setEditable(true);
		}
//		chooseClassifier.setEnabled(true);
		textfields[DBInformations.CLASSIFIER].setEnabled(false);
		startButton.setText("Start Process");
		startButton.setBackground(null);
		startButton.setForeground(null);
		startButton.setActionCommand("start");
		System.exit(0);
	}
	
	public String[] getInformations(){
		String[] infos = new String[textfields.length];
		
		for (int i = 0; i < infos.length; i++) {
			infos[i] = textfields[i].getText();
		}
		return infos;
	}


	public void setProgress(Status status) {
		int percentage = (int) (1000 * status.getPercent());
		progressBar.setValue(percentage);
		progressBar.setString(String.format("%.2f", percentage/10.0) + "%");
		long time = (System.currentTimeMillis() - status.getStartTime());
		varlblElapsedTime.setText(getTime(time));
		varlblExpectedTime.setText(getTime(status.getTimeRemaining()));
	}

	public boolean isAborted() {
		return isAborted;
	}

	class Updater implements Runnable {

		private OfflineGui gui;
		private Status status;

		public Updater(OfflineGui gui) {
			this.gui = gui;
		}
		public void setStatus(Status status){
			this.status = status;
		}

		public void run() {
			while(!gui.isAborted()){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			status.calcStatus();
			gui.setProgress(status);
			}
		}
	}

}
