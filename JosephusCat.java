public class JosephusCat
{
		private int catNumber;
		private int catLives;
		public JosephusCat(int number)
		{
			catNumber = number;
			catLives = number;
		}
		public String getName()
		{
			return "Cat " + catNumber;
		}
		public void loseLife()
		{
			catLives--;
		}
		public boolean isDead()
		{
			return catLives == 0;
		}
		public String printKillMessage()
		{
			return getName() + " went to animal heaven.";
		}
		public String printVictoryMessage()
		{
			return getName() + " is the last cat meowing.";
		}
}