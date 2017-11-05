import java.util.*;
public class Main {

	public static boolean haveNeighbors(double probability)
	{
		Random random = new Random();
		double randD= random.nextDouble();
		if (randD<probability)
			return true;
		else
			return false;
	}
	public static void BFS(int source, int destination, List<LinkedList<Integer>> adjacentCity, Stack<Integer> way,int numOfCity)
	{
		List<Boolean> visited = new ArrayList<>();
		List<Integer> prev = new ArrayList<>();
		
		for (int i=0;i<numOfCity;i++)
		{	
			visited.add(false);
			prev.add(numOfCity);
		}
		
		visited.set(source,true);
		
		LinkedList<Integer> queue=new LinkedList<>();
		queue.add(source);
		
		int curr=source;
		
		while (!queue.isEmpty() && curr!=destination)
		{
			curr=queue.poll();
			LinkedList<Integer> adj=adjacentCity.get(curr);
			for (Integer i:adj)
			{
				if (visited.get(i)== false)
				{
					queue.add(i);
					visited.set(i,true);
					prev.set(i, curr);
				}				
			}
			
		}
		int prevLoc=destination;
		while (prevLoc!=source)
		{
			way.push(prevLoc);
			prevLoc=prev.get(prevLoc);
		}
		way.push(source);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		while (true)
		{
			System.out.println("Enter the number of city:");
			int numOfCity = sc.nextInt();
			List<LinkedList<Integer>> adjacentCity= new ArrayList<LinkedList<Integer>>();
			for (int i=0;i<numOfCity;i++)
			{
				LinkedList<Integer> adjacent= new LinkedList<Integer>();
				adjacentCity.add(adjacent);
			}
			String[]cityName=CityNameGenerator.generateCityNames("city_name.txt",numOfCity);
			System.out.println("Enter the probability of having neighbors:");
			double probability=sc.nextDouble();
			boolean haveNeighborsBool;
			Random random = new Random();
			for (int i=0;i<numOfCity;i++)
			{
				if (i>0)
				{
					adjacentCity.get(i).add(i-1);
					adjacentCity.get(i-1).add(i);
				}
				for (int j=i+2;j<numOfCity;j++)
				{
					haveNeighborsBool = haveNeighbors(probability);
					if (haveNeighborsBool)
					{	
						adjacentCity.get(i).add(j);
						adjacentCity.get(j).add(i);
					}
				}
			}
			for (int i=0;i<numOfCity;i++)
			{
				System.out.println("["+i+"]\t"+cityName[i]);
			//	System.out.println(i+"\t"+adjacentCity.get(i));
			}
			System.out.println("Enter source and destination ID separated by space:");
			
			int source=sc.nextInt();
			int destination=sc.nextInt();
			
			List<Integer> wayToGo = new ArrayList<>();
			Stack<Integer> way = new Stack<Integer>();
			
			long start=System.nanoTime();
			BFS(source,destination,adjacentCity,way,numOfCity);
			long end=System.nanoTime();
			
			while (!way.isEmpty())
			{
				int curr=way.pop();
				System.out.println(curr+" "+cityName[curr]);
			}
			System.out.println("Time taken\t:"+ (end-start));
		
			System.out.println("Do you want to continue?Y/N");
			String input=sc.next();
			if (input.equals("N"))
				break;
		}
	}

}
