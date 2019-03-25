package aventure.models.adventurer;

import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.LinkedList;

import org.junit.Assert;
import org.junit.Test;

import adventure.enums.CaseTypeEnum;
import adventure.enums.CommandEnum;
import adventure.models.adventurer.Adventurer;
import adventure.models.adventurer.behavior.impl.EastDirection;
import adventure.models.adventurer.behavior.impl.NorthDirection;
import adventure.models.adventurer.behavior.impl.SouthDirection;
import adventure.models.adventurer.behavior.impl.WestDirection;
import adventure.models.collect.Treasure;
import adventure.models.worldmap.WorldMap;
import adventure.services.IMapService;
import adventure.services.impl.MapServiceImpl;


public class AdventurerTest {

	IMapService mapService = new MapServiceImpl();
	
	@Test
	public void testNorthDirectionMove(){
		System.out.println("------ TEST NORTH DIRECTION MOVE ------\n");
		int x = 0;
		int y = 1;
		Adventurer adventurer = new Adventurer("a", x, y, new NorthDirection(), new LinkedList<>(Arrays.asList(CommandEnum.AVANCER)));
		WorldMap worldMap = new WorldMap(1,2);
		worldMap.setAdventurers(Arrays.asList(adventurer));
				
		mapService.printMap(worldMap);
		mapService.runMap(worldMap);
		
		Assert.assertTrue(adventurer.getX().equals(x));
		Assert.assertTrue(adventurer.getY().equals(y-1));
	}
	
	@Test
	public void testSouthDirectionMove(){
		System.out.println("------ TEST SOUTH DIRECTION MOVE ------\n");
		int x = 0;
		int y = 0;
		Adventurer adventurer = new Adventurer("a", x, y, new SouthDirection(), new LinkedList<>(Arrays.asList(CommandEnum.AVANCER)));
		WorldMap worldMap = new WorldMap(1,2);
		worldMap.setAdventurers(Arrays.asList(adventurer));
			
		mapService.printMap(worldMap);
		mapService.runMap(worldMap);
		
		Assert.assertTrue(adventurer.getX().equals(x));
		Assert.assertTrue(adventurer.getY().equals(y+1));
	}
	
	@Test
	public void testEastDirectionMove(){
		System.out.println("------ TEST EAST DIRECTION MOVE ------\n");
		int x = 0;
		int y = 0;
		Adventurer adventurer = new Adventurer("a", x, y, new EastDirection(), new LinkedList<>(Arrays.asList(CommandEnum.AVANCER)));
		WorldMap worldMap = new WorldMap(2,1);
		worldMap.setAdventurers(Arrays.asList(adventurer));
		
		mapService.printMap(worldMap);
		mapService.runMap(worldMap);
		
		Assert.assertTrue(adventurer.getX().equals(x+1));
		Assert.assertTrue(adventurer.getY().equals(y));
	}
	
	@Test
	public void testWestDirectionMove(){
		System.out.println("------ TEST WEST DIRECTION MOVE ------\n");
		int x = 1;
		int y = 0;
		Adventurer adventurer = new Adventurer("a", x, y, new WestDirection(), new LinkedList<>(Arrays.asList(CommandEnum.AVANCER)));
		WorldMap worldMap = new WorldMap(2,1);
		worldMap.setAdventurers(Arrays.asList(adventurer));
			
		mapService.printMap(worldMap);
		mapService.runMap(worldMap);
		
		Assert.assertTrue(adventurer.getX().equals(x-1));
		Assert.assertTrue(adventurer.getY().equals(y));
	}
	
	@Test
	public void testNorthDirectionMoveToMountainCase(){
		System.out.println("------ TEST NORTH DIRECTION MOVE TO MOUNTAIN CASE ------\n");
		int x = 0;
		int y = 1;
		Adventurer adventurer = new Adventurer("a", x, y, new NorthDirection(), new LinkedList<>(Arrays.asList(CommandEnum.AVANCER)));
		WorldMap worldMap = new WorldMap(1,2);
		worldMap.getWorldBox(0, 0).setCaseType(CaseTypeEnum.MONTAGNE);
		worldMap.setAdventurers(Arrays.asList(adventurer));
				
		mapService.printMap(worldMap);
		mapService.runMap(worldMap);
		
		Assert.assertTrue(adventurer.getX().equals(x));
		Assert.assertTrue(adventurer.getY().equals(y));
	}
	
