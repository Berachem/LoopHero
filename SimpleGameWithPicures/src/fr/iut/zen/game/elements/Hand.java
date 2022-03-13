import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Hand{
	private final List<Card> list;
	private final int sizeLimit = 13;

	public Hand(){
		this.list = new ArrayList<Card>();
	}

	public void remove(Card c){
		Objects.requireNonNull(c);
		list.remove(c);
		// "Lorsqu’elles sont détruites,elles sont remises dans cette réserve." ???
	}
	
	public void add(Card c){
		Objects.requireNonNull(c);
		if (list.size() < sizeLimit){
			list.add(c);
		}
		else {
			list.remove(0);
			list.add(c);
		}
	}
}