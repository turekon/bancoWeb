package co.edu.usbcali.demo.vista;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.edu.usbcali.demo.delegado.IDelegadoDeNegocio;
import co.edu.usbcali.demo.modelo.TiposUsuarios;
import co.edu.usbcali.demo.modelo.Usuarios;

@ViewScoped
@ManagedBean
public class UsuarioVista {

	private static final Logger log = LoggerFactory.getLogger(UsuarioVista.class);

	@ManagedProperty(value = "#{delegadoDeNegocio}")
	private IDelegadoDeNegocio delegadoDeNegocio;

	private List<Usuarios> losUsuarios;

	private List<SelectItem> losTiposUsuariosItem;

	private InputText txtCedula;
	private InputText txtNombre;
	private InputText txtLogin;
	private InputText txtClave;

	private SelectOneMenu somTiposUsuario;

	private CommandButton btnCrear;
	private CommandButton btnModificar;
	private CommandButton btnBorrar;
	private CommandButton btnLimpiar;
	
	public String crearAction(){
		log.info("Ingresó a crear");
		
		try {
			Usuarios usuarios = new Usuarios();
			usuarios.setUsuCedula(Long.parseLong(txtCedula.getValue().toString().trim()));
			usuarios.setUsuClave(txtClave.getValue().toString().trim());
			usuarios.setUsuLogin(txtLogin.getValue().toString().trim());
			usuarios.setUsuNombre(txtNombre.getValue().toString().trim());
			
			TiposUsuarios tiposUsuarios = delegadoDeNegocio.consultarTiposUsuariosPorId(Long.parseLong(somTiposUsuario.getValue().toString()));
			usuarios.setTiposUsuarios(tiposUsuarios);
			
			delegadoDeNegocio.grabarUsuarios(usuarios);
			
			limpiarAction();
			
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "El usuario se ha grabado con éxito", ""));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, e.getMessage(), ""));
			e.printStackTrace();
		}
		
		return "";
	}
	
	public String modificarAction(){
		log.info("Ingresó a modificar");
		
		try {
			Usuarios usuarios = new Usuarios();
			usuarios.setUsuCedula(Long.parseLong(txtCedula.getValue().toString().trim()));
			usuarios.setUsuClave(txtClave.getValue().toString().trim());
			usuarios.setUsuLogin(txtLogin.getValue().toString().trim());
			usuarios.setUsuNombre(txtNombre.getValue().toString().trim());
			
			TiposUsuarios tiposUsuarios = delegadoDeNegocio.consultarTiposUsuariosPorId(Long.parseLong(somTiposUsuario.getValue().toString()));
			usuarios.setTiposUsuarios(tiposUsuarios);
			
			delegadoDeNegocio.modificarUsuarios(usuarios);
			
			limpiarAction();
			
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "El usuario se ha modificado con éxito", ""));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, e.getMessage(), ""));
			e.printStackTrace();
		}
		
		return "";
	}
	
	public String borrarAction(){
		log.info("Ingresó a borrar");
		
		try {
			Usuarios usuarios = new Usuarios();
			usuarios.setUsuCedula(Long.parseLong(txtCedula.getValue().toString().trim()));
			
			delegadoDeNegocio.borrarUsuarios(usuarios);
			
			limpiarAction();
			
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "El usuario se ha eliminado con éxito", ""));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, e.getMessage(), ""));
			e.printStackTrace();
		}
		
		return "";
	}
	
	public String limpiarAction(){
		log.info("Ingresó a crear");
		
		txtCedula.resetValue();
		txtClave.resetValue();
		txtLogin.resetValue();
		txtNombre.resetValue();
		somTiposUsuario.setValue("-1");
		
		btnBorrar.setDisabled(true);
		btnCrear.setDisabled(true);
		btnModificar.setDisabled(true);
		
		return "";
	}
	
	public void txtCedulaListener() {
		Usuarios entity = null;
		
		try {
			Long id = Long.parseLong(txtCedula.getValue().toString().trim());
			entity = delegadoDeNegocio.consultarUsuariosPorId(id);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		if (entity == null) {
//			txtCedula.resetValue();
			txtClave.resetValue();
			txtLogin.resetValue();
			txtNombre.resetValue();
			somTiposUsuario.setValue("-1");
			
			btnBorrar.setDisabled(true);
			btnCrear.setDisabled(false);
			btnModificar.setDisabled(true);
		}else {
//			txtCedula.setValue(entity.getUsuCedula());
			txtClave.setValue(entity.getUsuClave());
			txtLogin.setValue(entity.getUsuLogin());
			txtNombre.setValue(entity.getUsuNombre());
			somTiposUsuario.setValue(entity.getTiposUsuarios().getTusuCodigo());
			
			btnBorrar.setDisabled(false);
			btnCrear.setDisabled(true);
			btnModificar.setDisabled(false);
		}
	}

	public IDelegadoDeNegocio getDelegadoDeNegocio() {
		return delegadoDeNegocio;
	}

	public void setDelegadoDeNegocio(IDelegadoDeNegocio delegadoDeNegocio) {
		this.delegadoDeNegocio = delegadoDeNegocio;
	}

	public List<Usuarios> getLosUsuarios() {
		if (losUsuarios == null) {
			try {
				losUsuarios = delegadoDeNegocio.consultarTodosUsuarios();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return losUsuarios;
	}

	public void setLosUsuarios(List<Usuarios> losUsuarios) {
		this.losUsuarios = losUsuarios;
	}

	public List<SelectItem> getLosTiposUsuariosItem() {
		try {
			if (losTiposUsuariosItem == null) {
				losTiposUsuariosItem = new ArrayList<SelectItem>();
				List<TiposUsuarios> listaTiposUsuarioEntity = delegadoDeNegocio.consultarTodosTiposUsuarios();
				for (TiposUsuarios tiposUsuarios : listaTiposUsuarioEntity) {
					losTiposUsuariosItem.add(new SelectItem(tiposUsuarios.getTusuCodigo(), tiposUsuarios.getTusuNombre()));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return losTiposUsuariosItem;
	}

	public void setLosTiposUsuariosItem(List<SelectItem> losTiposUsuariosItem) {
		this.losTiposUsuariosItem = losTiposUsuariosItem;
	}

	public InputText getTxtCedula() {
		return txtCedula;
	}

	public void setTxtCedula(InputText txtCedula) {
		this.txtCedula = txtCedula;
	}

	public InputText getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(InputText txtNombre) {
		this.txtNombre = txtNombre;
	}

	public InputText getTxtLogin() {
		return txtLogin;
	}

	public void setTxtLogin(InputText txtLogin) {
		this.txtLogin = txtLogin;
	}

	public InputText getTxtClave() {
		return txtClave;
	}

	public void setTxtClave(InputText txtClave) {
		this.txtClave = txtClave;
	}

	public SelectOneMenu getSomTiposUsuario() {
		return somTiposUsuario;
	}

	public void setSomTiposUsuario(SelectOneMenu somTiposUsuario) {
		this.somTiposUsuario = somTiposUsuario;
	}

	public CommandButton getBtnCrear() {
		return btnCrear;
	}

	public void setBtnCrear(CommandButton btnCrear) {
		this.btnCrear = btnCrear;
	}

	public CommandButton getBtnModificar() {
		return btnModificar;
	}

	public void setBtnModificar(CommandButton btnModificar) {
		this.btnModificar = btnModificar;
	}

	public CommandButton getBtnBorrar() {
		return btnBorrar;
	}

	public void setBtnBorrar(CommandButton btnBorrar) {
		this.btnBorrar = btnBorrar;
	}

	public CommandButton getBtnLimpiar() {
		return btnLimpiar;
	}

	public void setBtnLimpiar(CommandButton btnLimpiar) {
		this.btnLimpiar = btnLimpiar;
	}

}
