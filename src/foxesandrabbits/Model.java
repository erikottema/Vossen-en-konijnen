package foxesandrabbits;

import java.util.ArrayList;
import java.util.List;



public class Model {
	private int aantal;
	private List<Viewr> views;
	
	public Model() {
		views=new ArrayList<Viewr>();
	}
	
	public int getAantal() {
		return aantal;
	}
	
	public void setAantal(int aantal) {
		if (aantal>=0 && aantal <=360) {
			this.aantal=aantal;
			notifyViews();
		}
	}
	
	public void addView(Viewr view) {
		views.add(view);
	}
	
	private void notifyViews() {
		for(Viewr v: views) v.updateView();
	}
}