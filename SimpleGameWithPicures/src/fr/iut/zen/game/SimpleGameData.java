package fr.iut.zen.game;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import fr.iut.zen.game.elements.Hero;
import fr.iut.zen.game.elements.cards.Battlefield;
import fr.iut.zen.game.elements.cards.Beacon;
import fr.iut.zen.game.elements.cards.Card;
import fr.iut.zen.game.elements.cards.Cemetery;
import fr.iut.zen.game.elements.cards.Grove;
import fr.iut.zen.game.elements.cards.Meadow;
import fr.iut.zen.game.elements.cards.Oblivion;
import fr.iut.zen.game.elements.cards.Rock;
import fr.iut.zen.game.elements.cards.Ruins;
import fr.iut.zen.game.elements.cards.SpiderCocoon;
import fr.iut.zen.game.elements.cards.VampireMansion;
import fr.iut.zen.game.elements.cards.Village;
import fr.iut.zen.game.elements.cards.WheatFields;
import fr.iut.zen.game.elements.enemies.Chest;
import fr.iut.zen.game.elements.enemies.Ghost;
import fr.iut.zen.game.elements.enemies.Mobs;
import fr.iut.zen.game.elements.enemies.Ratwolf;
import fr.iut.zen.game.elements.enemies.Scarecrow;
import fr.iut.zen.game.elements.enemies.ScorchWorm;
import fr.iut.zen.game.elements.enemies.Skeleton;
import fr.iut.zen.game.elements.enemies.Slime;
import fr.iut.zen.game.elements.enemies.Spider;
import fr.iut.zen.game.elements.enemies.Vampire;
import fr.iut.zen.game.elements.equipments.Armor;
import fr.iut.zen.game.elements.equipments.Equipment;
import fr.iut.zen.game.elements.equipments.Ring;
import fr.iut.zen.game.elements.equipments.Weapon;
import fr.iut.zen.game.elements.tiles.BattlefieldTile;
import fr.iut.zen.game.elements.tiles.BeaconTile;
import fr.iut.zen.game.elements.tiles.CemeteryTile;
import fr.iut.zen.game.elements.tiles.GroveTile;
import fr.iut.zen.game.elements.tiles.MeadowTile;
import fr.iut.zen.game.elements.tiles.RockTile;
import fr.iut.zen.game.elements.tiles.RuinsTile;
import fr.iut.zen.game.elements.tiles.SpiderCocoonTile;
import fr.iut.zen.game.elements.tiles.Tile;
import fr.iut.zen.game.elements.tiles.VampireMansionTile;
import fr.iut.zen.game.elements.tiles.VillageTile;
import fr.iut.zen.game.elements.tiles.WheatFieldsTile;


/**
 * @author Laura & Berachem :)
 *
 */
public class SimpleGameData { 
	
