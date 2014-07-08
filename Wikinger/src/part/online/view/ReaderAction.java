package part.online.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import data.City;
import part.online.control.ViewController;

public class ReaderAction implements ActionListener {

    private OnlineView onlineView;
    private ViewController viewController;
    private double[] classifierConfig;
    private City[] outCities;

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
		onlineView.setProcessLabelText();
		outCities = viewController.calculate(classifierConfig, outPath);
	    
	    	if(outCities.length != 0) {
	    		int count = (Integer) onlineView.getResultSizeSpinner().getValue();
	    		System.out.println("Count: " + count);
	    	    onlineView.setErgebnisCount(count);
	    	    onlineView.setCitiesToMap(outCities);
	    	    
	    	}else {
	    	    JOptionPane.showMessageDialog(new JFrame(), 
	    		    "No Cities found!", "WikiNer Error", JOptionPane.ERROR_MESSAGE);
	    	}
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