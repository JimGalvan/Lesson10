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
	int gamesWon = 0;
	int opportunities = 3;


	public static void main(String[] args) {
		Hangman hangman = new Hangman();
		hangman.addPuzzles();
		hangman.createUI();
	}

	private void addPuzzles() {
		
	for (String word: loadWords()) {
		puzzles.push(word.toLowerCase());
	}
	}
	
	JPanel panel = new JPanel();
	private String puzzle;

	private void createUI() {
		playDeathKnell();
		JFrame frame = new JFrame("June's Hangman");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel.add(livesLabel);
		loadNextPuzzle();
		frame.add(panel);
		frame.setVisible(true);
		frame.pack();
		frame.addKeyListener(this);
	}

	private void loadNextPuzzle() {
		
		removeBoxes();
		lives = 9;
		livesLabel.setText("" + lives);
		
		if (puzzles.isEmpty()) {
			System.out.println("No more games, start again");
			  System.exit(0);
		} else {
			puzzle = puzzles.pop();
		}
		System.out.println("");
		System.out.println("New puzzle loaded");
		System.out.println("Games won: " + gamesWon);		
		System.out.println("You have: " + opportunities + " opportunities");


		createBoxes();
	}

	public void keyTyped(KeyEvent arg0) {
		System.out.println(arg0.getKeyChar());
		updateBoxesWithUserInput(arg0.getKeyChar());
		
		if (opportunities == 0) {
			System.out.println("You have no more opportunities");
			  System.exit(0);
		}
				
		if (lives == 7) {
			playDeathKnell();
			loadNextPuzzle();			
			System.out.println("You lost game: " + puzzle);

			opportunities--;
		} 
		else if (puzzle.equalsIgnoreCase(correctWords.toString())) {
			gamesWon++;
			
			if (puzzles.isEmpty()) {
				System.out.println("You won " + gamesWon + "games");
				  System.exit(0);
				  } 
			else loadNextPuzzle();	
			
			lives = 9;
		}
		
		
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




