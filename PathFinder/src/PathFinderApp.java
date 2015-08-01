import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.Font;
import javax.swing.JTextField;

public class PathFinderApp extends JPanel {

	private JButton[] buttons = new JButton[25];
	private int levelCounter = 1;
	private int[] hiddenPath = new int[5];
	private int userGuess = 0;
	private int lives = 5;	
	private JPanel panel = this;

	public PathFinderApp() {
		
		//create & setup GUI
		setBackground(Color.BLACK);
		setLayout(null);

		JLabel lblStart = new JLabel("START");
		lblStart.setForeground(Color.WHITE);
		lblStart.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblStart.setHorizontalAlignment(SwingConstants.CENTER);
		lblStart.setBounds(293, 60, 63, 21);
		add(lblStart);

		JLabel lblFinish = new JLabel("FINISH");
		lblFinish.setHorizontalAlignment(SwingConstants.CENTER);
		lblFinish.setForeground(Color.WHITE);
		lblFinish.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblFinish.setBounds(293, 375, 63, 21);
		add(lblFinish);

		JLabel lblWelcomeToMinefield = new JLabel(
				"Welcome to MINEFIELD!! You need to navigate your way through the minefield without dying");
		lblWelcomeToMinefield.setForeground(Color.WHITE);
		lblWelcomeToMinefield.setBounds(37, 11, 529, 14);
		add(lblWelcomeToMinefield);

		JLabel lblChooseBox = new JLabel(
				"Choose 1 correct box per line, each subsequent correct box will be positioned below or diagonally to the previous box");
		lblChooseBox.setForeground(Color.WHITE);
		lblChooseBox.setBounds(10, 36, 668, 14);
		add(lblChooseBox);

		txtLives = new JTextField();
		txtLives.setHorizontalAlignment(SwingConstants.CENTER);
		txtLives.setFont(new Font("Tahoma", Font.BOLD, 20));
		txtLives.setForeground(Color.WHITE);
		txtLives.setBorder(null);
		txtLives.setBackground(Color.BLACK);
		txtLives.setEditable(false);
		txtLives.setBounds(37, 178, 49, 54);
		add(txtLives);
		txtLives.setColumns(10);

		JLabel lblLiveLeft = new JLabel("Lives left");
		lblLiveLeft.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblLiveLeft.setHorizontalAlignment(SwingConstants.CENTER);
		lblLiveLeft.setForeground(Color.WHITE);
		lblLiveLeft.setBounds(10, 133, 101, 29);
		add(lblLiveLeft);

		displayButtons();
		randomPath();
		txtLives.setText("" + lives);
		
		JButton btnNewButton = new JButton("NEW GAME");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lives = 5;
				for(int i = 0; i < buttons.length; ++i){
					buttons[i].setBackground(Color.BLACK);
					buttons[i].setForeground(Color.BLACK);
					txtLives.setText("" + lives);
				}
				randomPath();
				levelCounter = 1;
				userGuess = 0;
				
			}
		});
		btnNewButton.setBorder(null);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setBackground(Color.BLACK);
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBounds(487, 362, 107, 49);
		add(btnNewButton);
	}
	//setup button display
	public void displayButtons() {
		int x = 150;
		int y = 50;
		int width = 50;
		int height = 50;

		for (int i = 0; i < buttons.length; ++i) {
			if (i % 5 == 0) {
				y = y + height;
				x = 150;
			}
			x = x + width;
			buttons[i] = new JButton("" + i);
			buttons[i].setBounds(x, y, width, height);
			add(buttons[i]);
			buttons[i].addActionListener(myActionListener);
			buttons[i].setBackground(Color.BLACK);
			buttons[i].setForeground(Color.BLACK);
			buttons[i].setBorder(new LineBorder(Color.WHITE, 1, true));

		}

	}

	ActionListener myActionListener = new ActionListener() {
		

		@Override
		public void actionPerformed(ActionEvent event) {
			JLabel lblWin = new JLabel("Hooray!! You made it home from the war safely");
			JLabel lblLose = new JLabel("Woops, you were blown to pieces");
			
			//capture user guess as a string
			String buttonPressed = event.getActionCommand();
			
			//convert user guess to an integer
			int boxChoice = userChoice(buttonPressed);
			if (lives > 0) {
				//level 1
				//if user guess correct
				if (levelCounter == 1) {
					if (boxChoice == hiddenPath[userGuess]) {
						buttons[boxChoice].setBackground(Color.GREEN);
						buttons[boxChoice].setForeground(Color.GREEN);
						++levelCounter;
						++userGuess;
					}
					//if user guess incorrect
					else {
						buttons[boxChoice].setBackground(Color.RED);
						buttons[boxChoice].setForeground(Color.RED);
						--lives;
					}
				}
				//level 2
				//if correct user guess
				else if (levelCounter == 2) {
					if (boxChoice == hiddenPath[userGuess]) {
						buttons[boxChoice].setBackground(Color.GREEN);
						buttons[boxChoice].setForeground(Color.GREEN);
						++levelCounter;
						++userGuess;
					}
					//if incorrect user guess
					else {
						buttons[boxChoice].setBackground(Color.RED);
						buttons[boxChoice].setForeground(Color.RED);
						--lives;
					}
				}
				//level 3
				//if user guesses correctly
				else if (levelCounter == 3) {
					if (boxChoice == hiddenPath[userGuess]) {
						buttons[boxChoice].setBackground(Color.GREEN);
						buttons[boxChoice].setForeground(Color.GREEN);
						++levelCounter;
						++userGuess;
					}
					//if user has incorrect guess
					else {
						buttons[boxChoice].setBackground(Color.RED);
						buttons[boxChoice].setForeground(Color.RED);
						--lives;
					}					
				}
				//level 4
				//if user guesses correctly
				else if (levelCounter == 4) {
					if (boxChoice == hiddenPath[userGuess]) {
						buttons[boxChoice].setBackground(Color.GREEN);
						buttons[boxChoice].setForeground(Color.GREEN);
						++levelCounter;
						++userGuess;					
					}
					//if incorrect user guess
					else {
						buttons[boxChoice].setBackground(Color.RED);
						buttons[boxChoice].setForeground(Color.RED);
						--lives;
					}					
				}
				//level 5
				//if user guesses correctly
				else if (levelCounter == 5) {
					if (boxChoice == hiddenPath[userGuess]) {
						buttons[boxChoice].setBackground(Color.GREEN);
						buttons[boxChoice].setForeground(Color.GREEN);
						++levelCounter;
						++userGuess;
						JOptionPane.showMessageDialog(panel, lblWin);												
					}
					//if incorrect guess by user
					else {
						buttons[boxChoice].setBackground(Color.RED);
						buttons[boxChoice].setForeground(Color.RED);
						--lives;
					}

				}
				//if all lives lost
				if (lives == 0) {
					JOptionPane.showMessageDialog(panel, lblLose);
				}
				txtLives.setText("" + lives);
			}
		}

	};
	private JTextField txtLives;

	public void randomPath() {
		
		int[] level2a = { 5, 6 };
		int[] level2b = { 5, 6, 7 };
		int[] level2c = { 6, 7, 8 };
		int[] level2d = { 7, 8, 9 };
		int[] level2e = { 8, 9 };
		int[] level3a = { 10, 11 };
		int[] level3b = { 10, 11, 12 };
		int[] level3c = { 11, 12, 13 };
		int[] level3d = { 12, 13, 14 };
		int[] level3e = { 13, 14 };
		int[] level4a = { 15, 16 };
		int[] level4b = { 15, 16, 17 };
		int[] level4c = { 16, 17, 18 };
		int[] level4d = { 17, 18, 19 };
		int[] level4e = { 18, 19 };
		int[] level5a = { 20, 21 };
		int[] level5b = { 20, 21, 22 };
		int[] level5c = { 21, 22, 23 };
		int[] level5d = { 22, 23, 24 };
		int[] level5e = { 23, 24 };
		Random random = new Random();

		// level 1
		int randomPath = random.nextInt(5);
		hiddenPath[0] = randomPath;

		// hidden path for level 2
		if (hiddenPath[0] == 0) {
			randomPath = random.nextInt(2);
			hiddenPath[1] = level2a[randomPath];
		}
		if (hiddenPath[0] == 1) {
			randomPath = random.nextInt(3);
			hiddenPath[1] = level2b[randomPath];
		}
		if (hiddenPath[0] == 2) {
			randomPath = random.nextInt(3);
			hiddenPath[1] = level2c[randomPath];
		}
		if (hiddenPath[0] == 3) {
			randomPath = random.nextInt(3);
			hiddenPath[1] = level2d[randomPath];
		}
		if (hiddenPath[0] == 4) {
			randomPath = random.nextInt(2);
			hiddenPath[1] = level2e[randomPath];
		}

		// hidden path for level 3
		if (hiddenPath[1] == 5) {
			randomPath = random.nextInt(2);
			hiddenPath[2] = level3a[randomPath];
		}
		if (hiddenPath[1] == 6) {
			randomPath = random.nextInt(3);
			hiddenPath[2] = level3b[randomPath];
		}
		if (hiddenPath[1] == 7) {
			randomPath = random.nextInt(3);
			hiddenPath[2] = level3c[randomPath];
		}
		if (hiddenPath[1] == 8) {
			randomPath = random.nextInt(3);
			hiddenPath[2] = level3d[randomPath];
		}
		if (hiddenPath[1] == 9) {
			randomPath = random.nextInt(2);
			hiddenPath[2] = level3e[randomPath];
		}

		// hidden path for level 4
		if (hiddenPath[2] == 10) {
			randomPath = random.nextInt(2);
			hiddenPath[3] = level4a[randomPath];
		}
		if (hiddenPath[2] == 11) {
			randomPath = random.nextInt(3);
			hiddenPath[3] = level4b[randomPath];
		}
		if (hiddenPath[2] == 12) {
			randomPath = random.nextInt(3);
			hiddenPath[3] = level4c[randomPath];
		}
		if (hiddenPath[2] == 13) {
			randomPath = random.nextInt(3);
			hiddenPath[3] = level4d[randomPath];
		}
		if (hiddenPath[2] == 14) {
			randomPath = random.nextInt(2);
			hiddenPath[3] = level4e[randomPath];
		}

		// hidden path for level 5
		if (hiddenPath[3] == 15) {
			randomPath = random.nextInt(2);
			hiddenPath[4] = level5a[randomPath];
		}
		if (hiddenPath[3] == 16) {
			randomPath = random.nextInt(3);
			hiddenPath[4] = level5b[randomPath];
		}
		if (hiddenPath[3] == 17) {
			randomPath = random.nextInt(3);
			hiddenPath[4] = level5c[randomPath];
		}
		if (hiddenPath[3] == 18) {
			randomPath = random.nextInt(3);
			hiddenPath[4] = level5d[randomPath];
		}
		if (hiddenPath[3] == 19) {
			randomPath = random.nextInt(2);
			hiddenPath[4] = level5e[randomPath];
		}
		//used for testing
//		System.out.println(hiddenPath[0]);
//		System.out.println(hiddenPath[1]);
//		System.out.println(hiddenPath[2]);
//		System.out.println(hiddenPath[3]);
//		System.out.println(hiddenPath[4]);

	}
	//switch statement to find which box user selected - takes a string & gives back an integer
	public int userChoice(String str) {
		int boxSelected = 0;
		switch (str) {
		case "0":
			boxSelected = 0;
			break;
		case "1":
			boxSelected = 1;
			break;
		case "2":
			boxSelected = 2;
			break;
		case "3":
			boxSelected = 3;
			break;
		case "4":
			boxSelected = 4;
			break;
		case "5":
			boxSelected = 5;
			break;
		case "6":
			boxSelected = 6;
			break;
		case "7":
			boxSelected = 7;
			break;
		case "8":
			boxSelected = 8;
			break;
		case "9":
			boxSelected = 9;
			break;
		case "10":
			boxSelected = 10;
			break;
		case "11":
			boxSelected = 11;
			break;
		case "12":
			boxSelected = 12;
			break;
		case "13":
			boxSelected = 13;
			break;
		case "14":
			boxSelected = 14;
			break;
		case "15":
			boxSelected = 15;
			break;
		case "16":
			boxSelected = 16;
			break;
		case "17":
			boxSelected = 17;
			break;
		case "18":
			boxSelected = 18;
			break;
		case "19":
			boxSelected = 19;
			break;
		case "20":
			boxSelected = 20;
			break;
		case "21":
			boxSelected = 21;
			break;
		case "22":
			boxSelected = 22;
			break;
		case "23":
			boxSelected = 23;
			break;
		case "24":
			boxSelected = 24;
			break;

		}
		return boxSelected;
	}
}
