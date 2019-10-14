package subway;
import java.util.ArrayList;
import java.util.List;

public class Graph {
	
	private int[][] distance=new int[500][500];
	private int MaxSize;
	private List<Station> allStations= new ArrayList<Station>();
	
		
	public void InitGraph(List<SubwayLine> allLines) {
		
		for(int i=0;i<allLines.size();i++) {
			List<Station> Stations=allLines.get(i).getSubwayStation();
			for(int j=0;j<Stations.size();j++) {
				int index=this.getIndex(Stations.get(j));
				if(index==-1)
					allStations.add(Stations.get(j));
				else if(index!=-1) {
					allStations.get(index).setChangeStation(true);
					allLines.get(i).getSubwayStation().get(j).setChangeStation(true);
				}
			}	
		}
		
		
		MaxSize=allStations.size();
		
		for(int i=0;i<MaxSize;i++)
			for(int j=0;j<MaxSize;j++){
				if(i==j)
				distance[i][j]=0;
				else
				distance[i][j]=500;
			}		
		
		
		for(SubwayLine line:allLines) {
			List<Station> Stations=line.getSubwayStation();
			for(int j=0;j<Stations.size()-1;j++) {
				int start =this.getIndex(Stations.get(j));
				int end =this.getIndex(Stations.get(j+1));
				
				distance[start][end]=1;
				distance[end][start]=1;
			}	
		}
		
		
	}
	
	
	public int getIndex(Station s) {	
		for(int i=0;i<allStations.size();i++)
			if(allStations.get(i).getName().equals(s.getName()))
				return i;	
		return -1;
	}

	public int findStation(String name) {
		for(int i=0;i<allStations.size();i++)
			if(allStations.get(i).getName().equals(name))
				return i;	
		return -1;
	}
	
	public int[][] getDistance() {
		return distance;
	}

	public void setDistance(int[][] distance) {
		this.distance = distance;
	}

	public int getMaxSize() {
		return MaxSize;
	}

	public void setMaxSize(int maxSize) {
		MaxSize = maxSize;
	}

	public List<Station> getAllStations() {
		return allStations;
	}

	public void setAllStations(List<Station> allStations) {
		this.allStations = allStations;
	}
}
