package adventure.models.adventurer.behavior;

import adventure.models.adventurer.Position;

public interface IDirection {

	/** Avance d'une case */
	Position movingForward(Position p);
	
	/** Se tourne � 90 degr vers la droite */
	IDirection turnRight();
	
	/** Se tourne � 90 degr vers la gauche */
	IDirection turnLeft();
}
