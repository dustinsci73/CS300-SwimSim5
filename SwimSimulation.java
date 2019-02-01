import java.util.ArrayList;
import java.util.List;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.*;
public class SwimSimulation {
	private PApplet processing;
	private Hook [] hookPositions;
	private ArrayList<SimObject> objectFishAndFoodList;
	String[] strings;
	Fish fish1;
	Fish fish2;
	Fish fish3;
	Fish fish4;
	Food food1;
	Food food2;
	Food food3;
	Food food4;
	Food food5;
	Food food6;
	Hook hook1;
	
	public SwimSimulation(PApplet process) {
		this.processing = process;
		SimObject.setProcessing(process);
		try 
		{
			getData();
		}
		catch (RuntimeException e) {
			fish1 = new Fish();
			fish2 = new Fish();
			fish3 = new Fish();
			fish4 = new Fish();
			food1 = new Food();
			food2 = new Food();
			food3 = new Food();
			food4 = new Food();
			food5 = new Food();
			food6 = new Food();
			hook1 = new Hook();
			for (int i = 0; i < objectFishAndFoodList.size(); i++) {
					objectFishAndFoodList.remove(i); 
					i--;
			}
			objectFishAndFoodList.add(fish1);
			objectFishAndFoodList.add(fish2);
			objectFishAndFoodList.add(fish3);
			objectFishAndFoodList.add(fish4);
			objectFishAndFoodList.add(food1);
			objectFishAndFoodList.add(food2);
			objectFishAndFoodList.add(food3);
			objectFishAndFoodList.add(food4);
			objectFishAndFoodList.add(food5);
			objectFishAndFoodList.add(food6);
			this.hookPositions = new Hook[] {hook1};	
		}
	
	}

	public void update() {
		if (Math.random() < 0.05) {
			objectFishAndFoodList.add(new Food(Utility.randomInt(800),0));
		}
		processing.background(0,255,255);
		for (int i = 0; i < objectFishAndFoodList.size(); i++) {
			for (int j = 0; j < objectFishAndFoodList.size(); j++) {
				(objectFishAndFoodList.get(i)).tryToInteract(objectFishAndFoodList.get(j));
			}
		}
		for (int i = 0; i < hookPositions.length; i++) {
			for (int j = 0; j < objectFishAndFoodList.size(); j++) {
				(hookPositions[i]).tryToInteract(objectFishAndFoodList.get(j));
			}
		}
		for (int i = 0; i < objectFishAndFoodList.size(); i++) {
			(objectFishAndFoodList.get(i)).update();
		}
		for (int i = 0; i < hookPositions.length; i++) {
			(hookPositions[i]).update();
		}
		for (int i = 0; i < objectFishAndFoodList.size(); i++) {
			if (objectFishAndFoodList.get(i).shouldBeRemoved() == true) {
				objectFishAndFoodList.remove(i);
				i--;
			}
		}
	}

	public void handleClick(int mouseX, int mouseY) {
		for (int i = 0; i < hookPositions.length; i++) {
			((Hook) hookPositions[i]).handleClick(mouseX, mouseY);
		}	
	} 

	private void getData() {
		objectFishAndFoodList = new ArrayList<SimObject>();
		try {
			try {
				Files.readAllBytes(Paths.get("FileOptions.ssf"));
			} 
			catch (Exception e) {
				System.out.print("WARNING: Could not find or open the FileOptions.ssf file.");
				throw new RuntimeException();       
			}
			String line = new String(Files.readAllBytes(Paths.get("FileOptions.ssf"))); 
			String[] data = line.split(";");
			int x = Utility.randomInt(data.length); 
			String xFile = data[x]; 
			String xFileTwo = "";
			if (xFile.contains("/"))
			{
				String[] xFileOne = xFile.split("/");
				for (int i = 0; i < xFileOne.length; i++)
				{
					if (xFileOne[i].substring(0, 1).contains("D"))
					{
						xFileTwo = xFileOne[i].trim();
					}
				}
			}
			else
			{
				xFileTwo = xFile.trim();
			}
			try
			{
				Files.readAllLines(Paths.get(xFileTwo));
			}
			catch (Exception e) //checks to see if the .ssd file chosen exists
			{
				System.out.println("WARNING: Could not find or open the " + xFileTwo + " file.");
				throw new RuntimeException(); 
				//throws a runtime exception which will be caught in the 
			    //SwimSimulaton Constructor
			}
			List<String> lines = Files.readAllLines(Paths.get(xFileTwo));
			int counter = 0;
			String objTwo = "";
			for (int i = 0; i < lines.size(); i++) {
				String[] positions;
				String trim = lines.get(i).trim();
				if (trim.contains(":")) { 
					counter = 0;
					String[] obj = lines.get(i).split(":");
					objTwo = obj[0].trim().toUpperCase();
					String objOne = obj[1].trim();
					try {
						Integer.parseInt(objOne);
					} 
					catch (NumberFormatException e) {
						throw new RuntimeException("");
					}
					if (objTwo.equals("HOOK")) { 
						hookPositions = new Hook[Integer.parseInt(objOne)];
					}
				}
				else if (trim.contains(",")) {
					positions = trim.split(",");
					try {
						Integer.parseInt(positions[0].trim());
						Integer.parseInt(positions[1].trim());

					} 
					catch (NumberFormatException e) {
						System.out.println("WARNING: Missing specification for the number and "
								+ "initial positions of fishes, foods, or hook.” when any of the three "
								+ "sections is missing from the randomly chosen .ssd file.");
						throw new RuntimeException ();
					}
					int xPos = Integer.parseInt(positions[0].trim());
					int yPos = Integer.parseInt(positions[1].trim());
					if (objTwo.equals("HOOK")) {
						hookPositions[counter] = new Hook(xPos, yPos);							
					}
					if (objTwo.equals("FISH")) {
						objectFishAndFoodList.add(new Fish(xPos, yPos));
					}
					if (objTwo.equals("FOOD")) {
						objectFishAndFoodList.add(new Food(xPos, yPos));
					}	
					counter++;
				}
				else {
					continue; 		
				}
			}	
		}
		catch (IOException e) {
		}
	}	
}