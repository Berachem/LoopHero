package fr.iut.zen.game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.Toolkit;
import java.awt.desktop.ScreenSleepEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Ellipse2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.concurrent.TimeUnit;
import java.awt.image.*;

import javax.imageio.ImageIO;

import fr.iut.zen.game.elements.cards.Card;
import fr.iut.zen.game.elements.enemies.Mobs;
import fr.iut.zen.game.elements.equipments.Armor;
import fr.iut.zen.game.elements.equipments.Equipment;
import fr.iut.zen.game.elements.tiles.Tile;

public record SimpleGameView(int xOrigin, int yOrigin, int length, int width, int squareSize) implements GameView {
	
	private static Dimension size= Toolkit.getDefaultToolkit().getScreenSize();
	
	public static SimpleGameView initGameGraphics(int xOrigin, int yOrigin, int length, SimpleGameData data) {
		int squareSize = (int) (length * 1.0 / data.nbLines());
		
		return new SimpleGameView(xOrigin, yOrigin, length, data.nbColumns() * squareSize, squareSize);
	}

	public boolean checkXY(Point2D.Float location) {
		return location.x >= xOrigin && location.x < width + xOrigin && location.y >= yOrigin
				&& location.y < length + yOrigin;
	}
	

	private int indexFromReaCoord(float coord, int origin) {
		return (int) ((coord - origin) / squareSize);
	}

	/**
	 * Transforms a real y-coordinate into the index of the corresponding line.
	 * 
	 * @param y a float y-coordinate
	 * @return the index of the corresponding line.
	 */
	public int lineFromY(float y) {
		return indexFromReaCoord(y, yOrigin);
	}

	/**
	 * Transforms a real x-coordinate into the index of the corresponding column.
	 * 
	 * @param x a float x-coordinate
	 * @return the index of the corresponding column.
	 */
	public int columnFromX(float x) {
		return indexFromReaCoord(x, xOrigin);
	}

	private float realCoordFromIndex(int index, int origin) {
		return origin + index * squareSize;
	}

	private float xFromColumn(int column) {
		return realCoordFromIndex(column, xOrigin);
	}

	private float yFromLine(int line) {
		return realCoordFromIndex(line, yOrigin);
	}

	////////////////////////////// drawing methods //////////////////////////////
	private void drawSelectedCell(Graphics2D graphics, int line, int column) {
		String pictureName = "pictures/click.png";
		Path selectedPATH = Path.of(pictureName);
		drawImage(graphics, line, column, selectedPATH);
		
		/*
		float x = xFromColumn(column);
		float y = yFromLine(line);
		graphics.setColor(Color.ORANGE);
		graphics.fill(new Rectangle2D.Float(x, y, squareSize, squareSize));
		graphics.setColor(Color.LIGHT_GRAY);
		graphics.fill(new Rectangle2D.Float(x + 2, y + 2, squareSize - 4, squareSize - 4));
		*/
	}
	

	/**
	 * Draws Bob, draws crossed sword if Bob is fighting against a Mob
	 */
	private void drawBob(Graphics2D graphics, SimpleGameData data) {
		GridPosition position = data.bob();
		if (!data.isBobOnMobCell()) {
			Path heroPATH = Path.of(data.getHero().getImagePath());
			drawImage(graphics, position.line(), position.column(), heroPATH);
		}else {
			Path heroPATH = Path.of("pictures/fight.png");
			drawImage(graphics, position.line(), position.column(), heroPATH);
		}
		
	}

	
	
	
	/**
	 * Loads an image and then draws it in the given location based on the parameters line and column
	 * @param line + column the coordinates of the image 
	 * @param path the path of the image
	 * @throws RuntimeEsception if the path couldn't be found or the image can't be loaded 
	 */
	private void drawImage(Graphics2D graphics, int line, int column, Path path) {
		try (InputStream in = Files.newInputStream(path)) {
			BufferedImage img = ImageIO.read(in);
			AffineTransformOp scaling = new AffineTransformOp(AffineTransform
					.getScaleInstance(squareSize / (double) img.getWidth(), squareSize / (double) img.getHeight()),
					AffineTransformOp.TYPE_BILINEAR);
			graphics.drawImage(img, scaling, xOrigin + column * squareSize, yOrigin + line * squareSize);
		} catch (IOException e) {
			throw new RuntimeException("Problème d'affichage : " + path.getFileName());
		}
	}
	
	
	/**draws an image with the given coordinates
	 * @param x & y the coordinates 
	 * @param path the path of the image 
	 */
	private void drawImageByPixel(Graphics2D graphics, int x, int y, String path) {
		String pictureName = path;
		Path PathImage = Path.of(pictureName);
		try (InputStream in = Files.newInputStream(PathImage)) {
			BufferedImage img = ImageIO.read(in);
			AffineTransformOp scaling = new AffineTransformOp(AffineTransform
					.getScaleInstance(squareSize / (double) img.getWidth(), squareSize / (double) img.getHeight()),
					AffineTransformOp.TYPE_BILINEAR);
			graphics.drawImage(img, scaling, x, y);
		} catch (IOException e) {
			throw new RuntimeException("problème d'affichage : " + PathImage.getFileName());
		}
	}
	
	

