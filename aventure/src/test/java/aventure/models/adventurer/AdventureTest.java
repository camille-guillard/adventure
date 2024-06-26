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
import adventure.models.adventurer.Player;
import adventure.models.adventurer.behavior.impl.EastDirection;
import adventure.models.adventurer.behavior.impl.NorthDirection;
import adventure.models.adventurer.behavior.impl.SouthDirection;
import adventure.models.adventurer.behavior.impl.WestDirection;
import adventure.models.collect.Treasure;
import adventure.models.worldmap.WorldMap;
import adventure.services.IMapService;
import adventure.services.impl.MapServiceImpl;


public class AdventureTest {

	IMapService mapService = new MapServiceImpl();
	
	@Test
	public void testNorthDirectionMove(){
		System.out.println("------ TEST NORTH DIRECTION MOVE ------\n");
		
		/* Initialisation */
		int x = 0;
		int y = 1;
		Player player = new Player("p", x, y, new NorthDirection(), new LinkedList<>(Arrays.asList(CommandEnum.AVANCER)));
		WorldMap worldMap = new WorldMap(1,2);
		worldMap.setPlayers(Arrays.asList(player));
		mapService.printMap(worldMap); //affichage de la map apr�s initialisation
		
		/* Tests apr�s initialisation */
		Assert.assertFalse(worldMap.getWorldBox(x, y-1).isOccupied());
		Assert.assertTrue(worldMap.getWorldBox(player.getX(), player.getY()).isOccupied());
		
		/* Execution de l'op�ration */
		mapService.runMap(worldMap);
		
		/* Tests apr�s execution */
		Assert.assertTrue(player.getX().equals(x)); // Test de la position x
		Assert.assertTrue(player.getY().equals(y-1)); // Test de la position y
		Assert.assertFalse(worldMap.getWorldBox(x, y).isOccupied());  // Case de d�part lib�r�e
		Assert.assertTrue(worldMap.getWorldBox(player.getX(), player.getY()).isOccupied()); // Case occup� par le personnage
	}
	
	@Test
	public void testSouthDirectionMove(){
		System.out.println("------ TEST SOUTH DIRECTION MOVE ------\n");
		
		/* Initialisation */
		int x = 0;
		int y = 0;
		Player player = new Player("p", x, y, new SouthDirection(), new LinkedList<>(Arrays.asList(CommandEnum.AVANCER)));
		WorldMap worldMap = new WorldMap(1,2);
		worldMap.setPlayers(Arrays.asList(player));
		mapService.printMap(worldMap); //affichage de la map apr�s initialisation
		
		/* Tests apr�s initialisation */
		Assert.assertFalse(worldMap.getWorldBox(x, y+1).isOccupied());
		Assert.assertTrue(worldMap.getWorldBox(player.getX(), player.getY()).isOccupied());
			
		/* Execution de l'op�ration */
		mapService.runMap(worldMap);
		
		/* Tests apr�s execution */
		Assert.assertTrue(player.getX().equals(x)); // Test de la position x
		Assert.assertTrue(player.getY().equals(y+1)); // Test de la position y
		Assert.assertFalse(worldMap.getWorldBox(x, y).isOccupied()); // Case de d�part lib�r�e
		Assert.assertTrue(worldMap.getWorldBox(player.getX(), player.getY()).isOccupied()); // Case occup� par le personnage
	}
	
	@Test
	public void testEastDirectionMove(){
		System.out.println("------ TEST EAST DIRECTION MOVE ------\n");
		
		/* Initialisation */
		int x = 0;
		int y = 0;
		Player player = new Player("p", x, y, new EastDirection(), new LinkedList<>(Arrays.asList(CommandEnum.AVANCER)));
		WorldMap worldMap = new WorldMap(2,1);
		worldMap.setPlayers(Arrays.asList(player));
		mapService.printMap(worldMap); //affichage de la map apr�s initialisation
		
		/* Tests apr�s initialisation */
		Assert.assertFalse(worldMap.getWorldBox(x+1, y).isOccupied());
		Assert.assertTrue(worldMap.getWorldBox(player.getX(), player.getY()).isOccupied());
		
		/* Execution de l'op�ration */
		mapService.runMap(worldMap);
		
		/* Tests apr�s execution */
		Assert.assertTrue(player.getX().equals(x+1)); // Test de la position x
		Assert.assertTrue(player.getY().equals(y)); // Test de la position y
		Assert.assertFalse(worldMap.getWorldBox(x, y).isOccupied()); // Case de d�part lib�r�e
		Assert.assertTrue(worldMap.getWorldBox(player.getX(), player.getY()).isOccupied()); // Case occup� par le personnage
	}
	
