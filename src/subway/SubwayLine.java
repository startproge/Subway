package subway;
import java.util.ArrayList;
import java.util.List;

public class SubwayLine {
	
	private String Name;	
    private ArrayList<Station> SubwayStation=new ArrayList<Station>();
    
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	
	public List<Station> getSubwayStation() {
		return SubwayStation;
	}
	public void setSubwayStation(ArrayList<Station> subwayStation) {
		SubwayStation = subwayStation;
	}
    
	public boolean HaveStation(String name) {
		for(Station s:SubwayStation) {
			if(s.getName().equals(name))
				return true;
		}
		return false;
	}
    
}
