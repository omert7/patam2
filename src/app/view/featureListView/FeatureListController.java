package app.view.featureListView;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class FeatureListController {
    @FXML
    private ListView<String> listView;
    private ListProperty<String> listViewP;

    // Add a public no-args constructor
    public FeatureListController() {
    }

    @FXML
    private void initialize() {

//        listView.getItems().clear();
        listViewP = new SimpleListProperty<>();
        listView.itemsProperty().bind(listViewP);
    }

    public ListProperty<String> getListViewP() {
        return listViewP;
    }

    public void setListViewP(ListProperty<String> listViewP) {
        this.listViewP = listViewP;
    }

    public ListView<String> getListView() {
        return listView;
    }

    public void setListView(ListView<String> listView) {
        this.listView = listView;
    }

    public ListProperty<String> listViewPProperty() {
        return listViewP;
    }

    public void setListViewP(ObservableList<String> listViewP) {
        this.listViewP.set(listViewP);
    }


}
