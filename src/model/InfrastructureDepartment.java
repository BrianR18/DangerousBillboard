package model;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

public class InfrastructureDepartment {
	
	public final String BILLBOARD_FILE_NAME = "data/billboard.bbd";
	private final int DANGEROUS_BILLBOARD = 160000;
	private ArrayList<Billboard>  billboards;
	private ArrayList<Billboard> dangerousBillboards;
	
	public InfrastructureDepartment(){
		billboards = new ArrayList<Billboard>();
		dangerousBillboards = new ArrayList<Billboard>();
	}//End Constructor 1
	
	public void addBillboard(double width, double height, boolean use, String brand) throws IOException{
		Billboard b = new Billboard(width,height,use,brand);
		b.calculateArea();
		billboards.add(b);
		saveData();
	}//End addBillboard
	
	public ArrayList<Billboard> getBillboards(){
		return billboards;
	}//End getBillboards
	
	public ArrayList<Billboard> getDangerousBillboards(){
		for(int i = 0; i < billboards.size(); i++){
			if(billboards.get(i).getArea() >= DANGEROUS_BILLBOARD)
				dangerousBillboards.add(billboards.get(i));
		}//End for
		return dangerousBillboards;
	}//End getBillboards
	
	public void importData(File data) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(data));
		br.readLine();
		String line = br.readLine();
		while(line != null){
			String[] parts = line.split("\\|");
			addBillboard(Double.parseDouble(parts[0]),Double.parseDouble(parts[1]),Boolean.parseBoolean(parts[2]),parts[3]);
			line = br.readLine();
		}//End while
		br.close();
	}//End importData
	
	public void exportData(File data) throws IOException{
		PrintWriter pw = new PrintWriter(data);
		String d = "width|height|inUse|brand"; 
		for(int i = 0; i < billboards.size(); i++ ){
			Billboard bb = billboards.get(i);
			d += bb.getWidth() +"|"+ bb.getHeight() +"|"+ bb.getInUse() +"|"+ bb.getBrand()+"\n";
		}//End for
		pw.print(d);
		pw.close();
	}//End importData
	public void exportDangerousBillboards(File data) throws IOException{
		PrintWriter pw = new PrintWriter(data);
		String d = "===========================\n"
				+ "DANGEROUS BILLBOARD REPORT\n"
				+ "===========================\n"
				+ "The dangerous billboard are\n";
		for(int i = 0; i < dangerousBillboards.size(); i++ ){
			Billboard bb = dangerousBillboards.get(i);
			d += (i+1) + ". Billboard " + bb.getBrand() + " with area " + bb.getArea() +"\n";
		}//End for
		pw.print(d);
		pw.close();
	}//End importData
	public void saveData() throws IOException{
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(BILLBOARD_FILE_NAME));
		oos.writeObject(billboards);
		oos.close();
	}//End saveData
	
	@SuppressWarnings("unchecked")
	public boolean loadData() throws IOException, ClassNotFoundException{
		boolean loaded = false;
		File file = new File(BILLBOARD_FILE_NAME);
		if(file.exists()) {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
			billboards = (ArrayList<Billboard>) ois.readObject();
		     ois.close();
		     loaded = true;
		}
		return loaded;
	}//End loadData
}