	/**function made especially to draw cards
	 * @param line line of the grid
	 * @param column column of the grid
	 * @param path the path of the image
	 */
	private void drawImageCard(Graphics2D graphics, int line, int column, Path path) {
		try (InputStream in = Files.newInputStream(path)) {
			BufferedImage img = ImageIO.read(in);
			AffineTransformOp scaling = new AffineTransformOp(AffineTransform
					.getScaleInstance(82 / (double) img.getWidth(), 120 / (double) img.getHeight()),
					AffineTransformOp.TYPE_BILINEAR);
			graphics.drawImage(img, scaling, xOrigin + column * squareSize, yOrigin + line * squareSize);
		} catch (IOException e) {
			throw new RuntimeException("Problème d'affichage : " + path.getFileName());
		}
	}
	
	
	/**draws the time bar representing a whole day
	 * @param width the width of the time bar
	 * @param timeFraction is the fraction used to fill the bar (the bar will seem to advance each time this parameter will be refreshed
	 */
	private void drawBar(Graphics2D graphics,SimpleGameData data, int width, double timeFraction) {
		graphics.setFont(new Font("Dialog", Font.BOLD, 25));
		
		graphics.setColor(Color.LIGHT_GRAY);
		graphics.fill(new Rectangle2D.Double(xOrigin, yOrigin - 20, width, 10));
		
		if (data.isPlannificationMode()) {
			graphics.setColor(Color.RED);
			//draws the pause image
			Path path = Path.of("pictures/pause.png");

			try (InputStream in = Files.newInputStream(path)) {
				BufferedImage img = ImageIO.read(in);
				AffineTransformOp scaling = new AffineTransformOp(AffineTransform
						.getScaleInstance(0.75 , 0.75),
						AffineTransformOp.TYPE_BILINEAR);
				graphics.drawImage(img, scaling, xOrigin + width/2-25,yOrigin-45);
			} catch (IOException e) {
				throw new RuntimeException("Problème d'affichage : " + path.getFileName());
			}
			
			graphics.drawString("Paused", width/2, yOrigin-25); 
		}else {
			
			graphics.clearRect(0, 0, 1200, yOrigin-20); // Erase the word "pause"
			graphics.setColor(Color.ORANGE);
		}
		graphics.setStroke(new BasicStroke(1));
		graphics.fill(new Rectangle2D.Double(xOrigin, yOrigin - 20, width * timeFraction, 10));
		graphics.setColor(Color.white);
		graphics.draw(new Rectangle2D.Double(xOrigin, yOrigin - 20, width, 10));
	}
	
	
	
	private void drawHandContainer(Graphics2D graphics) {
        graphics.setColor(Color.black);
        graphics.fill(new Rectangle2D.Double(0,length+40, 1600 , 400));
    }
	
	
	
	/**
	 * @param Scale the wanted scaling of the image
	 * @param x coordinate x of the image
	 * @param y coordinate y of the image
	 */
	private void DrawImagebyXandY(Graphics2D graphics, String stringPath, double Scale, int x, int y ) {
		Path path = Path.of(stringPath);
		try (InputStream in = Files.newInputStream(path)) {
			BufferedImage img = ImageIO.read(in);
			AffineTransformOp scaling = new AffineTransformOp(AffineTransform
					.getScaleInstance(Scale, Scale),
					AffineTransformOp.TYPE_BILINEAR);
			graphics.drawImage(img, scaling, x,y );

		} catch (IOException e) {
			throw new RuntimeException("Problème d'affichage : " + path.getFileName());
			
		}
	}
	

