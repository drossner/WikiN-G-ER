package part.online.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import part.online.control.ViewController;

public class ReaderAction implements ActionListener {

    private OnlineView onlineView;
    private ViewController controller;
    private String[] dbParameters;

    public ReaderAction(OnlineView onlineView) {
	this.onlineView = onlineView;
	dbParameters = new String[5];
    }

    @Override
    public void actionPerformed(ActionEvent event) {
	String actionTodo = event.getActionCommand();
	String outPath = onlineView.getFileTextField().getText();
	String outClassifier = onlineView.getClassifierTextField().getText();

	if (actionTodo.equals("submitSettingValues")) {
	    readDatabaseParameters();
	}
	
	if (actionTodo.equals("startProcess")) {
	    if (outPath.equals("") && outClassifier.equals("")) {
		JOptionPane.showMessageDialog(null, 
			"No Text and Classifier input", "CritcalError",
			JOptionPane.ERROR_MESSAGE);
	    } else
		initViewController(outPath, outClassifier);
	}
    }

    private void readDatabaseParameters() {
	dbParameters[0] = onlineView.getHostnameTextfield().getText();
	dbParameters[1] = onlineView.getPasswordTextfield().getText();
	dbParameters[2] = onlineView.getPortTextfield().getText();
	dbParameters[3] = onlineView.getDatabaseTextfield().getText();
	dbParameters[4] = onlineView.getUsernameTextfield().getText();

    }

    private void initViewController(String incPath, String incClassifier) {
	controller = new ViewController(incPath, incClassifier);
	doWork();
    }

    private void doWork() {
	controller.readIncTextFile();
	controller.handleEntities();
    }

}