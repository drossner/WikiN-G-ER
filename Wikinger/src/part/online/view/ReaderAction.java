package part.online.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import part.online.control.ViewController;

public class ReaderAction implements ActionListener {

    private OnlineView onlineView;
    private ViewController viewController;
    private double[] classifierConfig;

    public ReaderAction(OnlineView onlineView, ViewController viewController) {
	this.onlineView = onlineView;
	this.viewController = viewController;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
	String actionTodo = event.getActionCommand();
	String outPath = getFilepath();
	classifierConfig = getClassifierConfig();
//	String outClassifier = onlineView.getClassifierTextField().getText();
	
	if (actionTodo.equals("startProcess")) {
	    if (outPath.equals("")) {
		JOptionPane.showMessageDialog(null, 
			"No Text and Classifier input", "CritcalError",
			JOptionPane.ERROR_MESSAGE);
	    } else
		//TODO: Classifier Path löschen!!! Sonst NullPointer!!!
		//viewController.calculate(classifierConfig, incPath)
		System.out.println("Null");
	}
    }

    private double[] getClassifierConfig() {
	double[] temp = new double[8];
	temp[0] = (Double) onlineView.getOrganizationSpinner().getModel().getValue();
	temp[1] = (Double) onlineView.getPersonSpinner().getModel().getValue();
	temp[2] = (Double) onlineView.getLocationSpinner().getModel().getValue();
	temp[3] = (Double) onlineView.getMiscSpinner().getModel().getValue();
	temp[4] = (Double) onlineView.getTimeSpinner().getModel().getValue();
	temp[5] = (Double) onlineView.getMoneySpinner().getModel().getValue();
	temp[6] = (Double) onlineView.getPercentSpinner().getModel().getValue();
	temp[7] = (Double) onlineView.getDateSpinner().getModel().getValue();
	return temp;
    }

    private String getFilepath() {
	return onlineView.getFileTextField().getText();
    }
}