	////////////////////////////////////////////////////////////////////////////

	/**
	 * Draws the game board from its data, using an existing Graphics2D object.
	 * 
	 * @param graphics a Graphics2D object provided by the default method
	 *                 {@code draw(ApplicationContext, GameData)}
	 * @param data     the GameData containing the game data.
	 */
	@Override
	public void draw(Graphics2D graphics, SimpleGameData data, TimeData timeData) {
		
		
		drawBar(graphics,data, data.nbColumns() * squareSize, timeData.timeFraction());

		//draws dirt tiles where cells are empty
		String pictureName = "pictures/dirt.jpg";
		Path dirtPATH = Path.of(pictureName);
		drawRestOfTheMap(graphics, data, data.nbLines(), data.nbColumns(),dirtPATH);
		
		// draws a grid
		//drawGrid(graphics, data.nbLines(), data.nbColumns()); 
		

		//draws the loop
		drawPath(graphics, data);

		
		
		
		// draws an arrow on the selected cell
		GridPosition position = data.getSelected();
		if (position != null) {
			drawSelectedCell(graphics, position.line(), position.column());
		}
		
		// AFFICHAGE DES LOGS DU COMBAT
		//drawFightInfos(graphics,data);
		
		drawBob(graphics, data);
		
		drawGameInfos(graphics, data);
		drawListResources(graphics, data);
		
		drawLogo(graphics,data);
		
		//drawInventoryContainer(graphics);
		drawEquipment(graphics, data);
		//drawGridEquip(graphics);
		
		drawInventory(graphics, data);
		
		if (data.getSelectedCard() != null) { // player has a card selected
			drawAvailablesTilesPositions(graphics, data);
		}
		if (data.getSelectedEquipment() != null) {// player has a equipment selected
			drawAvailableSpotEquipment(graphics, data);
		}
		
		if (data.isBobOnMobCell()) {
			drawFight(graphics, data);
			
		}else {
			System.out.println("---------");
		}

		if (!data.getHero().isAlive()) {
			graphics.setFont(new Font("Dialog", Font.BOLD, 300));
		    graphics.setColor(Color.red);
	    	graphics.drawString("DEAD",xFromColumn(3), yFromLine((7)));
		}
		
		//draws the card in the Hero's hand
		drawCards(graphics, data);
	}
		

		
		
		

		
	/**
	 * Draws a window when the hero is fighting against a monster, showing the hero, the monsters in battle and their  statistics
	 */
	private void drawFight(Graphics2D graphics, SimpleGameData data) {
		
			
			graphics.setFont(new Font("Dialog", Font.BOLD, 45));
		    graphics.setColor(Color.DARK_GRAY);
		    graphics.fill(new Rectangle(xOrigin, yOrigin, width, length));
		    
		    graphics.setColor(new Color(153,102,0));
		    graphics.drawString("Fight !", (int) (width/2.5), yOrigin+90);
		    
		    graphics.setFont(new Font("Dialog", Font.BOLD, 15));
		    graphics.setColor(Color.red);
	    	graphics.drawString("HP: "+ Math.round(data.getHero().getHp()), xFromColumn((5))-15, yFromLine(6)-10);
	    	graphics.setColor(Color.magenta);
	    	graphics.drawString("Damages: "+ Math.round(data.getHero().attack()), xFromColumn((5))-15, yFromLine(6)+20);
	    	graphics.setColor(Color.green);
	    	graphics.drawString("Defense: "+ Math.round(data.getHero().getHerostats().getDefense()), xFromColumn((5))-15, yFromLine(7)-10);
	    	graphics.setColor(Color.ORANGE);
	    	graphics.drawString("Evade: "+ Math.round(data.getHero().getHerostats().getEvade()), xFromColumn((5))-15, yFromLine(7)+20);
	    	
	    	

		    drawImage(graphics, 5,6, Path.of(data.getHero().getImagePath()));
		    
		    int cpt = 0;
		    int basePlacement = 3;
		    if (data.getMobOnBobCell().size()==1) {
		    	basePlacement = 5;
		    }
		    for (Mobs m : data.getMobOnBobCell()) {
		    	if (cpt==data.getMobFightTarget()) {
		    		drawImage(graphics, basePlacement,9, Path.of("pictures/a.png"));
		    	}
		    	drawImage(graphics, basePlacement,10, Path.of(m.getImagePath()));
		    	graphics.setFont(new Font("Dialog", Font.BOLD, 15));
		    	
		    	
		    	
		    	graphics.setColor(Color.red);
		    	graphics.drawString("HP: "+ Math.round(m.getHp()), xFromColumn(12), yFromLine(basePlacement));
		    	graphics.setColor(Color.magenta);
		    	graphics.drawString("Damages: "+ Math.round(m.getStats().getDamage()), xFromColumn(12), yFromLine(basePlacement+1));
		    	graphics.setColor(Color.green);
		    	graphics.drawString("Defense: "+ Math.round(m.getStats().getDefense()), xFromColumn(12), yFromLine(basePlacement+2));
		    	graphics.setColor(Color.ORANGE);
		    	graphics.drawString("Evade: "+ Math.round(m.getStats().getEvade()), xFromColumn(12), yFromLine(basePlacement+3));
		    	
		    	
		    	basePlacement+=4;
		    	cpt++;
		    }
		    drawFightInfos(graphics, data);
	}
	

