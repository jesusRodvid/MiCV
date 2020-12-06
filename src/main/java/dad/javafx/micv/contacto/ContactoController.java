package dad.javafx.micv.contacto;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import dad.javafx.micv.model.TipoTelefono;
import dad.javafx.micv.model.Contacto;
import dad.javafx.micv.model.Email;
import dad.javafx.micv.model.Telefono;
import dad.javafx.micv.model.Web;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ContactoController implements Initializable{
	
	//	model
	
	private ObjectProperty<Contacto> contacto = new SimpleObjectProperty<>();
	
	//	view

    @FXML
    private SplitPane view;

    public SplitPane getView() {
		return view;
	}

	public void setView(SplitPane view) {
		this.view = view;
	}
	@FXML
    private TitledPane titlepaneTelefono;

    @FXML
    private HBox hboxTelefono;

    @FXML
    private TableView<Telefono> tableviewtelefono;

    @FXML
    private TableColumn<Telefono, String> numerocolumn;

    @FXML
    private TableColumn<Telefono, TipoTelefono> tipocolumn;

    @FXML
    private VBox vboxTelefono;

    @FXML
    private Button addTelefonoButton;

    @FXML
    private Button deleteTelefonoButton;

    @FXML
    private TitledPane titlepaneMail;

    @FXML
    private HBox hboxMail;

    @FXML
    private TableView<Email> tableviewMail;

    @FXML
    private TableColumn<Email, String> emailcolumn;

    @FXML
    private VBox vboxMail;

    @FXML
    private Button addMailbutton;

    @FXML
    private Button deleteMailbutton;

    @FXML
    private TitledPane titlePaneEmail;

    @FXML
    private HBox hboxweb;

    @FXML
    private TableView<Web> tableviewWeb;

    @FXML
    private TableColumn<Web, String> urlcolumn;

    @FXML
    private VBox vboxWeb;

    @FXML
    private Button addWebbutton;

    @FXML
    private Button deleteWebbutton;

    @FXML
    void onAddMailAcction(ActionEvent event) {
    	
    	TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Nuevo e-mail");
		dialog.setHeaderText("Crear una nueva direcciónn de correo.");
		dialog.setContentText("E-mail:");
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()) {
			Email anadido = new Email();
			anadido.setDireccion(result.get());
			contacto.getValue().getEmails().add(anadido);
		}

    }

    @FXML
    void onAddTelefonoButton(ActionEvent event) {
    	Addtelefono dialog = new Addtelefono();
		Optional<Telefono> result = dialog.showAndWait();
		if (result.isPresent()) {
			System.out.println(result.get());
			getContacto().getTelefonos().add(result.get());
		}

    }

    @FXML
    void onAddWebAction(ActionEvent event) {
    	TextInputDialog dialog = new TextInputDialog("http://");
		dialog.setTitle("Nueva Web");
		dialog.setHeaderText("Crear una nueva dirección web.");
		dialog.setContentText("URL:");
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()) {
			Web anadida = new Web();
			anadida.setUrl(result.get());
			contacto.getValue().getWebs().add(anadida);
		}
    }

    @FXML
    void onDeleteMailAction(ActionEvent event) {

    }

    @FXML
    void onDeleteTelefonoButton(ActionEvent event) {

    }

    @FXML
    void onDeleteWebAction(ActionEvent event) {

    }

	public ContactoController() throws IOException{
		super();
		// TODO Auto-generated constructor stub
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ContactoView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		numerocolumn.setCellValueFactory(v -> v.getValue().numeroProperty());
		tipocolumn.setCellValueFactory(v -> v.getValue().tipoProperty());
		emailcolumn.setCellValueFactory(v -> v.getValue().direccionProperty());
		urlcolumn.setCellValueFactory(v -> v.getValue().urlProperty());

		numerocolumn.setCellFactory(TextFieldTableCell.forTableColumn());
		tipocolumn.setCellFactory(ComboBoxTableCell.forTableColumn(TipoTelefono.values()));
		emailcolumn.setCellFactory(TextFieldTableCell.forTableColumn());

		urlcolumn.setCellFactory(TextFieldTableCell.forTableColumn());
		contacto.addListener((o, ov, nv) -> onContactoChanged(o, ov, nv));

	}
	private void onContactoChanged(ObservableValue<? extends Contacto> o, Contacto ov, Contacto nv) {

		System.out.println("ov=" + ov + "/nv=" + nv);

		if (ov != null) {
			// unbind tabla telefonos
			tableviewtelefono.itemsProperty().unbindBidirectional(ov.telefonosProperty());
			// unbind tabla email
			tableviewWeb.itemsProperty().unbindBidirectional(ov.websProperty());
			// unbind tabla web
			tableviewMail.itemsProperty().unbindBidirectional(ov.emailsProperty());

		}

		if (nv != null) {
			// bind tabla telefonos
			tableviewtelefono.itemsProperty().bindBidirectional(nv.telefonosProperty());
			// bind tabla email
			tableviewWeb.itemsProperty().bindBidirectional(nv.websProperty());
			// bind tabla web
			tableviewMail.itemsProperty().bindBidirectional(nv.emailsProperty());
		}
	}

	public final ObjectProperty<Contacto> contactoProperty() {
		return this.contacto;
	}
	

	public final Contacto getContacto() {
		return this.contactoProperty().get();
	}
	

	public final void setContacto(final Contacto contacto) {
		this.contactoProperty().set(contacto);
	}
	
	
	
}

