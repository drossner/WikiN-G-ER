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

public class OnlineView
{

	private JFrame frmWikinerOnlinepart;
	private JTextField textField;

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
		frmWikinerOnlinepart.setBounds(100, 100, 450, 300);
		frmWikinerOnlinepart.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblWelcomeToWikiner = new JLabel("Welcome to Wiki-NER Online!");
		lblWelcomeToWikiner.setFont(new Font("Tahoma", Font.PLAIN, 17));
		frmWikinerOnlinepart.getContentPane().add(lblWelcomeToWikiner, BorderLayout.NORTH);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frmWikinerOnlinepart.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("General", null, panel, null);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblInputTextFor = new JLabel("Input Text for Named Entity Recognition");
		lblInputTextFor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblInputTextFor = new GridBagConstraints();
		gbc_lblInputTextFor.insets = new Insets(0, 0, 5, 5);
		gbc_lblInputTextFor.anchor = GridBagConstraints.WEST;
		gbc_lblInputTextFor.gridx = 0;
		gbc_lblInputTextFor.gridy = 0;
		panel.add(lblInputTextFor, gbc_lblInputTextFor);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 1;
		panel.add(textField, gbc_textField);
		textField.setColumns(10);
		
		JButton btnReadFile = new JButton("Read File");
		btnReadFile.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_btnReadFile = new GridBagConstraints();
		gbc_btnReadFile.insets = new Insets(0, 0, 5, 0);
		gbc_btnReadFile.gridx = 1;
		gbc_btnReadFile.gridy = 1;
		panel.add(btnReadFile, gbc_btnReadFile);
		
		JLabel lblFileReadAnd = new JLabel("File read and processing");
		lblFileReadAnd.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_lblFileReadAnd = new GridBagConstraints();
		gbc_lblFileReadAnd.insets = new Insets(0, 0, 5, 5);
		gbc_lblFileReadAnd.gridx = 0;
		gbc_lblFileReadAnd.gridy = 3;
		panel.add(lblFileReadAnd, gbc_lblFileReadAnd);
		
		JInternalFrame internalFrame = new JInternalFrame("Result in OpenStreetMap");
		internalFrame.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GridBagConstraints gbc_internalFrame = new GridBagConstraints();
		gbc_internalFrame.gridwidth = 2;
		gbc_internalFrame.fill = GridBagConstraints.HORIZONTAL;
		gbc_internalFrame.insets = new Insets(0, 0, 0, 5);
		gbc_internalFrame.gridx = 0;
		gbc_internalFrame.gridy = 4;
		panel.add(internalFrame, gbc_internalFrame);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Settings", null, panel_1, null);
		internalFrame.setVisible(true);
		
	}

}
