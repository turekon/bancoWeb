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
import co.edu.usbcali.demo.modelo.Clientes;
import co.edu.usbcali.demo.modelo.TiposDocumentos;

@ViewScoped
@ManagedBean
public class ClienteVista {
	
	private final static Logger log = LoggerFactory.getLogger(ClienteVista.class);
	
	@ManagedProperty(value="#{delegadoDeNegocio}")
	private IDelegadoDeNegocio delegadoDeNegocio;
	
	private List<Clientes> losClientes;
	
	private List<SelectItem> losTipoDocumentosItem;
	
	private InputText txtIdentificacion;
	private InputText txtNombre;
	private InputText txtDireccion;
	private InputText txtTelefono;
	private InputText txtMail;
	private SelectOneMenu somTipoDocumento;
	
	private CommandButton btnCrear;
	private CommandButton btnModificar;
	private CommandButton btnBorrar;
	private CommandButton btnLimpiar;
	
	public String crearAction(){
		log.info("ingresó a crear");
		
		try {
			Clientes clientes = new Clientes();
			clientes.setCliDireccion(txtDireccion.getValue().toString().trim());
			clientes.setCliId(Long.parseLong(txtIdentificacion.getValue().toString().trim()));
			clientes.setCliMail(txtMail.getValue().toString().trim());
			clientes.setCliNombre(txtNombre.getValue().toString().trim());
			clientes.setCliTelefono(txtTelefono.getValue().toString().trim());
			
			TiposDocumentos tiposDocumentos = delegadoDeNegocio.consultarTiposDocumentosPorId(Long.parseLong(somTipoDocumento.getValue().toString()));
			clientes.setTiposDocumentos(tiposDocumentos);
			
			delegadoDeNegocio.grabarClientes(clientes);
			
			limpiarAction();
			
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "El cliente se creó con éxito", ""));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
		return "";
	}
	
	public String modificarAction(){
		log.info("ingresó a modificar");
		
		try {
			Clientes clientes = new Clientes();
			clientes.setCliDireccion(txtDireccion.getValue().toString().trim());
			clientes.setCliId(Long.parseLong(txtIdentificacion.getValue().toString().trim()));
			clientes.setCliMail(txtMail.getValue().toString().trim());
			clientes.setCliNombre(txtNombre.getValue().toString().trim());
			clientes.setCliTelefono(txtTelefono.getValue().toString().trim());
			
			TiposDocumentos tiposDocumentos = delegadoDeNegocio.consultarTiposDocumentosPorId(Long.parseLong(somTipoDocumento.getValue().toString()));
			clientes.setTiposDocumentos(tiposDocumentos);
			
			delegadoDeNegocio.modificarClientes(clientes);
			
			limpiarAction();
			
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "El cliente se modificó con éxito", ""));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
		return "";
	}
	
	public String borrarAction(){
		log.info("ingresó a borrar");
		
		try {
			Clientes clientes = new Clientes();
			clientes.setCliId(Long.parseLong(txtIdentificacion.getValue().toString().trim()));
			
			delegadoDeNegocio.borrarClientes(clientes);
			
			limpiarAction();
			
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "El cliente se eliminó con éxito", ""));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
		return "";
	}
	
	public String limpiarAction(){
		log.info("ingresó a limpiar");
		
		txtIdentificacion.resetValue();
		txtDireccion.resetValue();
		txtMail.resetValue();
		txtNombre.resetValue();
		txtTelefono.resetValue();
		somTipoDocumento.setValue("-1");
		
		btnBorrar.setDisabled(true);
		btnCrear.setDisabled(true);
		btnModificar.setDisabled(true);
		
		return "";
	}
	
	public void txtIdentificacionListener() {
		Clientes entity = null;
		
		try {
			Long id = Long.parseLong(txtIdentificacion.getValue().toString().trim());
			entity = delegadoDeNegocio.consultarClientesPorId(id);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		if (entity == null) {
			txtDireccion.resetValue();
			txtMail.resetValue();
			txtNombre.resetValue();
			txtTelefono.resetValue();
			somTipoDocumento.setValue("-1");
			
			btnBorrar.setDisabled(true);
			btnCrear.setDisabled(false);
			btnModificar.setDisabled(true);
		}else {
			txtDireccion.setValue(entity.getCliDireccion());
			txtMail.setValue(entity.getCliMail());
			txtNombre.setValue(entity.getCliNombre());
			txtTelefono.setValue(entity.getCliTelefono());
			somTipoDocumento.setValue(entity.getTiposDocumentos().getTdocCodigo());
			
			btnBorrar.setDisabled(false);
			btnCrear.setDisabled(true);
			btnModificar.setDisabled(false);
		}
	}

	public List<Clientes> getLosClientes() {
		if (losClientes == null) {
			try {
				losClientes = delegadoDeNegocio.consultarTodosClientes();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return losClientes;
	}

	public void setLosClientes(List<Clientes> losClientes) {
		this.losClientes = losClientes;
	}

	public IDelegadoDeNegocio getDelegadoDeNegocio() {
		return delegadoDeNegocio;
	}

	public void setDelegadoDeNegocio(IDelegadoDeNegocio delegadoDeNegocio) {
		this.delegadoDeNegocio = delegadoDeNegocio;
	}

	public List<SelectItem> getLosTipoDocumentosItem() {
		try {
			if (losTipoDocumentosItem == null) {
				losTipoDocumentosItem = new ArrayList<SelectItem>();
				List<TiposDocumentos> listaTiposDocumentoEntity = delegadoDeNegocio.consultarTodosTiposDocumentos();
				for (TiposDocumentos tiposDocumentos : listaTiposDocumentoEntity) {
					losTipoDocumentosItem.add(new SelectItem(tiposDocumentos.getTdocCodigo(), tiposDocumentos.getTdocNombre()));
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return losTipoDocumentosItem;
	}

	public void setLosTipoDocumentosItem(List<SelectItem> losTipoDocumentosItem) {
		this.losTipoDocumentosItem = losTipoDocumentosItem;
	}

	public InputText getTxtIdentificacion() {
		return txtIdentificacion;
	}

	public void setTxtIdentificacion(InputText txtIdentificacion) {
		this.txtIdentificacion = txtIdentificacion;
	}

	public InputText getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(InputText txtNombre) {
		this.txtNombre = txtNombre;
	}

	public InputText getTxtDireccion() {
		return txtDireccion;
	}

	public void setTxtDireccion(InputText txtDireccion) {
		this.txtDireccion = txtDireccion;
	}

	public InputText getTxtTelefono() {
		return txtTelefono;
	}

	public void setTxtTelefono(InputText txtTelefono) {
		this.txtTelefono = txtTelefono;
	}

	public InputText getTxtMail() {
		return txtMail;
	}

	public void setTxtMail(InputText txtMail) {
		this.txtMail = txtMail;
	}

	public SelectOneMenu getSomTipoDocumento() {
		return somTipoDocumento;
	}

	public void setSomTipoDocumento(SelectOneMenu somTipoDocumento) {
		this.somTipoDocumento = somTipoDocumento;
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
