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
		if (actionTodo.equals("read")){
			readIncText(outPath);
		}
	}

	private void readIncText(String incPath){
		controller = new ViewController(incPath);
		controller.readIncTextFile();
		controller.handleEntities();
	}

}

