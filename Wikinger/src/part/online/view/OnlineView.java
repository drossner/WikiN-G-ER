package part.online.view;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.JTextField;

import java.awt.Insets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.border.EtchedBorder;
import javax.swing.JSeparator;

import org.jdesktop.swingx.JXMapKit;
import org.jdesktop.swingx.JXMapKit.DefaultProviders;
import org.jdesktop.swingx.JXMapViewer;
import org.jdesktop.swingx.mapviewer.GeoPosition;
import org.jdesktop.swingx.mapviewer.Waypoint;
import org.jdesktop.swingx.mapviewer.WaypointPainter;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;

import javax.swing.JSpinner;
import javax.swing.ImageIcon;

public class OnlineView{

	private JFrame frmWikinerOnlinepart;
	private JTextField fileTextField;
	private JTextField classifierTextField;
	private JInternalFrame internalFrame;
	private JTextField entityWeightTextField;
	private JXMapKit openMap;
	private ReaderAction read;
	private FileOpener open;
	private JTextField databaseTextfield;
	private JTextField passwordTextfield;
	private JTextField portTextfield;
	private JTextField usernameTextfield;
	private JTextField hostnameTextfield;

	/**
	 * Create the application.
	 */
	public OnlineView(){
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(){
		frmWikinerOnlinepart = new JFrame();
		frmWikinerOnlinepart.setLocationRelativeTo(null);
		frmWikinerOnlinepart.setTitle("Wiki-NER --- OnlinePart");
		frmWikinerOnlinepart.setBounds(100, 100, 618, 516);
		frmWikinerOnlinepart.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmWikinerOnlinepart.setVisible(true);

		JLabel lblWelcomeToWikiner = new JLabel("Welcome to Wiki-NER Online!");
		lblWelcomeToWikiner.setFont(new Font("Tahoma", Font.PLAIN, 17));
		frmWikinerOnlinepart.getContentPane().add(lblWelcomeToWikiner,
				BorderLayout.NORTH);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frmWikinerOnlinepart.getContentPane().add(tabbedPane,
				BorderLayout.CENTER);

		JPanel mainPanel = new JPanel();
		tabbedPane.addTab("General", null, mainPanel, null);
		GridBagLayout gbl_mainPanel = new GridBagLayout();
		gbl_mainPanel.columnWidths = new int[] { 0, 0, 0, 0 };
		gbl_mainPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_mainPanel.columnWeights = new double[] { 0.0, 1.0, 1.0,
				Double.MIN_VALUE };
		gbl_mainPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				1.0, 0.0, 1.0, Double.MIN_VALUE };
		mainPanel.setLayout(gbl_mainPanel);

		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.insets = new Insets(0, 0, 5, 5);
		gbc_separator.gridx = 1;
		gbc_separator.gridy = 0;
		mainPanel.add(separator, gbc_separator);

		JLabel lblInputTextFor = new JLabel(
				"Input Text for Named Entity Recognition");
		lblInputTextFor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblInputTextFor = new GridBagConstraints();
		gbc_lblInputTextFor.insets = new Insets(0, 0, 5, 5);
		gbc_lblInputTextFor.anchor = GridBagConstraints.WEST;
		gbc_lblInputTextFor.gridx = 1;
		gbc_lblInputTextFor.gridy = 1;
		mainPanel.add(lblInputTextFor, gbc_lblInputTextFor);

		setFileTextField(new JTextField());
		fileTextField.setEditable(false);
		GridBagConstraints gbc_fileTextField = new GridBagConstraints();
		gbc_fileTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_fileTextField.insets = new Insets(0, 0, 5, 5);
		gbc_fileTextField.gridx = 1;
		gbc_fileTextField.gridy = 2;
		mainPanel.add(getFileTextField(), gbc_fileTextField);
		getFileTextField().setColumns(10);

		JButton btnBrowse = new JButton("Browse");
		btnBrowse.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_btnBrowse = new GridBagConstraints();
		gbc_btnBrowse.insets = new Insets(0, 0, 5, 0);
		gbc_btnBrowse.gridx = 2;
		gbc_btnBrowse.gridy = 2;
		mainPanel.add(btnBrowse, gbc_btnBrowse);

		// Add action to btnBrowse
		btnBrowse.setActionCommand("browse");
		open = new FileOpener(fileTextField);
		btnBrowse.addActionListener(open);

		classifierTextField = new JTextField();
		classifierTextField.setEditable(false);
		GridBagConstraints gbc_classifierTextField = new GridBagConstraints();
		gbc_classifierTextField.insets = new Insets(0, 0, 5, 5);
		gbc_classifierTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_classifierTextField.gridx = 1;
		gbc_classifierTextField.gridy = 3;
		mainPanel.add(classifierTextField, gbc_classifierTextField);
		classifierTextField.setColumns(10);
		FileOpener openClf = new FileOpener(classifierTextField);

		JButton btnReadClassifier = new JButton("Read Classifier");
		btnReadClassifier.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_btnReadClassifier = new GridBagConstraints();
		gbc_btnReadClassifier.insets = new Insets(0, 0, 5, 0);
		gbc_btnReadClassifier.gridx = 2;
		gbc_btnReadClassifier.gridy = 3;
		mainPanel.add(btnReadClassifier, gbc_btnReadClassifier);

		read = new ReaderAction(this);
		btnReadClassifier.setActionCommand("readclassifier");
		btnReadClassifier.addActionListener(openClf);

		JSeparator separator_1 = new JSeparator();
		GridBagConstraints gbc_separator_1 = new GridBagConstraints();
		gbc_separator_1.insets = new Insets(0, 0, 5, 5);
		gbc_separator_1.gridx = 0;
		gbc_separator_1.gridy = 4;
		mainPanel.add(separator_1, gbc_separator_1);

		JLabel lblFileReadAnd = new JLabel("File read and processing...");
		lblFileReadAnd.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_lblFileReadAnd = new GridBagConstraints();
		gbc_lblFileReadAnd.insets = new Insets(0, 0, 5, 5);
		gbc_lblFileReadAnd.gridx = 1;
		gbc_lblFileReadAnd.gridy = 5;
		mainPanel.add(lblFileReadAnd, gbc_lblFileReadAnd);

		JButton btnStartProcess = new JButton("Start Process");
		btnStartProcess.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_btnStartProcess = new GridBagConstraints();
		gbc_btnStartProcess.insets = new Insets(0, 0, 5, 0);
		gbc_btnStartProcess.gridx = 2;
		gbc_btnStartProcess.gridy = 5;
		mainPanel.add(btnStartProcess, gbc_btnStartProcess);

		// adding action to btnReadFile
		btnStartProcess.setActionCommand("startProcess");
		btnStartProcess.addActionListener(read);

		internalFrame = new JInternalFrame("Result in OpenStreetMap");
		internalFrame.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null,
				null));
		GridBagConstraints gbc_internalFrame = new GridBagConstraints();
		gbc_internalFrame.gridheight = 3;
		gbc_internalFrame.gridwidth = 2;
		gbc_internalFrame.fill = GridBagConstraints.BOTH;
		gbc_internalFrame.gridx = 1;
		gbc_internalFrame.gridy = 6;
		mainPanel.add(internalFrame, gbc_internalFrame);
		this.setMap();

		JPanel settingsPanel = new JPanel();
		tabbedPane.addTab("Settings", null, settingsPanel, null);
		GridBagLayout gbl_settingsPanel = new GridBagLayout();
		gbl_settingsPanel.columnWidths = new int[] { 68, 0, 0, 0, 288, 0 };
		gbl_settingsPanel.rowHeights = new int[] { 24, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_settingsPanel.columnWeights = new double[] { 0.0, 1.0, 1.0, 0.0,
				1.0, Double.MIN_VALUE };
		gbl_settingsPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		settingsPanel.setLayout(gbl_settingsPanel);

		JLabel lblEnterYourSpecific = new JLabel(
				"Enter your specific Configuration for the loaded Classifier:");
		lblEnterYourSpecific.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblEnterYourSpecific = new GridBagConstraints();
		gbc_lblEnterYourSpecific.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblEnterYourSpecific.gridwidth = 4;
		gbc_lblEnterYourSpecific.insets = new Insets(0, 0, 5, 0);
		gbc_lblEnterYourSpecific.gridx = 1;
		gbc_lblEnterYourSpecific.gridy = 1;
		settingsPanel.add(lblEnterYourSpecific, gbc_lblEnterYourSpecific);

		JLabel lblLocation = new JLabel("Classifier Configuration");
		GridBagConstraints gbc_lblLocation = new GridBagConstraints();
		gbc_lblLocation.anchor = GridBagConstraints.EAST;
		gbc_lblLocation.insets = new Insets(0, 0, 5, 5);
		gbc_lblLocation.gridx = 1;
		gbc_lblLocation.gridy = 2;
		settingsPanel.add(lblLocation, gbc_lblLocation);

		JSpinner classifierConfigSpinner = new JSpinner();
		GridBagConstraints gbc_classifierConfigSpinner = new GridBagConstraints();
		gbc_classifierConfigSpinner.gridwidth = 2;
		gbc_classifierConfigSpinner.fill = GridBagConstraints.HORIZONTAL;
		gbc_classifierConfigSpinner.insets = new Insets(0, 0, 5, 5);
		gbc_classifierConfigSpinner.gridx = 2;
		gbc_classifierConfigSpinner.gridy = 2;
		settingsPanel.add(classifierConfigSpinner, gbc_classifierConfigSpinner);
				
						JLabel lblEntityWeighting = new JLabel("Entity Weighting");
						GridBagConstraints gbc_lblEntityWeighting = new GridBagConstraints();
						gbc_lblEntityWeighting.insets = new Insets(0, 0, 5, 5);
						gbc_lblEntityWeighting.anchor = GridBagConstraints.EAST;
						gbc_lblEntityWeighting.gridx = 1;
						gbc_lblEntityWeighting.gridy = 3;
						settingsPanel.add(lblEntityWeighting, gbc_lblEntityWeighting);
		
				entityWeightTextField = new JTextField();
				GridBagConstraints gbc_entityWeightTextField = new GridBagConstraints();
				gbc_entityWeightTextField.gridwidth = 2;
				gbc_entityWeightTextField.insets = new Insets(0, 0, 5, 5);
				gbc_entityWeightTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_entityWeightTextField.gridx = 2;
				gbc_entityWeightTextField.gridy = 3;
				settingsPanel.add(entityWeightTextField, gbc_entityWeightTextField);
				entityWeightTextField.setColumns(10);
				
						JLabel lblNewLabel_1 = new JLabel("");
						lblNewLabel_1
								.setIcon(new ImageIcon(
										"C:\\Users\\Mario\\Dropbox\\Projektordner\\Semester 6\\Wikiner\\StanfordNLP.jpg"));
						GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
						gbc_lblNewLabel_1.gridwidth = 2;
						gbc_lblNewLabel_1.gridheight = 4;
						gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
						gbc_lblNewLabel_1.gridx = 2;
						gbc_lblNewLabel_1.gridy = 4;
						settingsPanel.add(lblNewLabel_1, gbc_lblNewLabel_1);
				
				JLabel label_1 = new JLabel("Enter your Configuration for the connected Database");
				label_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
				GridBagConstraints gbc_label_1 = new GridBagConstraints();
				gbc_label_1.gridwidth = 4;
				gbc_label_1.fill = GridBagConstraints.HORIZONTAL;
				gbc_label_1.insets = new Insets(0, 0, 5, 0);
				gbc_label_1.gridx = 1;
				gbc_label_1.gridy = 8;
				settingsPanel.add(label_1, gbc_label_1);
				
				JLabel label_4 = new JLabel("Database");
				GridBagConstraints gbc_label_4 = new GridBagConstraints();
				gbc_label_4.anchor = GridBagConstraints.EAST;
				gbc_label_4.insets = new Insets(0, 0, 5, 5);
				gbc_label_4.gridx = 1;
				gbc_label_4.gridy = 9;
				settingsPanel.add(label_4, gbc_label_4);
				
				databaseTextfield = new JTextField();
				databaseTextfield.setColumns(10);
				GridBagConstraints gbc_databaseTextfield = new GridBagConstraints();
				gbc_databaseTextfield.insets = new Insets(0, 0, 5, 5);
				gbc_databaseTextfield.fill = GridBagConstraints.HORIZONTAL;
				gbc_databaseTextfield.gridx = 2;
				gbc_databaseTextfield.gridy = 9;
				settingsPanel.add(databaseTextfield, gbc_databaseTextfield);
				
				JLabel label = new JLabel("Password");
				GridBagConstraints gbc_label = new GridBagConstraints();
				gbc_label.anchor = GridBagConstraints.EAST;
				gbc_label.insets = new Insets(0, 0, 5, 5);
				gbc_label.gridx = 1;
				gbc_label.gridy = 10;
				settingsPanel.add(label, gbc_label);
				
				passwordTextfield = new JTextField();
				passwordTextfield.setColumns(10);
				GridBagConstraints gbc_passwordTextfield = new GridBagConstraints();
				gbc_passwordTextfield.insets = new Insets(0, 0, 5, 5);
				gbc_passwordTextfield.fill = GridBagConstraints.HORIZONTAL;
				gbc_passwordTextfield.gridx = 2;
				gbc_passwordTextfield.gridy = 10;
				settingsPanel.add(passwordTextfield, gbc_passwordTextfield);
				
				JLabel label_2 = new JLabel("Hostname");
				GridBagConstraints gbc_label_2 = new GridBagConstraints();
				gbc_label_2.anchor = GridBagConstraints.EAST;
				gbc_label_2.insets = new Insets(0, 0, 5, 5);
				gbc_label_2.gridx = 1;
				gbc_label_2.gridy = 12;
				settingsPanel.add(label_2, gbc_label_2);
				
				hostnameTextfield = new JTextField();
				hostnameTextfield.setColumns(10);
				GridBagConstraints gbc_hostnameTextfield = new GridBagConstraints();
				gbc_hostnameTextfield.insets = new Insets(0, 0, 5, 5);
				gbc_hostnameTextfield.fill = GridBagConstraints.HORIZONTAL;
				gbc_hostnameTextfield.gridx = 2;
				gbc_hostnameTextfield.gridy = 12;
				settingsPanel.add(hostnameTextfield, gbc_hostnameTextfield);
				
				JLabel label_3 = new JLabel("Port");
				GridBagConstraints gbc_label_3 = new GridBagConstraints();
				gbc_label_3.anchor = GridBagConstraints.EAST;
				gbc_label_3.insets = new Insets(0, 0, 5, 5);
				gbc_label_3.gridx = 1;
				gbc_label_3.gridy = 13;
				settingsPanel.add(label_3, gbc_label_3);
				
				portTextfield = new JTextField();
				portTextfield.setColumns(10);
				GridBagConstraints gbc_portTextfield = new GridBagConstraints();
				gbc_portTextfield.insets = new Insets(0, 0, 5, 5);
				gbc_portTextfield.fill = GridBagConstraints.HORIZONTAL;
				gbc_portTextfield.gridx = 2;
				gbc_portTextfield.gridy = 13;
				settingsPanel.add(portTextfield, gbc_portTextfield);
				
				JLabel label_5 = new JLabel("Username");
				GridBagConstraints gbc_label_5 = new GridBagConstraints();
				gbc_label_5.anchor = GridBagConstraints.EAST;
				gbc_label_5.insets = new Insets(0, 0, 5, 5);
				gbc_label_5.gridx = 1;
				gbc_label_5.gridy = 14;
				settingsPanel.add(label_5, gbc_label_5);
				
				usernameTextfield = new JTextField();
				usernameTextfield.setColumns(10);
				GridBagConstraints gbc_usernameTextfield = new GridBagConstraints();
				gbc_usernameTextfield.insets = new Insets(0, 0, 5, 5);
				gbc_usernameTextfield.fill = GridBagConstraints.HORIZONTAL;
				gbc_usernameTextfield.gridx = 2;
				gbc_usernameTextfield.gridy = 14;
				settingsPanel.add(usernameTextfield, gbc_usernameTextfield);
								
										JButton btnSubmit = new JButton("Submit Values");
										btnSubmit.setFont(new Font("Tahoma", Font.PLAIN, 13));
										GridBagConstraints gbc_btnSubmit = new GridBagConstraints();
										gbc_btnSubmit.gridwidth = 2;
										gbc_btnSubmit.gridheight = 2;
										gbc_btnSubmit.insets = new Insets(0, 0, 5, 5);
										gbc_btnSubmit.gridx = 2;
										gbc_btnSubmit.gridy = 15;
										settingsPanel.add(btnSubmit, gbc_btnSubmit);

		internalFrame.setVisible(true);

	}

	

	private void setMap(){
		openMap = new JXMapKit();
		openMap.setDefaultProvider(DefaultProviders.OpenStreetMaps);
		// openMap.setAddressLocation(new GeoPosition(50.241111, 11.328056));
		openMap.setCenterPosition(new GeoPosition(50.241111, 11.328056));
		
		Set<Waypoint> geopositions = new HashSet<Waypoint>();
		geopositions.add(new Waypoint(41.881944, -87.627778));
		geopositions.add(new Waypoint(40.716667, -74));
		geopositions.add(new Waypoint(50.241111, 11.328056));
		geopositions.add(new Waypoint(72.0000, 40.0000));

		WaypointPainter<JXMapViewer> painter = new WaypointPainter<JXMapViewer>();
		Iterator<Waypoint> it = geopositions.iterator();
		while (it.hasNext()){
			Waypoint wp = (Waypoint) it.next();
			painter.getWaypoints().add(wp);
		}
		painter.setWaypoints(geopositions);
		openMap.getMainMap().setOverlayPainter(painter);
		final ArrayList<Waypoint> points = new ArrayList<>(geopositions);
		for (int i = 0; i < points.size(); i++){
			JLabel hoverLabel = new JLabel(points.get(i).getPosition().toString());
			hoverLabel.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 14));
			hoverLabel.setVisible(true);
			openMap.getMainMap().add(hoverLabel);

			createMouseListener(points.get(i).getPosition(), hoverLabel);
		}
		internalFrame.getContentPane().add(openMap);
	}

	private void createMouseListener(final GeoPosition currentGP, final JLabel hoverLabel){
		openMap.getMainMap().addMouseMotionListener(new MouseMotionListener() {
			public void mouseMoved(MouseEvent e){
				JXMapViewer map = openMap.getMainMap();
				// convert to world bitmap
				Point2D gp_pt = map.getTileFactory().geoToPixel(currentGP,
						map.getZoom());
				// convert to screen
				Rectangle rect = map.getViewportBounds();
				Point converted_gp_pt = new Point((int) gp_pt.getX() - rect.x,
						(int) gp_pt.getY() - rect.y);

				// check if near the mouse
				if (converted_gp_pt.distance(e.getPoint()) < 10){
					hoverLabel.setLocation(converted_gp_pt);
					hoverLabel.setVisible(true);
				} else{
					hoverLabel.setVisible(false);
				}
			}
			@Override
			public void mouseDragged(MouseEvent e){}
		});
	}

	public JTextField getUsernameTextfield(){
		return usernameTextfield;
	}

	public void setUsernameTextfield(JTextField usernameTextfield){
		this.usernameTextfield = usernameTextfield;
	}

	public JTextField getPasswordTextfield(){
		return passwordTextfield;
	}

	public void setPasswordTextfield(JTextField passwordTextfield){
		this.passwordTextfield = passwordTextfield;
	}

	public JTextField getHostnameTextfield(){
		return hostnameTextfield;
	}

	public void setHostnameTextfield(JTextField hostnameTextfield){
		this.hostnameTextfield = hostnameTextfield;
	}

	public JTextField getPortTextfield(){
		return portTextfield;
	}

	public void setPortTextfield(JTextField portTextfield){
		this.portTextfield = portTextfield;
	}

	public JTextField getDatabaseTextfield(){
		return databaseTextfield;
	}

	public void setDatabaseTextfield(JTextField databaseTextfield){
		this.databaseTextfield = databaseTextfield;
	}
	
	public JTextField getClassifierTextField(){
		return classifierTextField;
	}

	public void setClassifierTextField(JTextField classifierTextField){
		this.classifierTextField = classifierTextField;
	}

	public JTextField getFileTextField(){
		return fileTextField;
	}

	public void setFileTextField(JTextField fileTextField){
		this.fileTextField = fileTextField;
	}
}
