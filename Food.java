public class Food extends SimObject {
	public Food () {
		super("images" + java.io.File.separator + "FOOD.png");
	}
	
	public Food (int x, int y) {
		super("images" + java.io.File.separator + "FOOD.png", x, y);
	}
	
	@Override
	public void update() {  
		processing.image(simImage, positionX, positionY);
		if (positionX > 0) {
			positionX = positionX - 1;
		}
		else {
			positionX = processing.width;
		}	
		if (positionY < processing.height) {
			positionY = positionY + 1;
		}
		else { 
			positionY = 0;
		}
	}
	
	public void getEaten() {
		eaten = true;
	} 
}