	@Test
	public void testWestDirectionMove(){
		System.out.println("------ TEST WEST DIRECTION MOVE ------\n");
		
		/* Initialisation */
		int x = 1;
		int y = 0;
		Player player = new Player("p", x, y, new WestDirection(), new LinkedList<>(Arrays.asList(CommandEnum.AVANCER)));
		WorldMap worldMap = new WorldMap(2,1);
		worldMap.setPlayers(Arrays.asList(player));
		mapService.printMap(worldMap); //affichage de la map apr�s initialisation
		
		/* Tests apr�s initialisation */
		Assert.assertFalse(worldMap.getWorldBox(x-1, y).isOccupied());
		Assert.assertTrue(worldMap.getWorldBox(player.getX(), player.getY()).isOccupied());
			
		/* Execution de l'op�ration */
		mapService.runMap(worldMap);
		
		/* Tests apr�s execution */
		Assert.assertTrue(player.getX().equals(x-1)); // Test de la position x
		Assert.assertTrue(player.getY().equals(y)); // Test de la position y
		Assert.assertFalse(worldMap.getWorldBox(x, y).isOccupied()); // Case de d�part lib�r�e
		Assert.assertTrue(worldMap.getWorldBox(player.getX(), player.getY()).isOccupied()); // Case occup� par le personnage
	}
	
	@Test
	public void testNorthDirectionMoveToMountainCase(){
		System.out.println("------ TEST NORTH DIRECTION MOVE TO MOUNTAIN CASE ------\n");
		
		/* Initialisation */
		int x = 0;
		int y = 1;
		Player player = new Player("p", x, y, new NorthDirection(), new LinkedList<>(Arrays.asList(CommandEnum.AVANCER)));
		WorldMap worldMap = new WorldMap(1,2);
		worldMap.getWorldBox(0, 0).setCaseType(CaseTypeEnum.MONTAGNE);
		worldMap.setPlayers(Arrays.asList(player));
		mapService.printMap(worldMap); //affichage de la map apr�s initialisation
				
		/* Execution de l'op�ration */
		mapService.runMap(worldMap);
		
		/* Tests apr�s execution */
		Assert.assertTrue(player.getX().equals(x)); // Personnage bloqu� par la montagne
		Assert.assertTrue(player.getY().equals(y));
	}
	
	@Test
	public void testSouthDirectionMoveToMountainCase(){
		System.out.println("------ TEST SOUTH DIRECTION MOVE TO MOUNTAIN CASE ------\n");
		
		/* Initialisation */
		int x = 0;
		int y = 0;
		Player player = new Player("p", x, y, new SouthDirection(), new LinkedList<>(Arrays.asList(CommandEnum.AVANCER)));
		WorldMap worldMap = new WorldMap(1,2);
		worldMap.getWorldBox(0, 1).setCaseType(CaseTypeEnum.MONTAGNE);
		worldMap.setPlayers(Arrays.asList(player));
		mapService.printMap(worldMap); //affichage de la map apr�s initialisation
		
		/* Execution de l'op�ration */
		mapService.runMap(worldMap);
		
		/* Tests apr�s execution */
		Assert.assertTrue(player.getX().equals(x)); // Personnage bloqu� par la montagne
		Assert.assertTrue(player.getY().equals(y));
	}
	
	@Test
	public void testEastDirectionMoveToMountainCase(){
		System.out.println("------ TEST EAST DIRECTION MOVE TO MOUNTAIN CASE ------\n");
		
		/* Initialisation */
		int x = 1;
		int y = 0;
		Player player = new Player("p", x, y, new EastDirection(), new LinkedList<>(Arrays.asList(CommandEnum.AVANCER)));
		WorldMap worldMap = new WorldMap(2,1);
		worldMap.getWorldBox(0, 0).setCaseType(CaseTypeEnum.MONTAGNE);
		worldMap.setPlayers(Arrays.asList(player));	
		mapService.printMap(worldMap); //affichage de la map apr�s initialisation
		
		/* Execution de l'op�ration */
		mapService.runMap(worldMap);
		
		/* Tests apr�s execution */
		Assert.assertTrue(player.getX().equals(x)); // Personnage bloqu� par la montagne
		Assert.assertTrue(player.getY().equals(y));
	}
	
