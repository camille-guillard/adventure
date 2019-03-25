package adventure;

import adventure.models.worldmap.WorldMap;
import adventure.services.IMapService;
import adventure.services.impl.MapServiceImpl;

public class Main {

	public static void main(String[] args) {
		IMapService mapService = new MapServiceImpl();
		
		// Initialisation
		WorldMap worldMap = mapService.initMap("src/main/resources/input/config2.properties");
		
		//Afficher la map après initialisation
		mapService.printMap(worldMap);
		
		// Exécution des mouvements
		mapService.runMap(worldMap);
				
		// Exporter le résultat
		mapService.exportMapState(worldMap, "src/main/resources/output/result.txt");
	}

}
