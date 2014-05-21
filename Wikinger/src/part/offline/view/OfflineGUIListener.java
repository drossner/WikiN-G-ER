package part.offline.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import part.offline.view.OfflineGui.Updater;

public class OfflineGUIListener implements ActionListener {
	
	

	private Updater updater;
	private OfflineGui gui;

	public OfflineGUIListener( OfflineGui gui, Updater updater) {
		this.updater = updater;
		this.gui = gui;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String temp =  e.getActionCommand();
		
		if (temp.equals("start")) {
			start();
		} else if (temp.equals("abort")) {
			abort();
		}
		
	}
	
	private void abort() {
		System.out.println("Abgebrochen"); 
		JDialog dialog  = new JDialog();
		Object[] options = {"Yes", "No"};
		int n =JOptionPane.showOptionDialog(dialog, "Are you sure?", "Abort Crawling", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null ,options, options[1]);

		if (n==0) {
			gui.abort();
		}
	}

	private void start(){
		
		String[] infos = gui.getInformations();
		
		for (int i = 0; i < infos.length; i++) {
			System.out.println(infos[i]);
		}
		
		gui.disableFields();
		Thread t = new Thread(updater);
		t.start();
	}

}