	private final Cell[][] matrix;
	private final List<Mobs> MobsOnthePath ;
	private final ArrayList<GridPosition> path = new ArrayList<GridPosition>(Arrays.asList(
			new GridPosition(3,4),new GridPosition(3,5),new GridPosition(3,6),new GridPosition(3,7),
			new GridPosition(3,8),new GridPosition(3,9),new GridPosition(3,10),new GridPosition(3,11),
			new GridPosition(4,11),new GridPosition(5,11),new GridPosition(5,12),new GridPosition(6,12),
			new GridPosition(7,12),new GridPosition(8,12),new GridPosition(8,11),new GridPosition(8,10),
			new GridPosition(8,9),new GridPosition(7,9),new GridPosition(7,8),new GridPosition(7,7),
			new GridPosition(7,6),new GridPosition(8,6),new GridPosition(9,6),new GridPosition(9,5),
			new GridPosition(9,4),new GridPosition(8,4),new GridPosition(8,3),new GridPosition(8,2),
			new GridPosition(7,2),new GridPosition(6,2),new GridPosition(5,2),new GridPosition(5,3),
			new GridPosition(4,3),new GridPosition(4,4)
			
			));
	private static final ArrayList<Card> Deck = new ArrayList<Card>();
	//Arrays.asList(new GridPosition(4,4),new GridPosition(4,5),new GridPosition(4,6),new GridPosition(4,7),new GridPosition(4,8),new GridPosition(4,9),new GridPosition(4,10),new GridPosition(4,11),new GridPosition(4,12),new GridPosition(4,13),new GridPosition(4,14),new GridPosition(4,15),new GridPosition(4,16),new GridPosition(4,17),new GridPosition(4,18),new GridPosition(4,19),new GridPosition(5,19),new GridPosition(6,19),new GridPosition(6,18),new GridPosition(6,17),new GridPosition(6,16),new GridPosition(6,15),new GridPosition(6,14),new GridPosition(6,13),new GridPosition(6,12),new GridPosition(6,11),new GridPosition(6,10),new GridPosition(6,9),new GridPosition(6,8),new GridPosition(6,7),new GridPosition(6,6),new GridPosition(6,5),new GridPosition(6,4),new GridPosition(5,4));
	private final GridPosition FireCamp;
	private Hero hero = new Hero("Bob");  
	private int LoopCount = 0;
	private int day = -1;
	private GridPosition selected;
	private final ArrayList<Tile> placedTiles ;
	private GridPosition bob ; // POSITION DE BOB AU DEPART
	private boolean GameContinue;
	private  ArrayList<GridPosition> emptyRoadTile;
	private ArrayList<GridPosition> emptyRoadSideTile;
	private  ArrayList<GridPosition> emptyLandscapeTile;
	private boolean PlannificationMode;
	private Card SelectedCard = null;
	private Equipment SelectedEquipment = null;
	private boolean inFight = false;
	private ArrayList<String> FightInfo ;
	private int MobFightTarget = 0;
	private boolean isBobFightTarget = false;
	private boolean isBobTurnInFight = true;
	private boolean isBobAffectedByBeaconTile = false;
	private int NumberMobsKilled = 0;
	private Mobs QuestMobTarget = null;
	
	

	
	/** constructor of the Class SimpleGameData, initialize the previous fields
	 * @exception throws an exception if the number of lines and column is invalid (must be at least 1 for both) 
	 * @param nbLines the number of lines in the matrix
	 * @param nbColumns the number of columns in the matrix
	 */
	public SimpleGameData(int nbLines, int nbColumns) {
		
		if (nbLines < 1 || nbColumns < 1) {
			throw new IllegalArgumentException("at least one line and column");
		}
		recoverPath();
		matrix = new Cell[nbLines][nbColumns];
		MobsOnthePath = new ArrayList<Mobs>();
		placedTiles = new ArrayList<Tile>();
		GameContinue= true;
		emptyRoadTile = new ArrayList<>(path);
		hero.setPos(bob);
		emptyRoadSideTile = initRoadSide();
		emptyLandscapeTile = initLandscape();
		PlannificationMode = false;
		FightInfo = new ArrayList<>();
		FireCamp = path.get(0);
		bob = path.get(0);
		initDeck();
		
		MobsOnthePath.add(new Slime(path.get(1),10));
		MobsOnthePath.add(new Slime(path.get(1),8));
		MobsOnthePath.add(new Slime(path.get(1),5));
	}

	/**
	 * Init the Cards deck for the game (2 Battlefield, 3 Beacon ...)
	 */
	private void initDeck() {
		for (int i=0;i<2;i++) {
			Deck.add(new Battlefield());
			Deck.add(new Oblivion());
			}
		for (int i=0;i<3;i++) {
			Deck.add(new Beacon());
			Deck.add(new Cemetery());
			Deck.add(new Ruins());
			Deck.add(new VampireMansion());
			Deck.add(new Village());
		}
		for (int i=0;i<4;i++) {Deck.add(new Grove());}
		for (int i=0;i<15;i++) {Deck.add(new Meadow());}
		for (int i=0;i<12;i++) {Deck.add(new Rock());}
		
		for (int i=0;i<6;i++) {
			Deck.add(new SpiderCocoon());
			Deck.add(new WheatFields());
			
			}
		for (int i=0;i<2;i++) {Deck.add(new Battlefield());}
		
	}


	/**
	 * The number of lines in the matrix contained in this GameData.
	 * 
	 * @return the number of lines in the matrix.
	 */
	public int nbLines() {
		return matrix.length;
	}