	@Test
	public void testSouthDirectionMoveToMountainCase(){
		System.out.println("------ TEST SOUTH DIRECTION MOVE TO MOUNTAIN CASE ------\n");
		int x = 0;
		int y = 0;
		Adventurer adventurer = new Adventurer("a", x, y, new SouthDirection(), new LinkedList<>(Arrays.asList(CommandEnum.AVANCER)));
		WorldMap worldMap = new WorldMap(1,2);
		worldMap.getWorldBox(0, 1).setCaseType(CaseTypeEnum.MONTAGNE);
		worldMap.setAdventurers(Arrays.asList(adventurer));		
		
		mapService.printMap(worldMap);
		mapService.runMap(worldMap);
		
		Assert.assertTrue(adventurer.getX().equals(x));
		Assert.assertTrue(adventurer.getY().equals(y));
	}
	
	@Test
	public void testEastDirectionMoveToMountainCase(){
		System.out.println("------ TEST EAST DIRECTION MOVE TO MOUNTAIN CASE ------\n");
		int x = 1;
		int y = 0;
		Adventurer adventurer = new Adventurer("a", x, y, new EastDirection(), new LinkedList<>(Arrays.asList(CommandEnum.AVANCER)));
		WorldMap worldMap = new WorldMap(2,1);
		worldMap.getWorldBox(0, 0).setCaseType(CaseTypeEnum.MONTAGNE);
		worldMap.setAdventurers(Arrays.asList(adventurer));	
		
		mapService.printMap(worldMap);
		mapService.runMap(worldMap);
		
		Assert.assertTrue(adventurer.getX().equals(x));
		Assert.assertTrue(adventurer.getY().equals(y));
	}
	
	@Test
	public void testWestDirectionMoveToMountainCase(){
		System.out.println("------ TEST WEST DIRECTION MOVE TO MOUNTAIN CASE ------\n");
		int x = 0;
		int y = 0;
		Adventurer adventurer = new Adventurer("a", x, y, new WestDirection(), new LinkedList<>(Arrays.asList(CommandEnum.AVANCER)));
		WorldMap worldMap = new WorldMap(2,1);
		worldMap.getWorldBox(1, 0).setCaseType(CaseTypeEnum.MONTAGNE);
		worldMap.setAdventurers(Arrays.asList(adventurer));
		
		mapService.printMap(worldMap);
		mapService.runMap(worldMap);
		
		Assert.assertTrue(adventurer.getX().equals(x));
		Assert.assertTrue(adventurer.getY().equals(y));
	}
		
	@Test
	public void testNorthDirectionMoveOOB(){
		System.out.println("------ TEST NORTH DIRECTION MOVE OUT OF BOUND ------\n");
		int x = 0;
		int y = 0;
		Adventurer adventurer = new Adventurer("a", x, y, new NorthDirection(), new LinkedList<>(Arrays.asList(CommandEnum.AVANCER)));
		WorldMap worldMap = new WorldMap(1,1);
		worldMap.setAdventurers(Arrays.asList(adventurer));
		
		mapService.printMap(worldMap);
		mapService.runMap(worldMap);
		
		Assert.assertTrue(adventurer.getX().equals(x));
		Assert.assertTrue(adventurer.getY().equals(y));
	}
	
	@Test
	public void testSouthDirectionMoveOOB(){
		System.out.println("------ TEST SOUTH DIRECTION MOVE OUT OF BOUND ------\n");
		int x = 0;
		int y = 0;
		Adventurer adventurer = new Adventurer("a", x, y, new SouthDirection(), new LinkedList<>(Arrays.asList(CommandEnum.AVANCER)));
		WorldMap worldMap = new WorldMap(1,1);
		worldMap.setAdventurers(Arrays.asList(adventurer));
		
		mapService.printMap(worldMap);
		mapService.runMap(worldMap);
		
		Assert.assertTrue(adventurer.getX().equals(x));
		Assert.assertTrue(adventurer.getY().equals(y));
	}
	
