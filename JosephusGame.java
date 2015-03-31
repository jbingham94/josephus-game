import java.util.Scanner;

/**
 * Class which creates a game to mimic the biblical Josephus problem.
 * @author Jasper Bingham
 * @version 10/10/13
 */
public class JosephusGame
{
	private int soldierCount;
	private int skipCount; 
	private JosephusDLL<JosephusSoldier> soldierList = new JosephusDLL<JosephusSoldier>(); //holds soldiers
	private JosephusSoldier lastSoldier; //last soldier alive
	/**
	 * Constructs a Josephus game with a number of soldiers, and a distance between kills.
	 * @param soldiers the number of soldiers playing
	 * @param skip the number of soldiers skipped between kills
	 */
	public JosephusGame(int soldiers, int skip)
	{
		soldierCount = soldiers;
		skipCount = skip;
		int index = 1;
		while(index <= soldierCount) //populates the list, numbering soldiers correctly
		{
			JosephusSoldier soldier = new JosephusSoldier(index);
			soldierList.add(soldier);
			index++;
		}
		soldierList.next(); //sets to first soldier
	}
	/**
	 * Method which determines whether there is more than one soldier left.
	 * @return true if there is more than one soldier left
	 */
	public boolean jHasNext()
	{
		return soldierList.hasNext();
	}
	/**
	 * Method which kills off the next soldier, and prints an update to the console.
	 * @return which soldier has been killed
	 */
	public String kill()
	{
		int index = skipCount;
		//moves forward to next victim
		while(index > 1) //accounts for skip forward after last removal
		{
			soldierList.next();
			index--;
		}
		JosephusSoldier current = soldierList.get();
		soldierList.remove();
		return current.printKillMessage();
	}
	/**
	 * Method which indicates which soldier wins the game.
	 * @return which soldier wins the game
	 */
	public String gameFinished()
	{
		lastSoldier = soldierList.get();
		return lastSoldier.printVictoryMessage();
	}
	/**
	 * Runs the Josephus game, taking user input.
	 */
	public static void main(String [] args)
	{
		Scanner input = new Scanner(System.in);
		System.out.println("Enter number n of soldiers, at least 2: ");
		int soldiers = input.nextInt();
		System.out.println("Enter spacing between victims, between 1 and n: ");
		int spacing = input.nextInt();
		JosephusGame josephus = new JosephusGame(soldiers, spacing);
		while(josephus.jHasNext()) //keep killing until one man left
		{
			System.out.println(josephus.kill());
		}
		System.out.println(josephus.gameFinished());
	}
}