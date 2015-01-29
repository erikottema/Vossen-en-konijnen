package foxesandrabbits;

import java.util.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import foxesandrabbits.Simulator;

/**
 * A simple predator-prey simulator, based on a rectangular field containing
 * rabbits and foxes.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2011.07.31
 */
public class Simulator extends JFrame implements ActionListener, Runnable {
	// Constants representing configuration information for the simulation.

	// M Verplaatst naar PopulationGenerator
	// The probability that a fox will be created in any given grid position.
	// private static final double FOX_CREATION_PROBABILITY = 0.02;
	// The probability that a rabbit will be created in any given grid position.
	// private static final double RABBIT_CREATION_PROBABILITY = 0.08;

	// The default width for the grid.
	private static final int DEFAULT_WIDTH = 120;
	// The default depth of the grid.
	private static final int DEFAULT_DEPTH = 80;

	// List of animals in the field.
	private List<Animal> animals;
	// The current state of the field.
	public Field field;
	// The current step of the simulation.
	private int step;
	// A graphical view of the simulation.
	private SimulatorView view;
	// buttons
	private JButton button1;
	private JButton button2;
	private JButton button3;
	private JButton button4;

	// M Runner
	private boolean run;

	public static void main(String[] args) {
		new Simulator();
	}

	/**
	 * Create a simulation field with the given size.
	 * 
	 * @param depth
	 *            Depth of the field. Must be greater than zero.
	 * @param width
	 *            Width of the field. Must be greater than zero.
	 */
	public Simulator() {
		animals = new ArrayList<Animal>();

		// Create a view of the state of each location in the field.
		view = new SimulatorView(DEFAULT_DEPTH, DEFAULT_WIDTH);
		field = new Field(DEFAULT_DEPTH, DEFAULT_WIDTH);
		// PopulationGenerator pop = new PopulationGenerator();

		// view.setColor(Rabbit.class, Color.orange);
		// view.setColor(Fox.class, Color.blue);

		PopulationGenerator pop = new PopulationGenerator(view);

		step = 0;
		animals.clear();

		pop.populate(field, animals);
		// this.field = pop.populate();
		// pop.populate(new SimulatorView(depth, width));
		// this.field = pop.populate(field);

		// Show the starting state in the view.
		// view.showStatus(step, pop.populate());
		// M Moved to PopGen
		// view.setColor(Rabbit.class, Color.orange);
		// view.setColor(Fox.class, Color.blue);

		// Setup a valid starting point.
		// reset();

		// M show view
		// view.showStatus(step, field);

		// button1
		button1 = new JButton("Step 1");
		view.add(button1, BorderLayout.WEST);
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				simulateOneStep();
			}
		});

		button2 = new JButton("Step 100");
		view.add(button2, BorderLayout.EAST);
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				simulate(100);
			}
		});

		// button 3
		button3 = new JButton("Start");
		view.add(button3, BorderLayout.NORTH);
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				Simulator.this.start();
			}
		});

		// button 4
		button4 = new JButton("Stop");
		view.add(button4, BorderLayout.SOUTH);
		button4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				;
				Simulator.this.stop();
			}
		});
		
		//DIT IS DE INTROMUZIEK
		new Music2();
		
	}

	// M Runner methods
	public void start() {
		new Thread(this).start();
	}

	public void stop() {
		run = false;
	}

	public void run() {
		run = true;
		while (run) {
			simulateOneStep();
			try {
				Thread.sleep(100);
			} catch (Exception e) {
			}
		}
	}

	/**
	 * Run the simulation from its current state for a reasonably long period,
	 * (4000 steps).
	 */
	public void runLongSimulation() {
		simulate(4000);
	}

	/**
	 * Run the simulation from its current state for the given number of steps.
	 * Stop before the given number of steps if it ceases to be viable.
	 * 
	 * @param numSteps
	 *            The number of steps to run for.
	 */
	public void simulate(int numSteps) {
		for (int step = 1; step <= numSteps && view.isViable(field); step++) {
			simulateOneStep();
		}
	}

	/**
	 * Run the simulation from its current state for a single step. Iterate over
	 * the whole field updating the state of each fox and rabbit.
	 */
	public void simulateOneStep() {
		step++;

		// Provide space for newborn animals.
		List<Animal> newAnimals = new ArrayList<Animal>();
		// Let all rabbits act.
		for (Iterator<Animal> it = animals.iterator(); it.hasNext();) {
			Animal animal = it.next();
			animal.act(newAnimals);
			if (!animal.isAlive()) {
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

	/*
	 * public void reset() { step = 0; animals.clear(); //this.field =
	 * pop.populate(); //pop.populate(new SimulatorView(depth, width));
	 * pop.populate(field); // Show the starting state in the view.
	 * //view.showStatus(step, pop.populate()); }
	 */

	// M Methode om een dier in de arraylist toe te voegen vanuit
	// PopulationGenerator
	public void addAnimal(Animal a) {
		animals.add(a);
	}

	/*
	 * Randomly populate the field with foxes and rabbits.
	 * 
	 * private void populate() { Random rand = Randomizer.getRandom();
	 * field.clear(); for(int row = 0; row < field.getDepth(); row++) { for(int
	 * col = 0; col < field.getWidth(); col++) { if(rand.nextDouble() <=
	 * FOX_CREATION_PROBABILITY) { Location location = new Location(row, col);
	 * Fox fox = new Fox(true, field, location); animals.add(fox); } else
	 * if(rand.nextDouble() <= RABBIT_CREATION_PROBABILITY) { Location location
	 * = new Location(row, col); Rabbit rabbit = new Rabbit(true, field,
	 * location); animals.add(rabbit); } // else leave the location empty. } } }
	 */
}
