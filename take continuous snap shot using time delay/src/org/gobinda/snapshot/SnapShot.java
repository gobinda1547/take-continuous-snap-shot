package org.gobinda.snapshot;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class SnapShot implements Runnable {

	private int ghumerTime = 0;
	private String computerName = null;

	private static int totalPhoto;
	private static boolean canContinue = false;

	public SnapShot(String computerName, int ghumerTime) {
		this.computerName = computerName;
		this.ghumerTime = ghumerTime;
		canContinue = true;
		totalPhoto = 0;
	}

	@Override
	public void run() {
		
		canContinue = true;
		while (canContinue) {
			String filePath = "C:/Users/" + computerName + "/Pictures/";
			filePath += new Date().toString().replaceAll(":", "_");
			filePath += ".jpg";

			File file = new File(filePath);
			try {
				Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
				BufferedImage capture = new Robot().createScreenCapture(screenRect);
				ImageIO.write(capture, "jpg", file);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Problme occurs.");
				return;
			}
			totalPhoto++;
			MainFrame.showInfo(String.valueOf(totalPhoto));
			ghumao(ghumerTime);
		}

	}

	private void ghumao(int ghumerTime2) {
		try {
			Thread.sleep(ghumerTime2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void setContinue(boolean canContinue) {
		SnapShot.canContinue = canContinue;
	}

}
