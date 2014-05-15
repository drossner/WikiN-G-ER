package part.online.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
			initViewController(outPath, outClassifier);
		}
	}

	private void initViewController(String incPath, String incClassifier){
		controller = new ViewController(incPath, incClassifier);
		controller.readIncTextFile();
		controller.handleEntities();
	}

}

