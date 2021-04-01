package test;

import java.util.ArrayList;

import test.Commands.Command;
import test.Commands.DefaultIO;

public class CLI {

	ArrayList<Command> commands;
	DefaultIO dio;
	Commands c;
	
	public CLI(DefaultIO dio) {
		this.dio=dio;
		c=new Commands(dio); 
		commands=new ArrayList<>();
		// example: commands.add(c.new ExampleCommand());
		// implement
		commands.add(c.new MenuCommmand());
		commands.add(c.new uploadFile());
		commands.add(c.new changeCoral());
		commands.add(c.new anomalyDetection());
		commands.add(c.new anomalyReport());
		commands.add(c.new detectionAlgo());
		
		
	}
	
	public void start() {
		// implement
		String s;
		commands.get(0).execute();
		do 
		{
			
			s=new String(dio.readText());
			while(s.compareTo("")==0)
				s=new String(dio.readText());
			
				
				switch (s) 
				{
					case "1":
						commands.get(1).execute();
						commands.get(0).execute();	
						break;
					case "2":
						commands.get(2).execute();
						commands.get(0).execute();	
						break;
					case "3":
						commands.get(3).execute();
						commands.get(0).execute();	
						break;
					case "4":
						commands.get(4).execute();
						commands.get(0).execute();	
						break;
					case "5":
						commands.get(5).execute();
						commands.get(0).execute();	
						break;
		
				}
			
		
		  
		
		}while( s.compareTo("6")>-6 &&   s.compareTo("6")<0 );
		
	}
	
	
	
	
	
	
}