	/**
	 * Is called in the function drawFight(Graphics2D graphics, SimpleGameData data), draws a type of console that shows the battle information in real time
	 */
	private void drawFightInfos(Graphics2D graphics, SimpleGameData data) {
		int deca = 0;
		
		graphics.setFont(new Font("Dialog", Font.BOLD, 20));
		graphics.setColor(Color.yellow);
		graphics.drawString("Console ", width-400, length-30+deca);
		graphics.setFont(new Font("Dialog", Font.BOLD, 10));
		for (String s : data.getFightInfo()) {
			Calendar now = Calendar.getInstance();
			int minute = now.get(Calendar.MINUTE);
			int second = now.get(Calendar.SECOND);
			graphics.drawString("["+minute+":"+second+"] "+s+"...", width-400, length-10+deca);
			
			System.out.println("INFO : "+s);
			deca+=20;
	}







	}

	
	private void drawLogo(Graphics2D graphics, SimpleGameData data) {
		String logoName = "pictures/loop-bob.png";
		Path logo = Path.of(logoName);
		try (InputStream in = Files.newInputStream(logo)) {
			BufferedImage img = ImageIO.read(in);
			AffineTransformOp scaling = new AffineTransformOp(AffineTransform
					.getScaleInstance(0.5 , 0.5),
					AffineTransformOp.TYPE_BILINEAR);
			graphics.drawImage(img, scaling, size.width-100,size.height-100);
		} catch (IOException e) {
			throw new RuntimeException("Problème d'affichage : " + logo.getFileName());
			
		}
	}

	
	/**
	 * Draws only the cell specified by the given coordinates in the game board from
	 * its data, using an existing Graphics2D object.
	 * 
	 * @param graphics a Graphics2D object provided by the default method
	 *                 {@code draw(ApplicationContext, GameData)}
	 * @param data     the GameData containing the game data.
	 * @param x        the float x-coordinate of the cell.
	 * @param y        the float y-coordinate of the cell.
	 */
	@Override
	public void drawOnlyOneCell(Graphics2D graphics, SimpleGameData data, int x, int y) {

			graphics.setColor(Color.white);
			graphics.fill(new Rectangle2D.Float(x, y, 10, 10));
		
	}
	
