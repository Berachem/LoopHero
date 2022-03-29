package fr.iut.zen.game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
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
import java.util.Random;
import java.awt.image.*;

import javax.imageio.ImageIO;

import fr.iut.zen.game.elements.cards.Card;
import fr.iut.zen.game.elements.enemies.Mobs;
import fr.iut.zen.game.elements.tiles.Tile;

public record SimpleGameView(int xOrigin, int yOrigin, int length, int width, int squareSize) implements GameView {
	
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

	private void drawBob(Graphics2D graphics, SimpleGameData data) {
		GridPosition position = data.bob();
		Path heroPATH = Path.of(data.getHero().getImagePath());
		drawImage(graphics, position.line(), position.column(), heroPATH);
	}

	private void drawImage(Graphics2D graphics, int line, int column, Path path) {
		try (InputStream in = Files.newInputStream(path)) {
			BufferedImage img = ImageIO.read(in);
			AffineTransformOp scaling = new AffineTransformOp(AffineTransform
					.getScaleInstance(squareSize / (double) img.getWidth(), squareSize / (double) img.getHeight()),
					AffineTransformOp.TYPE_BILINEAR);
			graphics.drawImage(img, scaling, xOrigin + column * squareSize, yOrigin + line * squareSize);
		} catch (IOException e) {
			throw new RuntimeException("problÃƒÂ¨me d'affichage : " + path.getFileName());
		}
	}
	
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
			throw new RuntimeException("problÃƒÂ¨me d'affichage : " + PathImage.getFileName());
		}
	}
	
	//Image dimensionnée pour les cartes
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
	
	
	private void drawGrid(Graphics2D graphics, int nbLines, int nbColumns) {
		graphics.setColor(Color.BLACK);
		graphics.setStroke(new BasicStroke(20));
		graphics.draw(new Rectangle2D.Float(xOrigin, yOrigin, width, length));
	}

	private void drawBar(Graphics2D graphics, int width, double timeFraction) {
		graphics.setColor(Color.LIGHT_GRAY);
		graphics.fill(new Rectangle2D.Double(xOrigin, yOrigin - 20, width, 10));
		graphics.setColor(Color.ORANGE);
		graphics.fill(new Rectangle2D.Double(xOrigin, yOrigin - 20, width * timeFraction, 10));
		graphics.setColor(Color.BLACK);
		graphics.draw(new Rectangle2D.Double(xOrigin, yOrigin - 20, width, 10));
	}
	
	
	private void drawHandContainer(Graphics2D graphics) {
		graphics.setColor(Color.LIGHT_GRAY);
		graphics.fill(new Rectangle2D.Double(0,length+40, width , 240));

		
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
		drawBar(graphics, data.nbColumns() * squareSize, timeData.timeFraction());
		//dessin des parcelles de terre du reste de la map
		String pictureName = "pictures/dirt.jpg";
		Path dirtPATH = Path.of(pictureName);
		drawRestOfTheMap(graphics, data, data.nbLines(), data.nbColumns(),dirtPATH);
		
		// dessin d'une grille
		//drawGrid(graphics, data.nbLines(), data.nbColumns());
		
		//drawHandContainer(graphics, 1100);
		
		//dessin des cases du chemin
		drawPath(graphics, data);
		
		
		
		drawCards(graphics, data);
		
		// dessin de la cellule selectionnÃƒÂ©e
		GridPosition position = data.getSelected();
		if (position != null) {
			drawSelectedCell(graphics, position.line(), position.column());
		}

		drawBob(graphics, data);
		
		
		drawGameInfos(graphics, data);
		
		/*
		// ajout d'une image de Slime aÂ une position donnÃƒÂ©e
		String pictureName = "pictures/green-slime.png";
		Path slimePATH = Path.of(pictureName);
		GridPosition slimePos = data.getPath().get(4);
		drawImage(graphics, slimePos.line(), slimePos.column(), slimePATH);
		
		*/
		
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

			graphics.setColor(Color.BLACK);
			graphics.fill(new Rectangle2D.Float(x, y, 10, 10));
		
	}
	public void drawPath(Graphics2D graphics, SimpleGameData data) {
		String pictureName = "pictures/path.png";
		Path pathPATH = Path.of(pictureName);
		
		for(GridPosition p: data.getPath()) {
			drawImage(graphics, p.line(),p.column(), pathPATH);
			
		}
		drawCampFire(graphics,data);
		drawTiles(graphics, data);
		drawMobs(graphics,data);
		
	}
	
	
	public void drawMobs(Graphics2D graphics, SimpleGameData data) {
		for(Mobs m: data.getMobsOnthePath()) {
			String pictureName = m.getImagePath();
			Path pathPATH = Path.of(pictureName);
			drawImage(graphics, m.getPosition().line(),m.getPosition().column(), pathPATH);
		}
	}
	
	public void drawTiles(Graphics2D graphics,SimpleGameData data) {
		for(Tile t: data.getPlacedTiles()) {
			String pictureName = t.getImagePath();
			//System.out.println(pictureName);
			Path pathPATH = Path.of(pictureName);
			drawImage(graphics, t.getPosition().line(),t.getPosition().column(), pathPATH);
		}
	}
	
	public void drawCards(Graphics2D graphics, SimpleGameData data) {
		drawHandContainer(graphics);
		graphics.setFont(new Font("Dialog", Font.BOLD, 36));
		graphics.setColor(Color.black);
		graphics.drawString("Cards", xFromColumn(0), yFromLine(13)-10);
		
		int decal = 0;
		System.out.println("---- ");
		for(Card c: data.getHero().getCardsList()) {
			
			String pictureName = c.getImagePath();
			Path pathPATH = Path.of(pictureName);
			
			System.out.println("---- "+c);
			drawImageCard(graphics, 13, decal, pathPATH);
			decal += 2;
		}
	}
	

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
	
	public void drawGameInfos(Graphics2D graphics, SimpleGameData data) {
		
		
		
		graphics.clearRect(width+100, 50, width, length);
		graphics.setFont(new Font("Dialog", Font.BOLD, 36));
		graphics.setColor(Color.blue);
		drawImageByPixel(graphics,  width+110,50, "pictures/watch.png");
		graphics.drawString("     "+data.getLoopCount(), width+120, 100);
		
		graphics.setColor(Color.red);
		drawImageByPixel(graphics,  width+110,125, "pictures/heart.png");
		graphics.drawString("       "+(int) data.getHero().getHp()+"/"+(int) data.getHero().getMaxHp(), width+120, 170);
		
		graphics.setColor(Color.orange);
		drawImageByPixel(graphics,  width+110,200, "pictures/wood.png");
		graphics.drawString("       "+(int) data.getHero().getRessources(), width+120, 240);
		
		

		graphics.setColor(Color.BLACK);
		drawImageByPixel(graphics,  width+110,260, "pictures/calendar.png");
		graphics.drawString("       "+(int) TimeData.getDay(), width+120, 300);
		
	}
	
	
	
}
