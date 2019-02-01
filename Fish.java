public class Fish extends SimObject {
	public Fish () {
		super("images" + java.io.File.separator + "FISH.png");
	}

	public Fish (int x, int y) {
		super("images" + java.io.File.separator + "FISH.png", x, y);
	}
	
	@Override
	public void update() { 
		processing.image(simImage, positionX, positionY);
		if (positionX < processing.width) {
			positionX = positionX + 1;
		}
		else {
			positionX = 0;
		}
	}		
	
	@Override
	public void tryToInteract(SimObject food) {
		if (food instanceof Food) {
			float distance = food.distanceTo(positionX, positionY);
			if (distance <= 40) {
				((Food)food).getEaten();
			}
		}
	}
	
	public void getCaught() {
		positionX = 0;
	} 
}