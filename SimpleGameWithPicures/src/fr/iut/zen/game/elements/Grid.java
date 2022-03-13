import java.util.ArrayList;

public class Grid{
  private final int nbLines;
  private final int nbColumn;
	private ArrayList<ArrayList<Box>> grid;

	public Grid(int lines , int columns){
		this.nbLines = lines;
		this.nbColumn = columns;
		createGrid(nbLines,nbColumn);
	}
	public void createGrid(int lines , int columns){
	ArrayList<ArrayList<Box>> TempGrid = new ArrayList<>();

		for (int i=0;i<lines;i++){
			ArrayList<Box> tempLine = new ArrayList<>();
			for (int j=0; j<columns;j++){
        tempLine.add(new Box(j,i));
      }
			TempGrid.add(tempLine);
		}
		this.grid = TempGrid;
	}
	@Override
  public String toString(){
		StringBuilder s = new StringBuilder();
    for (ArrayList<Box> a : grid){
			s.append(a+"\n\n");
		}
		return s.toString();
  }

	public ArrayList<Box> getPath(){
		ArrayList<Box> Path = new ArrayList<>();
		// A FAIRE 
		return Path;
		
	}

	
}