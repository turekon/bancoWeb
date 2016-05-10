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
import org.primefaces.extensions.component.inputnumber.InputNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.edu.usbcali.demo.delegado.IDelegadoDeNegocio;
import co.edu.usbcali.demo.modelo.Clientes;
import co.edu.usbcali.demo.modelo.Cuentas;

@ViewScoped
@ManagedBean
public class TransaccionesVista {
	
	private static final Logger log = LoggerFactory.getLogger(TransaccionesVista.class);
	
	@ManagedProperty(value="#{delegadoDeNegocio}")
	private IDelegadoDeNegocio delegadoDeNegocio;
	
//	private List<SelectItem> losTipoDocumentosItem;
	
	private List<SelectItem> lasCuentasItem;
	private List<SelectItem> losCajerosItem;
	
//	private SelectOneMenu somTipoDocumento;
	private InputText txtCedulaCliente;
	private InputText txtNombreCliente;
	private SelectOneMenu somNumeroCuenta;
	private SelectOneMenu somCajero;
	private InputNumber txtValor;
	
	private CommandButton btnConsignar;
	private CommandButton btnRetirar;
	private CommandButton btnLimpiar;
	
	public String consignarAction(){
		log.info("Ingresó a consignar");
		
		
		
		return "";
	}
	
	public String retirarAction(){
		log.info("Ingresó a consignar");
		
		return "";
	}
	
	public String limpiarAction(){
		log.info("Ingresó a consignar");
		
		return "";
	}
	
	public void txtCedulaClienteListener() {
		Clientes entity = null;
//		TiposDocumentos tiposDocumentosEntity = null;	
				
		try {
//			if (somTipoDocumento.getValue().toString().equals("-1")) {
//				throw new Exception("No ha seleccionado un tipo de documento");
//			}
//			
//			Long idTipoDoc = Long.parseLong(somTipoDocumento.getValue().toString());
//			tiposDocumentosEntity = delegadoDeNegocio.consultarTiposDocumentosPorId(idTipoDoc);
		
			try {
				Long id = Long.parseLong(txtCedulaCliente.getValue().toString().trim());
				entity = delegadoDeNegocio.consultarClientesPorId(id);
			} catch (Exception e) {
				throw new Exception("El cliente no existe");
			}
			
			if (entity == null) {
				txtNombreCliente.resetValue();
				somNumeroCuenta.setValue("-1");
				somCajero.setValue("-1");
				txtValor.resetValue();
				
				btnConsignar.setDisabled(true);
				btnRetirar.setDisabled(true);
			}else {
				txtNombreCliente.setValue(entity.getCliNombre());
				somNumeroCuenta.setValue("-1");
				somCajero.setValue("-1");
				txtValor.resetValue();
				
				btnConsignar.setDisabled(true);
				btnRetirar.setDisabled(true);
			}
		
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
			e.printStackTrace();
		}
	}
	
	public IDelegadoDeNegocio getDelegadoDeNegocio() {
		return delegadoDeNegocio;
	}

	public void setDelegadoDeNegocio(IDelegadoDeNegocio delegadoDeNegocio) {
		this.delegadoDeNegocio = delegadoDeNegocio;
	}

//	public List<SelectItem> getLosTipoDocumentosItem() {
//		try {
//			if (losTipoDocumentosItem == null) {
//				losTipoDocumentosItem = new ArrayList<SelectItem>();
//				List<TiposDocumentos> listaTiposDocumentosEntity = delegadoDeNegocio.consultarTodosTiposDocumentos();
//				for (TiposDocumentos tiposDocumentos : listaTiposDocumentosEntity) {
//					losTipoDocumentosItem.add(new SelectItem(tiposDocumentos.getTdocCodigo(), tiposDocumentos.getTdocNombre()));
//				}
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return losTipoDocumentosItem;
//	}
//
//	public void setLosTipoDocumentosItem(List<SelectItem> losTipoDocumentosItem) {
//		this.losTipoDocumentosItem = losTipoDocumentosItem;
//	}
//
//	public SelectOneMenu getSomTipoDocumento() {
//		return somTipoDocumento;
//	}
//
//	public void setSomTipoDocumento(SelectOneMenu somTipoDocumento) {
//		this.somTipoDocumento = somTipoDocumento;
//	}

	public InputText getTxtCedulaCliente() {
		return txtCedulaCliente;
	}

	public void setTxtCedulaCliente(InputText txtCedulaCliente) {
		this.txtCedulaCliente = txtCedulaCliente;
	}

	public SelectOneMenu getSomNumeroCuenta() {
		return somNumeroCuenta;
	}

	public void setSomNumeroCuenta(SelectOneMenu somNumeroCuenta) {
		this.somNumeroCuenta = somNumeroCuenta;
	}

	public SelectOneMenu getSomCajero() {
		return somCajero;
	}

	public void setSomCajero(SelectOneMenu somCajero) {
		this.somCajero = somCajero;
	}

	public InputNumber getTxtValor() {
		return txtValor;
	}

	public void setTxtValor(InputNumber txtValor) {
		this.txtValor = txtValor;
	}

	public CommandButton getBtnConsignar() {
		return btnConsignar;
	}
	public void setBtnConsignar(CommandButton btnConsignar) {
		this.btnConsignar = btnConsignar;
	}
	public CommandButton getBtnRetirar() {
		return btnRetirar;
	}
	public void setBtnRetirar(CommandButton btnRetirar) {
		this.btnRetirar = btnRetirar;
	}
	public CommandButton getBtnLimpiar() {
		return btnLimpiar;
	}
	public void setBtnLimpiar(CommandButton btnLimpiar) {
		this.btnLimpiar = btnLimpiar;
	}

	public InputText getTxtNombreCliente() {
		return txtNombreCliente;
	}

	public void setTxtNombreCliente(InputText txtNombreCliente) {
		this.txtNombreCliente = txtNombreCliente;
	}

	public List<SelectItem> getLasCuentasItem() {
		try {
			if (lasCuentasItem == null) {
				lasCuentasItem = new ArrayList<SelectItem>();
				List<Cuentas> listaCuentasItem = delegadoDeNegocio.consultarTodosCuentas();
				for (Cuentas cuentas : listaCuentasItem) {
					lasCuentasItem.add(new SelectItem(cuentas.getCueNumero(), cuentas.getCueNumero()));
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lasCuentasItem;
	}

	public void setLasCuentasItem(List<SelectItem> lasCuentasItem) {
		this.lasCuentasItem = lasCuentasItem;
	}

	public List<SelectItem> getLosCajerosItem() {
		return losCajerosItem;
	}

	public void setLosCajerosItem(List<SelectItem> losCajerosItem) {
		this.losCajerosItem = losCajerosItem;
	}
	
	
}
