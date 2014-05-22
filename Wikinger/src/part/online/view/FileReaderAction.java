package part.online.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import part.online.control.ViewController;

public class FileReaderAction implements ActionListener{

	private OnlineView onlineView;
	private ViewController controller;
	
	public FileReaderAction(OnlineView onlineView){
		this.onlineView = onlineView;
	}

	@Override
	public void actionPerformed(ActionEvent event){
		String actionTodo = event.getActionCommand();
		String outPath = onlineView.getTextField().getText();
		
		String outClassifier = onlineView.getClassifierTextField().getText();
		if (actionTodo.equals("startProcess")){
			if(outPath.equals("") && outClassifier.equals("")){
				JOptionPane.showMessageDialog(null, "No Text and Classifier input", 
												"CritcalError", JOptionPane.ERROR_MESSAGE);
			}
			else
				initViewController(outPath, outClassifier);
		}
	}

	
	//TODO ViewController muss noch geaendert werden!!!
	private void initViewController(String incPath, String incClassifier){
		controller.callViewController(incPath, incClassifier);
		doWork();
	}

	private void doWork(){
		controller.readIncTextFile();
		controller.handleEntities();
	}

}