	public void drawIntro(Graphics2D graphics, SimpleGameData data) {
		drawLogo(graphics, data);
	}
	
	
	/**
	 * draws the entire loop and everything that are on the path (Campfire, Mobs, Tiles) 
	 */
	public void drawPath(Graphics2D graphics, SimpleGameData data) {
		String pictureName = "pictures/oui.png";
		Path pathPATH = Path.of(pictureName);
		
		for(GridPosition p: data.getPath()) {
			drawImage(graphics, p.line(),p.column(), pathPATH);
			
		}
		drawCampFire(graphics,data);
		drawTiles(graphics, data);
		drawMobs(graphics,data);
		
	}
	
	
	//draws a Monster
	public void drawMobs(Graphics2D graphics, SimpleGameData data) {
		for(Mobs m: data.getMobsOnthePath()) {
			String pictureName = m.getImagePath();
			Path pathPATH = Path.of(pictureName);
			drawImage(graphics, m.getPosition().line(),m.getPosition().column(), pathPATH);
			if (data.getQuestMobTarget()!=null && data.getQuestMobTarget().equals(m)) {
				drawImage(graphics, m.getPosition().line(),m.getPosition().column(), Path.of("pictures/danger.jpg"));
			}
			//drawImage(graphics, m.getPosition().line()-1,m.getPosition().column(), Path.of("pictures/hpImg.png"));
			//graphics.drawString("    "+m.getHp(),xFromColumn(m.getPosition().column())+25, yFromLine(m.getPosition().line()-1));
			
		}
	}
	
	//Draws a tile
	public void drawTiles(Graphics2D graphics,SimpleGameData data) {
		for(Tile t: data.getPlacedTiles()) {
			String pictureName = t.getImagePath();
			//System.out.println(pictureName);
			Path pathPATH = Path.of(pictureName);
			drawImage(graphics, t.getPosition().line(),t.getPosition().column(), pathPATH);
		}
	}
	
	
	//draws the whole Hero's Hand up to thirteen cards
	public void drawCards(Graphics2D graphics, SimpleGameData data) {
		drawHandContainer(graphics);
		graphics.setFont(new Font("Times New Roman", Font.BOLD, 36));
		graphics.setColor(Color.white);
		graphics.drawString("Cards", xFromColumn(0), yFromLine(13)-10);
		
		if (data.getHero().getHand().getList().isEmpty()) {
			String pictureName = "pictures/nocards.png";
			Path pathPATH = Path.of(pictureName);
			
			drawImage(graphics, 13, 1, pathPATH);
		}else {
		
		int decal = 0;
		for(Card c: data.getHero().getCardsList()) {
			
			String pictureName = c.getImagePath();
			Path pathPATH = Path.of(pictureName);
			drawImageCard(graphics, 13, decal, pathPATH);
			decal += 2;
		}
	}
}
	
	
	//draws dirt tiles where cells are empty
	public void drawRestOfTheMap(Graphics2D graphics, SimpleGameData data, int nbLines, int nbColumns, Path dirtPATH ) {

		
		try (InputStream in = Files.newInputStream(dirtPATH)) {
			BufferedImage img = ImageIO.read(in);
			AffineTransformOp scaling = new AffineTransformOp(AffineTransform
					.getScaleInstance(squareSize / (double) img.getWidth(), squareSize / (double) img.getHeight()),
					AffineTransformOp.TYPE_BILINEAR);
			for (int i = 0; i < nbLines; i++) {
				for (int j = 0; j < nbColumns; j++) {
					if (!(data.getPath().contains(new GridPosition(i,j)))) {
						graphics.drawImage(img, scaling, xOrigin + j * squareSize, yOrigin + i * squareSize);
						
					}
				}
			}
			
		} catch (IOException e) {
			throw new RuntimeException("problÃƒÂ¨me d'affichage : " + dirtPATH.getFileName());
		}

	}

	
	
	public void drawCampFire(Graphics2D graphics, SimpleGameData data) {
		String pictureName = "pictures/campfire.png";
		Path campPATH = Path.of(pictureName);
		drawImage(graphics, data.getPath().get(0).line(),data.getPath().get(0).column(), campPATH);

	}
	
	
	public void drawAvailableSpotEquipment(Graphics2D graphics, SimpleGameData data) {

		switch (data.getSelectedEquipment().getEquipmentType()) {
				case "Shield" -> {
					Path pathPATH = Path.of("pictures/available.png");
					drawImage(graphics, 3, 25, pathPATH);
	
					}
				case "Armor" -> {
					Path pathPATH = Path.of("pictures/available.png");
					drawImage(graphics, 3, 26, pathPATH);
	
					}
				case "Weapon" -> {
					Path pathPATH = Path.of("pictures/available.png");
					drawImage(graphics, 3, 28, pathPATH);
	
					}
				case "Ring" -> {
					Path pathPATH = Path.of("pictures/available.png");
					drawImage(graphics, 3, 27, pathPATH);
	
					}
			}	
			
		}
	
	
	/**
	 *Draw the list of acquired resources with their respective amount
	 */
	public void drawListResources(Graphics2D graphics, SimpleGameData data) {
		Map<String,Integer> ressources = data.getHero().getRessources();
		graphics.setFont(new Font("Dialog", Font.BOLD, 20));
		graphics.setColor(Color.white);
		graphics.drawString("Resources", xFromColumn(22), yFromLine(0));
		graphics.setFont(new Font("Dialog", Font.ITALIC, 15));
		int i =1;
		for (String key: ressources.keySet()) {
			graphics.drawString(""+key+" : "+ressources.get(key), xFromColumn(22), yFromLine(i));
			i+=1;
		}
	}
		
	
	
