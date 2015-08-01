import javax.swing.JApplet;


public class PathFinderApplet extends JApplet {

	/**
	 * Create the applet.
	 */
	public PathFinderApplet() {
		PathFinderApp panel = new PathFinderApp();
		setContentPane(panel);

	}
	public void init(){
		setSize(700, 500);
	}

}
