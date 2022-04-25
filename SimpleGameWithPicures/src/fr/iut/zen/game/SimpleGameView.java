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
import java.util.HashMap;
import java.util.Random;
import java.util.Timer;
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
			throw new RuntimeException("problÃƒÂ¨me d'affichage : " + path.getFileName());
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
			
			graphics.clearRect(0, 0, 1200, yOrigin-20); // supprime le mot pause
			graphics.setColor(Color.ORANGE);
		}
		graphics.setStroke(new BasicStroke(1));
		graphics.fill(new Rectangle2D.Double(xOrigin, yOrigin - 20, width * timeFraction, 10));
		graphics.setColor(Color.white);
		graphics.draw(new Rectangle2D.Double(xOrigin, yOrigin - 20, width, 10));
	}
	
	
	private void drawHandContainer(Graphics2D graphics) {
        graphics.setColor(Color.black);
        graphics.fill(new Rectangle2D.Double(0,length+40, 1700 , 240));


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
		if (data.getSelectedCard() != null) { // player has a card selected
			drawAvailablesTilesPositions(graphics, data);
		}
		
		
		//draws the card in the Hero's hand
		drawCards(graphics, data);
		
		// draws an arrow on the selected cell
		GridPosition position = data.getSelected();
		if (position != null) {
			drawSelectedCell(graphics, position.line(), position.column());
		}
		
		// AFFICHAGE DES LOGS DU COMBAT
		//drawFightInfos(graphics,data);
		
		drawBob(graphics, data);
		
		drawGameInfos(graphics, data);
		
		drawLogo(graphics,data);
		
		//drawInventoryContainer(graphics);
		drawEquipment(graphics, data);
		//drawGridEquip(graphics);
		
		drawInventory(graphics, data);
		
		if (data.isBobOnMobCell()) {
			
			drawFight(graphics, data);
			
		}else {
			System.out.println("---------");
		}

		
		}
		

		
		
		

		
	private void drawFight(Graphics2D graphics, SimpleGameData data) {
		
			
			graphics.setFont(new Font("Dialog", Font.BOLD, 30));
		    graphics.setColor(Color.DARK_GRAY);
		    graphics.fill(new Rectangle(xOrigin, yOrigin, width, length));
		    
		    graphics.setColor(Color.white);
		    graphics.drawString("Combat !", (int) (width/2.5), yOrigin+30);
		    
		    graphics.setFont(new Font("Dialog", Font.BOLD, 9));
	    	graphics.drawString("HP: "+ Math.round(data.getHero().getHp()), xFromColumn((5))-15, yFromLine(6)-10);
		    drawImage(graphics, 5,6, Path.of(data.getHero().getImagePath()));
		    
		    int basePlacement = 3;
		    for (Mobs m : data.getMobOnBobCell()) {
		    	drawImage(graphics, basePlacement,10, Path.of(m.getImagePath()));
		    	graphics.setFont(new Font("Dialog", Font.BOLD, 9));
		    	graphics.drawString("HP: "+ Math.round(m.getHp()), xFromColumn(10)+30, yFromLine(basePlacement));
		    	basePlacement+=2;
		    }
			
		
		
	}

	private void drawFightInfos(Graphics2D graphics, SimpleGameData data) {
		int deca = 0;
		graphics.setFont(new Font("Dialog", Font.BOLD, 8));
		graphics.setColor(Color.cyan);
		for (String s : data.getFightInfo()) {
			
			graphics.drawString(" INFO : "+s, width-50, length+100+deca);
			System.out.println("INFOOO : "+s);
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
		String pictureName = "pictures/path1.png";
		Path pathPATH = Path.of(pictureName);
		
		for(GridPosition p: data.getPath()) {
			drawImage(graphics, p.line(),p.column(), pathPATH);
			
		}
		drawCampFire(graphics,data);
		drawTiles(graphics, data);
		drawMobs(graphics,data);
		
	}
	
	
	//draws a Mob
	public void drawMobs(Graphics2D graphics, SimpleGameData data) {
		for(Mobs m: data.getMobsOnthePath()) {
			String pictureName = m.getImagePath();
			Path pathPATH = Path.of(pictureName);
			drawImage(graphics, m.getPosition().line(),m.getPosition().column(), pathPATH);
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
	
	
	//draws the whole Hero's Hand
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
	
	
	public void drawAvailablesTilesPositions(Graphics2D graphics, SimpleGameData data) {
		try (InputStream in = Files.newInputStream(Path.of("pictures/available.png"))) {
			BufferedImage img = ImageIO.read(in);
			AffineTransformOp scaling = new AffineTransformOp(AffineTransform
					.getScaleInstance(squareSize / (double) img.getWidth(), squareSize / (double) img.getHeight()),
					AffineTransformOp.TYPE_BILINEAR);
			
			
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


			
			
		} catch (IOException e) {
			throw new RuntimeException("problÃƒÂ¨me d'affichage : ");
		}
	}
		
		

	
	
	
	/**
	 * draws the game informations : the number of loops, the hero's HP, the number of elapsed days, the amount of resources gained
	 */
	public void drawGameInfos(Graphics2D graphics, SimpleGameData data) {
		
		
		
		graphics.clearRect(width+125, 20, width, length);
		graphics.setFont(new Font("Dialog", Font.BOLD, 36));
		graphics.setColor(Color.blue);
		drawImageByPixel(graphics,  width+125,25, "pictures/loopCount.png");
		graphics.drawString("     "+data.getLoopCount(), width+130, 60);
		
		graphics.setColor(Color.white);
		drawImageByPixel(graphics,  width+220,25, "pictures/days.png");
		graphics.drawString("       "+(int) TimeData.getDay(), width+210, 60);
		
		//Drawing the hero stats
		graphics.setFont(new Font("Times New Roman", Font.BOLD, 36));
		graphics.drawString("Stats", xFromColumn(25), yFromLine(10));
				graphics.setFont(new Font("Lora", Font.ITALIC, 14));
				
				
				String hpImg = "pictures/hpImg.png";
				Path path = Path.of(hpImg);
				try (InputStream in = Files.newInputStream(path)) {
					BufferedImage img = ImageIO.read(in);
					AffineTransformOp scaling = new AffineTransformOp(AffineTransform
							.getScaleInstance(0.06, 0.06),
							AffineTransformOp.TYPE_BILINEAR);
					graphics.drawImage(img, scaling, width+100,size.height-215 );

				} catch (IOException e) {
					throw new RuntimeException("Problème d'affichage : " + path.getFileName());
					
				}
				String shieldImg = "pictures/shieldImg.png";
				Path path2 = Path.of(shieldImg);
				try (InputStream in2 = Files.newInputStream(path2)) {
					BufferedImage img = ImageIO.read(in2);
					AffineTransformOp scaling = new AffineTransformOp(AffineTransform
							.getScaleInstance(0.06, 0.06),
							AffineTransformOp.TYPE_BILINEAR);
					graphics.drawImage(img, scaling, width+280,size.height-215);
					

				} catch (IOException e) {
					throw new RuntimeException("Problème d'affichage : " + path.getFileName());
					
				}
				
				
				graphics.drawString("       "+(int) data.getHero().getHp()+"/"+(int) data.getHero().getMaxHp(), width+100, 570);
				
				drawImage(graphics, 11,25, Path.of("pictures/damageImg.png"));
				graphics.drawString("       "+(int) data.getHero().getHerostats().getDamage(), width+190, 570);
				
				
				graphics.drawString("       "+(int) data.getHero().getHerostats().getDefense(), width+300, 570);
				
				drawImage(graphics, 12,22, Path.of("pictures/counterImg.png"));
				graphics.drawString("       "+(int) data.getHero().getHerostats().getCounter(), width+80, 620);
				
				drawImage(graphics, 12,24, Path.of("pictures/vampirismImg.png"));
				graphics.drawString("       "+(int) data.getHero().getHerostats().getCounter(), width+170, 620);
		
				drawImage(graphics, 12,26, Path.of("pictures/regenImg.png"));
				graphics.drawString("       "+(int) data.getHero().getHerostats().getCounter(), width+250, 620);
				
				drawImage(graphics, 12,28, Path.of("pictures/evadeImg.png"));
				graphics.drawString("       "+(int) data.getHero().getHerostats().getCounter(), width+350, 620);
		/*graphics.setColor(Color.white);
		graphics.setFont(new Font("Dialog", Font.BOLD, 23));
		drawImageByPixel(graphics,  width+110,200, "pictures/wood.png");
		int decal = 0;
		for (String ressource : data.getHero().getRessources().keySet()) {
			graphics.drawString(" x "+(int) data.getHero().getRessources().get(ressource)+" "+ressource, width+180, 240+decal);
			decal+=20;
		}*/
		
	}
	
	public void drawInventoryContainer(Graphics2D graphics) {
		double x = width+50;
		double y = 0;
		graphics.setColor(Color.LIGHT_GRAY);
		graphics.fill(new Rectangle2D.Double(x,y, width/3.5, length/2));
	}
	
	public void drawGridEquip(Graphics2D graphics) {
		double x = width+25;
		double y = 50;
		double t = 50;
		graphics.setColor(Color.BLACK);
		graphics.setStroke(new BasicStroke(3));;

		for (int i = 0; i<4; i++) {
			graphics.draw(new Rectangle2D.Double(x+t,y, width-890, length-350));
			t+=50;
		}
	}
	
	//draws the equipied equipements
		public void drawEquipment(Graphics2D graphics, SimpleGameData data) {
			graphics.setFont(new Font("Times New Roman", Font.BOLD, 36));
			graphics.setColor(Color.white);
			graphics.drawString("Equipment", xFromColumn(24), yFromLine(2));
			
			int ligne = 3;
			int decal = 0;
			HashMap<String,Equipment> map = data.getHero().getPanoply().getEquipedItems() ;
			for(String key: map.keySet()){  
				String pictureName = map.get(key).getImagePath();
				Path pathPATH = Path.of(pictureName);
				drawImage(graphics, ligne, 24+decal, pathPATH);
				decal += 1;
				if (decal ==4) {
					decal = 0; 
					ligne+=1;
				}

			} 
			
		}

		//draws the equipied equipements
			public void drawInventory(Graphics2D graphics, SimpleGameData data) {
				graphics.setFont(new Font("Times New Roman", Font.BOLD, 36));
				graphics.setColor(Color.white);
				graphics.drawString("Inventaire", xFromColumn(24), yFromLine(5));
				
				int ligne = 6;
				int decal = 0;
				
				for(Equipment elem: data.getHero().getInventory().getList()){  
					String pictureName = elem.getImagePath();
					Path pathPATH = Path.of(pictureName);
					drawImage(graphics, ligne, 24+decal, pathPATH);
					decal += 1;
					if (decal ==4) {
						decal = 0; 
						ligne+=1;
					}

				} 
				
			}
	
	
	
	
}
