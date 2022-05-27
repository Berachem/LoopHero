package fr.iut.zen.game;

import java.io.Serializable;

/**
 * 
 */
public record GridPosition (int line, int column) implements Serializable {}
