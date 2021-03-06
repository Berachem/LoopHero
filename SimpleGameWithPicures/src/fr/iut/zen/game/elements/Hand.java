package fr.iut.zen.game.elements;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import fr.iut.zen.game.elements.cards.Card;


public class Hand implements Serializable{
	

	private static final long serialVersionUID = -498722421159501745L;
	private final List<Card> list;
	private final int sizeLimit = 13;

	public Hand(){
		this.list = new ArrayList<Card>();
	}

	public void remove(Card c){
		Objects.requireNonNull(c);
		list.remove(c);
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

	public List<Card> getList() {
		return list;
	}
	
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("----Hand----\n");
		for (Card c : list) {
			s.append(c).append("\n");
		}
		
		s.append("-------------\n");
		return s.toString();
	}
	
	
}