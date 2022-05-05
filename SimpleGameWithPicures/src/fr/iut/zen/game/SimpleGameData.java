package fr.iut.zen.game;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import fr.iut.zen.game.elements.Hero;
import fr.iut.zen.game.elements.cards.Card;
import fr.iut.zen.game.elements.cards.Grove;
import fr.iut.zen.game.elements.cards.Meadow;
import fr.iut.zen.game.elements.cards.Rock;
import fr.iut.zen.game.elements.enemies.Mobs;
import fr.iut.zen.game.elements.enemies.Ratwolf;
import fr.iut.zen.game.elements.enemies.Slime;
import fr.iut.zen.game.elements.equipments.Armor;
import fr.iut.zen.game.elements.equipments.Equipment;
import fr.iut.zen.game.elements.equipments.Shield;
import fr.iut.zen.game.elements.equipments.Weapon;
import fr.iut.zen.game.elements.tiles.GroveTile;
import fr.iut.zen.game.elements.tiles.MeadowTile;
import fr.iut.zen.game.elements.tiles.RockTile;
import fr.iut.zen.game.elements.tiles.Tile;


/**
 * @author Laura & Berachem :)
 *
 */
public class SimpleGameData { 
	
	private final Cell[][] matrix;
	private final List<Mobs> MobsOnthePath ;
	private final List<GridPosition> path = Arrays.asList(new GridPosition(3,4),new GridPosition(3,5),new GridPosition(3,6),new GridPosition(3,7),new GridPosition(3,8),new GridPosition(3,9),new GridPosition(3,10),new GridPosition(3,11),new GridPosition(4,11),new GridPosition(5,11),new GridPosition(5,12),new GridPosition(6,12),new GridPosition(7,12),new GridPosition(8,12),new GridPosition(8,11),new GridPosition(8,10),new GridPosition(8,9),new GridPosition(7,9),new GridPosition(7,8),new GridPosition(7,7),new GridPosition(7,6),new GridPosition(8,6),new GridPosition(9,6),new GridPosition(9,5),new GridPosition(9,4),new GridPosition(8,4),new GridPosition(8,3),new GridPosition(8,2),new GridPosition(7,2),new GridPosition(6,2),new GridPosition(5,2),new GridPosition(5,3),new GridPosition(4,3),new GridPosition(4,4));
	//Arrays.asList(new GridPosition(4,4),new GridPosition(4,5),new GridPosition(4,6),new GridPosition(4,7),new GridPosition(4,8),new GridPosition(4,9),new GridPosition(4,10),new GridPosition(4,11),new GridPosition(4,12),new GridPosition(4,13),new GridPosition(4,14),new GridPosition(4,15),new GridPosition(4,16),new GridPosition(4,17),new GridPosition(4,18),new GridPosition(4,19),new GridPosition(5,19),new GridPosition(6,19),new GridPosition(6,18),new GridPosition(6,17),new GridPosition(6,16),new GridPosition(6,15),new GridPosition(6,14),new GridPosition(6,13),new GridPosition(6,12),new GridPosition(6,11),new GridPosition(6,10),new GridPosition(6,9),new GridPosition(6,8),new GridPosition(6,7),new GridPosition(6,6),new GridPosition(6,5),new GridPosition(6,4),new GridPosition(5,4));
	private final GridPosition FireCamp = path.get(0);
	private final Hero hero = new Hero("Bob");
	private int LoopCount = 0;
	private int day = -1;
	private GridPosition selected;
	private final ArrayList<Tile> placedTiles ;
	private GridPosition bob = path.get(0); // POSITION DE BOB AU DEPART
	private boolean GameContinue;
	private final ArrayList<GridPosition> emptyRoadTile;
	private final ArrayList<GridPosition> emptyRoadSideTile;
	private final ArrayList<GridPosition> emptyLandscapeTile;
	private boolean PlannificationMode;
	private Card SelectedCard = null;
	private Equipment SelectedEquipment = null;
	private boolean inFight = false;
	private ArrayList<String> FightInfo ;
	private int MobFightTarget = 0;
	private boolean isBobFightTarget = false;
	private boolean isBobTurnInFight = true;
	

	
	/** constructor of the Class SimpleGameData, initialize the previous fields
	 * @exception throws an exception if the number of lines and column is invalid (must be at least 1 for both) 
	 * @param nbLines the number of lines in the matrix
	 * @param nbColumns the number of columns in the matrix
	 */
	public SimpleGameData(int nbLines, int nbColumns) {
		
		if (nbLines < 1 || nbColumns < 1) {
			throw new IllegalArgumentException("at least one line and column");
		}
		matrix = new Cell[nbLines][nbColumns];
		MobsOnthePath = new ArrayList<Mobs>();
		placedTiles = new ArrayList<Tile>();
		GameContinue= true;
		emptyRoadTile = new ArrayList<>(path);
		emptyRoadTile.remove(0);
		emptyRoadSideTile = initRoadSide();
		emptyLandscapeTile = initLandscape();
		PlannificationMode = false;
		FightInfo = new ArrayList<>();
		
		
		//hero.addEquipmentsInInventory(List.of(new Shield("Grey", 2),new Armor("Grey", 2),new Weapon("Grey", 2)));

		
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
		System.out.println("Vous avez cliquï¿½ sur la case : "+selected);
		
		
		if (selected.line()>=13 && selected.line()<=14) { // si je vise l'endroit des cartes
			int indexInListCard = columnIntoIndexCard(column);
			if (indexInListCard>=0 && indexInListCard<hero.getHand().getList().size()) {
				SelectedCard = hero.getCardsList().get(indexInListCard);
			}
				//
			System.out.println(SelectedCard);  
			
			
		}else if (selected.line()>=6 && selected.line()<=9 && selected.column()>=24 && selected.column()<=28) {
			System.out.println("T'as sélectionné un équipement");
			int indexEquipmentSelected = 4*(selected.line()-6) + selected.column()-24;
			if (indexEquipmentSelected < hero.getInventory().getList().size()) {
				SelectedEquipment = hero.getInventory().getList().get(4*(selected.line()-6) + selected.column()-24);
			System.out.println("EQUIPEMMENNTTT SELECTIONNE : "+SelectedEquipment);
			}
			
		}
		
		// s'il clique sur la panoply et a un Equipment dans les mains
		else if (SelectedEquipment != null && selected.line()==3 && selected.column()>=24 && selected.column()<28) {
			placeEquipment();
			unselect();
		}
		
		else { // s'il clique sur la map
			if (SelectedCard != null) { // s'il a une carte dans les mains
				placeCard();
				unselect();
			}
			
		}
	}
	
	
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
			if (cardHasBeenPlaced && SelectedCard instanceof Rock) {
				hero.increaseMaximumHpPercentage(RockAdjacentsTileBonus(new RockTile(getSelected()))+1);
			}
		}else if (SelectedCard.getType().equals("Road")) {
			cardHasBeenPlaced = SelectedCard.placeTile(getSelected(), placedTiles, emptyRoadTile);
		}else {
			cardHasBeenPlaced = SelectedCard.placeTile(getSelected(), placedTiles, emptyRoadSideTile);
		}
			
		if (cardHasBeenPlaced) {
			System.out.println(hero.getHand());
			hero.getHand().remove(SelectedCard);
			System.out.println(hero.getHand());
			SelectedCard = null;
		}
		
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
			inFight=false;
		}
			
					
			
				
				if (day != TimeData.getDay() && TimeData.getDay()%2==0 && TimeData.getDay()!=0) {
					spawnRatwolf();	
				}
				if (day != TimeData.getDay()) {
					day++;
					spawnSlimes();
					hero.healValue(2);
				}
				
			}
			
		
		
	}
	
	/**
	 * Checks if bob is on the campFire or on a Mob and applies the corresponding effects
	 */
	public void checkCampFire() {
		if (bob.equals(FireCamp)) {
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
	 * Spawns Slimes based on the spawn rate 
	 */
	public void spawnSlimes() {
		for (GridPosition p:path) {
			
			if (!(p.equals(path.get(0)))){
				double random = new Random().nextDouble(1.0);
					if (random<=0.05) {
						MobsOnthePath.add(new Slime(p,LoopCount));
						}
				}
			}
			
		}


	
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
		System.out.println("---COMBAT---");
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
						}
					}else {
						HeroIsAttacked(m);
						isBobTurnInFight = true;
						isBobFightTarget = true;
						
						if (!hero.isAlive()) {
							GameContinue=false;
							FightInfo.add("Bob est mort ;(");
						}
						MobFightTarget++;
					}
					

					
				
				}
			
		}


	}
	
	
	private void MobIsAttacked(Mobs m) {
		System.out.println("HP du mob : "+m.getHp());
		int MobAttackedInfo = m.attacked(hero);
		if (MobAttackedInfo==1) {
			FightInfo.add("Bob a fait une attaque sur le "+m.getClass().getSimpleName()+" de "+Math.round(hero.attack())+" degats");
			if (m.getLastCounterAttackDamage() !=0) {
				FightInfo.add("Le "+m.getClass().getSimpleName()+" a contre attaqué...");
			}
		}else if (MobAttackedInfo==0) {
			FightInfo.add("Le "+m.getClass().getSimpleName()+" a equivé l'attaque...");
		} 
		
		
		System.out.println("HP du mob : "+m.getHp());

		
	}


	private void HeroIsAttacked(Mobs m) {
		int HeroAttackedInfo = hero.attacked(m);
		if (HeroAttackedInfo==1) {
			FightInfo.add("Le "+m.getClass().getSimpleName()+" a fait une attaque sur Bob de "+Math.round(m.attack())+" degats");
			if (hero.getLastCounterAttackDamage() !=0) {
				FightInfo.add("Bob a contre attaqué...");
			}
		}else if (HeroAttackedInfo==0) {
			FightInfo.add("Bob a equivé l'attaque...");
		} 
		
	}


	private void MobDead(Mobs m, ArrayList<Mobs> listOfMobs) {
		System.out.println("Monstre mort...");
		FightInfo.add("Le "+m.getClass().getSimpleName()+" est mort...");
		hero.winRessources(m.dropRessources());
		//FightInfo.add("Le "+m.getClass().getName()+" a fait droper un Equipement...");
		hero.addCardsInHand(m.dropCards());
		hero.addEquipmentsInInventory(m.dropEquipments(LoopCount));
		System.out.println("Inventaire du hero :"+hero.getInventory());
		listOfMobs.remove(m);
		MobsOnthePath.remove(m);
		
	}


	/**
	 * @param r the rock tile that we are checking
	 * @return the number of rock tiles that are adjacent to the Rock tile in parameter (knowing that a Rock tile increase 1% of maxHP )
	 */
	public int RockAdjacentsTileBonus(RockTile r){
		Objects.requireNonNull(r);
		List<List<Integer>> decal = List.of(List.of(0,1),List.of(1,0), List.of(-1,0), List.of(0,-1));  
		int count =0;
		for (List<Integer> c : decal) {
			GridPosition p = r.getPosition(); 
			if (p.column()+c.get(0)<nbColumns() && p.line()+c.get(1)<nbLines() && placedTiles.contains(new RockTile(new GridPosition(p.line()+c.get(1), p.column()+c.get(0))))) {
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
	
	
	
	/**
	 * Spawns a RatWolf at a random location adjacent of the Grove tile or on The groveTile
	 */
	public void spawnRatwolf() {
		for (Tile t : placedTiles) {
			if ( t instanceof GroveTile) {
				int tilePositionInPath = path.indexOf(t.getPosition());
				List<Integer> PotentialIndex = Arrays.asList(tilePositionInPath-1, tilePositionInPath,tilePositionInPath+1); 
				int randomPosition = new Random().nextInt(3);
				while (randomPosition==0) {
					randomPosition = new Random().nextInt(3);
				}
				MobsOnthePath.add(new Ratwolf(path.get(PotentialIndex.get(randomPosition)), LoopCount));
			}
		}
	}
	
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
		// selectionnÃ©es ou non...)
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
