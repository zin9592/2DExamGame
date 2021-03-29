package zin.game.ui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import zin.game.effect.image.ImageType;

/*
 * DeathController Ŭ����
 * 
 * deathUI ��Ʈ�ѷ��̴�.
 * ĳ���Ͱ� �׾��� �� ǥ���ϴ� ȭ���̴�.
 * 
 */

public class DeathController implements Initializable {
	@FXML
	private ImageView exitBtn;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		exitBtn.setOnMouseClicked(event -> mouseBtnClicked(event));
		exitBtn.setOnMouseEntered(event -> mouseBtnEntered(event));
		exitBtn.setOnMouseExited(event -> mouseBtnExited(event));
	}

	private void mouseBtnEntered(MouseEvent event) {
		if (event.getSource().equals(exitBtn)) {
			exitBtn.setImage(ImageType.ExitBtnSelect.getImage());
		}
	}

	private void mouseBtnExited(MouseEvent event) {
		if (event.getSource().equals(exitBtn)) {
			exitBtn.setImage(ImageType.ExitBtn.getImage());
		}
	}

	private void mouseBtnClicked(MouseEvent event) {
		if (event.getSource().equals(exitBtn)) {
			System.out.println("Exit >>> Game Exited");
			System.exit(0);
		}
	}
}