	/**
	 * The number of columns in the matrix contained in this GameData.
	 * 
	 * @return the number of columns in the matrix.
	 */
	public int nbColumns() {
		return matrix[0].length;
	}
	
	
	/**The cells that are considered as Road cells 
	 * @return the list of the cells 
	 */
	public ArrayList<GridPosition> refreshEmptyRoadTiles(){

		ArrayList<GridPosition> RoadList = new ArrayList<>();
		for (GridPosition p: path) {

			if (getTileOnGridPosition(p) == null) {
				RoadList.add(p);
			}
		}
		RoadList.remove(path.get(0));
		return RoadList;
		
	}
	
	
	
	
	/**The cells that are considered as RoadSide cells 
	 * @return the list of the cells 
	 */
	public ArrayList<GridPosition> initRoadSide(){
		
		List<List<Integer>> decal = List.of(List.of(0,1),List.of(1,0), List.of(-1,0), List.of(0,-1));  
		ArrayList<GridPosition> RoadSideList = new ArrayList<>();
		for (GridPosition p: path) {
			for (List<Integer> c : decal) {
				
				if (p.column()+c.get(0)<nbColumns() && p.line()+c.get(1)<nbLines()) {
					GridPosition potentialPosition = new GridPosition(p.line()+c.get(0),p.column()+c.get(1));
					if (! path.contains(potentialPosition)) {
						RoadSideList.add(potentialPosition);
					}
				}
			}
		}
		return RoadSideList;
		
	}
	
	
	/**The cells that are considered as Landscape cells 
	 * @return the list of the cells
	 */
	public ArrayList<GridPosition> initLandscape(){
		ArrayList<GridPosition> LandscapeList = new ArrayList<>();
		ArrayList<GridPosition> roadSide = initRoadSide();
		for (int i = 0;i<nbLines();i++) {
			for (int j = 0;j<nbColumns();j++) {
				GridPosition potentialPosition = new GridPosition(i, j);
				if (!path.contains(potentialPosition) && !roadSide.contains(potentialPosition)) {
					LandscapeList.add(potentialPosition);
				}
			}
		}
		return LandscapeList;
		
	}
	
	
	
	private void checkBoundsOrThrow(int line, int column) {
		Objects.checkIndex(line, matrix.length);
		Objects.checkIndex(column, matrix[0].length);
	}

	/**
	 * The color of the given cell.
	 * 
	 * @param line   the first coordinate of the cell.
	 * @param column the second coordinate of the cell.
	 * @return the number of columns in the matrix.
	 * @throws IndexOutOfBoundsException
	 */
	public Color cellColor(int line, int column) {
		checkBoundsOrThrow(line, column);
		return matrix[line][column].color();
	}

	
	/**
	 * The coordinates of the cell selected, if a cell is selected.
	 * 
	 * @return the coordinates of the selected cell; null otherwise.
	 */
	public GridPosition getSelected() {
		return selected;
	}

	
	public GridPosition bob() {
		return bob;
	}

	/**
	 * Tests if a cell is selected.
	 * 
	 * @return true if and only if at least one cell is selected; false otherwise.
	 */
	public boolean hasASelectedCell() {
		return selected != null;
	}