	@Test
	public void testWestDirectionMoveToMountainCase(){
		System.out.println("------ TEST WEST DIRECTION MOVE TO MOUNTAIN CASE ------\n");
		
		/* Initialisation */
		int x = 0;
		int y = 0;
		Player player = new Player("p", x, y, new WestDirection(), new LinkedList<>(Arrays.asList(CommandEnum.AVANCER)));
		WorldMap worldMap = new WorldMap(2,1);
		worldMap.getWorldBox(1, 0).setCaseType(CaseTypeEnum.MONTAGNE);
		worldMap.setPlayers(Arrays.asList(player));
		mapService.printMap(worldMap); //affichage de la map apr�s initialisation
		
		/* Execution de l'op�ration */
		mapService.runMap(worldMap);
		
		/* Tests apr�s execution */
		Assert.assertTrue(player.getX().equals(x)); // Personnage bloqu� par la montagne
		Assert.assertTrue(player.getY().equals(y));
	}
		
	@Test
	public void testNorthDirectionMoveOOB(){
		System.out.println("------ TEST NORTH DIRECTION MOVE OUT OF BOUND ------\n");
		
		/* Initialisation */
		int x = 0;
		int y = 0;
		Player player = new Player("p", x, y, new NorthDirection(), new LinkedList<>(Arrays.asList(CommandEnum.AVANCER)));
		WorldMap worldMap = new WorldMap(1,1);
		worldMap.setPlayers(Arrays.asList(player));
		mapService.printMap(worldMap); //affichage de la map apr�s initialisation
		
		/* Execution de l'op�ration */
		mapService.runMap(worldMap);
		
		/* Tests apr�s execution */
		Assert.assertTrue(player.getX().equals(x));  // Le personnage ne peut pas se d�placer � l'exterieur de la map
		Assert.assertTrue(player.getY().equals(y));
	}
	
	@Test
	public void testSouthDirectionMoveOOB(){
		System.out.println("------ TEST SOUTH DIRECTION MOVE OUT OF BOUND ------\n");
		
		/* Initialisation */
		int x = 0;
		int y = 0;
		Player player = new Player("p", x, y, new SouthDirection(), new LinkedList<>(Arrays.asList(CommandEnum.AVANCER)));
		WorldMap worldMap = new WorldMap(1,1);
		worldMap.setPlayers(Arrays.asList(player));
		mapService.printMap(worldMap); //affichage de la map apr�s initialisation
		
		/* Execution de l'op�ration */
		mapService.runMap(worldMap);
		
		/* Tests apr�s execution */
		Assert.assertTrue(player.getX().equals(x)); // Le personnage ne peut pas se d�placer � l'exterieur de la map
		Assert.assertTrue(player.getY().equals(y));
	}
	
	@Test
	public void testEastDirectionMoveOOB(){
		System.out.println("------ TEST EAST DIRECTION MOVE OUT OF BOUND ------\n");
		
		/* Initialisation */
		int x = 0;
		int y = 0;
		Player player = new Player("p", x, y, new EastDirection(), new LinkedList<>(Arrays.asList(CommandEnum.AVANCER)));
		WorldMap worldMap = new WorldMap(1,1);
		worldMap.setPlayers(Arrays.asList(player));
		mapService.printMap(worldMap); //affichage de la map apr�s initialisation
		
		/* Execution de l'op�ration */
		mapService.runMap(worldMap);
		
		/* Tests apr�s execution */
		Assert.assertTrue(player.getX().equals(x)); // Le personnage ne peut pas se d�placer � l'exterieur de la map
		Assert.assertTrue(player.getY().equals(y));
	}
	
