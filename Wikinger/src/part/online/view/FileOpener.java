package part.online.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileOpener implements ActionListener{

	private JTextField textFeld;
	
	public FileOpener(JTextField textFeld){
		this.textFeld = textFeld;
	}

	@Override
	public void actionPerformed(ActionEvent event){
		String actionTodo = event.getActionCommand();
		if(actionTodo.equals("browse")){
			showFileChooser(new FileNameExtensionFilter("Textfiles (*.txt)", "txt"));
		}
		
		//TODO geht so nicht!!
		else if(actionTodo.equals("readclassifier")){
			showFileChooser(new FileNameExtensionFilter("Classifiers Files (*.tar)", "gz"));
		}
		
	}

	private void showFileChooser(FileNameExtensionFilter filter){
		JFileChooser chooser = new JFileChooser("./");
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		chooser.setFileFilter(filter);
		
		int resultOpen = chooser.showOpenDialog(null);
		
		if(resultOpen == JFileChooser.APPROVE_OPTION){
			String absPath = chooser.getSelectedFile().getAbsolutePath();
			textFeld.setText(absPath);
		}
		else if(resultOpen == JFileChooser.CANCEL_OPTION){
			chooser.cancelSelection();
		}
		
	}
	

}