	/**
	 * Selects the cell identified by the specified coordinates.
	 * 
	 * @param line   the first coordinate of the cell.
	 * @param column the second coordinate of the cell.
	 * @throws IndexOutOfBoundsException
	 * @throws IllegalStateException     if a first cell is already selected.
	 */
	public void selectCell(int line, int column) {
		//checkBoundsOrThrow(line, column);
		if (selected != null) {
			throw new IllegalStateException("First cell already selected");
		}
		selected = new GridPosition(line, column);
		System.out.println("You clicked on the cell : "+selected);
		
		
		if (selected.line()>=13 && selected.line()<=14) { //if the selection is in the cards' area
			int indexInListCard = columnIntoIndexCard(column);
			if (indexInListCard>=0 && indexInListCard<hero.getHand().getList().size()) {
				SelectedCard = hero.getCardsList().get(indexInListCard);  
			}
				//
			System.out.println(SelectedCard);  
			
			
		}else if (selected.line()>=6 && selected.line()<=9 && selected.column()>=25 && selected.column()<=29) { //if the selection is in the equipment' area
			System.out.println("An equipment has been selected");
			int indexEquipmentSelected = 4*(selected.line()-6) + selected.column()-25;
			if (indexEquipmentSelected < hero.getInventory().getList().size()) {
				SelectedEquipment = hero.getInventory().getList().get(4*(selected.line()-6) + selected.column()-25);
			System.out.println("SELECTED EQUIPMENT : "+SelectedEquipment);
			}
			
		}
		
		// if the player clicks on the panoply with an equipment in hand
		else if (SelectedEquipment != null && selected.line()==3 && selected.column()>=24 && selected.column()<=28) {
			placeEquipment();
			unselect();
		}
		
		else { // if the click is on the map
			if (SelectedCard != null) { // if a card has been selected
				placeCard();
				unselect();
			}
			
		}
	}
	
	
	/**
	 * Equip the selected equipment(selectedEquipment)
	 */
	private void placeEquipment() {

		System.out.println(hero.getPanoply().getEquipedItems());
		hero.equipItem(SelectedEquipment);
		hero.getInventory().remove(SelectedEquipment);
		System.out.println(hero.getInventory());
		SelectedEquipment = null;
		
	}


	
	private void placeCard() {
		boolean cardHasBeenPlaced = false;

			if (SelectedCard.getType().equals("Landscape")) {
				
						cardHasBeenPlaced = SelectedCard.placeTile(getSelected(), placedTiles, emptyLandscapeTile);
						
					
			}else if (SelectedCard.getType().equals("Road")) {
				
				
						if (SelectedCard instanceof WheatFields) {
							cardHasBeenPlaced = placeWheatField();
						}else {
							cardHasBeenPlaced = SelectedCard.placeTile(getSelected(), placedTiles, emptyRoadTile);
						}
				
						
			}else if (SelectedCard.getType().equals("Oblivion")) {
				
					cardHasBeenPlaced = oblivionRemove(getSelected());
			}
			else {
				
					cardHasBeenPlaced = SelectedCard.placeTile(getSelected(), placedTiles, emptyRoadSideTile);
					
			}
		
		
			
		if (cardHasBeenPlaced) {
			System.out.println(hero.getHand());
			hero.getHand().remove(SelectedCard);
			System.out.println(hero.getHand());
			
			if (SelectedCard instanceof VampireMansion) {
				VampireMansionTile.spawnVampire(new VampireMansionTile(getSelected()),MobsOnthePath,placedTiles,LoopCount,path);
			}else if (SelectedCard instanceof Rock) {
				hero.increaseMaximumHpPercentage(RockTile.RockAdjacentsTileBonus(placedTiles,nbColumns(), nbLines(),new RockTile(getSelected()))+1);
			}
			SelectedCard = null;
			
		}  
		emptyRoadTile = refreshEmptyRoadTiles();
		//emptyRoadSideTile = refreshEmptyRoadSideTiles();
	}


	private ArrayList<GridPosition> refreshEmptyRoadSideTiles() {
	
		
		List<List<Integer>> decal = List.of(List.of(0,1),List.of(1,0), List.of(-1,0), List.of(0,-1));  
		ArrayList<GridPosition> RoadSideList = new ArrayList<>();
		for (GridPosition p: path) {
			for (List<Integer> c : decal) {
				
				if (p.column()+c.get(0)<nbColumns() && p.line()+c.get(1)<nbLines()) {
					GridPosition potentialPosition = new GridPosition(p.line()+c.get(0),p.column()+c.get(1));
					if (! path.contains(potentialPosition) && getMobOnGridPosition(potentialPosition) != null) {
						
						RoadSideList.add(potentialPosition);
					}
				}
			}
		}
		return RoadSideList;
	}

	private boolean placeWheatField() {//wheatfield is a special card than can only be placed besides a village, that why it has its own function
		int tilePositionInPath = path.indexOf(getSelected());
		List<Integer> PotentialIndex = Arrays.asList(tilePositionInPath-1,tilePositionInPath+1); 
		for (int n :PotentialIndex ) {
			//System.out.println("*-*-**--*****-*"+n);
			if ( getTileOnGridPosition(path.get(n)) instanceof VillageTile ) {
				//System.out.println(getTileOnGridPosition(path.get(n)) instanceof VillageTile);

				placedTiles.add(new WheatFieldsTile(getSelected()));
				hero.healValue(5*LoopCount);
				
				return true;
			}
		}  
		return false;
		
	}  


	/**
	 * Applies the affect of oblivion: the card can remove a tile and all the monsters on the tile
	 * @param pos the tile that has to be remove
	 * @return  true if the tile has been deleted , false otherwise
	 */
	private boolean oblivionRemove(GridPosition pos) {
		Objects.requireNonNull(pos);
	
		Tile targetTile = getTileOnGridPosition(pos);
		if (targetTile != null) { // il y a une Tuile sur la position
			placedTiles.remove(targetTile);
			emptyRoadSideTile = initRoadSide();
			emptyLandscapeTile = initLandscape();
			emptyRoadTile = refreshEmptyRoadTiles();
			
			System.out.println(targetTile.getClass().getSimpleName() + " has been deleted by an Oblivion Card...");
				
				for (Mobs m : getMobsOnGridPosition(pos)) {

					if (m.getPosition().equals(targetTile.getPosition())) {
						System.out.println("******************");
						System.out.println(MobsOnthePath);
						System.out.println(m);
						System.out.println(MobsOnthePath.contains(m));
						
						MobsOnthePath.remove(m);
						System.out.println("******************");
				}
			}
			System.out.println(targetTile.getClass().getSimpleName() + " has been deleted with all mobs hover by an Oblivion Card...");
			return true;
		}
		return false;
	}


