package exercises;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import examples.FileHelper;

public class Hangman extends KeyAdapter {

	Stack<String> puzzles = new Stack<String>();

	ArrayList<JLabel> boxes = new ArrayList<JLabel>();

	int lives = 9;

	JLabel livesLabel = new JLabel("" + lives);

	StringBuilder correctWords = new StringBuilder();


	characterException specialCharacters;
	

	public static void main(String[] args) {
		Hangman hangman = new Hangman();
		hangman.addPuzzles();
		hangman.createUI();
	}

	// Load puzzles
	private void addPuzzles() {
		for (String word : loadWords()) {
			puzzles.push(word.toLowerCase());
		}
	}

	JPanel panel = new JPanel();
	private String puzzle;

	private void createUI() {
//		playDeathKnell();
		JFrame frame = new JFrame("June's Hangman");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel.add(livesLabel);
		checkForValidPuzzle(); // this loads first puzzle in the list
		frame.add(panel);
		frame.setVisible(true);
		frame.pack();
		frame.addKeyListener(this);
	}

	private void loadCurrentPuzzle() {
		removeBoxes();
		lives = 9;
		livesLabel.setText("" + lives);
		puzzle = puzzles.peek();
		System.out.println("");
		System.out.println("New puzzle loaded: " + puzzle);
		createBoxes();
	}

	// THIS IS THE LOAD NEXT PUZZLE //
	private void loadNextPuzzle() throws characterException {
		removeBoxes();
		lives = 9;
		livesLabel.setText("" + lives);
		puzzle = puzzles.pop();
		puzzle = puzzles.peek();

		// throws an exception if an special character is found
		if (!puzzles.peek().matches("[A-Za-z]*"))
			throw specialCharacters = new characterException("puzzle \"" + puzzle
					+ characterException.message);

		puzzle = puzzles.peek();		
		System.out.println("");
		System.out.println("New puzzle loaded: " + puzzle);

		createBoxes();
	}

	public void keyTyped(KeyEvent arg0) {
		System.out.println(arg0.getKeyChar());
		updateBoxesWithUserInput(arg0.getKeyChar());

		if (lives == 0) {
			playDeathKnell();
			loadCurrentPuzzle();
		}

		// Check if typed letters are correct //
		if (puzzle.equalsIgnoreCase(correctWords.toString())) {
			lives = 9;
			checkForValidPuzzle();

		} // end of checking of correctWords

	}

	private void checkForValidPuzzle() {

		// Determines if need to loop more for a valid puzzle //
		boolean continueLooping = true;

		while (continueLooping) {

			try {
				loadNextPuzzle();

			} catch (characterException e) {
				// print warning of invalid puzzle //
				System.out.println(e);

			} finally {
				// if the new puzzle is valid, stop looping and use that puzzle //
				if (puzzles.peek().matches("[A-Za-z]*")) {
					continueLooping = false;

				}
			}
		} // end of while loop

	}

	private void updateBoxesWithUserInput(char keyChar) {
		boolean gotOne = false;
		correctWords.setLength(puzzle.length());

		for (int i = 0; i < puzzle.length(); i++) {

			if (puzzle.charAt(i) == keyChar) {

				boxes.get(i).setText("" + keyChar);
				gotOne = true;

				correctWords.setCharAt(i, keyChar);
			}
		}
		if (!gotOne)
			livesLabel.setText("" + --lives);
	}

	// The stuff after this line is not relevant //

	void createBoxes() {
		for (int i = 0; i < puzzle.length(); i++) {
			JLabel textField = new JLabel("_");
			boxes.add(textField);
			panel.add(textField);
		}
	}

	void removeBoxes() {
		for (JLabel box : boxes) {
			panel.remove(box);
		}
		boxes.clear();
	}

	public void playDeathKnell() {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("resource/funeral-march.wav"));
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
			Thread.sleep(8400);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public List<String> loadWords() {
		return FileHelper.loadFileContentsIntoArrayList("resource/words.txt");
	}

}
