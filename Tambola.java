package tambola_game;

import java.util.HashMap;
import java.util.Scanner;

public class Tambola {

	public static Ticket takeInput()
	{
		Scanner s=new Scanner(System.in);
		System.out.print("enter player name= ");
		String name=s.next();

		return new Ticket(name);
	}

	public static void start() {
		Scanner sc =new Scanner(System.in);
		System.out.print("enter number of players you want=");

		int n=sc.nextInt();

		Ticket player[]=new Ticket[n];

		for(int i=0;i<n;i++)
		{
			player[i]=takeInput();
		}

		for(int i=0;i<n;i++)
		{
			player[i].print();
			System.out.println();
		}

		HashMap<Integer, Integer> numbersDrawn=new HashMap<>();
		numbersDrawn.put(0, 0);
		int nextNumber;

		while(!Ticket.getFullStatus())
		{
			nextNumber=(int) (Math.random()*91);

			while(numbersDrawn.containsKey(nextNumber))
			{
				nextNumber=(int) (Math.random()*91);
			}

			System.out.println("number drawn ="+nextNumber);
			numbersDrawn.put(nextNumber, nextNumber);

			Ticket.strike(player, nextNumber);
			sc.nextLine();	
		}

		for(int a: numbersDrawn.keySet())
		{
			System.out.print(a+" ");
		}

		Ticket winner=player[0];

		System.out.println();
		for(int i=0;i<player.length;i++)
		{
			System.out.println(player[i].getName()+"  =  "+ player[i].getPoints());
			if(winner.getPoints()<player[i].getPoints())
			{
				winner=player[i];
			}
		}
		System.out.println();
		System.out.println("winner is= "+ winner.getName());
		System.out.println("with points="+ winner.getPoints());


	}

	public static void main(String[] args) {
		start();
	}

}
