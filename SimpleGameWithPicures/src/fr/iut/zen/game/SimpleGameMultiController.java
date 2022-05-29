package fr.iut.zen.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.geom.Point2D;
import java.io.IOException;

import fr.umlv.zen5.Application;
import fr.umlv.zen5.ApplicationContext;
import fr.umlv.zen5.Event;
import fr.umlv.zen5.ScreenInfo;

public class SimpleGameMultiController { //Our Main class!
	
	private final SimpleGameData data = new SimpleGameData(12, 21);
	private Dimension size= Toolkit.getDefaultToolkit().getScreenSize();
	private final SimpleGameView view = SimpleGameView.initGameGraphics(0, 50, (int) (size.getWidth()/2.5), data);
	private final TimeData timeData = new TimeData();
	private final static int USER_ACTION_DELAY = 200; // attention, ne doit pas dépasser BOB_DELAY

	
	
	/**
	 * Prints the screen informations on the console (size of the screen : width x height ) 
	 */
	private static void printScreenInfo(ApplicationContext context) {
		ScreenInfo screenInfo = context.getScreenInfo();
		float width = screenInfo.getWidth();
		float height = screenInfo.getHeight();
		System.out.println("size of the screen (%.0f x %.0f)".formatted(width, height));
	}

	
	/**
	 * Execute an action based on the pressed key 
	 * @throws IOException 
	 */
	@SuppressWarnings("static-access")
	private void doKeyAction(ApplicationContext context, Event event) {
		switch (event.getKey()) {
		case SPACE -> {
			System.out.println("End of the Game");
			context.exit(0);
			throw new AssertionError("should not happen");
		}
		case S -> {//the playing enters in plannification mode
			data.startPlannificationMode();
			//view.draw(context, data, timeData);
			timeData.stop();
		}
		case D -> {//the playing returns in adventure mode
			data.stopPlannificationMode();
			timeData.start();
		}
		case L -> {//the game is being saved 
			System.out.println("Saving the game...");
			data.saveTheGame();
		}
		case R -> {//the last saved game is loaded
			System.out.println("Reloading the game...");
			data.reloadTheGame();
		}
		case LEFT -> { //decrease the hero's speed
			TimeData.decreaseSpeed();
		}
		case RIGHT -> {//increase the hero's speed
			TimeData.increaseSpeed();;
		}
		default -> System.out.println("Inactive Key : " + event.getKey());
		}
	}
	
	
	
	private void doMouseAction(ApplicationContext context, Event event) {
		if (!data.hasASelectedCell()) { // no cell is selected
			Point2D.Float location = event.getLocation();
			//if (view.checkXY(location)) {
				data.selectCell(view.lineFromY(location.y), view.columnFromX(location.x));
				
			//}
		} else {
			data.unselect();
		}
	}

	/**
	 * Draws elements of the game after moving Bob + resets time elements
	 */
	private void doBobActionAndDraw(ApplicationContext context) {
		if (timeData.elapsedBob() >= TimeData.BOB_DELAY) {
			data.moveBob();
			view.draw(context, data, timeData);
			timeData.resetElapsedBob();
			timeData.resetElapsedTotal();
			
		}
	}

	@SuppressWarnings("incomplete-switch")
	private void doEventActionAndDraw(ApplicationContext context) {
		Event event = context.pollOrWaitEvent(USER_ACTION_DELAY);
		if (event == null) { // no event
			return;
		}

		switch (event.getAction()) {
		case KEY_PRESSED:
			doKeyAction(context, event);
			break;
		case POINTER_DOWN:
			if (timeData.stopped()) {
				doMouseAction(context, event);
				
			}
			break;
		}
		view.draw(context, data, timeData);
	}

	
	private void simpleGame(ApplicationContext context) { // the type of method that takes run() in parameters
		printScreenInfo(context);

		data.setRandomMatrix();
		view.draw(context, data, timeData);

		while (true) {
			doBobActionAndDraw(context);
			doEventActionAndDraw(context);
		}
	}

	
	
	public static void main(String[] args) {
		// pour changer de jeu, remplacer stupidGame par le nom de la méthode de jeu
		// (elle doit avoir extactement la même en-tête).

		SimpleGameMultiController controller = new SimpleGameMultiController();
		Application.run(Color.black, controller::simpleGame); // use of lambda function
		System.out.println("should not show");
	}
}
