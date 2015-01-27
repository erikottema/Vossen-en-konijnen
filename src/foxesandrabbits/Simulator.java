package foxesandrabbits;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/**
 * A simple predator-prey simulator, based on a rectangular field
 * containing rabbits and foxes.
 * 
 * @author David J. Barnes and Michael K�lling
 * @version 2011.07.31
 */
public class Simulator extends JFrame implements ActionListener
{
    // Constants representing configuration information for the simulation.
    // The default width for the grid.
    private static final int DEFAULT_WIDTH = 120;
    // The default depth of the grid.
    private static final int DEFAULT_DEPTH = 80;
    // The probability that a fox will be created in any given grid position.
    //----------@Sara, deze heb ik verplaatst naar PopulationGenerator en hier uitgezet
    //private static final double FOX_CREATION_PROBABILITY = 0.02;
    // The probability that a rabbit will be created in any given grid position.
    //----------@Sara, deze heb ik verplaatst naar PopulationGenerator en hier uitgezet
    //private static final double RABBIT_CREATION_PROBABILITY = 0.08;    

    // List of animals in the field.
    //----------@Sara, deze heb ik verplaatst naar PopulationGenerator en hier uitgezet
    //private List<Animal> animals;
    // The current state of the field.
    private Field field;
    // The current step of the simulation.
    private int step;
    // A graphical view of the simulation.
    private SimulatorView view;
    //------------@SARA hier wordt PopulationGenerator aangeroepen.
    
    
    private JButton button1;
    private JButton button2;
    
    /**
     * Construct a simulation field with default size.
     */
    public Simulator()
    {
        this(DEFAULT_DEPTH, DEFAULT_WIDTH);
    }
    
    public static void main(String[] args) {
    	new Simulator();
    	//--------@Sara, hieronder roep ik PopulationGenerator aan, bij het aanroepen vd Simulator
    	
    	
    	
    	
    }
    
    /**
     * Create a simulation field with the given size.
     * @param depth Depth of the field. Must be greater than zero.
     * @param width Width of the field. Must be greater than zero.
     */
    public Simulator(int depth, int width) 
    {
        if(width <= 0 || depth <= 0) {
            System.out.println("The dimensions must be greater than zero.");
            System.out.println("Using default values.");
            depth = DEFAULT_DEPTH;
            width = DEFAULT_WIDTH;
        }
        
       // ----------@Sara, deze heb ik verplaatst naar PopulationGenerator en hier uitgezet
       // animals = new ArrayList<Animal>();
        
        field = new Field(depth, width);

        // Create a view of the state of each location in the field.
        view = new SimulatorView(depth, width);
        
        
        view.setColor(Rabbit.class, Color.ORANGE);
        view.setColor(Fox.class, Color.BLUE);
        PopulationGenerator populationgenerator = new PopulationGenerator(view);
        // Setup a valid starting point.
        reset();
        
        //button 1
    	button1 = new JButton("Step 1");
        view.add(button1, BorderLayout.WEST);
        simulateOneStep();
        
        //button 2
    	button2 = new JButton("Step 100");
        view.add(button2, BorderLayout.EAST);
        
		button1.addActionListener(new ActionListener() 
		{

			public void actionPerformed(ActionEvent evt) 
			{
				simulateOneStep();
			}
		});
		button2.addActionListener(new ActionListener() {
			  public void actionPerformed(ActionEvent evt) {
			    simulate(100);
			  }
			});

		
    
    }
    /**
     * Run the simulation from its current state for a reasonably long period,
     * (4000 steps).
     */
    public void runLongSimulation()
    {
        simulate(4000);
    }
    
    public 
    
    /**
     * Run the simulation from its current state for the given number of steps.
     * Stop before the given number of steps if it ceases to be viable.
     * @param numSteps The number of steps to run for.
     */
    public void simulate(int numSteps)
    {
        for(int step = 1; step <= numSteps && view.isViable(field); step++) {
            simulateOneStep();
        }
    }
    
    /**
     * Run the simulation from its current state for a single step.
     * Iterate over the whole field updating the state of each
     * fox and rabbit.
     */
    public void simulateOneStep()
    {
        step++;

        // Provide space for newborn animals.
        List<Animal> newAnimals = new ArrayList<Animal>();        
        // Let all rabbits act.
        for(Iterator<Animal> it = animals.iterator(); it.hasNext(); ) {
            Animal animal = it.next();
            animal.act(newAnimals);
            if(! animal.isAlive()) {
                it.remove();
            }
        }
               
        // Add the newly born foxes and rabbits to the main lists.
        animals.addAll(newAnimals);

        view.showStatus(step, field);
    }
        
    /**
     * Reset the simulation to a starting position.
     */
    public void reset()
    {
        step = 0;
        animals.clear();
        populationgenerator.populate();
        
        // Show the starting state in the view.
        //view.showStatus(step, field);
    }
    
    /**
     * Randomly populate the field with foxes and rabbits.
     */
    // ----------@Sara, deze heb ik verplaatst naar PopulationGenerator en hier uitgezet
    /*private void populate()
    {
        Random rand = Randomizer.getRandom();
        field.clear();
        for(int row = 0; row < field.getDepth(); row++) {
            for(int col = 0; col < field.getWidth(); col++) {
                if(rand.nextDouble() <= FOX_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Fox fox = new Fox(true, field, location);
                    animals.add(fox);
                }
                else if(rand.nextDouble() <= RABBIT_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Rabbit rabbit = new Rabbit(true, field, location);
                    animals.add(rabbit);
                }
                // else leave the location empty.
            }
        }
    }*/
}