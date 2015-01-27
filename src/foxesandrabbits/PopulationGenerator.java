package foxesandrabbits;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PopulationGenerator {

	// Kans dat een vos gecreeërd wordt.
	private static final double FOX_CREATION_PROBABILITY = 0.02;
	// Kans dat een konijn gecreeërd wordt.
	private static final double RABBIT_CREATION_PROBABILITY = 0.08;
	private List<Animal> animals;
	//M
	private SimulatorView view;

	public PopulationGenerator(SimulatorView view) // hier roep ik de view aan die in Simulator is aangemaakt
													 
	{
		animals = new ArrayList<Animal>();
		
		view.setColor(Rabbit.class, Color.ORANGE);
		view.setColor(Fox.class, Color.BLUE);
		
		this.view=view;
	}

	private void populate(Field field) {
		Random rand = Randomizer.getRandom();
		field.clear();
		for (int row = 0; row < field.getDepth(); row++) {
			for (int col = 0; col < field.getWidth(); col++) {
				if (rand.nextDouble() <= FOX_CREATION_PROBABILITY) {
					Location location = new Location(row, col);
					Fox fox = new Fox(true, field, location);
					animals.add(fox);
				} else if (rand.nextDouble() <= RABBIT_CREATION_PROBABILITY) {
					Location location = new Location(row, col);
					Rabbit rabbit = new Rabbit(true, field, location);
					animals.add(rabbit);
				}
				// else leave the location empty.
			}
		}
		view.showStatus(0,field);
	}
}