import java.awt.Color;
import java.util.Random;
import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {
		JFrame myFrame = new JFrame("Minesweeper Game");
		myFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		myFrame.setLocation(400, 150);
		myFrame.setSize(900, 900);

		MyPanel myPanel = new MyPanel();
		myFrame.add(myPanel);

		MyMouseAdapter myMouseAdapter = new MyMouseAdapter();
		myFrame.addMouseListener(myMouseAdapter);

		myFrame.setVisible(true);
		Random a = new Random();
		Random b = new Random();
		
	for(int i = 0;i<=10;i++){
			int rx = a.nextInt(9);
			int ry = b.nextInt(9);
			myPanel.colorArray[rx][ry] = Color.BLACK;
			myPanel.isMine[rx][ry] = true; 
			}
	}
}
