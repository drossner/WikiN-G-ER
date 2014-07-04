package main;

import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import edu.stanford.nlp.util.logging.Color;
import part.online.control.ViewController;
import part.online.view.OnlineView;

public class OnlineStarter {
	
	private JFrame wait;
	
	public static void main(String[] args) {
		OnlineStarter os = new OnlineStarter();
		os.go();
	}

	private void go() {
		showWaitScreen();
		ViewController control = new ViewController();
		terminateWaitScreen();
		
		OnlineView view = new OnlineView(control);
		
	}

	private void terminateWaitScreen() {
		wait.setVisible(false);
		wait.dispose();
	}

	private void showWaitScreen() {
		wait = new JFrame();	
		wait.setSize(new Dimension(500, 270));
		wait.setAlwaysOnTop(true);
		wait.setUndecorated(true);
		wait.setBackground(new java.awt.Color(0, 0, 0, 0));
		wait.setLocationRelativeTo(null);
		wait.setCursor(new Cursor(Cursor.WAIT_CURSOR));
		
		ImageIcon icon = new ImageIcon("./splash.png");
		JLabel label = new JLabel(icon);
		label.setOpaque(false);
		wait.add(label);
		//wait.setIconImage(new ImageIcon("./NERICO.ico"));
		wait.setVisible(true);
	}
	

}