	@Test
	public void testWestDirectionMoveOOB(){
		System.out.println("------ TEST WEST DIRECTION MOVE OUT OF BOUND ------\n");
		
		/* Initialisation */
		int x = 0;
		int y = 0;
		Player player = new Player("p", x, y, new WestDirection(), new LinkedList<>(Arrays.asList(CommandEnum.AVANCER)));
		WorldMap worldMap = new WorldMap(1,1);
		worldMap.setPlayers(Arrays.asList(player));
		mapService.printMap(worldMap); //affichage de la map apr�s initialisation
		
		/* Execution de l'op�ration */
		mapService.runMap(worldMap);
		
		/* Tests apr�s execution */
		Assert.assertTrue(player.getX().equals(x)); // Le personnage ne peut pas se d�placer � l'exterieur de la map
		Assert.assertTrue(player.getY().equals(y));
	}
	
	@Test
	public void testNorthDirectionMoveToPlayer(){
		System.out.println("------ TEST NORTH DIRECTION MOVE TO A OTHER PLAYER ------\n");
		
		/* Initialisation */
		int x = 0;
		int y = 1;
		Player player = new Player("p", x, y, new NorthDirection(), new LinkedList<>(Arrays.asList(CommandEnum.AVANCER)));
		Player player2 = new Player("p", x, y-1, new NorthDirection(), new ArrayList<>());
		WorldMap worldMap = new WorldMap(1,2);
		worldMap.setPlayers(Arrays.asList(player, player2));
		mapService.printMap(worldMap); //affichage de la map apr�s initialisation
		
		/* Tests apr�s initialisation */
		Assert.assertTrue(worldMap.getWorldBox(x, y-1).isOccupied());
		Assert.assertTrue(worldMap.getWorldBox(player.getX(), player.getY()).isOccupied());
				
		/* Execution de l'op�ration */
		mapService.runMap(worldMap);
		
		/* Tests apr�s execution */
		Assert.assertTrue(player.getX().equals(x)); // Le personnage ne peut pas se d�placer vers une case occup�
		Assert.assertTrue(player.getY().equals(y));
		Assert.assertTrue(worldMap.getWorldBox(x, y).isOccupied());
		Assert.assertTrue(worldMap.getWorldBox(player.getX(), player.getY()).isOccupied());
	}
	
	@Test
	public void testSouthDirectionMoveToplayer(){
		System.out.println("------ TEST SOUTH DIRECTION MOVE TO A OTHER PLAYER ------\n");
		
		/* Initialisation */
		int x = 0;
		int y = 0;
		Player player = new Player("p", x, y, new SouthDirection(), new LinkedList<>(Arrays.asList(CommandEnum.AVANCER)));
		Player player2 = new Player("p", x, y+1, new NorthDirection(), new ArrayList<>());
		WorldMap worldMap = new WorldMap(1,2);
		worldMap.setPlayers(Arrays.asList(player, player2));
		mapService.printMap(worldMap); //affichage de la map apr�s initialisation
		
		/* Tests apr�s initialisation */
		Assert.assertTrue(worldMap.getWorldBox(x, y+1).isOccupied());
		Assert.assertTrue(worldMap.getWorldBox(player.getX(), player.getY()).isOccupied());
			
		/* Execution de l'op�ration */
		mapService.runMap(worldMap);
		
		/* Tests apr�s execution */
		Assert.assertTrue(player.getX().equals(x)); // Le personnage ne peut pas se d�placer vers une case occup�
		Assert.assertTrue(player.getY().equals(y));
		Assert.assertTrue(worldMap.getWorldBox(x, y).isOccupied());
		Assert.assertTrue(worldMap.getWorldBox(player.getX(), player.getY()).isOccupied());
	}
	
	@Test
	public void testEastDirectionMoveToplayer(){
		System.out.println("------ TEST EAST DIRECTION MOVE TO A OTHER PLAYER ------\n");
		
		/* Initialisation */
		int x = 0;
		int y = 0;
		Player player = new Player("p", x, y, new EastDirection(), new LinkedList<>(Arrays.asList(CommandEnum.AVANCER)));
		Player player2 = new Player("p", x+1, y, new NorthDirection(), new ArrayList<>());
		WorldMap worldMap = new WorldMap(2,1);
		worldMap.setPlayers(Arrays.asList(player, player2));
		mapService.printMap(worldMap); //affichage de la map apr�s initialisation
		
		/* Tests apr�s initialisation */
		Assert.assertTrue(worldMap.getWorldBox(x+1, y).isOccupied());
		Assert.assertTrue(worldMap.getWorldBox(player.getX(), player.getY()).isOccupied());
		
		/* Execution de l'op�ration */
		mapService.runMap(worldMap);
		
		/* Tests apr�s execution */
		Assert.assertTrue(player.getX().equals(x)); // Le personnage ne peut pas se d�placer vers une case occup�
		Assert.assertTrue(player.getY().equals(y));
		Assert.assertTrue(worldMap.getWorldBox(x, y).isOccupied());
		Assert.assertTrue(worldMap.getWorldBox(player.getX(), player.getY()).isOccupied());
	}
	