	/**
	 * Draws all the available tiles when the player clicked on a card, meaning all the tile that aren't special tiles from a card, if the selected car is Oblivion, the function marks with a cross the placed tiles than can be removed from the map 
	 */
	public void drawAvailablesTilesPositions(Graphics2D graphics, SimpleGameData data) {
		
		if (data.getSelectedCard().getType().equals("Oblivion")) {//If the selected card is oblivion
			try (InputStream in = Files.newInputStream(Path.of("pictures/remove.png"))) {
				BufferedImage img = ImageIO.read(in);
				AffineTransformOp scaling = new AffineTransformOp(AffineTransform
						.getScaleInstance(squareSize / (double) img.getWidth(), squareSize / (double) img.getHeight()),
						AffineTransformOp.TYPE_BILINEAR);
		
					for (Tile t : data.getPlacedTiles()) {
						graphics.drawImage(img, scaling, xOrigin + t.getPosition().column() * squareSize, yOrigin + t.getPosition().line() * squareSize);

				}
			}catch (IOException e) {
				throw new RuntimeException("problème d'affichage : ");
			}
		} else {
			try (InputStream in = Files.newInputStream(Path.of("pictures/available.png"))) {
				BufferedImage img = ImageIO.read(in);
				AffineTransformOp scaling = new AffineTransformOp(AffineTransform
						.getScaleInstance(squareSize / (double) img.getWidth(), squareSize / (double) img.getHeight()),
						AffineTransformOp.TYPE_BILINEAR);
				
				//the available tiles are shown whether they are Landscape, Road or Roadside
				switch (data.getSelectedCard().getType()) {
				case "Landscape" -> {
					for (GridPosition p : data.getEmptyLandscapeTile()) {
						graphics.drawImage(img, scaling, xOrigin + p.column() * squareSize, yOrigin + p.line() * squareSize);
					}
				}
				case "Road" -> {
					for (GridPosition p : data.getEmptyRoadTile()) {
						graphics.drawImage(img, scaling, xOrigin + p.column() * squareSize, yOrigin + p.line() * squareSize);
					}
				}
				case "RoadSide" -> {
					for (GridPosition p : data.getEmptyRoadSideTile()) {
						graphics.drawImage(img, scaling, xOrigin + p.column() * squareSize, yOrigin + p.line() * squareSize);
					}
				}
			}
			}catch (IOException e) {
				throw new RuntimeException("problÃƒÂ¨me d'affichage : ");
			}	
		}
	}
		
		

	
	
	
	/**
	 * draws the game informations : the number of loops, the hero's HP, the number of elapsed days, the amount of resources gained
	 */
	public void drawGameInfos(Graphics2D graphics, SimpleGameData data) {
		
		
		
		graphics.clearRect(width, 20, 3000, 3000);
		graphics.setFont(new Font("Dialog", Font.BOLD, 36));
		graphics.setColor(Color.blue);
		drawImageByPixel(graphics,  width+225,25, "pictures/loopCount.png");
		graphics.drawString("     "+data.getLoopCount(), width+230, 60);
		
		graphics.setColor(Color.white);
		drawImageByPixel(graphics,  width+320,25, "pictures/days.png");
		graphics.drawString("       "+(int) TimeData.getDay(), width+310, 60);
		
		graphics.setColor(Color.white);
		drawImageByPixel(graphics,  width+410,18, "pictures/star.png");
		
		int score = 0;
		for (int numb : data.getHero().getRessources().values()) {
			score+=numb;
		}
		
		
		graphics.drawString("       "+score , width+410, 60);
		
		//Drawing the hero stats
		graphics.setFont(new Font("Times New Roman", Font.BOLD, 36));
		graphics.drawString("Stats", xFromColumn(26), yFromLine(10));
				graphics.setFont(new Font("Lora", Font.ITALIC, 14));
				
				int baseWidth = width+250;
				int baseHeightImages = length+35;
				int baseHeight = length+60;
				
				String hpImg = "pictures/hpImg.png";
				DrawImagebyXandY(graphics, hpImg, 0.09, baseWidth, baseHeightImages-70);
				graphics.drawString("    "+(int) data.getHero().getHp()+"/"+(int) data.getHero().getMaxHp(), baseWidth+25, baseHeight-70);
				
				String shieldImg = "pictures/shieldImg.png";
				DrawImagebyXandY(graphics, shieldImg, 0.10, baseWidth+100, baseHeightImages-70);
				graphics.drawString(""+(int) data.getHero().getHerostats().getDefense(), baseWidth+150, baseHeight-70);
				
				
				String SwordImg = "pictures/damageImg.png";
				DrawImagebyXandY(graphics, SwordImg, 0.06, baseWidth+170, baseHeightImages-70);
				graphics.drawString("       "+(int) data.getHero().getHerostats().getDamage(), baseWidth+190, baseHeight-70);
					
				
				String CounterImg = "pictures/counterImg.png";
				DrawImagebyXandY(graphics, CounterImg, 0.10, baseWidth, baseHeightImages);
				graphics.drawString("       "+(int) data.getHero().getHerostats().getCounter(), baseWidth+25, baseHeight);
				
				
				String VampirismImg = "pictures/vampirismImg.png";
				DrawImagebyXandY(graphics, VampirismImg, 0.08, baseWidth+80, baseHeightImages);
				graphics.drawString("       "+(int) data.getHero().getHerostats().getVampirism(), baseWidth+100, baseHeight);
				
				
				String RegenImg = "pictures/regenImg.png";
				DrawImagebyXandY(graphics, RegenImg, 0.40, baseWidth+150, baseHeightImages);
				graphics.drawString("       "+(int) data.getHero().getHerostats().getRegen(), baseWidth+170, baseHeight);
				

				String EvadeImg = "pictures/evadeImg.png";
				DrawImagebyXandY(graphics, EvadeImg, 0.40, baseWidth+210, baseHeightImages);
				graphics.drawString("       "+(int) data.getHero().getHerostats().getEvade(), baseWidth+230, baseHeight);
	}
	
