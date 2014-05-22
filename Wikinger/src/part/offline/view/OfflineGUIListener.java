package part.offline.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import part.offline.constants.DBInformations;
import part.offline.control.OfflineController;
import part.offline.control.Status;
import part.offline.view.OfflineGui.Updater;
import test.GUIController;

public class OfflineGUIListener implements ActionListener {
	
	

	private Updater updater;
	private OfflineGui gui;
	GUIController controller;

	public OfflineGUIListener(OfflineGui gui) {
		controller = new GUIController();
		this.gui = gui;
	}

	public void setUpdater(Updater updater){
		this.updater = updater;
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
		JDialog dialog  = new JDialog();
		Object[] options = {"Yes", "No"};
		int n =JOptionPane.showOptionDialog(dialog, "Are you sure?", "Abort Crawling", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null ,options, options[1]);
		if (n==0) {
			gui.abort();
		}
	}

	private void start(){
		
		String[] infos = gui.getInformations();
		
		if (!fullyFilled(gui.getInformations())) {
			JDialog dialog  = new JDialog();
			JOptionPane.showMessageDialog(dialog, "Please fill in all Fields");
			return;
		}
		Status status = controller.startOfflineController(infos);
		updater.setStatus(status);
		gui.disableFields();
		Thread t = new Thread(updater);
		controller.startCrawling(infos);
		t.start();
	}
	
	private boolean fullyFilled(String[] information){
		boolean temp = true;
		
		for (int i = 0; i < information.length; i++) {
			if (information[i].equals("") && i != DBInformations.PASSWD) {
				temp =false;
			}
		}
		return temp;
	}
	
	

}