	private Tile getTileOnGridPosition(GridPosition pos) {
		for (Tile t : placedTiles) {
			if (t.getPosition().equals(pos)) {
				return t;
			}
		}
		return null;
	}
	
	private ArrayList<Mobs> getMobsOnGridPosition(GridPosition pos) {
		ArrayList<Mobs> list = new ArrayList<Mobs>();
		for (Mobs m : MobsOnthePath) {
			if (m.getPosition().equals(pos)) {
				list.add(m);
			}
		}
		return list;
	}
	
	private Mobs getMobOnGridPosition(GridPosition pos) {
		for (Mobs m : MobsOnthePath) {
			if (m.getPosition().equals(pos)) {
				return m;
			}
		}
		return null;
	}


	/**
	 * @param column the coordinate of the card's column 
	 * @return the index of the Card regarding the Hero's Hand
	 */
	public int columnIntoIndexCard(int column) {
		int x = 0;
		int x2 = 1;
		
		for (int i = 0; i<=12; i++) {
			if (column>=x && column <= x2 ) {
				return i;
			}
			x +=2;
			x2+=2;
		}
		return -1;
	}
	

	/**
	 * Selects the next cell in the same line, if it exists. If no cell is selected,
	 * start with the first cell.
	 */
	public void selectNextCellInLine() {
		if (selected == null) { // no cell is selected
			selectCell(0, 0);
			return;
		}
		int column = selected.column() + 1;
		if (column >= matrix[0].length) {
			selected = null;
		} else {
			selected = new GridPosition(selected.line(), column);
		}
	}
	
	
	

	/**
	 * Selects the next cell in the same line, if it exists. If no cell is selected,
	 * start with the first cell.
	 */
	public void moveBob() {
		if (GameContinue) {
			checkCampFire();
			if (getTileOnGridPosition(bob) instanceof VillageTile) {
				VillageTile.questStartVillage(MobsOnthePath, QuestMobTarget, LoopCount, hero);
			}
			
			
		if (isBobOnMobCell()) {
			fightVsMob();
			
			if (MobFightTarget>=getMobOnBobCell().size()) {
				MobFightTarget=0;
			}
		}else {
			MobFightTarget = 0;
			int index = path.indexOf(new GridPosition(bob.line(),bob.column()));
			if (index+1>path.size()-1) {
				bob = path.get(0);
				
			}else {
				bob = path.get(index+1);
			}
			hero.setPos(bob);
			inFight=false;
		}
		if (bobNearBeaconTile() && ! isBobAffectedByBeaconTile) {
			TimeData.increaseSpeed();
			System.out.println("tu vas plus vite");
			isBobAffectedByBeaconTile = true;
		}else {
			isBobAffectedByBeaconTile = false;
		}
					
				// APPLIES THE EFFECT OF EACH TILE INFLUENCED BY THE DAY
				if (day != TimeData.getDay() && TimeData.getDay()%4==0 && TimeData.getDay()!=0) {
					WheatFieldsTile.spawnScarecrow(placedTiles, MobsOnthePath,LoopCount);
				}
				if (day != TimeData.getDay() && TimeData.getDay()%2==0 && TimeData.getDay()!=0) {
					GroveTile.spawnRatwolf(placedTiles,MobsOnthePath,path,LoopCount);	
					RuinsTile.spawnScorchWorm(placedTiles, MobsOnthePath,LoopCount);
				}
				if (day != TimeData.getDay() && TimeData.getDay()%3==0 && TimeData.getDay()!=0) {
					CemeteryTile.spawnSkeletonCimetery(MobsOnthePath, cimeteryTilesList(), LoopCount);
				}
				if (day != TimeData.getDay()) {
					day++;
					spawnSlimes();
					hero.healValue(2);
					SpiderCocoonTile.spawnSpiderCocoon(MobsOnthePath,LoopCount,path,placedTiles);
					
				}
				
		}
	}
	
	
//----------------------------------SPAWNING FUNCTION---------------------------------------
	



/**
 * Spawns Slimes based on the spawn rate 
 */
public void spawnSlimes() {
	for (GridPosition p:path) {
		
		if (!(p.equals(path.get(0))) && getTileOnGridPosition(p)==null){
			double random = new Random().nextDouble(1.0);
				if (random<=0.05) {
					MobsOnthePath.add(new Slime(p,LoopCount));
					}
			}
	}
		
}



//----------------------------------------------------------------------------------


/**
 * effect of the beacon tile: if the hero is within its range, his speed will increase
 * @return true if the hero is in the range of the tile, false otherwise
 */
public boolean bobNearBeaconTile() {
	for (Tile tile : placedTiles) {
		if (tile instanceof BeaconTile) {
			
			int ligne = bob.line();
			int col = bob.column();
			for (int i = tile.getPosition().line()-2 ; i<tile.getPosition().line()+2;i++) {
				for (int j = tile.getPosition().column()-2;j<tile.getPosition().column()+2;j++) {
					GridPosition potentialPosition = new GridPosition(i, j);
					if (path.contains(potentialPosition) &&  potentialPosition.equals(bob) ) {
						return true;
					}
				}
			}	
			
		}	
	}
	return false;	
}
	
	


/**
 * @return a list of cemetery tiles too then apply their effect in the function movebob()
 */
private ArrayList<Tile> cimeteryTilesList() {
	ArrayList<Tile> list = new ArrayList<>();
	for (Tile t : placedTiles) {
		if (t instanceof CemeteryTile) {
			list.add(t);
		}
	}
	return list;
}


