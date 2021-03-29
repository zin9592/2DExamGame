package zin.game.window;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import zin.game.effect.image.ImageType;

/*
 * TitleWindowController 클래스
 * 
 * titleWindow.fxml 컨트롤러이다.
 * 
 * 메인화면을 나타낸다.
 * 
 */

public class TitleWindowController implements Initializable {
	@FXML
	private ImageView startBtn;
	@FXML
	private ImageView keyBtn;
	@FXML
	private ImageView exitBtn;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		startBtn.setOnMouseClicked(event -> mouseBtnClicked(event));
		startBtn.setOnMouseEntered(event -> mouseBtnEntered(event));
		startBtn.setOnMouseExited(event -> mouseBtnExited(event));
		keyBtn.setOnMouseClicked(event -> mouseBtnClicked(event));
		keyBtn.setOnMouseEntered(event -> mouseBtnEntered(event));
		keyBtn.setOnMouseExited(event -> mouseBtnExited(event));
		exitBtn.setOnMouseClicked(event -> mouseBtnClicked(event));
		exitBtn.setOnMouseEntered(event -> mouseBtnEntered(event));
		exitBtn.setOnMouseExited(event -> mouseBtnExited(event));
	}

	private void mouseBtnEntered(MouseEvent event) {
		if (event.getSource().equals(startBtn)) {
			startBtn.setImage(ImageType.StartBtnSelect.getImage());
		} else if (event.getSource().equals(keyBtn)) {
			keyBtn.setImage(ImageType.KeyBtnSelect.getImage());
		} else if (event.getSource().equals(exitBtn)) {
			exitBtn.setImage(ImageType.ExitBtnSelect.getImage());
		}
	}

	private void mouseBtnExited(MouseEvent event) {
		if (event.getSource().equals(startBtn)) {
			startBtn.setImage(ImageType.StartBtn.getImage());
		} else if (event.getSource().equals(keyBtn)) {
			keyBtn.setImage(ImageType.KeyBtn.getImage());
		} else if (event.getSource().equals(exitBtn)) {
			exitBtn.setImage(ImageType.ExitBtn.getImage());
		}
	}

	private void mouseBtnClicked(MouseEvent event) {
		if (event.getSource().equals(startBtn)) {
			System.out.println("Enter >>> Game Start");
			MainWindow main = new MainWindow();
			main.init();
			try {
				main.start(TitleWindow.getStage());
			} catch (Exception e) {
				System.out.println("TitleWindowController 에러1");
			}
		} else if (event.getSource().equals(keyBtn)) {
			Stage dialog = new Stage(StageStyle.UNDECORATED);
			dialog.initModality(Modality.WINDOW_MODAL);
			dialog.initOwner(TitleWindow.getStage());
			dialog.setTitle("키 확인 화면");
			try {
				Parent parent = FXMLLoader.load(getClass().getResource("keyCheck.fxml"));
				Scene scene = new Scene(parent);
				ImageView backBtn = (ImageView) parent.lookup("#backBtn");
				backBtn.setOnMouseClicked(e->dialog.close());
				dialog.setScene(scene);
				dialog.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (event.getSource().equals(exitBtn)) {
			System.out.println("Exit >>> Game Exited");
			System.exit(0);
		}
	}
}