	@Test
	public void testEastDirectionMoveOOB(){
		System.out.println("------ TEST EAST DIRECTION MOVE OUT OF BOUND ------\n");
		int x = 0;
		int y = 0;
		Adventurer adventurer = new Adventurer("a", x, y, new EastDirection(), new LinkedList<>(Arrays.asList(CommandEnum.AVANCER)));
		WorldMap worldMap = new WorldMap(1,1);
		worldMap.setAdventurers(Arrays.asList(adventurer));
		
		mapService.printMap(worldMap);
		mapService.runMap(worldMap);
		
		Assert.assertTrue(adventurer.getX().equals(x));
		Assert.assertTrue(adventurer.getY().equals(y));
	}
	
	@Test
	public void testWestDirectionMoveOOB(){
		System.out.println("------ TEST WEST DIRECTION MOVE OUT OF BOUND ------\n");
		int x = 0;
		int y = 0;
		Adventurer adventurer = new Adventurer("a", x, y, new WestDirection(), new LinkedList<>(Arrays.asList(CommandEnum.AVANCER)));
		WorldMap worldMap = new WorldMap(1,1);
		worldMap.setAdventurers(Arrays.asList(adventurer));
		
		mapService.printMap(worldMap);
		mapService.runMap(worldMap);
		
		Assert.assertTrue(adventurer.getX().equals(x));
		Assert.assertTrue(adventurer.getY().equals(y));
	}
	
	@Test
	public void testNorthDirectionTurnRight(){
		System.out.println("------ TEST NORTH DIRECTION TURN RIGHT ------\n");
		int x = 0;
		int y = 0;
		Adventurer adventurer = new Adventurer("a", x, y, new NorthDirection(), new LinkedList<>(Arrays.asList(CommandEnum.TOURNER_DROITE)));
		WorldMap worldMap = new WorldMap(1,1);
		worldMap.setAdventurers(Arrays.asList(adventurer));
		
		mapService.printMap(worldMap);
		mapService.runMap(worldMap);
		
		assertNotNull(adventurer.getDirection());
		Assert.assertTrue(adventurer.getDirection().equals(new EastDirection()));
	}
	
	@Test
	public void testSouthDirectionTurnRight(){
		System.out.println("------ TEST SOUTH DIRECTION TURN RIGHT ------\n");
		int x = 0;
		int y = 0;
		Adventurer adventurer = new Adventurer("a", x, y, new SouthDirection(), new LinkedList<>(Arrays.asList(CommandEnum.TOURNER_DROITE)));
		WorldMap worldMap = new WorldMap(1,1);
		worldMap.setAdventurers(Arrays.asList(adventurer));
		
		mapService.printMap(worldMap);
		mapService.runMap(worldMap);
		
		assertNotNull(adventurer.getDirection());
		Assert.assertTrue(adventurer.getDirection().equals(new WestDirection()));
	}
	
	@Test
	public void testEastDirectionTurnRight(){
		System.out.println("------ TEST EAST DIRECTION TURN RIGHT ------\n");
		int x = 0;
		int y = 0;
		Adventurer adventurer = new Adventurer("a", x, y, new EastDirection(), new LinkedList<>(Arrays.asList(CommandEnum.TOURNER_DROITE)));
		WorldMap worldMap = new WorldMap(1,1);
		worldMap.setAdventurers(Arrays.asList(adventurer));
		
		mapService.printMap(worldMap);
		mapService.runMap(worldMap);
		
		assertNotNull(adventurer.getDirection());
		Assert.assertTrue(adventurer.getDirection().equals(new SouthDirection()));
	}
	
	@Test
	public void testWestDirectionTurnRight(){
		System.out.println("------ TEST WEST DIRECTION TURN RIGHT ------\n");
		int x = 0;
		int y = 0;
		Adventurer adventurer = new Adventurer("a", x, y, new WestDirection(), new LinkedList<>(Arrays.asList(CommandEnum.TOURNER_DROITE)));
		WorldMap worldMap = new WorldMap(1,1);
		worldMap.setAdventurers(Arrays.asList(adventurer));
		
		mapService.printMap(worldMap);
		mapService.runMap(worldMap);
		
		assertNotNull(adventurer.getDirection());
		Assert.assertTrue(adventurer.getDirection().equals(new NorthDirection()));
	}	
	
