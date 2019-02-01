
public class SimObject 
{
	protected int positionX;
	protected int positionY;
	protected static PApplet processing;
	protected PImage simImage;
	protected boolean eaten;
	
	public static void setProcessing(PApplet processing) 
	{
		SimObject.processing = processing;
	}

	
	public SimObject (String imagePath)
	{
		if (processing == null)
		{
			throw new IllegalStateException("SimObject.setProcessing() must be called before constructing "
					+ "any SimObjects.");
		}
		else 
		{
			simImage = processing.loadImage(imagePath);
			positionX = Utility.randomInt(processing.width);
			positionY = Utility.randomInt(processing.height);
			eaten = false;
		}
	}
	
	public SimObject (String imagePath, int x, int y)
	{
		if (processing == null)
		{
			throw new IllegalStateException("SimObject.setProcessing() must be called before constructing "
					+ "any SimObjects.");
		}
		else 
		{
			simImage = processing.loadImage(imagePath);
			positionX = x;
			positionY = y;
			eaten = false;
		}
	}
	
	public float distanceTo(int x, int y) 
	{
		float distance = (float) Math.sqrt(Math.pow(x - positionX, 2) + Math.pow(y - positionY, 2));
		return distance; // sqrt((x2-x1)^2 + (y2-y1)^2)
	}
	
	public void update() { }
	public void tryToInteract(SimObject other) { }
	public boolean shouldBeRemoved()
	{
		return eaten;
	}
}