	@Test
	public void testWestDirectionMoveToplayer(){
		System.out.println("------ TEST WEST DIRECTION MOVE TO A OTHER PLAYER ------\n");
		
		/* Initialisation */
		int x = 1;
		int y = 0;
		Player player = new Player("p", x, y, new WestDirection(), new LinkedList<>(Arrays.asList(CommandEnum.AVANCER)));
		Player player2 = new Player("p", x-1, y, new NorthDirection(), new ArrayList<>());
		WorldMap worldMap = new WorldMap(2,1);
		worldMap.setPlayers(Arrays.asList(player, player2));
		mapService.printMap(worldMap); //affichage de la map apr�s initialisation
		
		/* Tests apr�s initialisation */
		Assert.assertTrue(worldMap.getWorldBox(x-1, y).isOccupied());
		Assert.assertTrue(worldMap.getWorldBox(player.getX(), player.getY()).isOccupied());
			
		/* Execution de l'op�ration */
		mapService.runMap(worldMap);
		
		/* Tests apr�s execution */
		Assert.assertTrue(player.getX().equals(x)); // Le personnage ne peut pas se d�placer vers une case occup�
		Assert.assertTrue(player.getY().equals(y));
		Assert.assertTrue(worldMap.getWorldBox(x, y).isOccupied());
		Assert.assertTrue(worldMap.getWorldBox(player.getX(), player.getY()).isOccupied());
	}
	
	@Test
	public void testNorthDirectionTurnRight(){
		System.out.println("------ TEST NORTH DIRECTION TURN RIGHT ------\n");
		
		/* Initialisation */
		int x = 0;
		int y = 0;
		Player player = new Player("p", x, y, new NorthDirection(), new LinkedList<>(Arrays.asList(CommandEnum.TOURNER_DROITE)));
		WorldMap worldMap = new WorldMap(1,1);
		worldMap.setPlayers(Arrays.asList(player));
		mapService.printMap(worldMap); //affichage de la map apr�s initialisation
		
		/* Execution de l'op�ration */
		mapService.runMap(worldMap);
		
		/* Tests apr�s execution */
		assertNotNull(player.getDirection());
		Assert.assertTrue(player.getDirection().equals(new EastDirection())); // NORD + TURN RIGHT -> EST
	}
	
	@Test
	public void testSouthDirectionTurnRight(){
		System.out.println("------ TEST SOUTH DIRECTION TURN RIGHT ------\n");
		
		/* Initialisation */
		int x = 0;
		int y = 0;
		Player player = new Player("p", x, y, new SouthDirection(), new LinkedList<>(Arrays.asList(CommandEnum.TOURNER_DROITE)));
		WorldMap worldMap = new WorldMap(1,1);
		worldMap.setPlayers(Arrays.asList(player));
		mapService.printMap(worldMap); //affichage de la map apr�s initialisation
		
		/* Execution de l'op�ration */
		mapService.runMap(worldMap);
		
		/* Tests apr�s execution */
		assertNotNull(player.getDirection());
		Assert.assertTrue(player.getDirection().equals(new WestDirection())); // SOUTH + TURN RIGHT -> WEST
	}
	
	@Test
	public void testEastDirectionTurnRight(){
		System.out.println("------ TEST EAST DIRECTION TURN RIGHT ------\n");
		
		/* Initialisation */
		int x = 0;
		int y = 0;
		Player player = new Player("p", x, y, new EastDirection(), new LinkedList<>(Arrays.asList(CommandEnum.TOURNER_DROITE)));
		WorldMap worldMap = new WorldMap(1,1);
		worldMap.setPlayers(Arrays.asList(player));
		mapService.printMap(worldMap); //affichage de la map apr�s initialisation
		
		/* Execution de l'op�ration */
		mapService.runMap(worldMap);
		
		/* Tests apr�s execution */
		assertNotNull(player.getDirection());
		Assert.assertTrue(player.getDirection().equals(new SouthDirection())); // EAST + TURN RIGHT -> SOUTH
	}
	
