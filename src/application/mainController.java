package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class mainController implements Initializable {

	@FXML
	TextField textFieldNumber;
	@FXML
	ComboBox<String> comboBoxValue;
	@FXML
	Label labelscore;
	@FXML
	Button button1;
	ArrayList<currency> list = new ArrayList<>();
	ObservableList<String> observableList = FXCollections.observableArrayList();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		try {
			Document doc = Jsoup.connect("http://www.nbp.pl/home.aspx?f=/kursy/kursya.html").get();
			Elements elemntTable = doc.select(".pad5");
			Elements newsHeadlines1 = elemntTable.select("td");
			String name = "";
			float number = 0;
			for (int i = 0; i < newsHeadlines1.size(); i++) {
				if (i == 0 || (i % 3) == 0) {
					name = newsHeadlines1.get(i).text();
				} else if ((i % 3) == 2) {
					String s = newsHeadlines1.get(i).text();
					s = s.replaceAll(",", ".");
					number = Float.valueOf(s);
					list.add(new currency(name, number));
				} else {
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < list.size(); i++) {
			observableList.add(list.get(i).getNameCurrency());
		}
		comboBoxValue.setItems(observableList);

	}

	public void button() {
		float Number = isOnTextFieldNumer();
		if (Number == 0) {
			textFieldNumber.setText("Musisz wpisaæ liczbê");
			return;
		}
		if (comboBoxValue.getSelectionModel().getSelectedItem() == null) {
			comboBoxValue.setPromptText("Musisz wybraæ");
			return;
		}
		Float Number2 = getCurrency();
		if (Number2 == 0) {
			return;
		}
		System.out.println("Wartoœæ 1: " + Number + "\nWartoœæ 2: " + Number2);
		labelscore.setText((Number * Number2) + "");
	}

	// Sprawdza czy zosta³a podana liczba w TextField i zamienia na liczbe
	// zmiennoprzecinkowa
	private float isOnTextFieldNumer() {
		try {
			Float f = Float.valueOf(textFieldNumber.getText());
			return f;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;

	}

	// Zamienia wybrany kurs na kurs œredni(typ zmiennoprzecinkowy)
	private float getCurrency() {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getNameCurrency().equals(comboBoxValue.getSelectionModel().getSelectedItem())) {
				return list.get(i).getNumber();
			}
		}
		return 0;

	}

}
