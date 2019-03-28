package adventure.services.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import adventure.enums.CaseTypeEnum;
import adventure.enums.CommandEnum;
import adventure.models.adventurer.Adventurer;
import adventure.models.adventurer.Position;
import adventure.models.adventurer.behavior.IDirection;
import adventure.models.adventurer.behavior.impl.EastDirection;
import adventure.models.adventurer.behavior.impl.NorthDirection;
import adventure.models.adventurer.behavior.impl.SouthDirection;
import adventure.models.adventurer.behavior.impl.WestDirection;
import adventure.models.collect.Treasure;
import adventure.models.worldmap.WorldBox;
import adventure.models.worldmap.WorldMap;
import adventure.services.IMapService;

public class MapServiceImpl implements IMapService {
	
	private static final char COMMENT ='#';
	private static final String SEPARATOR = "-";

	@Override
	public WorldMap initMap(String path) {
		WorldMap result = null;
		
		List<Adventurer> adventurers = new ArrayList<>();
		
		try{
			InputStream flux=new FileInputStream(path); 
			InputStreamReader lecture=new InputStreamReader(flux);
			BufferedReader buff=new BufferedReader(lecture);
			String ligne;
			while ((ligne=buff.readLine())!=null){
				try {
					ligne = ligne.trim();
					if(ligne.charAt(0) == COMMENT) continue;
					
					String [] params = ligne.split(SEPARATOR);
					
					String name;
					Integer x;
					Integer y;
					Integer nb;
					
					switch (params[0]) {
						case "C":
							result = new WorldMap(Integer.parseInt(params[1]), Integer.parseInt(params[2]));
						    break;
						case "A":
							
							name = params[1];
							x = Integer.parseInt(params[2]);
							y = Integer.parseInt(params[3]);
							String direction = params[4];
							String commands = params[5];
							
							IDirection directionState;
							switch (direction) {
								case "N": directionState = new NorthDirection(); break;
								case "S": directionState = new SouthDirection(); break;
								case "E": directionState = new EastDirection(); break;
								case "W": directionState = new WestDirection(); break;
								default: directionState = null; break;
							}
							
							List<CommandEnum> commandsEnum = new ArrayList<>();
							for(int i = 0; i< commands.length(); i++) {
								commandsEnum.add(CommandEnum.getEnumByCommand(String.valueOf(commands.charAt(i))));
							}
							
							Adventurer adventurer = new Adventurer(name, x, y, directionState, commandsEnum);
							adventurers.add(adventurer);
							
							if(result != null) {
								WorldBox box = result.getWorldBox(adventurer.getPosition());
								if(box != null) {
									box.setOccupied(true);
								} else {
									System.out.println("Erreur positionnement");
								}
							}
							
							break;
						case "T" : 
							x = Integer.parseInt(params[1]);
							y = Integer.parseInt(params[2]);
							nb = Integer.parseInt(params[3]);
							
							List<Treasure> treasures = result.getWorldBox(x, y).getTreasures();
							for(int i = 0; i < nb; i++) {
								treasures.add(new Treasure());
							}
							break;
						case "M" : 
							if(result != null && result.getWorld() != null) {
								x = Integer.parseInt(params[1]);
								y = Integer.parseInt(params[2]);
								WorldBox box = result.getWorld()[x][y];
								box.setCaseType(CaseTypeEnum.MONTAGNE);
							}
							break;
						default: break;
					}
					
					result.setAdventurers(adventurers);

				} catch (ArrayIndexOutOfBoundsException e) {
					e.toString();
				} catch (NumberFormatException e) {
					e.toString();
				} catch (NullPointerException e) {
					e.toString();
				}
			}
			buff.close(); 
			}		
			catch (Exception e){
			System.out.println(e.toString());
		}
		
		return result;
	}

