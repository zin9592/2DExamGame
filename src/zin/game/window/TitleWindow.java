package zin.game.window;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/*
 * TitleWindow 클래스
 * 
 * 게임화면의 타이틀
 * 
 */

public class TitleWindow extends Application {
	private static Parent root;
	private static Scene scene;
	private static Stage stage;
	
	@Override
	public void start(Stage _stage) throws Exception {
		stage = _stage;
		// TODO Auto-generated method stub
		FXMLLoader loader = new FXMLLoader(getClass().getResource("titleWindow.fxml"));
		root = loader.load();	
		scene = new Scene(root);
		stage.setScene(scene);
		stage.initStyle(StageStyle.TRANSPARENT);
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public static Parent getRoot() {
		return root;
	}
	public static Scene getScene() {
		return scene;
	}
	public static Stage getStage() {
		return stage;
	}
}
