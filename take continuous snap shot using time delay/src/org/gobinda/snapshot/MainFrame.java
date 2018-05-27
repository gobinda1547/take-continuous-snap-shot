package org.gobinda.snapshot;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private static Thread photoTakingThread;
	private JPanel contentPane;
	private JTextField ghumerTimeTextField;
	private static JLabel totalPhotoCounterLabel;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 401, 441);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("TAKE CONTINUES SNAP SHOT");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(10, 26, 365, 35);
		contentPane.add(lblNewLabel);

		JLabel lblAfter = new JLabel("AFTER");
		lblAfter.setHorizontalAlignment(SwingConstants.CENTER);
		lblAfter.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblAfter.setBounds(10, 72, 365, 35);
		contentPane.add(lblAfter);

		ghumerTimeTextField = new JTextField();
		ghumerTimeTextField.setFont(new Font("Tahoma", Font.BOLD, 18));
		ghumerTimeTextField.setHorizontalAlignment(SwingConstants.CENTER);
		ghumerTimeTextField.setBounds(88, 118, 202, 41);
		contentPane.add(ghumerTimeTextField);
		ghumerTimeTextField.setColumns(10);

		JLabel lblMs = new JLabel("MS");
		lblMs.setHorizontalAlignment(SwingConstants.CENTER);
		lblMs.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblMs.setBounds(10, 170, 365, 35);
		contentPane.add(lblMs);

		JButton btnNewButton = new JButton("START");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String computerName = getComputerName();
				int ghumerTime = Integer.parseInt(ghumerTimeTextField.getText());
				photoTakingThread = new Thread(new SnapShot(computerName, ghumerTime));
				photoTakingThread.start();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton.setBounds(59, 234, 269, 53);
		contentPane.add(btnNewButton);

		JButton btnStop = new JButton("STOP");
		btnStop.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				photoTakingThread.stop();
				SnapShot.setContinue(false);
			}
		});
		btnStop.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnStop.setBounds(59, 298, 269, 53);
		contentPane.add(btnStop);

		totalPhotoCounterLabel = new JLabel("0");
		totalPhotoCounterLabel.setHorizontalAlignment(SwingConstants.CENTER);
		totalPhotoCounterLabel.setBounds(10, 362, 54, 29);
		contentPane.add(totalPhotoCounterLabel);

		setLocationRelativeTo(null);
	}

	private static String getComputerName() {
		return System.getProperty("user.name");
	}

	public static void showInfo(String information) {
		totalPhotoCounterLabel.setText(information);
	}
}
