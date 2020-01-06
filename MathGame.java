import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MathGame implements ActionListener{

	int WIN_WIDTH = 600;
	int WIN_HEIGHT = 400;

	String FONT = "Sans Serif";
	int TITLE_FONT_SIZE = 50;
	int TITLE_X = 120;
	int TITLE_Y = 50;
	int TITLE_SIZE_X = 350;
	int TITLE_SIZE_Y = 60;

	int START_FONT_SIZE = 20;
	int START_X = 200;
	int START_Y = 150;
	int START_SIZE_X = 150;
	int START_SIZE_Y = 50;

	int QUIT_FONT_SIZE = START_FONT_SIZE;
	int QUIT_X = 200;
	int QUIT_Y = 250;
	int QUIT_SIZE_X = START_SIZE_X;
	int QUIT_SIZE_Y = START_SIZE_Y;

	int READY_FONT_SIZE = START_FONT_SIZE;
	int READY_SIZE_X = START_SIZE_X;
	int READY_SIZE_Y = START_SIZE_Y;
	int READY_X = WIN_WIDTH - READY_SIZE_X;
	int READY_Y = WIN_HEIGHT - READY_SIZE_Y;

	int SELECT_FONT_SIZE = START_FONT_SIZE;
	int SELECT_X = 10;
	int SELECT_Y = 20;
	int SELECT_SIZE_X = 300;
	int SELECT_SIZE_Y = 60;

	int OP_FONT_SIZE = 15;
	int OP_X = 40;
	int OP_Y = 100;
	int OP_SIZE_X = 150;
	int OP_SIZE_Y = 50;
	// Operation checkbox properties
	int OP_BOX_FONT_SIZE = OP_FONT_SIZE;
	int OP_BOX_X = OP_X;
	int OP_BOX_SIZE_X = 150;
	int OP_BOX_SIZE_Y = 30;

	int ADD_BOX_Y = OP_Y + 50;
	int SUB_BOX_Y = ADD_BOX_Y + OP_BOX_SIZE_Y;
	int MULT_BOX_Y = SUB_BOX_Y + OP_BOX_SIZE_Y;
	int DIV_BOX_Y = MULT_BOX_Y + OP_BOX_SIZE_Y;
	int ALL_BOX_Y = DIV_BOX_Y + OP_BOX_SIZE_Y;

	int RANGE_FONT_SIZE = OP_FONT_SIZE;
	int RANGE_X = 350;
	int RANGE_Y = OP_Y;
	int RANGE_SIZE_X = OP_SIZE_X;
	int RANGE_SIZE_Y = OP_SIZE_Y;

	int EASY_HIGH = 10;
	int MEDIUM_HIGH = 20;
	int HARD_HIGH = 50;
	int PRO_HIGH = 100;
	// Range checkbox properties
	int RANGE_BOX_FONT_SIZE = OP_BOX_FONT_SIZE;
	int RANGE_BOX_X = RANGE_X;
	int RANGE_BOX_SIZE_X = OP_BOX_SIZE_X;
	int RANGE_BOX_SIZE_Y = OP_BOX_SIZE_Y;

	int EASY_BOX_Y = RANGE_Y + 50;
	int MEDIUM_BOX_Y = EASY_BOX_Y + RANGE_BOX_SIZE_Y;
	int HARD_BOX_Y = MEDIUM_BOX_Y + RANGE_BOX_SIZE_Y;
	int PRO_BOX_Y = HARD_BOX_Y + RANGE_BOX_SIZE_Y;

	int QUES_FONT_SIZE = SELECT_FONT_SIZE;
	int QUES_X = SELECT_X;
	int QUES_Y =  SELECT_Y;
	int QUES_SIZE_X = SELECT_SIZE_X;
	int QUES_SIZE_Y = SELECT_SIZE_Y;

	int CORRECT_FONT_SIZE = 15;
	int CORRECT_X = 450;
	int CORRECT_Y = QUES_Y;
	int CORRECT_SIZE_X = 300;
	int CORRECT_SIZE_Y = 50;

	int EQN_FONT_SIZE = 50;
	int EQN_X = 50;
	int EQN_Y = 120;
	int EQN_SIZE_X = 500;
	int EQN_SIZE_Y = 60;

	int ANS_FIELD_X = EQN_X + 350;
	int ANS_FIELD_Y = EQN_Y + 22;
	int ANS_FIELD_SIZE_X = 150;
	int ANS_FIELD_SIZE_Y = EQN_SIZE_Y;

	int ANS_LABEL_FONT_SIZE = 15;
	int ANS_LABEL_X = EQN_X + 125;
	int ANS_LABEL_Y = EQN_Y + 100;
	int ANS_LABEL_SIZE_X = 300;
	int ANS_LABEL_SIZE_Y = 70;

	int NEXT_FONT_SIZE = START_FONT_SIZE;
	int NEXT_SIZE_X = 180;
	int NEXT_SIZE_Y = 50;
	int NEXT_X = WIN_WIDTH - NEXT_SIZE_X;
	int NEXT_Y = WIN_HEIGHT - NEXT_SIZE_Y;

	int RESTART_SIZE_X = 180;
	int RESTART_SIZE_Y = 50;
	int RESTART_X = 0;
	int RESTART_Y = WIN_HEIGHT - RESTART_SIZE_Y;

	boolean addition, subtraction, multiplication, division, easyMode, medMode, hardMode, proMode;

	String[] operator;
	String displayOp;
	int quesNum = 0;
	int numCorrect = 0;
	int numIncorrect = 0;
	String userAns;
	int convertedAns;
	int num1, num2, randOp, tempNum;
	boolean validOp = false;
	boolean invalidDivision = false;

	JFrame frame = new JFrame("Math Training Game - James Cahyadi");
	MathGamePanel panel = new MathGamePanel();
	Timer timer = new Timer(1000/60, this);

	JButton startBut = new JButton("Start");
	JButton quitBut = new JButton("Quit"); 
	JButton readyBut = new JButton("Ready!");
	JButton nextBut = new JButton("Next Question");
	JButton restartBut = new JButton("Restart");

	JLabel titleLabel = new JLabel("Math Training");
	JLabel selectLabel = new JLabel("Choose your preferences:");
	JLabel operationsLabel = new JLabel("Operations:");
	JLabel numRangeLabel = new JLabel("Number Range:");
	JLabel ansLabel = new JLabel();
	JLabel quesLabel = new JLabel();
	JLabel correctLabel = new JLabel("Correct: " + numCorrect);
	JLabel incorrectLabel = new JLabel("Incorrect: " + numIncorrect);
	JLabel equationLabel = new JLabel();

	JTextField ansField = new JTextField();

	JCheckBox addBox = new JCheckBox("Addition", false);
	JCheckBox subBox = new JCheckBox("Subtraction", false);
	JCheckBox multBox = new JCheckBox("Multiplication", false);
	JCheckBox divBox = new JCheckBox("Division", false);
	JCheckBox allBox = new JCheckBox("All of the Above", false);
	JCheckBox easyBox = new JCheckBox("0 - " + EASY_HIGH + "   (Easy)", false);
	JCheckBox medBox = new JCheckBox("0 - " + MEDIUM_HIGH + "   (Medium)", false);
	JCheckBox hardBox = new JCheckBox("0 - " + HARD_HIGH + "   (Hard)", false);
	JCheckBox proBox = new JCheckBox("0 - " + PRO_HIGH + " (Pro)", false);

	public void actionPerformed(ActionEvent evt) {
		if(evt.getSource() == timer) {
			panel.repaint();
		}

		// Main menu start button
		if(evt.getSource() == startBut) {
			panel.removeAll();
			optionSelect();
		}

		// Main menu quit
		if(evt.getSource() == quitBut) {
			System.exit(0);
		}

		// Ready button to start game after selecting preferences
		if(evt.getSource() == readyBut) {
			if(addBox.isSelected()) {
				addition = true;
			}
			if(subBox.isSelected()) {
				subtraction = true;
			}
			if(multBox.isSelected()) {
				multiplication = true;
			}
			if(divBox.isSelected()) {
				division = true;
			}
			if(easyBox.isSelected()) {
				easyMode = true;
			} else if(medBox.isSelected()) {
				medMode = true;
			} else if(hardBox.isSelected()) {
				hardMode = true;
			} else if(proBox.isSelected()) {
				proMode = true;
			}
			panel.removeAll();
			startGame();
			ansField.grabFocus();
		}

		// Next Question Button
		if(evt.getSource() == nextBut) {
			panel.removeAll();
			quesNum++;
			startGame();
			ansField.grabFocus();
		}

		// Restart Button
		if(evt.getSource() == restartBut) {
			panel.removeAll();
			quesNum = 0;
			numCorrect = 0;
			numIncorrect = 0;
			correctLabel.setText("Correct: " + numCorrect);
			incorrectLabel.setText("Wrong: " + numIncorrect);
			optionSelect();
		}

		// To select all operations
		if(allBox.isSelected()) {
			addBox.setSelected(true);
			subBox.setSelected(true);
			multBox.setSelected(true);
			divBox.setSelected(true);
			addBox.setEnabled(false);
			subBox.setEnabled(false);
			multBox.setEnabled(false);
			divBox.setEnabled(false);
		} else { // Unselect all of the above option
			addBox.setEnabled(true);
			subBox.setEnabled(true);
			multBox.setEnabled(true);
			divBox.setEnabled(true);
		}

		// Only one range can be chosen
		if(evt.getSource() == easyBox) {
			if(easyBox.isSelected()) { // If one mode is selected, unselect other modes
				easyBox.setSelected(true);
				medBox.setSelected(false);
				hardBox.setSelected(false);
				proBox.setSelected(false);
			} else { // If it was already selected, unselect it
				easyBox.setSelected(false);
			}
		} else if(evt.getSource() == medBox) {
			if(medBox.isSelected()) {
				easyBox.setSelected(false);
				medBox.setSelected(true);
				hardBox.setSelected(false);
				proBox.setSelected(false);
			} else {
				medBox.setSelected(false);
			}
		} else if(evt.getSource() == hardBox) {
			if(hardBox.isSelected()) {
				easyBox.setSelected(false);
				medBox.setSelected(false);
				hardBox.setSelected(true);
				proBox.setSelected(false);
			} else {
				hardBox.setSelected(false);
			}
		} else if(evt.getSource() == proBox) {
			if(proBox.isSelected()) {
				easyBox.setSelected(false);
				medBox.setSelected(false);
				hardBox.setSelected(false);
				proBox.setSelected(true);
			} else {
				proBox.setSelected(false);
			}
		}

		// Enable the ready button only if one number range and at least one operation is chosen
		if((addBox.isSelected() || subBox.isSelected() || multBox.isSelected() || divBox.isSelected())
				&& (easyBox.isSelected() || medBox.isSelected() || hardBox.isSelected() || proBox.isSelected())) {
			readyBut.setEnabled(true);
		} else {
			readyBut.setEnabled(false);
		}

		// Textfield for when user answers
		if(evt.getSource() == ansField) {
			nextBut.setEnabled(true);
			ansField.setEnabled(false);
			panel.add(ansLabel);

			// Convert the textfield  answer into an integer
			try {
				convertedAns = Integer.parseInt(ansField.getText());
			} catch(NumberFormatException e) { // If no answer is given or user answer is not an integer, answer with -1
				convertedAns = -1;
			}

			if(displayOp.equals("+")) {
				if(num1 + num2 == convertedAns) {
					numCorrect++;
					ansLabel.setForeground(Color.GREEN);
					ansLabel.setText("Well done your answer is correct!");
				} else {
					numIncorrect++; 
					ansLabel.setForeground(Color.RED);
					ansLabel.setText("Incorrect! The answer is " + (num1 + num2) + ".");
				}
			} else if(displayOp.equals("-")) {
				if(num1 - num2 == convertedAns) {
					numCorrect++;
					ansLabel.setForeground(Color.GREEN);
					ansLabel.setText("Well done your answer is correct!");
				} else {
					numIncorrect++; 
					ansLabel.setForeground(Color.RED);
					ansLabel.setText("Incorrect! The answer is " + (num1 - num2) + ".");
				}
			}  else if(displayOp.equals("*")) {
				if(num1 * num2 == convertedAns) {
					numCorrect++;
					ansLabel.setForeground(Color.GREEN);
					ansLabel.setText("Well done your answer is correct!");
				} else {
					numIncorrect++; 
					ansLabel.setForeground(Color.RED);
					ansLabel.setText("Incorrect! The answer is " + (num1 * num2) + ".");
				}
			}  else if(displayOp.equals("/")) {
				if(num1 / num2 == convertedAns) {
					numCorrect++;
					ansLabel.setForeground(Color.GREEN);
					ansLabel.setText("Well done your answer is correct!");
				} else {
					numIncorrect++; 
					ansLabel.setForeground(Color.RED);
					ansLabel.setText("Incorrect! The answer is " + (num1 / num2) + ".");
				}
			}

			// Update correct and incorrect numbers
			correctLabel.setText("Correct: " + numCorrect);
			incorrectLabel.setText("Wrong: " + numIncorrect);
		}


	}

	public void optionSelect() {
		addition = false;
		subtraction = false;
		multiplication = false;
		division = false;
		easyMode = false;
		medMode = false;
		hardMode = false;
		proMode = false;

		addBox.setSelected(false);
		subBox.setSelected(false);
		multBox.setSelected(false);
		divBox.setSelected(false);
		allBox.setSelected(false);
		easyBox.setSelected(false);
		medBox.setSelected(false);
		hardBox.setSelected(false);
		proBox.setSelected(false);

		panel.add(selectLabel);		
		panel.add(operationsLabel);
		panel.add(addBox);		
		panel.add(subBox);		
		panel.add(multBox);		
		panel.add(divBox);	
		panel.add(allBox);
		panel.add(numRangeLabel);
		panel.add(easyBox);
		panel.add(medBox);
		panel.add(hardBox);
		panel.add(proBox);
		panel.add(readyBut);
	}

	public void startGame() {
		validOp = false;
		displayOp = getOperation();
		num1 = getNumber();
		num2 = getNumber();

		// For subtraction, num1 must be greater than num2 so that difference is positive
		if(displayOp.equals("-") && num1 < num2) {
			tempNum = num1;
			num1 = num2;
			num2 = tempNum;
		}

		if(displayOp.equals("/")) {
			invalidDivision = true;
		} else {
			invalidDivision = false;
		}

		while(invalidDivision) {
			// Prevent division by 0
			if(num2 == 0) {
				num2 = 1; 
			}			

			// Ensure num2 is a multiple of num1 for division
			if(num1 % num2 == 0) {
				invalidDivision = false;
			} else {
				num1 = getNumber();
				num2 = getNumber();
			}
		}


		equationLabel.setText(num1 + " " + displayOp + " " + num2 + " = ");
		quesLabel.setText("Question " + quesNum);

		panel.add(quesLabel);
		panel.add(correctLabel);
		panel.add(incorrectLabel);
		panel.add(equationLabel);
		panel.add(ansField);
		panel.add(nextBut);
		panel.add(restartBut);
		ansField.setEnabled(true);
		ansField.setText(null);
		nextBut.setEnabled(false);
	}

	public String getOperation() {
		// Exit loop when a user selected operator is chosen
		while(validOp == false) {
			randOp = (int) (Math.random() * 4); 

			if(randOp == 0 && addition
					|| randOp == 1 && subtraction
					|| randOp == 2 && multiplication
					|| randOp == 3 && division) {
				validOp = true;
			} 
		}
		return operator[randOp];
	}

	public int getNumber() {
		// Choose numbers based on chosen mode
		if(easyMode) {
			return (int)(Math.random() * (EASY_HIGH + 1));
		} else if(medMode) {
			return (int)(Math.random() * (MEDIUM_HIGH + 1));
		} else if(hardMode) {
			return (int)(Math.random() * (HARD_HIGH + 1));
		} else {
			return (int)(Math.random() * (PRO_HIGH + 1));
		}
	}

	public MathGame() {
		// Set up the 4 operators
		operator = new String[4];
		operator[0] = "+";
		operator[1] = "-";
		operator[2] = "*";
		operator[3] = "/";

		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(WIN_WIDTH, WIN_HEIGHT));

		// Main Menu components
		titleLabel.setSize(TITLE_SIZE_X, TITLE_SIZE_Y);
		titleLabel.setLocation(TITLE_X, TITLE_Y);
		titleLabel.setFont(new Font(FONT, Font.BOLD, TITLE_FONT_SIZE));
		panel.add(titleLabel);

		startBut.setSize(START_SIZE_X, START_SIZE_Y);
		startBut.setLocation(START_X, START_Y);
		startBut.setFont(new Font(FONT, Font.BOLD, START_FONT_SIZE));
		startBut.addActionListener(this);
		panel.add(startBut);

		quitBut.setSize(QUIT_SIZE_X, QUIT_SIZE_Y);
		quitBut.setLocation(QUIT_X, QUIT_Y);
		quitBut.setFont(new Font(FONT, Font.BOLD, QUIT_FONT_SIZE));
		quitBut.addActionListener(this);
		panel.add(quitBut);

		// Option Select Components
		selectLabel.setSize(SELECT_SIZE_X, SELECT_SIZE_Y);
		selectLabel.setLocation(SELECT_X, SELECT_Y);
		selectLabel.setFont(new Font(FONT, Font.BOLD, SELECT_FONT_SIZE));

		operationsLabel.setSize(OP_SIZE_X, OP_SIZE_Y);
		operationsLabel.setLocation(OP_X, OP_Y);
		operationsLabel.setFont(new Font(FONT, Font.BOLD, OP_FONT_SIZE));

		addBox.setSize(OP_BOX_SIZE_X, OP_BOX_SIZE_Y);
		addBox.setLocation(OP_BOX_X, ADD_BOX_Y);
		addBox.setFont(new Font(FONT, Font.BOLD, OP_BOX_FONT_SIZE));
		addBox.addActionListener(this);

		subBox.setSize(OP_BOX_SIZE_X, OP_BOX_SIZE_Y);
		subBox.setLocation(OP_BOX_X, SUB_BOX_Y);
		subBox.setFont(new Font(FONT, Font.BOLD, OP_BOX_FONT_SIZE));
		subBox.addActionListener(this);

		multBox.setSize(OP_BOX_SIZE_X, OP_BOX_SIZE_Y);
		multBox.setLocation(OP_BOX_X, MULT_BOX_Y);
		multBox.setFont(new Font(FONT, Font.BOLD, OP_BOX_FONT_SIZE));
		multBox.addActionListener(this);

		divBox.setSize(OP_BOX_SIZE_X, OP_BOX_SIZE_Y);
		divBox.setLocation(OP_BOX_X, DIV_BOX_Y);
		divBox.setFont(new Font(FONT, Font.BOLD, OP_BOX_FONT_SIZE));
		divBox.addActionListener(this);

		allBox.setSize(OP_BOX_SIZE_X, OP_BOX_SIZE_Y);
		allBox.setLocation(OP_BOX_X, ALL_BOX_Y);
		allBox.setFont(new Font(FONT, Font.BOLD,OP_BOX_FONT_SIZE));
		allBox.addActionListener(this);

		numRangeLabel.setSize(RANGE_SIZE_X, RANGE_SIZE_Y);
		numRangeLabel.setLocation(RANGE_X, RANGE_Y);
		numRangeLabel.setFont(new Font(FONT, Font.BOLD, RANGE_FONT_SIZE));

		easyBox.setSize(RANGE_BOX_SIZE_X, RANGE_BOX_SIZE_Y);
		easyBox.setLocation(RANGE_BOX_X, EASY_BOX_Y);
		easyBox.setFont(new Font(FONT, Font.BOLD, RANGE_BOX_FONT_SIZE));
		easyBox.addActionListener(this);

		medBox.setSize(RANGE_BOX_SIZE_X, RANGE_BOX_SIZE_Y);
		medBox.setLocation(RANGE_BOX_X, MEDIUM_BOX_Y);
		medBox.setFont(new Font(FONT, Font.BOLD, RANGE_BOX_FONT_SIZE));
		medBox.addActionListener(this);

		hardBox.setSize(RANGE_BOX_SIZE_X, RANGE_BOX_SIZE_Y);
		hardBox.setLocation(RANGE_BOX_X, HARD_BOX_Y);
		hardBox.setFont(new Font(FONT, Font.BOLD, RANGE_BOX_FONT_SIZE));
		hardBox.addActionListener(this);

		proBox.setSize(RANGE_BOX_SIZE_X, RANGE_BOX_SIZE_Y);
		proBox.setLocation(RANGE_BOX_X, PRO_BOX_Y);
		proBox.setFont(new Font(FONT, Font.BOLD, RANGE_BOX_FONT_SIZE));
		proBox.addActionListener(this);

		readyBut.setSize(READY_SIZE_X, READY_SIZE_Y);
		readyBut.setLocation(READY_X, READY_Y);
		readyBut.setFont(new Font(FONT, Font.BOLD, READY_FONT_SIZE));
		readyBut.addActionListener(this);

		// Game start components
		quesLabel.setSize(QUES_SIZE_X, QUES_SIZE_Y);
		quesLabel.setLocation(QUES_X, QUES_Y);
		quesLabel.setFont(new Font(FONT, Font.BOLD, QUES_FONT_SIZE));

		correctLabel.setSize(CORRECT_SIZE_X, CORRECT_SIZE_Y);
		correctLabel.setLocation(CORRECT_X, CORRECT_Y);
		correctLabel.setFont(new Font(FONT, Font.BOLD, CORRECT_FONT_SIZE));

		incorrectLabel.setSize(CORRECT_SIZE_X, CORRECT_SIZE_Y);
		incorrectLabel.setLocation(CORRECT_X, CORRECT_Y + 20);
		incorrectLabel.setFont(new Font(FONT, Font.BOLD, CORRECT_FONT_SIZE));

		equationLabel.setSize(EQN_SIZE_X, EQN_SIZE_Y);
		equationLabel.setLocation(EQN_X, EQN_Y + 20);
		equationLabel.setFont(new Font(FONT, Font.BOLD, EQN_FONT_SIZE));

		ansField.setSize(ANS_FIELD_SIZE_X, ANS_FIELD_SIZE_Y);
		ansField.setLocation(ANS_FIELD_X, ANS_FIELD_Y);
		ansField.setFont(new Font(FONT, Font.BOLD, EQN_FONT_SIZE));
		ansField.addActionListener(this);

		nextBut.setSize(NEXT_SIZE_X, NEXT_SIZE_Y);
		nextBut.setLocation(NEXT_X, NEXT_Y);
		nextBut.setFont(new Font(FONT, Font.BOLD, NEXT_FONT_SIZE));
		nextBut.addActionListener(this);

		restartBut.setSize(RESTART_SIZE_X, RESTART_SIZE_Y);
		restartBut.setLocation(RESTART_X, RESTART_Y);
		restartBut.setFont(new Font(FONT, Font.BOLD, NEXT_FONT_SIZE));
		restartBut.addActionListener(this);

		ansLabel.setSize(ANS_LABEL_SIZE_X, ANS_LABEL_SIZE_Y);
		ansLabel.setLocation(ANS_LABEL_X, ANS_LABEL_Y);
		ansLabel.setFont(new Font(FONT, Font.BOLD, ANS_LABEL_FONT_SIZE));

		frame.setContentPane(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setFocusable(true);
		panel.setFocusable(true);

		timer.start();
	}

	public static void main(String[] args) {
		new MathGame();

	}
}