	@Test
	public void testNorthDirectionTurnLeft(){
		System.out.println("------ TEST NORTH DIRECTION TURN LEFT ------\n");
		int x = 0;
		int y = 0;
		Adventurer adventurer = new Adventurer("a", x, y, new NorthDirection(), new LinkedList<>(Arrays.asList(CommandEnum.TOURNER_GAUCHE)));
		WorldMap worldMap = new WorldMap(1,1);
		worldMap.setAdventurers(Arrays.asList(adventurer));
		
		mapService.printMap(worldMap);
		mapService.runMap(worldMap);
		
		assertNotNull(adventurer.getDirection());
		Assert.assertTrue(adventurer.getDirection().equals(new WestDirection()));
	}
	
	@Test
	public void testSouthDirectionTurnLeft(){
		System.out.println("------ TEST SOUTH DIRECTION TURN LEFT ------\n");
		int x = 0;
		int y = 0;
		Adventurer adventurer = new Adventurer("a", x, y, new SouthDirection(), new LinkedList<>(Arrays.asList(CommandEnum.TOURNER_GAUCHE)));
		WorldMap worldMap = new WorldMap(1,1);
		worldMap.setAdventurers(Arrays.asList(adventurer));
		
		mapService.printMap(worldMap);
		mapService.runMap(worldMap);
		
		assertNotNull(adventurer.getDirection());
		Assert.assertTrue(adventurer.getDirection().equals(new EastDirection()));
	}
	
	@Test
	public void testEastDirectionTurnLeft(){
		System.out.println("------ TEST EAST DIRECTION TURN LEFT ------\n");
		int x = 0;
		int y = 0;
		Adventurer adventurer = new Adventurer("a", x, y, new EastDirection(), new LinkedList<>(Arrays.asList(CommandEnum.TOURNER_GAUCHE)));
		WorldMap worldMap = new WorldMap(1,1);
		worldMap.setAdventurers(Arrays.asList(adventurer));
		
		mapService.printMap(worldMap);
		mapService.runMap(worldMap);
		
		assertNotNull(adventurer.getDirection());
		Assert.assertTrue(adventurer.getDirection().equals(new NorthDirection()));
	}
	
	@Test
	public void testWestDirectionTurnLeft(){
		System.out.println("------ TEST WEST DIRECTION TURN LEFT ------\n");
		int x = 0;
		int y = 0;
		Adventurer adventurer = new Adventurer("a", x, y, new WestDirection(), new LinkedList<>(Arrays.asList(CommandEnum.TOURNER_GAUCHE)));
		WorldMap worldMap = new WorldMap(1,1);
		worldMap.setAdventurers(Arrays.asList(adventurer));
		
		mapService.printMap(worldMap);
		mapService.runMap(worldMap);
		
		assertNotNull(adventurer.getDirection());
		Assert.assertTrue(adventurer.getDirection().equals(new SouthDirection()));
	}
	
	@Test
	public void testSearchTreasure(){
		System.out.println("------ TEST SEARCH TREASURE ------\n");
		int x = 0;
		int y = 0;
		Adventurer adventurer = new Adventurer("a", x, y, new EastDirection(), new LinkedList<>(Arrays.asList(CommandEnum.AVANCER)));
		WorldMap worldMap = new WorldMap(2,1);
		worldMap.setAdventurers(Arrays.asList(adventurer));
		worldMap.getWorldBox(1, 0).setTreasures(new LinkedList<>(Arrays.asList(new Treasure(), new Treasure())));
		
		mapService.printMap(worldMap);
		mapService.runMap(worldMap);
		
		assertNotNull(adventurer.getTreasures());
		Assert.assertEquals(1, adventurer.getTreasures().size());
		Assert.assertEquals(1, worldMap.getWorldBox(1, 0).getTreasures().size());
	}

}

