package part.offline.view;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.FlowLayout;

import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JSeparator;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.BoxLayout;

import part.offline.control.Status;
import test.OfflineControllerTestGui;


public class OfflineGui_LayoutManager extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldPort;
	private JTextField textFieldHostname;
	private JTextField textFieldMaxThreads;
	private JTextField textFieldDBName;
	private JTextField textFieldUsername;
	private JTextField textFieldPassword;
	private JProgressBar progressBar;
	private JLabel varlblExpectedTime;
	private JLabel varlblElapsedTime;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("kk:mm:ss");
	private Status status;
	OfflineGui_LayoutManager bla;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					OfflineGui_LayoutManager frame = new OfflineGui_LayoutManager(new Status(1, 1));
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}


	public OfflineGui_LayoutManager(Status status) {
		initiateFrame();
		this.status = status;
		bla = this;
	}
	
	
	private void initiateFrame(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 715, 358);
		
		contentPane = new JPanel();
		
		JPanel panel_1 = new JPanel();
		JPanel mainPanel = new JPanel();
		JPanel panelNorth = new JPanel();
		
		JLabel lblWelcomeToWiki = new JLabel("Welcome To Wiki - NER --- Offline Part");
		JLabel lblDbName = new JLabel("DB Name:");
		JLabel lblPort = new JLabel("Port:");
		JLabel lblUsername = new JLabel("Username:");
		JLabel lblHostname = new JLabel("Hostname:");
		JLabel lblPassword = new JLabel("Password:");
		JLabel lblMaxThreads = new JLabel("Max. Threads");
		JLabel lblProgress = new JLabel("Progress:");
		JLabel lblCities = new JLabel("total Cities:");
		JLabel varlblTotalCities = new JLabel("XXXXXXx");
		JLabel lblCrawledCities = new JLabel("Crawled Cities:");
		JLabel varlblcrawledCities = new JLabel("XXXXX");
		JLabel lblElapsedTime = new JLabel("Elapsed Time:");
		varlblElapsedTime = new JLabel("XXXX");
		JLabel lblExpectedTime = new JLabel("Expected Time:");
		varlblExpectedTime = new JLabel("XXXX");
		JLabel label = new JLabel("                                                                          ");
		JLabel lblProtocol = new JLabel("Protocol:");
		
		textFieldDBName = new JTextField();
		textFieldPort = new JTextField();
		textFieldUsername = new JTextField();
		textFieldHostname = new JTextField();
		textFieldPassword = new JTextField();
		textFieldMaxThreads = new JTextField();
		
		JButton FileButton = new JButton("Choose Classifier");
		JButton StartButton = new JButton("Start Process");
		StartButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Thread t = new Thread(new Updater(bla, status));
				t.start();
			}
		});
		
		progressBar = new JProgressBar(0,1000);
		
		JScrollPane scrollPane = new JScrollPane();
		JTextPane textPane = new JTextPane();
		
		
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
		gbl_mainPanel.columnWidths = new int[]{0, 0, 0};
		gbl_mainPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_mainPanel.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_mainPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		mainPanel.setLayout(gbl_mainPanel);
		

		GridBagConstraints gbc_panelNorth = new GridBagConstraints();
		gbc_panelNorth.gridwidth = 2;
		gbc_panelNorth.insets = new Insets(0, 0, 5, 0);
		gbc_panelNorth.fill = GridBagConstraints.BOTH;
		gbc_panelNorth.gridx = 0;
		gbc_panelNorth.gridy = 0;
		mainPanel.add(panelNorth, gbc_panelNorth);
		GridBagLayout gbl_panelNorth = new GridBagLayout();
		gbl_panelNorth.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_panelNorth.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panelNorth.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panelNorth.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
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
		panelNorth.add(textFieldDBName, gbc_textFieldDBName);
		textFieldDBName.setColumns(10);
		
		
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
		panelNorth.add(textFieldPort, gbc_textFieldPort);
		textFieldPort.setColumns(10);
		

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
		panelNorth.add(textFieldUsername, gbc_textFieldUsername);
		textFieldUsername.setColumns(10);
		

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
		panelNorth.add(textFieldHostname, gbc_textFieldHostname);
		textFieldHostname.setColumns(10);
		

		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.anchor = GridBagConstraints.WEST;
		gbc_lblPassword.insets = new Insets(0, 0, 0, 5);
		gbc_lblPassword.gridx = 0;
		gbc_lblPassword.gridy = 2;
		panelNorth.add(lblPassword, gbc_lblPassword);
		
		
		GridBagConstraints gbc_textFieldPassword = new GridBagConstraints();
		gbc_textFieldPassword.insets = new Insets(0, 0, 0, 5);
		gbc_textFieldPassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldPassword.gridx = 1;
		gbc_textFieldPassword.gridy = 2;
		panelNorth.add(textFieldPassword, gbc_textFieldPassword);
		textFieldPassword.setColumns(10);
		
	
		GridBagConstraints gbc_lblMaxThreads = new GridBagConstraints();
		gbc_lblMaxThreads.anchor = GridBagConstraints.WEST;
		gbc_lblMaxThreads.insets = new Insets(0, 0, 0, 5);
		gbc_lblMaxThreads.gridx = 2;
		gbc_lblMaxThreads.gridy = 2;
		panelNorth.add(lblMaxThreads, gbc_lblMaxThreads);
		
		
		GridBagConstraints gbc_textFieldMaxThreads = new GridBagConstraints();
		gbc_textFieldMaxThreads.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldMaxThreads.gridx = 3;
		gbc_textFieldMaxThreads.gridy = 2;
		panelNorth.add(textFieldMaxThreads, gbc_textFieldMaxThreads);
		textFieldMaxThreads.setColumns(10);
		
		
		GridBagConstraints gbc_FileButton = new GridBagConstraints();
		gbc_FileButton.insets = new Insets(0, 0, 5, 5);
		gbc_FileButton.gridx = 0;
		gbc_FileButton.gridy = 1;
		mainPanel.add(FileButton, gbc_FileButton);
		
		
		GridBagConstraints gbc_StartButton = new GridBagConstraints();
		gbc_StartButton.insets = new Insets(0, 0, 5, 0);
		gbc_StartButton.gridx = 1;
		gbc_StartButton.gridy = 1;
		mainPanel.add(StartButton, gbc_StartButton);
		
		JPanel panel_3 = new JPanel();
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.gridwidth = 2;
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
		gbc_separator_1.gridx = 1;
		gbc_separator_1.gridy = 3;
		mainPanel.add(separator_1, gbc_separator_1);
		
		JPanel panel_4 = new JPanel();
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.gridwidth = 2;
		gbc_panel_4.fill = GridBagConstraints.BOTH;
		gbc_panel_4.gridx = 0;
		gbc_panel_4.gridy = 4;
		mainPanel.add(panel_4, gbc_panel_4);
		GridBagLayout gbl_panel_4 = new GridBagLayout();
		gbl_panel_4.columnWidths = new int[]{0, 0, 0};
		gbl_panel_4.rowHeights = new int[]{0, 0, 0};
		gbl_panel_4.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_4.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
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
		gbl_panelSouthLeft.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panelSouthLeft.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panelSouthLeft.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panelSouthLeft.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelSouthLeft.setLayout(gbl_panelSouthLeft);
		

		GridBagConstraints gbc_lblProgress = new GridBagConstraints();
		gbc_lblProgress.anchor = GridBagConstraints.WEST;
		gbc_lblProgress.insets = new Insets(0, 0, 5, 5);
		gbc_lblProgress.gridx = 0;
		gbc_lblProgress.gridy = 0;
		panelSouthLeft.add(lblProgress, gbc_lblProgress);
		
		
		GridBagConstraints gbc_lblCities = new GridBagConstraints();
		gbc_lblCities.anchor = GridBagConstraints.WEST;
		gbc_lblCities.insets = new Insets(0, 0, 5, 5);
		gbc_lblCities.gridx = 0;
		gbc_lblCities.gridy = 1;
		panelSouthLeft.add(lblCities, gbc_lblCities);
		
		
		GridBagConstraints gbc_varlblTotalCities = new GridBagConstraints();
		gbc_varlblTotalCities.anchor = GridBagConstraints.WEST;
		gbc_varlblTotalCities.insets = new Insets(0, 0, 5, 0);
		gbc_varlblTotalCities.gridx = 2;
		gbc_varlblTotalCities.gridy = 1;
		panelSouthLeft.add(varlblTotalCities, gbc_varlblTotalCities);
		
		
		GridBagConstraints gbc_lblCrawledCities = new GridBagConstraints();
		gbc_lblCrawledCities.anchor = GridBagConstraints.WEST;
		gbc_lblCrawledCities.insets = new Insets(0, 0, 5, 5);
		gbc_lblCrawledCities.gridx = 0;
		gbc_lblCrawledCities.gridy = 2;
		panelSouthLeft.add(lblCrawledCities, gbc_lblCrawledCities);
		
		
		GridBagConstraints gbc_varlblcrawlesCities = new GridBagConstraints();
		gbc_varlblcrawlesCities.anchor = GridBagConstraints.WEST;
		gbc_varlblcrawlesCities.insets = new Insets(0, 0, 5, 0);
		gbc_varlblcrawlesCities.gridx = 2;
		gbc_varlblcrawlesCities.gridy = 2;
		panelSouthLeft.add(varlblcrawledCities, gbc_varlblcrawlesCities);
		
		
		GridBagConstraints gbc_lblElapsedTime = new GridBagConstraints();
		gbc_lblElapsedTime.anchor = GridBagConstraints.WEST;
		gbc_lblElapsedTime.insets = new Insets(0, 0, 5, 5);
		gbc_lblElapsedTime.gridx = 0;
		gbc_lblElapsedTime.gridy = 3;
		panelSouthLeft.add(lblElapsedTime, gbc_lblElapsedTime);
		

		GridBagConstraints gbc_varlblElapsedTime = new GridBagConstraints();
		gbc_varlblElapsedTime.anchor = GridBagConstraints.WEST;
		gbc_varlblElapsedTime.insets = new Insets(0, 0, 5, 0);
		gbc_varlblElapsedTime.gridx = 2;
		gbc_varlblElapsedTime.gridy = 3;
		panelSouthLeft.add(varlblElapsedTime, gbc_varlblElapsedTime);
		
		
		GridBagConstraints gbc_lblExpectedTime = new GridBagConstraints();
		gbc_lblExpectedTime.anchor = GridBagConstraints.WEST;
		gbc_lblExpectedTime.insets = new Insets(0, 0, 5, 5);
		gbc_lblExpectedTime.gridx = 0;
		gbc_lblExpectedTime.gridy = 4;
		panelSouthLeft.add(lblExpectedTime, gbc_lblExpectedTime);
		
		
		GridBagConstraints gbc_varlblExpectedTime = new GridBagConstraints();
		gbc_varlblExpectedTime.insets = new Insets(0, 0, 5, 0);
		gbc_varlblExpectedTime.anchor = GridBagConstraints.WEST;
		gbc_varlblExpectedTime.gridx = 2;
		gbc_varlblExpectedTime.gridy = 4;
		panelSouthLeft.add(varlblExpectedTime, gbc_varlblExpectedTime);
		
		
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.gridwidth = 3;
		gbc_label.insets = new Insets(0, 0, 0, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 5;
		panelSouthLeft.add(label, gbc_label);
		
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
	
	
	
	public void setProgress(Status status){
	
		int percentage =(int) (1000*status.getPercent());
		progressBar.setValue(percentage);
		progressBar.setString(percentage+"%");
		varlblElapsedTime.setText(dateFormat.format(status.getTimeRemaining()/1000));
		long time = (System.currentTimeMillis()-status.getStartTime())/1000;
		varlblExpectedTime.setText(dateFormat.format(time));
		
		
		
	}
	
	
	
	class Updater implements Runnable{
		
		OfflineGui_LayoutManager gui;
		Status status;
		
		public Updater(OfflineGui_LayoutManager gui, Status status){
			this.gui = gui;
			this.status = status;
		}

		@Override
		public void run() {
			
			while(true){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			status.calcStatus();
			gui.setProgress(status);
			}
		}
}
}
