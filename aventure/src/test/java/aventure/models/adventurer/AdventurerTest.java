package aventure.models.adventurer;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
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
		
		/* Initialisation */
		int x = 0;
		int y = 1;
		Adventurer adventurer = new Adventurer("a", x, y, new NorthDirection(), new LinkedList<>(Arrays.asList(CommandEnum.AVANCER)));
		WorldMap worldMap = new WorldMap(1,2);
		worldMap.setAdventurers(Arrays.asList(adventurer));
		mapService.printMap(worldMap); //affichage de la map après initialisation
		
		/* Tests après initialisation */
		Assert.assertFalse(worldMap.getWorldBox(x, y-1).isOccupied());
		Assert.assertTrue(worldMap.getWorldBox(adventurer.getX(), adventurer.getY()).isOccupied());
		
		/* Execution de l'opération */
		mapService.runMap(worldMap);
		
		/* Tests après execution */
		Assert.assertTrue(adventurer.getX().equals(x)); // Test de la position x
		Assert.assertTrue(adventurer.getY().equals(y-1)); // Test de la position y
		Assert.assertFalse(worldMap.getWorldBox(x, y).isOccupied());  // Case de départ libérée
		Assert.assertTrue(worldMap.getWorldBox(adventurer.getX(), adventurer.getY()).isOccupied()); // Case occupé par le personnage
	}
	
	@Test
	public void testSouthDirectionMove(){
		System.out.println("------ TEST SOUTH DIRECTION MOVE ------\n");
		
		/* Initialisation */
		int x = 0;
		int y = 0;
		Adventurer adventurer = new Adventurer("a", x, y, new SouthDirection(), new LinkedList<>(Arrays.asList(CommandEnum.AVANCER)));
		WorldMap worldMap = new WorldMap(1,2);
		worldMap.setAdventurers(Arrays.asList(adventurer));
		mapService.printMap(worldMap); //affichage de la map après initialisation
		
		/* Tests après initialisation */
		Assert.assertFalse(worldMap.getWorldBox(x, y+1).isOccupied());
		Assert.assertTrue(worldMap.getWorldBox(adventurer.getX(), adventurer.getY()).isOccupied());
			
		/* Execution de l'opération */
		mapService.runMap(worldMap);
		
		/* Tests après execution */
		Assert.assertTrue(adventurer.getX().equals(x)); // Test de la position x
		Assert.assertTrue(adventurer.getY().equals(y+1)); // Test de la position y
		Assert.assertFalse(worldMap.getWorldBox(x, y).isOccupied()); // Case de départ libérée
		Assert.assertTrue(worldMap.getWorldBox(adventurer.getX(), adventurer.getY()).isOccupied()); // Case occupé par le personnage
	}
	
	@Test
	public void testEastDirectionMove(){
		System.out.println("------ TEST EAST DIRECTION MOVE ------\n");
		
		/* Initialisation */
		int x = 0;
		int y = 0;
		Adventurer adventurer = new Adventurer("a", x, y, new EastDirection(), new LinkedList<>(Arrays.asList(CommandEnum.AVANCER)));
		WorldMap worldMap = new WorldMap(2,1);
		worldMap.setAdventurers(Arrays.asList(adventurer));
		mapService.printMap(worldMap); //affichage de la map après initialisation
		
		/* Tests après initialisation */
		Assert.assertFalse(worldMap.getWorldBox(x+1, y).isOccupied());
		Assert.assertTrue(worldMap.getWorldBox(adventurer.getX(), adventurer.getY()).isOccupied());
		
		/* Execution de l'opération */
		mapService.runMap(worldMap);
		
		/* Tests après execution */
		Assert.assertTrue(adventurer.getX().equals(x+1)); // Test de la position x
		Assert.assertTrue(adventurer.getY().equals(y)); // Test de la position y
		Assert.assertFalse(worldMap.getWorldBox(x, y).isOccupied()); // Case de départ libérée
		Assert.assertTrue(worldMap.getWorldBox(adventurer.getX(), adventurer.getY()).isOccupied()); // Case occupé par le personnage
	}
	
	@Test
	public void testWestDirectionMove(){
		System.out.println("------ TEST WEST DIRECTION MOVE ------\n");
		
		/* Initialisation */
		int x = 1;
		int y = 0;
		Adventurer adventurer = new Adventurer("a", x, y, new WestDirection(), new LinkedList<>(Arrays.asList(CommandEnum.AVANCER)));
		WorldMap worldMap = new WorldMap(2,1);
		worldMap.setAdventurers(Arrays.asList(adventurer));
		mapService.printMap(worldMap); //affichage de la map après initialisation
		
		/* Tests après initialisation */
		Assert.assertFalse(worldMap.getWorldBox(x-1, y).isOccupied());
		Assert.assertTrue(worldMap.getWorldBox(adventurer.getX(), adventurer.getY()).isOccupied());
			
		/* Execution de l'opération */
		mapService.runMap(worldMap);
		
		/* Tests après execution */
		Assert.assertTrue(adventurer.getX().equals(x-1)); // Test de la position x
		Assert.assertTrue(adventurer.getY().equals(y)); // Test de la position y
		Assert.assertFalse(worldMap.getWorldBox(x, y).isOccupied()); // Case de départ libérée
		Assert.assertTrue(worldMap.getWorldBox(adventurer.getX(), adventurer.getY()).isOccupied()); // Case occupé par le personnage
	}
	
	@Test
	public void testNorthDirectionMoveToMountainCase(){
		System.out.println("------ TEST NORTH DIRECTION MOVE TO MOUNTAIN CASE ------\n");
		
		/* Initialisation */
		int x = 0;
		int y = 1;
		Adventurer adventurer = new Adventurer("a", x, y, new NorthDirection(), new LinkedList<>(Arrays.asList(CommandEnum.AVANCER)));
		WorldMap worldMap = new WorldMap(1,2);
		worldMap.getWorldBox(0, 0).setCaseType(CaseTypeEnum.MONTAGNE);
		worldMap.setAdventurers(Arrays.asList(adventurer));
		mapService.printMap(worldMap); //affichage de la map après initialisation
				
		/* Execution de l'opération */
		mapService.runMap(worldMap);
		
		/* Tests après execution */
		Assert.assertTrue(adventurer.getX().equals(x)); // Personnage bloqué par la montagne
		Assert.assertTrue(adventurer.getY().equals(y));
	}
	
	@Test
	public void testSouthDirectionMoveToMountainCase(){
		System.out.println("------ TEST SOUTH DIRECTION MOVE TO MOUNTAIN CASE ------\n");
		
		/* Initialisation */
		int x = 0;
		int y = 0;
		Adventurer adventurer = new Adventurer("a", x, y, new SouthDirection(), new LinkedList<>(Arrays.asList(CommandEnum.AVANCER)));
		WorldMap worldMap = new WorldMap(1,2);
		worldMap.getWorldBox(0, 1).setCaseType(CaseTypeEnum.MONTAGNE);
		worldMap.setAdventurers(Arrays.asList(adventurer));
		mapService.printMap(worldMap); //affichage de la map après initialisation
		
		/* Execution de l'opération */
		mapService.runMap(worldMap);
		
		/* Tests après execution */
		Assert.assertTrue(adventurer.getX().equals(x)); // Personnage bloqué par la montagne
		Assert.assertTrue(adventurer.getY().equals(y));
	}
	
	@Test
	public void testEastDirectionMoveToMountainCase(){
		System.out.println("------ TEST EAST DIRECTION MOVE TO MOUNTAIN CASE ------\n");
		
		/* Initialisation */
		int x = 1;
		int y = 0;
		Adventurer adventurer = new Adventurer("a", x, y, new EastDirection(), new LinkedList<>(Arrays.asList(CommandEnum.AVANCER)));
		WorldMap worldMap = new WorldMap(2,1);
		worldMap.getWorldBox(0, 0).setCaseType(CaseTypeEnum.MONTAGNE);
		worldMap.setAdventurers(Arrays.asList(adventurer));	
		mapService.printMap(worldMap); //affichage de la map après initialisation
		
		/* Execution de l'opération */
		mapService.runMap(worldMap);
		
		/* Tests après execution */
		Assert.assertTrue(adventurer.getX().equals(x)); // Personnage bloqué par la montagne
		Assert.assertTrue(adventurer.getY().equals(y));
	}
	
	@Test
	public void testWestDirectionMoveToMountainCase(){
		System.out.println("------ TEST WEST DIRECTION MOVE TO MOUNTAIN CASE ------\n");
		
		/* Initialisation */
		int x = 0;
		int y = 0;
		Adventurer adventurer = new Adventurer("a", x, y, new WestDirection(), new LinkedList<>(Arrays.asList(CommandEnum.AVANCER)));
		WorldMap worldMap = new WorldMap(2,1);
		worldMap.getWorldBox(1, 0).setCaseType(CaseTypeEnum.MONTAGNE);
		worldMap.setAdventurers(Arrays.asList(adventurer));
		mapService.printMap(worldMap); //affichage de la map après initialisation
		
		/* Execution de l'opération */
		mapService.runMap(worldMap);
		
		/* Tests après execution */
		Assert.assertTrue(adventurer.getX().equals(x)); // Personnage bloqué par la montagne
		Assert.assertTrue(adventurer.getY().equals(y));
	}
		
	@Test
	public void testNorthDirectionMoveOOB(){
		System.out.println("------ TEST NORTH DIRECTION MOVE OUT OF BOUND ------\n");
		
		/* Initialisation */
		int x = 0;
		int y = 0;
		Adventurer adventurer = new Adventurer("a", x, y, new NorthDirection(), new LinkedList<>(Arrays.asList(CommandEnum.AVANCER)));
		WorldMap worldMap = new WorldMap(1,1);
		worldMap.setAdventurers(Arrays.asList(adventurer));
		mapService.printMap(worldMap); //affichage de la map après initialisation
		
		/* Execution de l'opération */
		mapService.runMap(worldMap);
		
		/* Tests après execution */
		Assert.assertTrue(adventurer.getX().equals(x));  // Le personnage ne peut pas se déplacer à l'exterieur de la map
		Assert.assertTrue(adventurer.getY().equals(y));
	}
	
	@Test
	public void testSouthDirectionMoveOOB(){
		System.out.println("------ TEST SOUTH DIRECTION MOVE OUT OF BOUND ------\n");
		
		/* Initialisation */
		int x = 0;
		int y = 0;
		Adventurer adventurer = new Adventurer("a", x, y, new SouthDirection(), new LinkedList<>(Arrays.asList(CommandEnum.AVANCER)));
		WorldMap worldMap = new WorldMap(1,1);
		worldMap.setAdventurers(Arrays.asList(adventurer));
		mapService.printMap(worldMap); //affichage de la map après initialisation
		
		/* Execution de l'opération */
		mapService.runMap(worldMap);
		
		/* Tests après execution */
		Assert.assertTrue(adventurer.getX().equals(x)); // Le personnage ne peut pas se déplacer à l'exterieur de la map
		Assert.assertTrue(adventurer.getY().equals(y));
	}
	
	@Test
	public void testEastDirectionMoveOOB(){
		System.out.println("------ TEST EAST DIRECTION MOVE OUT OF BOUND ------\n");
		
		/* Initialisation */
		int x = 0;
		int y = 0;
		Adventurer adventurer = new Adventurer("a", x, y, new EastDirection(), new LinkedList<>(Arrays.asList(CommandEnum.AVANCER)));
		WorldMap worldMap = new WorldMap(1,1);
		worldMap.setAdventurers(Arrays.asList(adventurer));
		mapService.printMap(worldMap); //affichage de la map après initialisation
		
		/* Execution de l'opération */
		mapService.runMap(worldMap);
		
		/* Tests après execution */
		Assert.assertTrue(adventurer.getX().equals(x)); // Le personnage ne peut pas se déplacer à l'exterieur de la map
		Assert.assertTrue(adventurer.getY().equals(y));
	}
	
	@Test
	public void testWestDirectionMoveOOB(){
		System.out.println("------ TEST WEST DIRECTION MOVE OUT OF BOUND ------\n");
		
		/* Initialisation */
		int x = 0;
		int y = 0;
		Adventurer adventurer = new Adventurer("a", x, y, new WestDirection(), new LinkedList<>(Arrays.asList(CommandEnum.AVANCER)));
		WorldMap worldMap = new WorldMap(1,1);
		worldMap.setAdventurers(Arrays.asList(adventurer));
		mapService.printMap(worldMap); //affichage de la map après initialisation
		
		/* Execution de l'opération */
		mapService.runMap(worldMap);
		
		/* Tests après execution */
		Assert.assertTrue(adventurer.getX().equals(x)); // Le personnage ne peut pas se déplacer à l'exterieur de la map
		Assert.assertTrue(adventurer.getY().equals(y));
	}
	
	@Test
	public void testNorthDirectionMoveToAdventurer(){
		System.out.println("------ TEST NORTH DIRECTION MOVE TO A OTHER ADVENTURER ------\n");
		
		/* Initialisation */
		int x = 0;
		int y = 1;
		Adventurer adventurer = new Adventurer("a", x, y, new NorthDirection(), new LinkedList<>(Arrays.asList(CommandEnum.AVANCER)));
		Adventurer adventurer2 = new Adventurer("a", x, y-1, new NorthDirection(), new ArrayList<>());
		WorldMap worldMap = new WorldMap(1,2);
		worldMap.setAdventurers(Arrays.asList(adventurer, adventurer2));
		mapService.printMap(worldMap); //affichage de la map après initialisation
		
		/* Tests après initialisation */
		Assert.assertTrue(worldMap.getWorldBox(x, y-1).isOccupied());
		Assert.assertTrue(worldMap.getWorldBox(adventurer.getX(), adventurer.getY()).isOccupied());
				
		/* Execution de l'opération */
		mapService.runMap(worldMap);
		
		/* Tests après execution */
		Assert.assertTrue(adventurer.getX().equals(x)); // Le personnage ne peut pas se déplacer vers une case occupé
		Assert.assertTrue(adventurer.getY().equals(y));
		Assert.assertTrue(worldMap.getWorldBox(x, y).isOccupied());
		Assert.assertTrue(worldMap.getWorldBox(adventurer.getX(), adventurer.getY()).isOccupied());
	}
	
	@Test
	public void testSouthDirectionMoveToAdventurer(){
		System.out.println("------ TEST SOUTH DIRECTION MOVE TO A OTHER ADVENTURER ------\n");
		
		/* Initialisation */
		int x = 0;
		int y = 0;
		Adventurer adventurer = new Adventurer("a", x, y, new SouthDirection(), new LinkedList<>(Arrays.asList(CommandEnum.AVANCER)));
		Adventurer adventurer2 = new Adventurer("a", x, y+1, new NorthDirection(), new ArrayList<>());
		WorldMap worldMap = new WorldMap(1,2);
		worldMap.setAdventurers(Arrays.asList(adventurer, adventurer2));
		mapService.printMap(worldMap); //affichage de la map après initialisation
		
		/* Tests après initialisation */
		Assert.assertTrue(worldMap.getWorldBox(x, y+1).isOccupied());
		Assert.assertTrue(worldMap.getWorldBox(adventurer.getX(), adventurer.getY()).isOccupied());
			
		/* Execution de l'opération */
		mapService.runMap(worldMap);
		
		/* Tests après execution */
		Assert.assertTrue(adventurer.getX().equals(x)); // Le personnage ne peut pas se déplacer vers une case occupé
		Assert.assertTrue(adventurer.getY().equals(y));
		Assert.assertTrue(worldMap.getWorldBox(x, y).isOccupied());
		Assert.assertTrue(worldMap.getWorldBox(adventurer.getX(), adventurer.getY()).isOccupied());
	}
	
	@Test
	public void testEastDirectionMoveToAdventurer(){
		System.out.println("------ TEST EAST DIRECTION MOVE TO A OTHER ADVENTURER ------\n");
		
		/* Initialisation */
		int x = 0;
		int y = 0;
		Adventurer adventurer = new Adventurer("a", x, y, new EastDirection(), new LinkedList<>(Arrays.asList(CommandEnum.AVANCER)));
		Adventurer adventurer2 = new Adventurer("a", x+1, y, new NorthDirection(), new ArrayList<>());
		WorldMap worldMap = new WorldMap(2,1);
		worldMap.setAdventurers(Arrays.asList(adventurer, adventurer2));
		mapService.printMap(worldMap); //affichage de la map après initialisation
		
		/* Tests après initialisation */
		Assert.assertTrue(worldMap.getWorldBox(x+1, y).isOccupied());
		Assert.assertTrue(worldMap.getWorldBox(adventurer.getX(), adventurer.getY()).isOccupied());
		
		/* Execution de l'opération */
		mapService.runMap(worldMap);
		
		/* Tests après execution */
		Assert.assertTrue(adventurer.getX().equals(x)); // Le personnage ne peut pas se déplacer vers une case occupé
		Assert.assertTrue(adventurer.getY().equals(y));
		Assert.assertTrue(worldMap.getWorldBox(x, y).isOccupied());
		Assert.assertTrue(worldMap.getWorldBox(adventurer.getX(), adventurer.getY()).isOccupied());
	}
	
	@Test
	public void testWestDirectionMoveToAdventurer(){
		System.out.println("------ TEST WEST DIRECTION MOVE TO A OTHER ADVENTURER ------\n");
		
		/* Initialisation */
		int x = 1;
		int y = 0;
		Adventurer adventurer = new Adventurer("a", x, y, new WestDirection(), new LinkedList<>(Arrays.asList(CommandEnum.AVANCER)));
		Adventurer adventurer2 = new Adventurer("a", x-1, y, new NorthDirection(), new ArrayList<>());
		WorldMap worldMap = new WorldMap(2,1);
		worldMap.setAdventurers(Arrays.asList(adventurer, adventurer2));
		mapService.printMap(worldMap); //affichage de la map après initialisation
		
		/* Tests après initialisation */
		Assert.assertTrue(worldMap.getWorldBox(x-1, y).isOccupied());
		Assert.assertTrue(worldMap.getWorldBox(adventurer.getX(), adventurer.getY()).isOccupied());
			
		/* Execution de l'opération */
		mapService.runMap(worldMap);
		
		/* Tests après execution */
		Assert.assertTrue(adventurer.getX().equals(x)); // Le personnage ne peut pas se déplacer vers une case occupé
		Assert.assertTrue(adventurer.getY().equals(y));
		Assert.assertTrue(worldMap.getWorldBox(x, y).isOccupied());
		Assert.assertTrue(worldMap.getWorldBox(adventurer.getX(), adventurer.getY()).isOccupied());
	}
	
	@Test
	public void testNorthDirectionTurnRight(){
		System.out.println("------ TEST NORTH DIRECTION TURN RIGHT ------\n");
		
		/* Initialisation */
		int x = 0;
		int y = 0;
		Adventurer adventurer = new Adventurer("a", x, y, new NorthDirection(), new LinkedList<>(Arrays.asList(CommandEnum.TOURNER_DROITE)));
		WorldMap worldMap = new WorldMap(1,1);
		worldMap.setAdventurers(Arrays.asList(adventurer));
		mapService.printMap(worldMap); //affichage de la map après initialisation
		
		/* Execution de l'opération */
		mapService.runMap(worldMap);
		
		/* Tests après execution */
		assertNotNull(adventurer.getDirection());
		Assert.assertTrue(adventurer.getDirection().equals(new EastDirection())); // NORD + TURN RIGHT -> EST
	}
	
	@Test
	public void testSouthDirectionTurnRight(){
		System.out.println("------ TEST SOUTH DIRECTION TURN RIGHT ------\n");
		
		/* Initialisation */
		int x = 0;
		int y = 0;
		Adventurer adventurer = new Adventurer("a", x, y, new SouthDirection(), new LinkedList<>(Arrays.asList(CommandEnum.TOURNER_DROITE)));
		WorldMap worldMap = new WorldMap(1,1);
		worldMap.setAdventurers(Arrays.asList(adventurer));
		mapService.printMap(worldMap); //affichage de la map après initialisation
		
		/* Execution de l'opération */
		mapService.runMap(worldMap);
		
		/* Tests après execution */
		assertNotNull(adventurer.getDirection());
		Assert.assertTrue(adventurer.getDirection().equals(new WestDirection())); // SOUTH + TURN RIGHT -> WEST
	}
	
	@Test
	public void testEastDirectionTurnRight(){
		System.out.println("------ TEST EAST DIRECTION TURN RIGHT ------\n");
		
		/* Initialisation */
		int x = 0;
		int y = 0;
		Adventurer adventurer = new Adventurer("a", x, y, new EastDirection(), new LinkedList<>(Arrays.asList(CommandEnum.TOURNER_DROITE)));
		WorldMap worldMap = new WorldMap(1,1);
		worldMap.setAdventurers(Arrays.asList(adventurer));
		mapService.printMap(worldMap); //affichage de la map après initialisation
		
		/* Execution de l'opération */
		mapService.runMap(worldMap);
		
		/* Tests après execution */
		assertNotNull(adventurer.getDirection());
		Assert.assertTrue(adventurer.getDirection().equals(new SouthDirection())); // EAST + TURN RIGHT -> SOUTH
	}
	
	@Test
	public void testWestDirectionTurnRight(){
		System.out.println("------ TEST WEST DIRECTION TURN RIGHT ------\n");
		
		/* Initialisation */
		int x = 0;
		int y = 0;
		Adventurer adventurer = new Adventurer("a", x, y, new WestDirection(), new LinkedList<>(Arrays.asList(CommandEnum.TOURNER_DROITE)));
		WorldMap worldMap = new WorldMap(1,1);
		worldMap.setAdventurers(Arrays.asList(adventurer));
		mapService.printMap(worldMap); //affichage de la map après initialisation
		
		/* Execution de l'opération */
		mapService.runMap(worldMap);
		
		/* Tests après execution */
		assertNotNull(adventurer.getDirection());
		Assert.assertTrue(adventurer.getDirection().equals(new NorthDirection())); // WEST + TURN RIGHT -> NORTH
	}	
	
	@Test
	public void testNorthDirectionTurnLeft(){
		System.out.println("------ TEST NORTH DIRECTION TURN LEFT ------\n");
		
		/* Initialisation */
		int x = 0;
		int y = 0;
		Adventurer adventurer = new Adventurer("a", x, y, new NorthDirection(), new LinkedList<>(Arrays.asList(CommandEnum.TOURNER_GAUCHE)));
		WorldMap worldMap = new WorldMap(1,1);
		worldMap.setAdventurers(Arrays.asList(adventurer));
		mapService.printMap(worldMap); //affichage de la map après initialisation
		
		/* Execution de l'opération */
		mapService.runMap(worldMap);
		
		/* Tests après execution */
		assertNotNull(adventurer.getDirection());
		Assert.assertTrue(adventurer.getDirection().equals(new WestDirection())); // NORTH + TURN LEFT -> WEST
	}
	
	@Test
	public void testSouthDirectionTurnLeft(){
		System.out.println("------ TEST SOUTH DIRECTION TURN LEFT ------\n");
		
		/* Initialisation */
		int x = 0;
		int y = 0;
		Adventurer adventurer = new Adventurer("a", x, y, new SouthDirection(), new LinkedList<>(Arrays.asList(CommandEnum.TOURNER_GAUCHE)));
		WorldMap worldMap = new WorldMap(1,1);
		worldMap.setAdventurers(Arrays.asList(adventurer));
		mapService.printMap(worldMap); //affichage de la map après initialisation
		
		/* Execution de l'opération */
		mapService.runMap(worldMap);
		
		/* Tests après execution */
		assertNotNull(adventurer.getDirection());
		Assert.assertTrue(adventurer.getDirection().equals(new EastDirection())); // SOUTH + TURN LEFT -> EAST
	}
	
	@Test
	public void testEastDirectionTurnLeft(){
		System.out.println("------ TEST EAST DIRECTION TURN LEFT ------\n");
		
		/* Initialisation */
		int x = 0;
		int y = 0;
		Adventurer adventurer = new Adventurer("a", x, y, new EastDirection(), new LinkedList<>(Arrays.asList(CommandEnum.TOURNER_GAUCHE)));
		WorldMap worldMap = new WorldMap(1,1);
		worldMap.setAdventurers(Arrays.asList(adventurer));
		mapService.printMap(worldMap); //affichage de la map après initialisation
		
		/* Execution de l'opération */
		mapService.runMap(worldMap);
		
		/* Tests après execution */
		assertNotNull(adventurer.getDirection());
		Assert.assertTrue(adventurer.getDirection().equals(new NorthDirection())); // EAST + TURN LEFT -> NORTH
	}
	
	@Test
	public void testWestDirectionTurnLeft(){
		System.out.println("------ TEST WEST DIRECTION TURN LEFT ------\n");
		
		/* Initialisation */
		int x = 0;
		int y = 0;
		Adventurer adventurer = new Adventurer("a", x, y, new WestDirection(), new LinkedList<>(Arrays.asList(CommandEnum.TOURNER_GAUCHE)));
		WorldMap worldMap = new WorldMap(1,1);
		worldMap.setAdventurers(Arrays.asList(adventurer));
		mapService.printMap(worldMap); //affichage de la map après initialisation
		
		/* Execution de l'opération */
		mapService.runMap(worldMap);
		
		/* Tests après execution */
		assertNotNull(adventurer.getDirection());
		Assert.assertTrue(adventurer.getDirection().equals(new SouthDirection())); // WEST + TURN LEFT -> SOUTH
	}
	
	@Test
	public void testSearchTreasure(){
		System.out.println("------ TEST SEARCH TREASURE ------\n");
		
		/* Initialisation */
		int x = 0;
		int y = 0;
		Adventurer adventurer = new Adventurer("a", x, y, new EastDirection(), new LinkedList<>(Arrays.asList(CommandEnum.AVANCER)));
		WorldMap worldMap = new WorldMap(2,1);
		worldMap.setAdventurers(Arrays.asList(adventurer));
		worldMap.getWorldBox(1, 0).setTreasures(new LinkedList<>(Arrays.asList(new Treasure(), new Treasure())));
		mapService.printMap(worldMap); //affichage de la map après initialisation
		
		/* Tests après initialisation */
		Assert.assertEquals(0, adventurer.getTreasures().size()); // Le personnage n'a pas de trésor
		Assert.assertEquals(2, worldMap.getWorldBox(1, 0).getTreasures().size()); // Il y a 2 trésors sur la case
		
		/* Execution de l'opération */
		mapService.runMap(worldMap);
		
		/* Tests après execution */
		assertNotNull(adventurer.getTreasures());
		Assert.assertEquals(1, adventurer.getTreasures().size()); // Le personnage récupère 1 trésor
		Assert.assertEquals(1, worldMap.getWorldBox(1, 0).getTreasures().size()); // Il y a 1 trésor sur la case
	}
	
	@Test
	public void testValidateAdventurers(){
		System.out.println("------ TEST VALIDATE ADVENTURER ------\n");
		
		/* Initialisation */
		int x = 100;
		int y = 100;
		Adventurer adventurer = new Adventurer("a", x, y, new NorthDirection(), new LinkedList<>(Arrays.asList(CommandEnum.AVANCER)));
		WorldMap worldMap = new WorldMap(1,2);
		worldMap.setAdventurers(new LinkedList<>(Arrays.asList(adventurer)));
		
		/* Test : personnage placé à l'exterieur de la map */
		Assert.assertEquals(0, worldMap.getAdventurers().size());
		
		adventurer.setX(0);
		adventurer.setY(0);
		worldMap.setAdventurers(Arrays.asList(adventurer));
		
		/* Test : personnage placé dans les bornes de la map */
		Assert.assertEquals(1, worldMap.getAdventurers().size());

	}

}

