import java.util.Scanner;

public class JosephusCatGame
{
	private int catCount;
	private int skipCount;
	private boolean removal;
	private JosephusDLL<JosephusCat> catList = new JosephusDLL<JosephusCat>();
	private JosephusCat lastCat;
	public JosephusCatGame(int cats, int skip)
	{
		catCount = cats;
		skipCount = skip;
		int index = 1;
		while(index <= catCount)
		{
			JosephusCat cat = new JosephusCat(index);
			catList.add(cat);
			index++;
		}
		catList.next(); //sets to first cat
		removal = true;
	}
	public boolean jHasNext()
	{
		return catList.hasNext();
	}
	public String kill()
	{
		int index = skipCount;
		if(removal == true)
		{
			while(index > 1) //accounts for skip forward after removal
			{
				catList.next();
				index--;
			}
			catList.get().loseLife();
		}
		else
		{
			while(index > 0)
			{
				catList.next();
				index--;
			}
			catList.get().loseLife();
		}
		if(catList.get().isDead())
		{
			JosephusCat current = catList.get();
			catList.remove();
			removal = true;
			return current.printKillMessage();
		}
		else 
		{
			removal = false;
			return catList.get().getName() + " lost a life in a catfight! That escalated quickly...";
	  }
	}
	public String gameFinished()
	{
		lastCat = catList.get();
		return lastCat.printVictoryMessage();
	}
	public static void main(String [] args)
	{
		Scanner input = new Scanner(System.in);
		System.out.println("Enter number n of cats, at least 2: ");
		int catNumber = input.nextInt();
		System.out.println("Enter spacing between victims, between 1 and n: ");
		int spacing = input.nextInt();
		JosephusCatGame cats = new JosephusCatGame(catNumber, spacing);
		while(cats.jHasNext())
		{
			System.out.println(cats.kill());
		}
		System.out.println(cats.gameFinished());
	}
}