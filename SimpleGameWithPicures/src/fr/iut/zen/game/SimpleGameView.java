package fr.iut.zen.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
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

import javax.imageio.ImageIO;

import fr.iut.zen.game.elements.cards.Card;
import fr.iut.zen.game.elements.enemies.Mobs;

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
		float x = xFromColumn(column);
		float y = yFromLine(line);
		graphics.setColor(Color.ORANGE);
		graphics.fill(new Rectangle2D.Float(x, y, squareSize, squareSize));
		graphics.setColor(Color.LIGHT_GRAY);
		graphics.fill(new Rectangle2D.Float(x + 2, y + 2, squareSize - 4, squareSize - 4));
	}

	private void drawBob(Graphics2D graphics, GridPosition position) {
		String pictureName = "pictures/HERO.png";
		Path heroPATH = Path.of(pictureName);
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
		graphics.setColor(new Color(160,82,45));
		graphics.fill(new Rectangle2D.Float(xOrigin, yOrigin, width, length));
		
		graphics.setColor(Color.WHITE);
		for (int i = 0; i <= nbLines; i++) {
			int line = yOrigin + i * squareSize;
			graphics.draw(new Line2D.Float(xOrigin, line, xOrigin + width, line));
		}

		for (int i = 0; i <= nbColumns; i++) {
			int column = xOrigin + i * squareSize;
			graphics.draw(new Line2D.Float(column, yOrigin, column, yOrigin + length));
		}
		
	}

	private void drawBar(Graphics2D graphics, int width, double timeFraction) {
		graphics.setColor(Color.LIGHT_GRAY);
		graphics.fill(new Rectangle2D.Double(xOrigin, yOrigin - 20, width, 10));
		graphics.setColor(Color.ORANGE);
		graphics.fill(new Rectangle2D.Double(xOrigin, yOrigin - 20, width * timeFraction, 10));
		graphics.setColor(Color.BLACK);
		graphics.draw(new Rectangle2D.Double(xOrigin, yOrigin - 20, width, 10));
	}
	
	
	private void drawHandContainer(Graphics2D graphics, int width) {
		graphics.setColor(Color.LIGHT_GRAY);
		graphics.fill(new Rectangle2D.Double(0, yOrigin + 600, width , 120));
		graphics.setColor(Color.LIGHT_GRAY);
		graphics.fill(new Rectangle2D.Double(0, yOrigin + 600, width, 120));
		graphics.setColor(Color.LIGHT_GRAY);
		graphics.draw(new Rectangle2D.Double(0, yOrigin + 600, width, 120));
		
		for (int i = 0; i <= 13; i++) {
			int column = 0 + i * width/13;
			graphics.draw(new Line2D.Float(column, 800, column, yOrigin + length));
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
		drawBar(graphics, data.nbColumns() * squareSize, timeData.timeFraction());

		// dessin d'une grille
		drawGrid(graphics, data.nbLines(), data.nbColumns());
		
		//drawHandContainer(graphics, 1100);
		
		//dessin des cases du chemin
		drawPath(graphics, data);
		
		//dessin des parcelles de terre du reste de la map
		// drawRestOfTheMap(graphics, data, data.nbLines(), data.nbColumns());
		
		// dessin de la cellule selectionnÃƒÂ©e
		GridPosition position = data.getSelected();
		if (position != null) {
			drawSelectedCell(graphics, position.line(), position.column());
		}

		drawBob(graphics, data.bob());
		
		drawGameInfos(graphics, data);
		
		/*
		// ajout d'une image de Slime aÂ une position donnÃƒÂ©e
		String pictureName = "pictures/green-slime.png";
		Path slimePATH = Path.of(pictureName);
		GridPosition slimePos = data.path.get(4);
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
		
		for(GridPosition p: data.path) {
			drawImage(graphics, p.line(),p.column(), pathPATH);
			
		}
		drawCampFire(graphics,data);
		drawMobs(graphics,data);
		drawCards(graphics, data);
	}
	
	
	public void drawMobs(Graphics2D graphics, SimpleGameData data) {
		for(Mobs m: data.MobsOnthePath) {
			String pictureName = m.getImagePath();
			Path pathPATH = Path.of(pictureName);
			drawImage(graphics, m.getPosition().line(),m.getPosition().column(), pathPATH);
		}
	}
	
	public void drawCards(Graphics2D graphics, SimpleGameData data) {
		int i = 1;
		for(Card c: data.getHero().getCardsList()) {
			
			String pictureName = c.getImagePath();
			Path pathPATH = Path.of(pictureName);
			drawImageCard(graphics, 12, i, pathPATH);
			i += 2;
		}
	}
	
	/*
	 * DESSINS DES CARRES DE TERRE DU RESTE DE LA MAP
	 * 
	public void drawRestOfTheMap(Graphics2D graphics, SimpleGameData data, int nbLines, int nbColumns) {
		String pictureName = "pictures/dirt.jpg";
		Path dirtPATH = Path.of(pictureName);
		
		for (int i = 0; i < nbLines; i++) {
			for (int j = 0; j < nbColumns; j++) {
				if (!(data.path.contains(new GridPosition(i,j)))) {
					drawImage(graphics, i,j, dirtPATH);
					System.out.println("TEST");
					
				}
			}
		}
	}
	*/
	
	public void drawCampFire(Graphics2D graphics, SimpleGameData data) {
		String pictureName = "pictures/campfire.png";
		Path campPATH = Path.of(pictureName);
		drawImage(graphics, data.path.get(0).line(),data.path.get(0).column(), campPATH);

	}
	
	public void drawGameInfos(Graphics2D graphics, SimpleGameData data) {
		
		graphics.clearRect(width+100, 50, width, length);
		graphics.setFont(new Font("Dialog", Font.BOLD, 36));
		graphics.setColor(Color.blue);
		graphics.drawString("⏱️ "+data.getLoopCount(), width+120, 100);
		
		graphics.setColor(Color.red);
		graphics.drawString("❤️ "+data.getHero().getHp(), width+120, 150);
		
		graphics.setColor(Color.orange);
		graphics.drawString("🌳 "+data.getHero().getRessources(), width+120, 200);


	}
	
	
	
}
