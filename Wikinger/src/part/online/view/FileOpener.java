package part.online.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileOpener implements ActionListener{

	private OnlineView onlineView;
	
	public FileOpener(OnlineView onlineView){
		this.onlineView = onlineView;
	}

	@Override
	public void actionPerformed(ActionEvent event){
		String actionTodo = event.getActionCommand();
		if(actionTodo.equals("browse")){
			showFileChooser();
		}
		
		//TODO geht so nicht!!
		else if(actionTodo.equals("readclassifier")){
			showFileChooser();
		}
		
	}

	private void showFileChooser(){
		JFileChooser chooser = new JFileChooser("./");
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		chooser.setFileFilter(new FileNameExtensionFilter("Textfiles (*.txt)", "txt"));
		
		int resultOpen = chooser.showOpenDialog(null);
		
		if(resultOpen == JFileChooser.APPROVE_OPTION){
			String absPath = chooser.getSelectedFile().getAbsolutePath();
			onlineView.getTextField().setText(absPath);
		}
		else if(resultOpen == JFileChooser.CANCEL_OPTION){
			chooser.cancelSelection();
		}
		
	}
	

}
