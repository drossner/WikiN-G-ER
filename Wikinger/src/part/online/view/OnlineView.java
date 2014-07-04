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

import part.online.control.ViewController;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;

import javax.swing.JSpinner;
import javax.swing.ImageIcon;
import javax.swing.SpinnerNumberModel;

public class OnlineView {

	private JFrame frmWikinerOnlinepart;
	private JTextField fileTextField;
	private JTextField classifierTextField;
	private JInternalFrame internalFrame;
	private JXMapKit openMap;
	private ReaderAction read;
	private FileOpener open;
	private JSpinner organizationSpinner;
	private JSpinner personSpinner;
	private JSpinner locationSpinner;
	private JSpinner miscSpinner;
	private JSpinner timeSpinner;
	private JSpinner percentSpinner;
	private JSpinner dateSpinner;
	private JSpinner moneySpinner;
	private ImageIcon icon;

	/**
	 * Creates the Frame for the Online-Part
	 */
	public OnlineView(ViewController viewController) {
		read = new ReaderAction(this, viewController);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmWikinerOnlinepart = new JFrame();
		frmWikinerOnlinepart.setLocationRelativeTo(null);
		frmWikinerOnlinepart.setTitle("Wiki-NER --- OnlinePart");
		frmWikinerOnlinepart.setBounds(100, 100, 618, 516);
		frmWikinerOnlinepart.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmWikinerOnlinepart.setVisible(true);
		icon = new ImageIcon("./NERICO.ico");
		frmWikinerOnlinepart.setIconImage(icon.getImage());
		
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
		btnReadClassifier.setEnabled(false);
		btnReadClassifier.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_btnReadClassifier = new GridBagConstraints();
		gbc_btnReadClassifier.insets = new Insets(0, 0, 5, 0);
		gbc_btnReadClassifier.gridx = 2;
		gbc_btnReadClassifier.gridy = 3;
		mainPanel.add(btnReadClassifier, gbc_btnReadClassifier);

		btnReadClassifier.setActionCommand("readclassifier");
		btnReadClassifier.addActionListener(openClf);

		JSeparator seperator1 = new JSeparator();
		GridBagConstraints gbc_separator_1 = new GridBagConstraints();
		gbc_separator_1.insets = new Insets(0, 0, 5, 5);
		gbc_separator_1.gridx = 0;
		gbc_separator_1.gridy = 4;
		mainPanel.add(seperator1, gbc_separator_1);

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
		
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Config", null, panel, null);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Mario\\Dropbox\\Projektordner\\Semester 6\\Wikiner\\StanfordNLP.jpg"));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 6;
		gbc_lblNewLabel.gridy = 0;
		panel.add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel lblOrganizaion = new JLabel("Organization");
		GridBagConstraints gbc_lblOrganizaion = new GridBagConstraints();
		gbc_lblOrganizaion.anchor = GridBagConstraints.EAST;
		gbc_lblOrganizaion.insets = new Insets(0, 0, 5, 5);
		gbc_lblOrganizaion.gridx = 4;
		gbc_lblOrganizaion.gridy = 1;
		panel.add(lblOrganizaion, gbc_lblOrganizaion);
		
		organizationSpinner = new JSpinner();
		organizationSpinner.setModel(new SpinnerNumberModel(new Double(0), null, null, new Double(1)));
		GridBagConstraints gbc_organizationSpinner = new GridBagConstraints();
		gbc_organizationSpinner.fill = GridBagConstraints.HORIZONTAL;
		gbc_organizationSpinner.insets = new Insets(0, 0, 5, 5);
		gbc_organizationSpinner.gridx = 6;
		gbc_organizationSpinner.gridy = 1;
		panel.add(organizationSpinner, gbc_organizationSpinner);
		
		JLabel lblPerson = new JLabel("Person");
		GridBagConstraints gbc_lblPerson = new GridBagConstraints();
		gbc_lblPerson.insets = new Insets(0, 0, 5, 5);
		gbc_lblPerson.gridx = 4;
		gbc_lblPerson.gridy = 2;
		panel.add(lblPerson, gbc_lblPerson);
		
		personSpinner = new JSpinner();
		personSpinner.setModel(new SpinnerNumberModel(new Double(0), null, null, new Double(1)));
		GridBagConstraints gbc_personSpinner = new GridBagConstraints();
		gbc_personSpinner.fill = GridBagConstraints.HORIZONTAL;
		gbc_personSpinner.insets = new Insets(0, 0, 5, 5);
		gbc_personSpinner.gridx = 6;
		gbc_personSpinner.gridy = 2;
		panel.add(personSpinner, gbc_personSpinner);
		
		JLabel lblLocation = new JLabel("Location");
		GridBagConstraints gbc_lblLocation = new GridBagConstraints();
		gbc_lblLocation.insets = new Insets(0, 0, 5, 5);
		gbc_lblLocation.gridx = 4;
		gbc_lblLocation.gridy = 3;
		panel.add(lblLocation, gbc_lblLocation);
		
		locationSpinner = new JSpinner();
		locationSpinner.setModel(new SpinnerNumberModel(new Double(0), null, null, new Double(1)));
		GridBagConstraints gbc_locationSpinner = new GridBagConstraints();
		gbc_locationSpinner.fill = GridBagConstraints.HORIZONTAL;
		gbc_locationSpinner.insets = new Insets(0, 0, 5, 5);
		gbc_locationSpinner.gridx = 6;
		gbc_locationSpinner.gridy = 3;
		panel.add(locationSpinner, gbc_locationSpinner);
		
		JLabel lblMisc = new JLabel("Misc");
		GridBagConstraints gbc_lblMisc = new GridBagConstraints();
		gbc_lblMisc.insets = new Insets(0, 0, 5, 5);
		gbc_lblMisc.gridx = 4;
		gbc_lblMisc.gridy = 4;
		panel.add(lblMisc, gbc_lblMisc);
		
		miscSpinner = new JSpinner();
		miscSpinner.setModel(new SpinnerNumberModel(new Double(0), null, null, new Double(1)));
		GridBagConstraints gbc_miscSpinner = new GridBagConstraints();
		gbc_miscSpinner.fill = GridBagConstraints.HORIZONTAL;
		gbc_miscSpinner.insets = new Insets(0, 0, 5, 5);
		gbc_miscSpinner.gridx = 6;
		gbc_miscSpinner.gridy = 4;
		panel.add(miscSpinner, gbc_miscSpinner);
		
		JLabel lblTime = new JLabel("Time");
		GridBagConstraints gbc_lblTime = new GridBagConstraints();
		gbc_lblTime.insets = new Insets(0, 0, 5, 5);
		gbc_lblTime.gridx = 4;
		gbc_lblTime.gridy = 5;
		panel.add(lblTime, gbc_lblTime);
		
		timeSpinner = new JSpinner();
		timeSpinner.setModel(new SpinnerNumberModel(new Double(0), null, null, new Double(1)));
		GridBagConstraints gbc_timeSpinner = new GridBagConstraints();
		gbc_timeSpinner.fill = GridBagConstraints.HORIZONTAL;
		gbc_timeSpinner.insets = new Insets(0, 0, 5, 5);
		gbc_timeSpinner.gridx = 6;
		gbc_timeSpinner.gridy = 5;
		panel.add(timeSpinner, gbc_timeSpinner);
		
		JLabel lblMoney = new JLabel("Money");
		GridBagConstraints gbc_lblMoney = new GridBagConstraints();
		gbc_lblMoney.insets = new Insets(0, 0, 5, 5);
		gbc_lblMoney.gridx = 4;
		gbc_lblMoney.gridy = 6;
		panel.add(lblMoney, gbc_lblMoney);
		
		moneySpinner = new JSpinner();
		moneySpinner.setModel(new SpinnerNumberModel(new Double(0), null, null, new Double(1)));
		GridBagConstraints gbc_moneySpinner = new GridBagConstraints();
		gbc_moneySpinner.fill = GridBagConstraints.HORIZONTAL;
		gbc_moneySpinner.insets = new Insets(0, 0, 5, 5);
		gbc_moneySpinner.gridx = 6;
		gbc_moneySpinner.gridy = 6;
		panel.add(moneySpinner, gbc_moneySpinner);
		
		JLabel lblPerson_1 = new JLabel("Percent");
		GridBagConstraints gbc_lblPerson_1 = new GridBagConstraints();
		gbc_lblPerson_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblPerson_1.gridx = 4;
		gbc_lblPerson_1.gridy = 7;
		panel.add(lblPerson_1, gbc_lblPerson_1);
		
		percentSpinner = new JSpinner();
		percentSpinner.setModel(new SpinnerNumberModel(new Double(0), null, null, new Double(1)));
		GridBagConstraints gbc_percentSpinner = new GridBagConstraints();
		gbc_percentSpinner.fill = GridBagConstraints.HORIZONTAL;
		gbc_percentSpinner.insets = new Insets(0, 0, 5, 5);
		gbc_percentSpinner.gridx = 6;
		gbc_percentSpinner.gridy = 7;
		panel.add(percentSpinner, gbc_percentSpinner);
		
		JLabel lblDate = new JLabel("Date");
		GridBagConstraints gbc_lblDate = new GridBagConstraints();
		gbc_lblDate.insets = new Insets(0, 0, 0, 5);
		gbc_lblDate.gridx = 4;
		gbc_lblDate.gridy = 8;
		panel.add(lblDate, gbc_lblDate);
		
		dateSpinner = new JSpinner();
		dateSpinner.setModel(new SpinnerNumberModel(new Double(0), null, null, new Double(1)));
		GridBagConstraints gbc_dateSpinner = new GridBagConstraints();
		gbc_dateSpinner.fill = GridBagConstraints.HORIZONTAL;
		gbc_dateSpinner.insets = new Insets(0, 0, 0, 5);
		gbc_dateSpinner.gridx = 6;
		gbc_dateSpinner.gridy = 8;
		panel.add(dateSpinner, gbc_dateSpinner);
		this.setMap();

		internalFrame.setVisible(true);
	}

	private void setMap() {
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
		while (it.hasNext()) {
			Waypoint wp = (Waypoint) it.next();
			painter.getWaypoints().add(wp);
		}
		painter.setWaypoints(geopositions);
		openMap.getMainMap().setOverlayPainter(painter);
		final ArrayList<Waypoint> points = new ArrayList<>(geopositions);
		for (int i = 0; i < points.size(); i++) {
			JLabel hoverLabel = new JLabel(points.get(i).getPosition()
					.toString());
			hoverLabel.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 14));
			hoverLabel.setVisible(true);
			openMap.getMainMap().add(hoverLabel);

			createMouseListener(points.get(i).getPosition(), hoverLabel);
		}
		internalFrame.getContentPane().add(openMap);
	}

	private void createMouseListener(final GeoPosition currentGP,
			final JLabel hoverLabel) {
		openMap.getMainMap().addMouseMotionListener(new MouseMotionListener() {
			public void mouseMoved(MouseEvent e) {
				JXMapViewer map = openMap.getMainMap();
				// convert to world bitmap
				Point2D gp_pt = map.getTileFactory().geoToPixel(currentGP,
						map.getZoom());
				// convert to screen
				Rectangle rect = map.getViewportBounds();
				Point converted_gp_pt = new Point((int) gp_pt.getX() - rect.x,
						(int) gp_pt.getY() - rect.y);

				// check if near the mouse
				if (converted_gp_pt.distance(e.getPoint()) < 10) {
					hoverLabel.setLocation(converted_gp_pt);
					hoverLabel.setVisible(true);
				} else {
					hoverLabel.setVisible(false);
				}
			}

			@Override
			public void mouseDragged(MouseEvent e) {
			}
		});
	}

	public JTextField getClassifierTextField() {
		return classifierTextField;
	}

	public void setClassifierTextField(JTextField classifierTextField) {
		this.classifierTextField = classifierTextField;
	}

	public JTextField getFileTextField() {
		return fileTextField;
	}

	public void setFileTextField(JTextField fileTextField) {
		this.fileTextField = fileTextField;
	}
	
	public JSpinner getOrganizationSpinner() {
	    return organizationSpinner;
	}

	public void setOrganizationSpinner(JSpinner organizationSpinner) {
	    this.organizationSpinner = organizationSpinner;
	}

	public JSpinner getPersonSpinner() {
	    return personSpinner;
	}

	public void setPersonSpinner(JSpinner personSpinner) {
	    this.personSpinner = personSpinner;
	}

	public JSpinner getLocationSpinner() {
	    return locationSpinner;
	}

	public void setLocationSpinner(JSpinner locationSpinner) {
	    this.locationSpinner = locationSpinner;
	}

	public JSpinner getMiscSpinner() {
	    return miscSpinner;
	}

	public void setMiscSpinner(JSpinner miscSpinner) {
	    this.miscSpinner = miscSpinner;
	}

	public JSpinner getTimeSpinner() {
	    return timeSpinner;
	}

	public void setTimeSpinner(JSpinner timeSpinner) {
	    this.timeSpinner = timeSpinner;
	}

	public JSpinner getPercentSpinner() {
	    return percentSpinner;
	}

	public void setPercentSpinner(JSpinner percentSpinner) {
	    this.percentSpinner = percentSpinner;
	}

	public JSpinner getDateSpinner() {
	    return dateSpinner;
	}

	public void setDateSpinner(JSpinner dateSpinner) {
	    this.dateSpinner = dateSpinner;
	}
	
	public JSpinner getMoneySpinner() {
	    return moneySpinner;
	}

	public void setMoneySpinner(JSpinner moneySpinner) {
	    this.moneySpinner = moneySpinner;
	}
}