	/**
	 * Checks if bob is on the campFire or on a Mob and applies the corresponding effects
	 */
	public void checkCampFire() {
		if (bob.equals(FireCamp)) {
			BattlefieldTile.spawnChests(placedTiles, MobsOnthePath,LoopCount,path);
			hero.heroOnCampFire();
			LoopCount++;

			//MobsOnthePath.add(new Ratwolf(new GridPosition(4, 6), LoopCount)); 
			hero.healValue(countMeadowTilePlaced()*2);
		}
		
	}
	
	/**
	 * @return the number of meadow tiles placed on the board
	 */
	public int countMeadowTilePlaced() {
		int count = 0;
		for (Tile e : placedTiles) {
			if (e instanceof MeadowTile) {
				count++;
			}
		}
		return count;
		
	}
	
	
	
	
	
	/**
	 * Applies the effect of Meadow Tiles
	 */
	public void MeadowEffects() {
		for (Tile t : placedTiles) {
			if ( t instanceof MeadowTile) {
				hero.healValue(2);
			}
		}
	}
	

	

//-----------------------------------BATTLE----------------------------------
	
	/**
	 * @return true if Bob is on a cell with a Mob , return false otherwise
	 */
	public boolean isBobOnMobCell() {
		for (Mobs m : MobsOnthePath) {
			if (m.isInPosition(bob)) {
				return true;
			}
		}
		return false;
	}
	
	/**Simulates a fight between Bob and Mobs (attacks, gained resources, gained cards), adding 2 seconds to the elapsed
	 * @param listOfMobs the list of Mobs  that are on the cell where Bob is 
	 */
	public void fightVsMob() {
		ArrayList<Mobs> listOfMobs  = getMobOnBobCell();
		System.out.println("---BATTLE---");
		if (hero.isAlive() && listOfMobs.size()>0 && GameContinue) {
			FightInfo = new ArrayList<>();
			inFight = true;
			
			System.out.println(listOfMobs);
			if (MobFightTarget< listOfMobs.size()) {
					isBobFightTarget = false;
					Mobs m =listOfMobs.get(MobFightTarget);
					
					if (isBobTurnInFight) {
						MobIsAttacked(m);
						isBobTurnInFight = false;
						if (!m.isAlive()) {
							MobDead(m, listOfMobs);
							BattlefieldTile.ghostTransformation(bob,m, MobsOnthePath,LoopCount,path, placedTiles);
							// Was it a target of a quest
							if (QuestMobTarget != null && QuestMobTarget.equals(m)) { 
								QuestMobTarget = null;
							}
						}
					}else {
						HeroIsAttacked(m);
						isBobTurnInFight = true;
						isBobFightTarget = true;
						
						if (!hero.isAlive()) {
							GameContinue=false;
							FightInfo.add("Bob died ;(");
						}
						MobFightTarget++;
					}
					

					
				
				}
			
		}


	}
	
	
	private void MobIsAttacked(Mobs m) {
		System.out.println("The monster HP : "+m.getHp());
		int MobAttackedInfo = m.attacked(hero);
		if (MobAttackedInfo==1) {
			FightInfo.add("Bob attacked the "+m.getClass().getSimpleName()+" with "+Math.round(hero.attack())+" damage");
			if (m.getLastCounterAttackDamage() !=0) {
				FightInfo.add("The "+m.getClass().getSimpleName()+" counter attacked...");
			}
		}else if (MobAttackedInfo==0) {
			FightInfo.add("The "+m.getClass().getSimpleName()+" dodged the attack...");
		} 
		
		
		System.out.println("The monster HP : "+m.getHp());

		
	}


