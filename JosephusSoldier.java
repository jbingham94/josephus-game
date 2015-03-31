/**
 * Class which represents a soldier in the Josephus game.
 * @author Jasper Bingham
 * @version 10/10/13
 */
public class JosephusSoldier
{
	private int soldierNumber;
	/**
	 * Constructs a soldier with an assigned number.
	 * @param number the soldier's number
	 */
	public JosephusSoldier(int number)
	{
		soldierNumber = number;
	}
	/**
	 * Returns the name of the soldier, i.e. Soldier #
	 * @return the soldier's name
	 */
	public String getName()
	{
		return "Soldier " + soldierNumber;
	}
	/**
	 * Prints a message when the soldier dies.
	 * @return a message indicating the soldier is dead
	 */
	public String printKillMessage()
	{
		return getName() + " bit the dust.";
	}
	/**
	 * Prints a message that the soldier has survived the game.
	 * @return a message indicating the soldier survived
	 */
	public String printVictoryMessage()
	{
		return getName() + " is the last man standing.";
	}
}