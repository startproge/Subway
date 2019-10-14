package subway;

import java.util.ArrayList;
import java.util.List;

public class FindWay {

	
	private List<Station> outList = new ArrayList<Station>();
	
	public ArrayList<String> findMin(String s1,String s2,Graph G,List<SubwayLine> allLines) throws Exception {
		int size=G.getMaxSize();
		int[][] path=new int[size][size];
		int[][] d=G.getDistance();
			
		for(int i=0;i<size;i++)
		    for(int j=0;j<size;j++){
		        path[i][j]=j;
		}
				
		for(int k=0; k<size; k++){
			for(int i=0; i<size; i++){
				for(int j=0; j<size; j++) {
					if(d[i][k]!=-1&&d[k][j]!=-1) {
						if(d[i][j]>d[i][k]+d[k][j]) {
							d[i][j]=d[i][k]+d[k][j];
							path[i][j]=path[i][k];
						}	
					}
				}
			}
		}
		
		int start=G.findStation(s1);
		int end=G.findStation(s2);		
		if(start==-1)
			throw new Exception("起点不存在");
		if(end==-1)
			throw new Exception("终点不存在");	
		
		ArrayList<String> result=new ArrayList<String>();
		if(start!=-1&&end!=-1) {
			int count=0;
			int temp=path[start][end];
			outList.add(G.getAllStations().get(start));
			while(temp!=end ) {
				outList.add(G.getAllStations().get(temp));
				
				temp=path[temp][end];
			}
			outList.add(G.getAllStations().get(temp));
			result.add(Integer.toString(outList.size()));
			
			//System.out.println(outList.size());
			result.add(outList.get(0).getName());
			//System.out.println(outList.get(0).getName());
			for(int i=1;i<outList.size()-1;i++) {	
				result.add(outList.get(i).getName());
				//System.out.println(outList.get(i).getName());
				if(outList.get(i).isChangeStation()==true) {
					String res=IsChangeLine(outList.get(i-1).getName(),outList.get(i).getName(),outList.get(i+1).getName(),allLines);
					if(res!=null)
						result.add(res);
						//System.out.println("换乘"+result);
				}
			}
			//System.out.println(outList.get(outList.size()-1).getName());		
			result.add(outList.get(outList.size()-1).getName());
			
		}
		
		return result;
		
	}
	
	public  SubwayLine PrintLine(String name,List<SubwayLine> allLines) {
		for(SubwayLine s:allLines) {
			if(name.equals(s.getName())) {
				return s;
			}		
		}
		return null;
	}
	
	public String IsChangeLine(String pre,String mid,String next,List<SubwayLine> allLines) {
		String start=null;
		String end=null;
		for(SubwayLine s:allLines) {
			if(s.HaveStation(pre)&&s.HaveStation(mid))
				start=s.getName();
			if(s.HaveStation(mid)&&s.HaveStation(next))
				end=s.getName();
		}
		if(!start.equals(end))
			return end;
			
		return null;
	}
	
	public int min(int a,int b) {
		if(a<b)
			return a;
		else
			return b;
	}
	
	
}
