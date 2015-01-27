package foxesandrabbits;

import javax.swing.*;

@SuppressWarnings("serial")
public class Viewr extends JPanel {
	private Model model;
	
	public Viewr(Model model) {
		this.model=model;
		model.addView(this);
		setSize(200, 200);
		setVisible(true);
	}
	
	public void setModel(Model model) {
		this.model=model;
	}
	
	public Model getModel() {
		return model;
	}
	
	public void updateView() {
		repaint();
	}
}
