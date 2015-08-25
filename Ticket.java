package tambola_game;

import java.util.HashMap;

public class Ticket {

	private String name;
	private int[] ticket;
	private HashMap<Integer, Integer>ticketNumbers;
	private static boolean firstFive=false;
	private static boolean row1=false;
	private static boolean row2=false;
	private static boolean row3=false;
	private static boolean fullHouse=false;
	private static boolean corners=false;
	private int points;

	public Ticket(String player) 
	{
		this.name=player;
		points=0;
		ticket=new int[15];
		ticketNumbers=new HashMap<Integer, Integer>();
		int num;
		int i=0;

		while(i<15)
		{
			num=(int) (Math.random()*91);
			if(num==0)
				num++;

			if(!ticketNumbers.containsKey(num))
			{
				ticket[i]=num;
				ticketNumbers.put(num, i);
				i++;
			}
		}
	}

	public static boolean getFullStatus()
	{
		return fullHouse;
	}

	public void print()
	{
		System.out.print(name);
		System.out.println(" & points= "+ points);
		System.out.println("________________");
		for(int i=0;i<15;i++)
		{
			if(ticket[i]>9)
				System.out.print("|"+ticket[i]);
			else
				System.out.print("| "+ticket[i]);

			if((i+1)%5==0)
			{
				System.out.println("|");
				if(i+1!=15)
					System.out.println("----------------");
			}
		}
		System.out.println("~~~~~~~~~~~~~~~~");

	}

	public static void strike(Ticket[] player,int num) {

		for(int i=0;i<player.length;i++)
		{		
			if(player[i].ticketNumbers.containsKey(num))
			{
				player[i].ticket[player[i].ticketNumbers.get(num)]=0;
				player[i].ticketNumbers.remove(num);
			}
		}

		if(!row1)
		{
			checkRow1(player);
		}
		
		if(!row2)
		{
			checkRow2(player);
		}
		
		if(!row3)
		{
			checkRow3(player);
		}

		if(!firstFive)
		{
			checkFirstFive(player);
		}
		if(!corners)
		{
			checkCorners(player);
		}
		if(!fullHouse)
		{
			checkFullHouse(player);
		}
	}

	private static void checkRow3(Ticket[] player) {
		for(int i=0;i<player.length;i++)
		{
			
				if(player[i].ticket[10]==player[i].ticket[11] && player[i].ticket[11]==player[i].ticket[12] && player[i].ticket[12]==player[i].ticket[13] && player[i].ticket[13]==player[i].ticket[14] && player[i].ticket[10]==0)
				{
					System.out.println("\n"+player[i].getName()+" gets 7 points for row 3");
					row3=true;
					player[i].points+=7;
				}
			
		}				
	}

	private static void checkRow2(Ticket[] player) {
		for(int i=0;i<player.length;i++)
		{
			
				if(player[i].ticket[5]==player[i].ticket[6] && player[i].ticket[6]==player[i].ticket[7] && player[i].ticket[7]==player[i].ticket[8] && player[i].ticket[8]==player[i].ticket[9] && player[i].ticket[5]==0)
				{
					System.out.println("\n"+player[i].getName()+" gets 7 points for row 2");
					row2=true;
					player[i].points+=7;
				}
			
		}				
	}

	private static void checkRow1(Ticket[] player) {
		
		for(int i=0;i<player.length;i++)
		{
			
				if(player[i].ticket[0]==player[i].ticket[1] && player[i].ticket[1]==player[i].ticket[2] && player[i].ticket[2]==player[i].ticket[3] && player[i].ticket[3]==player[i].ticket[4] && player[i].ticket[0]==0)
				{
					System.out.println("\n"+player[i].getName()+" gets 7 points for row 1");
					row1=true;
					player[i].points+=7;
				}
			
		}		
	}

	private static void checkCorners(Ticket[] player) {
		for(int i=0;i<player.length;i++)
		{
			if(player[i].ticket[0]==player[i].ticket[4] && player[i].ticket[10]==player[i].ticket[14])
			{
				System.out.println("\n"+player[i].getName()+" gets 4 points for the corners");
				player[i].points+=4;
				corners=true;
			}
		}
		
	}

	private static void checkFullHouse(Ticket[] player) {

		for(int i=0;i<player.length;i++)
		{
			if(player[i].ticketNumbers.size()==0)

			{
				System.out.println("\n"+player[i].getName()+" gets 10 points for the full house");
				player[i].points+=10;
				fullHouse=true;
			}
		}
	}

	private static void checkFirstFive(Ticket player[]) {
		for(int i=0;i<player.length;i++){
			if(player[i].ticketNumbers.size()==10)
			{
				System.out.println("\n"+player[i].getName()+" gets 5 points for first five");
				firstFive=true;
				player[i].points+=5;
			}
		}
	}

//	private static void checkRow(Ticket player[]) {
//
//		for(int i=0;i<player.length;i++)
//		{
//			for(int j=0;j<15;j+=5)
//			{
//				if(player[i].ticket[j]==player[i].ticket[j+1] && player[i].ticket[j+1]==player[i].ticket[j+2] && player[i].ticket[j+2]==player[i].ticket[j+3] && player[i].ticket[j+3]==player[i].ticket[j+4] && player[i].ticket[j]==0)
//				{
//					System.out.println("\n"+player[i].getName()+" gets 7 points for row");
//					row=true;
//					player[i].points+=7;
//				}
//			}
//		}
//	}

	public String getName() {
		return name;
	}

	public int getPoints() {

		return points;
	}
}