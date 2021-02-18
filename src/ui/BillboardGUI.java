package ui;

import model.Billboard;
import model.InfrastructureDepartment;

import java.io.File;
import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class BillboardGUI {
	
	private InfrastructureDepartment department;
	@FXML private Pane MainPane;
	@FXML private TableView<Billboard> table;
	@FXML private TableColumn<Billboard,String> width;
	@FXML private TableColumn<Billboard,String> height;
	@FXML private TableColumn<Billboard,String> inUse;
	@FXML private TableColumn<Billboard,String> brand;
	@FXML private TableColumn<Billboard,String> area;
	@FXML private TextField txtWidth;
	@FXML private TextField txtHeight;
	@FXML private TextField txtBrand;
	@FXML private ToggleGroup use;
	@FXML private RadioButton used;
	@FXML private RadioButton notUsed;
	public BillboardGUI(InfrastructureDepartment department){
		this.department = department;
		try{
			department.loadData();
			}catch(IOException | ClassNotFoundException e){}
	}//End constructor
	
	@FXML
	public void loadBillboardRegistered() throws IOException{
		FXMLLoader fxml = new FXMLLoader(getClass().getResource("ShowBillboardRegisteredWindow.fxml"));
		fxml.setController(this);
		Parent billboardRegistered = fxml.load();
		MainPane.getChildren().setAll(billboardRegistered);
		Stage st = (Stage) billboardRegistered.getScene().getWindow();
		st.setHeight(420);
		st.setWidth(616);
		st.setResizable(false);
		initializeBillboard();
	}//End loadBillboard
	@FXML
	public void loadBillboard() throws IOException{
		FXMLLoader fxml = new FXMLLoader(getClass().getResource("ShowBillboardWindow.fxml"));
		fxml.setController(this);
		Parent billboard = fxml.load();
		MainPane.getChildren().setAll(billboard);
		Stage st = (Stage) billboard.getScene().getWindow();
		st.setHeight(430);
		st.setWidth(509);
		st.setResizable(false);
		initializeDangerousBillboard();
	}//End loadBillboard
	@FXML
	public void loadAddBillboard() throws IOException{
		FXMLLoader fxml = new FXMLLoader(getClass().getResource("addBillboardWindow.fxml"));
		fxml.setController(this);
		Parent addBillboard = fxml.load();
		MainPane.getChildren().setAll(addBillboard);
		Stage st = (Stage) addBillboard.getScene().getWindow();
		st.setHeight(390);
		st.setWidth(330);
		st.setResizable(false);
	}//End loadBillboard
	
	@FXML
	public void importData(){
		FileChooser fc = new FileChooser();
		fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("CSV", "*.csv"));
		File data = fc.showOpenDialog(MainPane.getScene().getWindow());
		try{
			if(data != null){
				department.importData(data);
				initializeBillboard();
			}
		}catch(IOException e){System.out.println("Tremendo error crack");}
	}//End importData
	@FXML
	public void exportData(){
		FileChooser fc = new FileChooser();
		fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("CSV", "*.csv"));
		File data = fc.showSaveDialog(MainPane.getScene().getWindow());
		try{
			if(data != null)
				department.exportData(data);
		}catch(IOException e){System.out.println("Tremendo error crack");}
	}//End exportData
	@FXML
	public void exportDangerousBillboard(){
		Alert status = new Alert(null);
		FileChooser fc = new FileChooser();
		fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Txt", "*.txt"));
		File data = fc.showSaveDialog(MainPane.getScene().getWindow());
		try{
			if(data != null){
				department.exportDangerousBillboards(data);
				status.setAlertType(AlertType.INFORMATION);
				status.setHeaderText(null);
				status.setContentText("Dangerous billboard exported succesfully.");
			}//End if
		}catch(IOException e){
			status.setAlertType(AlertType.ERROR);
			status.setHeaderText(null);
			status.setContentText("Error exporting dangerous billboard.");
		}
		status.showAndWait();
	}//End exportDangerousBillboard
	
	@FXML
	public void addBillboard(){
		Alert status = new Alert(null);
		status.setAlertType(AlertType.ERROR);
		status.setHeaderText(null);
		status.setContentText("The values given are incorrects.");
		if(!txtBrand.getText().equals("") && !txtHeight.getText().equals("") && !txtWidth.getText().equals("")){
			try{
				department.addBillboard(
				Double.parseDouble(txtWidth.getText()), Double.parseDouble(txtHeight.getText()), getUse(),
				txtBrand.getText());
				txtWidth.setText("");
				txtHeight.setText("");
				txtBrand.setText("");
				status.setAlertType(AlertType.INFORMATION);
				status.setHeaderText(null);
				status.setContentText("The billboard has been added succesfully.");
			}catch(IllegalArgumentException | IOException e){}
		}//End if
		status.showAndWait();
	}//End addBillboard
	
	private boolean getUse() {
		boolean s = false;
		if(used.isSelected())
			s = true;
		return s;
	}//End getRadioBtnOn
	
	private void initializeDangerousBillboard(){
		ObservableList<Billboard> dangeorusBillboard = FXCollections.observableArrayList(department.getDangerousBillboards());
		table.setItems(dangeorusBillboard);
		brand.setCellValueFactory(new PropertyValueFactory<Billboard,String>("brand"));
		area.setCellValueFactory(new PropertyValueFactory<Billboard,String>("area"));
	}//End initializeDangerousBillboard
	
	private void initializeBillboard(){
		ObservableList<Billboard> dangeorusBillboard = FXCollections.observableArrayList(department.getBillboards());
		table.setItems(dangeorusBillboard);
		width.setCellValueFactory(new PropertyValueFactory<Billboard,String>("width"));
		height.setCellValueFactory(new PropertyValueFactory<Billboard,String>("height"));
		inUse.setCellValueFactory(new PropertyValueFactory<Billboard,String>("inUse"));
		brand.setCellValueFactory(new PropertyValueFactory<Billboard,String>("brand"));
	}//End initializeDangerousBillboard
	
}//End BillboardGUI