	@Test
	public void testWestDirectionTurnRight(){
		System.out.println("------ TEST WEST DIRECTION TURN RIGHT ------\n");
		
		/* Initialisation */
		int x = 0;
		int y = 0;
		Player player = new Player("p", x, y, new WestDirection(), new LinkedList<>(Arrays.asList(CommandEnum.TOURNER_DROITE)));
		WorldMap worldMap = new WorldMap(1,1);
		worldMap.setPlayers(Arrays.asList(player));
		mapService.printMap(worldMap); //affichage de la map apr�s initialisation
		
		/* Execution de l'op�ration */
		mapService.runMap(worldMap);
		
		/* Tests apr�s execution */
		assertNotNull(player.getDirection());
		Assert.assertTrue(player.getDirection().equals(new NorthDirection())); // WEST + TURN RIGHT -> NORTH
	}	
	
	@Test
	public void testNorthDirectionTurnLeft(){
		System.out.println("------ TEST NORTH DIRECTION TURN LEFT ------\n");
		
		/* Initialisation */
		int x = 0;
		int y = 0;
		Player player = new Player("p", x, y, new NorthDirection(), new LinkedList<>(Arrays.asList(CommandEnum.TOURNER_GAUCHE)));
		WorldMap worldMap = new WorldMap(1,1);
		worldMap.setPlayers(Arrays.asList(player));
		mapService.printMap(worldMap); //affichage de la map apr�s initialisation
		
		/* Execution de l'op�ration */
		mapService.runMap(worldMap);
		
		/* Tests apr�s execution */
		assertNotNull(player.getDirection());
		Assert.assertTrue(player.getDirection().equals(new WestDirection())); // NORTH + TURN LEFT -> WEST
	}
	
	@Test
	public void testSouthDirectionTurnLeft(){
		System.out.println("------ TEST SOUTH DIRECTION TURN LEFT ------\n");
		
		/* Initialisation */
		int x = 0;
		int y = 0;
		Player player = new Player("p", x, y, new SouthDirection(), new LinkedList<>(Arrays.asList(CommandEnum.TOURNER_GAUCHE)));
		WorldMap worldMap = new WorldMap(1,1);
		worldMap.setPlayers(Arrays.asList(player));
		mapService.printMap(worldMap); //affichage de la map apr�s initialisation
		
		/* Execution de l'op�ration */
		mapService.runMap(worldMap);
		
		/* Tests apr�s execution */
		assertNotNull(player.getDirection());
		Assert.assertTrue(player.getDirection().equals(new EastDirection())); // SOUTH + TURN LEFT -> EAST
	}
	
	@Test
	public void testEastDirectionTurnLeft(){
		System.out.println("------ TEST EAST DIRECTION TURN LEFT ------\n");
		
		/* Initialisation */
		int x = 0;
		int y = 0;
		Player player = new Player("p", x, y, new EastDirection(), new LinkedList<>(Arrays.asList(CommandEnum.TOURNER_GAUCHE)));
		WorldMap worldMap = new WorldMap(1,1);
		worldMap.setPlayers(Arrays.asList(player));
		mapService.printMap(worldMap); //affichage de la map apr�s initialisation
		
		/* Execution de l'op�ration */
		mapService.runMap(worldMap);
		
		/* Tests apr�s execution */
		assertNotNull(player.getDirection());
		Assert.assertTrue(player.getDirection().equals(new NorthDirection())); // EAST + TURN LEFT -> NORTH
	}
	
	@Test
	public void testWestDirectionTurnLeft(){
		System.out.println("------ TEST WEST DIRECTION TURN LEFT ------\n");
		
		/* Initialisation */
		int x = 0;
		int y = 0;
		Player player = new Player("p", x, y, new WestDirection(), new LinkedList<>(Arrays.asList(CommandEnum.TOURNER_GAUCHE)));
		WorldMap worldMap = new WorldMap(1,1);
		worldMap.setPlayers(Arrays.asList(player));
		mapService.printMap(worldMap); //affichage de la map apr�s initialisation
		
		/* Execution de l'op�ration */
		mapService.runMap(worldMap);
		
		/* Tests apr�s execution */
		assertNotNull(player.getDirection());
		Assert.assertTrue(player.getDirection().equals(new SouthDirection())); // WEST + TURN LEFT -> SOUTH
	}
	