	//draws the equipped equipments
	public void drawEquipment(Graphics2D graphics, SimpleGameData data) {
		graphics.setFont(new Font("Times New Roman", Font.BOLD, 36));
		graphics.setColor(Color.white);
		graphics.drawString("Equipments", xFromColumn(25), yFromLine(2));
		
		int ligne = 3;
		int decal = 0;
		HashMap<String,Equipment> map = data.getHero().getPanoply().getEquipedItems() ;
		for(String key: map.keySet()){  
			String pictureName = map.get(key).getImagePath();
			Path pathPATH = Path.of(pictureName);
			drawImage(graphics, ligne, 25+decal, pathPATH);
			decal += 1;
			if (decal ==4) {
				decal = 0; 
				ligne+=1;
			}

		} 
		
	}

	//draws the inventory
	public void drawInventory(Graphics2D graphics, SimpleGameData data) {
		graphics.setFont(new Font("Times New Roman", Font.BOLD, 36));
		graphics.setColor(Color.white);
		graphics.drawString("Inventory", xFromColumn(25), yFromLine(5));
		
		int ligne = 6;
		int decal = 0;
		
		for(Equipment elem: data.getHero().getInventory().getList()){  
			String pictureName = elem.getImagePath();
			Path pathPATH = Path.of(pictureName);
			drawImage(graphics, ligne, 25+decal, pathPATH);
			decal += 1;
			if (decal ==4) {
				decal = 0; 
				ligne+=1;
			}

		} 
		
	}
}
