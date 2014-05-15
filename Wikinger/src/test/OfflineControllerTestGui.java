package test;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

import part.offline.control.Status;

public class OfflineControllerTestGui extends JFrame{
	
	private JLabel time;
	private JProgressBar progress;
	private Status status;
	
//	public static void main(String[] args) {
//		OfflineControllerTestGui t = new OfflineControllerTestGui();
//		t.init();
//	}
	
	public OfflineControllerTestGui(Status status){
		this.status = status;
	}
	
	public void init(){
		this.setSize(400, 300);
		progress = new JProgressBar(0, 1000);
		progress.setSize(350, 30);
		progress.setValue(200);
		time = new JLabel();
		time.setSize(350, 50);
		time.setText("asdasd");
		this.add(time);
		this.add(progress);
		
		Thread t = new Thread(new Updater(this, status));
		t.start();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public void setProgress(double percent, long timeRem){
		progress.setValue((int) (1000*percent));
		time.setText("<html>"+timeRem/1000+"<body>s<br><br>"+(int)(1000*percent)+"promille"+"</body></html>");
	}

}

class Updater implements Runnable{
	
	OfflineControllerTestGui gui;
	Status status;
	
	public Updater(OfflineControllerTestGui gui, Status status){
		this.gui = gui;
		this.status = status;
	}

	@Override
	public void run() {
		
		while(true){
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		status.calcStatus();
		gui.setProgress(status.getPercent(), status.getTimeRemaining());
		}
		
	}
	
}
