package subway;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ui.Main;

public class work {

	public static void main(String[] args) {

		Graph G=new Graph();
		String pathname = ".\\station.txt";
		ArrayList<String> Information=new ArrayList<String>();
		ArrayList<SubwayLine> allLines=new ArrayList<SubwayLine>();
		
		
		try (
				FileReader reader = new FileReader(pathname);
	             BufferedReader br = new BufferedReader(reader)
	        ) {
	            String line;	        
	            while ((line = br.readLine()) != null) {
	            	Information.add(line);
	            }
	     } 
		catch (IOException e) {
	            e.printStackTrace();
	        }
		
		
		for(int i=0;i<Information.size();i+=2) {
			SubwayLine line =new SubwayLine();
			String LineName=Information.get(i);
			line.setName(LineName);
			String[] stations=Information.get(i+1).split("\\s+");	
			
			for(String s:stations) {
				Station curStation=new Station();
				curStation.setLine(LineName);
				curStation.setName(s);
				line.getSubwayStation().add(curStation);
			}
			
			allLines.add(line);
		}
		
		
		G.InitGraph(allLines);
		new Main(G,allLines).setVisible(true);;
		
	}

}
