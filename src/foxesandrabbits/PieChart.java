package foxesandrabbits;

import java.awt.*;



@SuppressWarnings("serial")
public class PieChart extends Viewr {

	public PieChart(Model model) {
			super(model);
	}

	public void paintComponent(Graphics g) {
		int aantal=25;
		int aantal2=3;
		int aantal3=40;
		int aantal4=10;
		int aantal5=90;
		
		double avg = (aantal + aantal2 + aantal3 + aantal4 + aantal5)/360;
		double pie1=aantal/avg;
		double pie2=aantal2/avg;
		double pie3=aantal3/avg;
		double pie4=aantal4/avg;
		double pie5=aantal5/avg;
		
		
		
		
		int piedef1 = (int)(pie1);
		int piedef2 = (int)(pie2);
		int piedef3 = (int)(pie3);
		int piedef4 = (int)(pie4);
		int piedef5 = (int)(pie5);
		
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 200, 200);
		g.setColor(Color.BLUE);		
		g.fillArc(10, 10, 180, 180, 0, piedef1);
		g.setColor(Color.RED);		
		g.fillArc(10, 10, 180, 180, piedef1, piedef2);
		g.setColor(Color.GREEN);		
		g.fillArc(10, 10, 180, 180, piedef2, piedef3);
		g.setColor(Color.YELLOW);		
		g.fillArc(10, 10, 180, 180, piedef3, piedef4);
		g.setColor(Color.PINK);		
		g.fillArc(10, 10, 180, 180, piedef4, piedef5);
		
	}

//public static void main(String[] args) {
	//	paintComponent();
	//}

}