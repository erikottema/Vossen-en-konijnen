package foxesandrabbits;

import java.util.*;
import java.awt.Color;
import java.util.Random;

public class PopulationGenerator {

    // The probability that a fox will be created in any given grid position.
    private static final double FOX_CREATION_PROBABILITY = 0.02;
    // The probability that a rabbit will be created in any given grid position.
    private static final double RABBIT_CREATION_PROBABILITY = 0.08;    
	//M Zorg dat je methodes van Simulator aan kunt roepen
	//private Simulator simulator;
	// The current state of the field.
    //private Field field;
    //M Location
    //private Location location;
    //depth and width
    // The default width for the grid.
    private static final int DEFAULT_WIDTH = 120;
    // The default depth of the grid.
    private static final int DEFAULT_DEPTH = 80;
    //M
    private int depth;
    //M
    private int width;
    //M
    private SimulatorView view;
    //M
    //private Field field;
    
    public PopulationGenerator(SimulatorView view1) {
    	 if(width <= 0 || depth <= 0) {
    	        System.out.println("The dimensions must be greater than zero.");
    	        System.out.println("Using default values.");
    	        this.depth = DEFAULT_DEPTH;
    	        this.width = DEFAULT_WIDTH;
    	 }
    	 
    	 this.view = view1;
    	 
    	 //M Define colors
         view.setColor(Rabbit.class, Color.orange);
         view.setColor(Fox.class, Color.blue);
    }

	public void populate(Field field, List<Animal> actors) {
		//Field field = new Field(depth, width);
		//this.field = new Field(depth, width);
	    Random rand = Randomizer.getRandom();
	    //field.clear();
	    for(int row = 0; row < field.getDepth(); row++) {
	        for(int col = 0; col < field.getWidth(); col++) {
	            if(rand.nextDouble() <= FOX_CREATION_PROBABILITY) {
	                Location location = new Location(row, col);
	                Fox fox = new Fox(true, field, location);
	                actors.add(fox);
	                //simulator.addAnimal(fox);
	                field.place(fox, location);
	            }
	            else if(rand.nextDouble() <= RABBIT_CREATION_PROBABILITY) {
	                Location location = new Location(row, col);
	                Rabbit rabbit = new Rabbit(true, field, location);
	                actors.add(rabbit);
	                //simulator.addAnimal(rabbit);
	                field.place(rabbit, location);
	            }
	            // else leave the location empty.
	        }
	    }
	    //return field;
	   view.showStatus(0, field);
	}
}
