package part.online.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;

public class FileOpener implements ActionListener{

	private OnlineView onlineView;
	
	public FileOpener(OnlineView onlineView){
		System.out.println("Im opener drinne");
		this.onlineView = onlineView;
	}

	@Override
	public void actionPerformed(ActionEvent event){
		String actionTodo = event.getActionCommand();
		if(actionTodo.equals("browse")){
			showFileChooser();
		}
		
		
	}

	private void showFileChooser(){
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		
		chooser.showOpenDialog(null);
		System.out.println(chooser.getSelectedFile().getAbsolutePath());
		
		String absPath = chooser.getSelectedFile().getAbsolutePath();
		onlineView.getTextField().setText(absPath);
	}
	

}