	private void HeroIsAttacked(Mobs m) {
		int HeroAttackedInfo = hero.attacked(m);
		if (HeroAttackedInfo==1) {
			FightInfo.add("The "+m.getClass().getSimpleName()+" did an attack on Bob of "+Math.round(m.attack())+" damage");
			if (hero.getLastCounterAttackDamage() !=0) {
				FightInfo.add("Bob counter attacked...");
			}
		}else if (HeroAttackedInfo==0) {
			FightInfo.add("Bob dodged the attack...");
		} 
		
	}


	private void MobDead(Mobs m, ArrayList<Mobs> listOfMobs) {
		System.out.println("The monster died...");
		FightInfo.add("The "+m.getClass().getSimpleName()+" is dead...");
		hero.winRessources(m.dropRessources());
		//FightInfo.add("Le "+m.getClass().getName()+" a fait droper un Equipement...");
		hero.addCardsInHand(m.dropCards());
		hero.addEquipmentsInInventory(m.dropEquipments(LoopCount));
		System.out.println("Hero's inventory :"+hero.getInventory());
		listOfMobs.remove(m);
		NumberMobsKilled++;
		MobsOnthePath.remove(m);
		
	}


	
	
	/**
	 * @return the list of Mobs that are in the same location as Bob
	 */
	public ArrayList<Mobs> getMobOnBobCell() {
		ArrayList<Mobs> MobsOnBobCell = new ArrayList<>();
		for (Mobs m : MobsOnthePath) {
			if (m.isInPosition(bob)) {
				MobsOnBobCell.add(m);
			}
		}
		return MobsOnBobCell;
	}
	
	
	
	
//---------------------------------------OTHER--------------------------------
	/**
	 * Unselects the cell (whether they is a selected cell or not).
	 */
	public void unselect() {
		selected = null;
	}

	
	/**
	 * Initialize a random grid
	 */
	public void setRandomMatrix() {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				matrix[i][j] = Cell.randomGameCell();
			}
		}
	}

	/**
	 * Updates the data contained in the GameData.
	 */
	public void updateData() {
		// update (attention traitement different si des cases sont
		// selectionnées ou non...)
	}
	
	/*
	public void spawnMobs() {
		
	}
	*/
	
	
	/**
	 * Switch the value of the Boolean PlannificationMode 
	 */
	public void startPlannificationMode() {
			PlannificationMode = true;
	}
	
	/**
	 * Switch the value of the Boolean PlannificationMode 
	 */
	public void stopPlannificationMode() {
			PlannificationMode = false;
	}
	
	
	