	@Override
	public void printMap(WorldMap worldMap) {
		try {
			if(worldMap != null && worldMap.getWorld() != null) {
				
				WorldBox[][] tab = worldMap.getWorld();
				
				for(int i = 0; i < worldMap.getHeight(); i++) {
					for(int j = 0; j < worldMap.getWidth(); j++) {				
						Boolean b = false;
						
						for(Adventurer a : worldMap.getAdventurers()){
							if(a.getX().equals(j) && a.getY().equals(i)) {
								System.out.format("A(%s)\t\t\t", a.getName());
								b = true;
							}
						}
						
						if(!b) {
							if(worldMap.getWorldBox(j, i) != null
								&& worldMap.getWorldBox(j, i).getTreasures() != null
								&& worldMap.getWorldBox(j, i).getTreasures().size() > 0) {
								
									System.out.format("T(%d)\t\t\t", worldMap.getWorldBox(j, i).getTreasures().size());
									b = true;
							}
						}
						
						if(!b) System.out.format("%s\t\t\t", tab[j][i].getCaseType().getLogo());
					}
					System.out.println("");
				}
				System.out.println("");System.out.println("");
				
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			e.toString();
		} catch (NullPointerException e) {
			e.toString();
		}
	}

	@Override
	public void runMap(WorldMap worldMap) {
		//DEBUG
		while(!worldMap.hasFinish()) {
			for(Adventurer adventurer : worldMap.getAdventurers()) {
				if(!adventurer.hasFinish()) {
					CommandEnum command = adventurer.getCommands().remove(0);
					switch (command.getCommand()) {	
						case "A" : movingForward(worldMap, adventurer); break;
						case "D" : turnRight(adventurer); break;
						case "G" : turnLeft(adventurer); break;
						default : break;
					}
					System.out.println();
					printMap(worldMap);
				}
			}
		}		
	}
	
	private void turnRight(Adventurer adventurer) {
		adventurer.turnRight();
		successTurn(adventurer);
	}
	
	private void turnLeft(Adventurer adventurer) {
		adventurer.turnLeft();
		successTurn(adventurer);
	}
	
	private void successTurn(Adventurer adventurer) {
		System.out.format("%s se tourne vers la direction %s.\n", 
				adventurer.getName(), 
				adventurer.getDirection().toString());
	}
	
	private void successMoving(Adventurer adventurer) {
		System.out.format("%s se déplace vers la case [%d, %d].\n", 
				adventurer.getName(), 
				adventurer.getX(), 
				adventurer.getY());
	}
	
	private void tryMovingForward(Adventurer adventurer, Position position) {
		System.out.format("%s tente un déplacement vers la case [%d, %d].\n", 
				adventurer.getName(), 
				position.getX(), 
				position.getY());
	}
	
	private void movingForward(WorldMap worldMap, Adventurer adventurer) {
		Position newPosition = adventurer.movingForward();
		WorldBox newBox = worldMap.getWorldBox(newPosition);
		if(newBox == null) {
			tryMovingForward(adventurer, newPosition);
			System.out.println("Déplacement hors des limites");
		} else if(newBox.isOccupied()){
			tryMovingForward(adventurer, newPosition);
			System.out.println("La case est occupée");
		} else if(newBox.getCaseType().equals(CaseTypeEnum.MONTAGNE)){
			tryMovingForward(adventurer, newPosition);
			System.out.println("Déplacement impossible sur une case montagne!");
		} else {
			// Libérer l'ancienne case
			WorldBox oldBox = worldMap.getWorldBox(adventurer.getPosition());
			oldBox.setOccupied(false);
			
			// Déplacement vers la nouvelle case
			adventurer.setPosition(newPosition);
			newBox.setOccupied(true);
			successMoving(adventurer);
			
			// Chercher trésor
			Boolean find = adventurer.searchTreasure(newBox);
			if(find) System.out.format("%s a trouvé un trésor!\n", adventurer.getName());
		}		
	}

	@Override
	public void exportMapState(WorldMap worldMap, String path) {
		File fichier =  new File(path) ;
		
	    try {
	    	Writer writer =  new FileWriter(fichier) ;
	    	
	    	// Export de la carte
	    	writer.write(String.format("C-%d-%d\n",
	    			worldMap.getWidth(),
	    			worldMap.getHeight()));
	    	
	    	WorldBox[][] worldBoxTab = worldMap.getWorld();
			for(int i = 0; i < worldMap.getHeight(); i++) {
				for(int j = 0; j < worldMap.getWidth(); j++) {
					WorldBox box = worldBoxTab[j][i];
					// Export des montagnes
					if(box.getCaseType().equals(CaseTypeEnum.MONTAGNE)) {
						writer.write(String.format("M-%d-%d\n", j, i));
					}
					// Export des trésors
					if(!box.getTreasures().isEmpty()) {
						writer.write(String.format("T-%d-%d-%d\n", j, i, box.getTreasures().size()));
					}
				}
			}
	    	
			// Export des aventuriers
	    	for(Adventurer adventurer : worldMap.getAdventurers()) {
	    		writer.write(String.format("A-%s-%d-%d-%c-%d\n", 
	    				adventurer.getName(),
	    				adventurer.getX(),
	    				adventurer.getY(),
	    				adventurer.getDirection().toString().charAt(0),
	    				adventurer.getTreasures().size())) ;
	    	}
	    	
	    	writer.close() ;

	    	System.out.println("L'export a été exécuté avec succès!");
	    }  catch (IOException e) {
	    	System.out.println(e.getMessage()) ;
	    	e.printStackTrace() ;
	    }
	}
}
