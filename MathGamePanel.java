import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class MathGamePanel extends JPanel{

	BufferedImage background;
	int BACKGROUND_X = -200;
	int BACKGROUND_Y = -300;

	public void paintComponent(Graphics g) {
		//.setColor(Color.BLACK);
		g.drawImage(background, BACKGROUND_X, BACKGROUND_Y, null);
	}

	public MathGamePanel() {
		super();

		try{
			background = ImageIO.read(new File("mathbackground.jpg"));
		}catch(IOException e){
			System.out.println("Unable to load background");
		}

	}

}
