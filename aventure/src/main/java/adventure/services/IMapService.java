package adventure.services;

import adventure.models.worldmap.WorldMap;

public interface IMapService {
	
	/** Initialisation de la map*/
	WorldMap initMap(String path);
	
	/** Afficher la map*/
	void printMap(WorldMap worldMap);
	
	/** Anime la map*/
	void runMap(WorldMap worldMap);
	
	/** Export la map vers un fichier */
	void exportMapState(WorldMap worldMap, String path);
}
