package ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;


import subway.FindWay;
import subway.Graph;
import subway.Station;
import subway.SubwayLine;



public class Main extends JDialog implements ActionListener {

	private ArrayList<SubwayLine> allLines=new ArrayList<SubwayLine>();

	private Graph G=new Graph();
	
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private JButton btnFindLine = new JButton("查询线路");
	private JButton btnFindWay = new JButton("查询最短路径");
	
	private JLabel labelLine = new JLabel("线路号：");
	private JLabel labelStart = new JLabel("起点：");
	private JLabel labelEnd = new JLabel("终点：");
	
	
	private JTextField edtLine = new JTextField(50);
	private JTextField edtStart = new JTextField(50);
	private JTextField edtEnd = new JTextField(50);
	private JList<String> list=new JList();
	private JScrollPane scrollPane=new JScrollPane(list);
	
		
	private Object tblStationData[][];
	DefaultTableModel tabStationModel=new DefaultTableModel();
	private JTable dataTableStation=new JTable(tabStationModel);
	private JScrollPane StationPane=new JScrollPane(this.dataTableStation);
	
	public Main(Graph G,ArrayList<SubwayLine> Lines) {
		this.allLines=Lines;
		this.G=G;
		
		workPane.setLayout(null);
		workPane.setBounds(0,0,400,250);
		
		btnFindLine.setBounds(10, 40, 160, 20);
		workPane.add(btnFindLine);
		btnFindWay.setBounds(300, 70, 160, 20);
		workPane.add(btnFindWay);
		
		labelLine.setBounds(10, 10, 60, 20);
		workPane.add(labelLine);
		edtLine.setBounds(70, 10, 100, 20);
		workPane.add(edtLine);
		
		labelStart.setBounds(300, 10, 60, 20);
		workPane.add(labelStart);
		edtStart.setBounds(360, 10, 100, 20);
		workPane.add(edtStart);
		
		labelEnd.setBounds(300, 40, 60, 20);
		workPane.add(labelEnd);
		edtEnd.setBounds(360, 40, 100, 20);
		workPane.add(edtEnd);
		
		scrollPane.setBounds(10,100,160,300);
		workPane.add(scrollPane);
		
		StationPane.setBounds(300,100,160,300);
		workPane.add(StationPane);
		
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(600, 500);
		
		// 屏幕居中显示
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();
		this.btnFindLine.addActionListener(this);
		this.btnFindWay.addActionListener(this);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btnFindLine) {
			String LineName=this.edtLine.getText();
			
			try {
			SubwayLine result=new FindWay().PrintLine(LineName, allLines);
			if(result==null) throw new Exception("线路不存在");
			String [] stations= new String[result.getSubwayStation().size()];
			for(int i=0;i<result.getSubwayStation().size();i++) {
				stations[i]=result.getSubwayStation().get(i).getName();
			}
			this.list.setListData(stations);		
			}
			
			catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		else if(e.getSource()==this.btnFindWay){
			String start=this.edtStart.getText();
			String end=this.edtEnd.getText();	
			try {
				
				ArrayList<String> result=new FindWay().findMin(start, end, G, allLines);
				tblStationData =  new Object[result.size()][1];
				for(int i=0;i<result.size();i++) {
					tblStationData[i][0]=result.get(i);			
				}
				Object[] msg= {"最短路径"};
				tabStationModel.setDataVector(tblStationData,msg);
				this.dataTableStation.validate();
				this.dataTableStation.repaint();
				
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		
	}
	
	
}
