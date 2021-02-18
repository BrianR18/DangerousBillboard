package ui;
import model.InfrastructureDepartment;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	private BillboardGUI bbg;
	private InfrastructureDepartment deparment;
	public Main(){
		deparment = new InfrastructureDepartment();
		bbg = new BillboardGUI(deparment);
	}//End constructor
	
	public static void main(String[] args){
		launch(args);
	}//End main

	@Override
	public void start(Stage window) throws Exception {
		FXMLLoader fxml = new FXMLLoader(getClass().getResource("MainWindow.fxml"));
		fxml.setController(bbg);
		Parent root = fxml.load();
		Scene scene = new Scene(root,null);
		window.setTitle("Infrastructure Department");
		window.setScene(scene);
		window.show();
		bbg.loadBillboardRegistered();
	}//End start
	
}//End Main
