package zin.game.window;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import zin.game.effect.image.ImageType;

/*
 * KeyCheckController Ŭ����
 * 
 * keyCheck.fxml�� ��Ʈ�ѷ��̴�.
 * �� ������ ����Ű�� �����ϴ� ȭ���̴�.
 * 
 */

public class KeyCheckController implements Initializable {
	@FXML
	private ImageView backBtn;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		backBtn.setOnMouseEntered(event -> mouseBtnEntered(event));
		backBtn.setOnMouseExited(event -> mouseBtnExited(event));
	}

	private void mouseBtnEntered(MouseEvent event) {
		if (event.getSource().equals(backBtn)) {
			backBtn.setImage(ImageType.BackBtnSelect.getImage());
		}
	}

	private void mouseBtnExited(MouseEvent event) {
		if (event.getSource().equals(backBtn)) {
			backBtn.setImage(ImageType.BackBtn.getImage());
		}
	}
}