	@Test
	public void testSearchTreasure(){
		System.out.println("------ TEST SEARCH TREASURE ------\n");
		
		/* Initialisation */
		int x = 0;
		int y = 0;
		Adventurer adventurer = new Adventurer("a", x, y, new EastDirection(), new LinkedList<>(Arrays.asList(CommandEnum.AVANCER)));
		WorldMap worldMap = new WorldMap(2,1);
		worldMap.setPlayers(Arrays.asList(adventurer));
		worldMap.getWorldBox(1, 0).setTreasures(new LinkedList<>(Arrays.asList(new Treasure(1, 0), new Treasure(1, 0))));
		mapService.printMap(worldMap); //affichage de la map apr�s initialisation
		
		/* Tests apr�s initialisation */
		Assert.assertEquals(0, adventurer.getTreasures().size()); // Le personnage n'a pas de tr�sor
		Assert.assertEquals(2, worldMap.getWorldBox(1, 0).getTreasures().size()); // Il y a 2 tr�sors sur la case
		
		/* Execution de l'op�ration */
		mapService.runMap(worldMap);
		
		/* Tests apr�s execution */
		assertNotNull(adventurer.getTreasures());
		Assert.assertEquals(1, adventurer.getTreasures().size()); // Le personnage r�cup�re 1 tr�sor
		Assert.assertEquals(1, worldMap.getWorldBox(1, 0).getTreasures().size()); // Il y a 1 tr�sor sur la case
	}
	
	@Test
	public void testValidateAdventurers(){
		System.out.println("------ TEST VALIDATE ADVENTURER ------\n");
		
		/* Initialisation */
		int x = 100;
		int y = 100;
		Player player = new Player("p", x, y, new NorthDirection(), new LinkedList<>(Arrays.asList(CommandEnum.AVANCER)));
		WorldMap worldMap = new WorldMap(1,2);
		worldMap.setPlayers(new LinkedList<>(Arrays.asList(player)));
		
		/* Test : personnage plac� � l'exterieur de la map */
		Assert.assertEquals(0, worldMap.getPlayers().size());
		
		player.setX(0);
		player.setY(0);
		worldMap.setPlayers(Arrays.asList(player));
		
		/* Test : personnage plac� dans les bornes de la map */
		Assert.assertEquals(1, worldMap.getPlayers().size());

	}
	
	@Test
	public void testMain1(){
		// Initialisation
		WorldMap worldMap = mapService.initMap("src/test/resources/input/test1.properties");
		
		/* Tests apr�s initialisation */
		Assert.assertEquals(1, worldMap.getPlayers().size());
		Assert.assertEquals("Lara".toLowerCase(), worldMap.getPlayers().get(0).getName().toLowerCase()); // player[0] -> nom lara
		Assert.assertTrue(worldMap.getPlayers().get(0).getX().equals(1)); // Lara x = 1
		Assert.assertTrue(worldMap.getPlayers().get(0).getY().equals(1)); // Lara y = 1
		Assert.assertEquals(CaseTypeEnum.MONTAGNE, worldMap.getWorldBox(1, 0).getCaseType()); // Case montagne
		Assert.assertEquals(CaseTypeEnum.MONTAGNE, worldMap.getWorldBox(2, 1).getCaseType()); // Case montagne	
		Assert.assertFalse(worldMap.getWorldBox(0, 3).getTreasures().isEmpty()); // Case tresors
		Assert.assertEquals(worldMap.getWorldBox(0, 3).getTreasures().size(), 2); // Nombre de tr�sors
		Assert.assertFalse(worldMap.getWorldBox(1, 3).getTreasures().isEmpty()); // Case tresors
		Assert.assertEquals(worldMap.getWorldBox(1, 3).getTreasures().size(), 3); // Nombre de tr�sors
				
		//Afficher la map apr�s initialisation
		mapService.printMap(worldMap);
				
		// Ex�cution des mouvements
		mapService.runMap(worldMap);
		
		/* Tests apr�s execution */
		Assert.assertTrue(worldMap.getPlayers().get(0).getX().equals(0)); // Position x Lara
		Assert.assertTrue(worldMap.getPlayers().get(0).getY().equals(3)); // Position y Lara
	}

}

