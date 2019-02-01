public class Hook extends SimObject {
	public Hook () {
		super("images" + java.io.File.separator + "HOOK.png");
	}
	
	public Hook (int x, int y) {
		super("images" + java.io.File.separator + "HOOK.png", x, y);
	}

	@Override
	public void update() { 
		processing.image(simImage, positionX, positionY);
		processing.line(positionX + 5, positionY, positionX + 5, 0);
		if (positionY > 0) {
			positionY = positionY -(processing.height + 50 - positionY)/50;
		}
		else {
			positionY = processing.height;
		}
	}	
	
	public void handleClick(int mouseX, int mouseY) {
		positionX = mouseX;
		positionY = 	processing.height;	
	}
	
	@Override
	public void tryToInteract(SimObject fish) {
		if (fish instanceof Fish) {
			float distance = fish.distanceTo(positionX, positionY);
			if (distance <= 40) {
				((Fish)fish).getCaught();
			}
		}
	}
}