//----------------------------------SAVE & LOADING-------------------------------------------------------
	
	/**
	 * Saves the game in .txt files for those parameters: The monsters on the loop, all the placed tile, the game information(day, number of loops), info about the hero
	 */
	public void saveTheGame()  {
		
		System.out.println("salut");
		try(FileOutputStream fos = new FileOutputStream("MobsOnPath.tmp")){
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(MobsOnthePath);
			oos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("salut");
		try(FileOutputStream fos = new FileOutputStream("PlacedTiles.tmp")){
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(placedTiles);
			oos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		Path file = Path.of("smallGameInfos.txt");
		try(BufferedWriter writer = Files.newBufferedWriter(file,StandardCharsets.UTF_8)) {// ou writer.write(s, 0, s.length());
			
			writer.write(""+LoopCount);
			writer.newLine(); 
			writer.write(""+day);
			writer.newLine();
		}
		catch (IOException e) {
				System.err.format("IOException: %s%n", e);
			}
		
		file = Path.of("savedGamePath.tmp");
		try(BufferedWriter writer = Files.newBufferedWriter(file,StandardCharsets.UTF_8)) {// ou writer.write(s, 0, s.length());
			
			for (GridPosition gp : path) {
				writer.write(gp.column()+" "+gp.line());
				writer.newLine();
			}
		}
		catch (IOException e) {
				System.err.format("IOException: %s%n", e);
			}
		
		try(FileOutputStream fos = new FileOutputStream("hero.tmp")){
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(hero);
			oos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	/**
	 * Reload the game with all the informations of the game in the txt files obtained with the function saveTheGame()
	 */
	public void reloadTheGame() {
		
		
		try(FileInputStream fos1 = new FileInputStream("MobsOnPath.tmp")){
			ObjectInputStream oos1 = new ObjectInputStream(fos1);
			this.MobsOnthePath.clear();
			this.MobsOnthePath.addAll((ArrayList<Mobs>) oos1.readObject());
			
			oos1.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Path file = Path.of("smallGameInfos.txt");
		try(BufferedReader reader = Files.newBufferedReader(file,StandardCharsets.UTF_8)) {// ou writer.write(s, 0, s.length());
			this.LoopCount = Integer.parseInt(reader.readLine());
			this.day = Integer.parseInt(reader.readLine());

		}
		catch (IOException e) {
				System.err.format("IOException: %s%n", e);
			}
		
		try(FileInputStream fos1 = new FileInputStream("PlacedTiles.tmp")){
			ObjectInputStream oos1 = new ObjectInputStream(fos1);
			this.placedTiles.clear();
			this.placedTiles.addAll((ArrayList<Tile>) oos1.readObject());
			
			oos1.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		try (BufferedReader br = new BufferedReader(new FileReader("savedGamePath.tmp"))) {
		    String line;
		    path.clear();
		    while ((line = br.readLine()) != null) {
		       path.add(new GridPosition(Integer.parseInt( line.split(" ")[1]),Integer.parseInt(  line.split(" ")[0])));
		    }
			emptyRoadTile = new ArrayList<>(path);
			emptyRoadSideTile = initRoadSide();
			emptyLandscapeTile = initLandscape();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try(FileInputStream fos1 = new FileInputStream("hero.tmp")){
			ObjectInputStream oos1 = new ObjectInputStream(fos1);
			this.hero = (Hero) oos1.readObject();
			
			oos1.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.bob = hero.getPos();
	}
	
	
	
	//Recreate the path with the given file
	public void recoverPath() {
		int lineNumber; int column;
		Path file = Path.of("Path.txt");  
		
		path.remove(0);
		
		try (BufferedReader reader = Files.newBufferedReader(file, StandardCharsets.UTF_8)){
			String line;
			this.path.clear();
			while ((line = reader.readLine()) != null) {
				
				lineNumber= Integer.parseInt(line.split(" ")[1]);
				column= Integer.parseInt(line.split(" ")[0]);
				path.add(new GridPosition(lineNumber, column));
				
			}
			System.out.println("Path has been recover from a file successfuly");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}  
	
	
	
	//ACCESSORS
	
	public Hero getHero() {
		return hero;
	}

	public int getLoopCount() {
		return LoopCount;
	}


	public List<Tile> getPlacedTiles() {
		return placedTiles;
	}
	
	public List<Mobs> getMobsOnthePath(){
		return MobsOnthePath;
	}
	
	public List<GridPosition> getPath(){
		return path;
	}

	public boolean isPlannificationMode() {
		return PlannificationMode;
	}


	public ArrayList<GridPosition> getEmptyRoadTile() {
		return emptyRoadTile;
	}


	public ArrayList<GridPosition> getEmptyRoadSideTile() {
		return emptyRoadSideTile;
	}


	public ArrayList<GridPosition> getEmptyLandscapeTile() {
		return emptyLandscapeTile;
	}


	public Card getSelectedCard() {
		return SelectedCard;
	}
	

	public Equipment getSelectedEquipment() {
		return SelectedEquipment;
	}


	public boolean isBobInFight() {
		return inFight;
	}


	public ArrayList<String> getFightInfo() {
		return FightInfo;
	}


	public int getMobFightTarget() {
		return MobFightTarget;
	}


	public boolean isBobFightTarget() {
		return isBobFightTarget;
	}


	public Mobs getQuestMobTarget() {
		return QuestMobTarget;
	}

	public static ArrayList<Card> getDeck() {
		return Deck;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
