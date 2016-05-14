package co.edu.usbcali.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.edu.usbcali.demo.delegado.IDelegadoDeNegocio;
import co.edu.usbcali.demo.dto.ClienteDTO;
import co.edu.usbcali.demo.modelo.Clientes;

@RestController
@RequestMapping("/cliente")
public class ClienteRest {

	@Autowired
	private IDelegadoDeNegocio delegadoDeNegocio;
	
//	public void grabar(ClienteDTO clientes) throws Exception;
//	public void modificar(ClienteDTO clientes) throws Exception;
//	public void borrar(ClienteDTO clientes) throws Exception;
	
	@RequestMapping(value="/consultarClientePorId/{cliId}", method=RequestMethod.GET)
	public ClienteDTO consultarClientePorId(@PathVariable("cliId") long cliId) throws Exception {
		Clientes clientes = delegadoDeNegocio.consultarClientesPorId(cliId);
		if (clientes == null) {
			throw new Exception("El cliente no existe");
		}
		ClienteDTO clienteDTO = new ClienteDTO();
		clienteDTO.setCliDireccion(clientes.getCliDireccion());
		clienteDTO.setCliId(clientes.getCliId());
		clienteDTO.setCliMail(clientes.getCliMail());
		clienteDTO.setCliNombre(clientes.getCliNombre());
		clienteDTO.setCliTelefono(clientes.getCliTelefono());
		clienteDTO.setTdocCodigo(clientes.getTiposDocumentos().getTdocCodigo());
		
		return clienteDTO;
	};
	
	@RequestMapping(value="/consultarTodosLosClientes", method=RequestMethod.GET)
	public List<ClienteDTO> consultarTodos() throws Exception {
		List<Clientes> listaClientes = delegadoDeNegocio.consultarTodosClientes();
		
		if (listaClientes.isEmpty()) {
			throw new Exception("No existe ningún cliente para retornar");
		}
		
		List<ClienteDTO> losClientesDTO = new ArrayList<ClienteDTO>();
		for (Clientes clientes : listaClientes) {
			ClienteDTO clienteDTO = new ClienteDTO();
			clienteDTO.setCliDireccion(clientes.getCliDireccion());
			clienteDTO.setCliId(clientes.getCliId());
			clienteDTO.setCliMail(clientes.getCliMail());
			clienteDTO.setCliNombre(clientes.getCliNombre());
			clienteDTO.setCliTelefono(clientes.getCliTelefono());
			clienteDTO.setTdocCodigo(clientes.getTiposDocumentos().getTdocCodigo());
			
			losClientesDTO.add(clienteDTO);
		}
		return losClientesDTO;
	}
}
