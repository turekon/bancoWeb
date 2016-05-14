package co.edu.usbcali.demo.controller.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import co.edu.usbcali.demo.dto.Resultado;

public class OperacionesMatematicasTest {

	private static final Logger log = LoggerFactory.getLogger(OperacionesMatematicasTest.class);
	
	@Test
	public void aTest() {
		RestTemplate restTemplate = new RestTemplate();
		Resultado resultado = restTemplate.getForObject("http://localhost:8080/bancoWeb/controller/operacionesMatematicas/sumar/7/4", Resultado.class);
		assertNotNull("El resultado es null " + resultado );
		log.info(resultado.getValor().toString());
	}
	
	@Test
	public void bTest() {
		RestTemplate restTemplate = new RestTemplate();
		Resultado resultado = restTemplate.getForObject("http://localhost:8080/bancoWeb/controller/operacionesMatematicas/restar/7/4", Resultado.class);
		assertNotNull("El resultado es null " + resultado );
		log.info(resultado.getValor().toString());
	}
	
	@Test
	public void cTest() {
		RestTemplate restTemplate = new RestTemplate();
		Resultado resultado = restTemplate.getForObject("http://localhost:8080/bancoWeb/controller/operacionesMatematicas/multiplicar/7/4", Resultado.class);
		assertNotNull("El resultado es null " + resultado );
		log.info(resultado.getValor().toString());
	}
	
	@Test
	public void dTest() {
		RestTemplate restTemplate = new RestTemplate();
		Resultado resultado = restTemplate.getForObject("http://localhost:8080/bancoWeb/controller/operacionesMatematicas/dividir/7/4", Resultado.class);
		assertNotNull("El resultado es null " + resultado );
		log.info(resultado.getValor().toString());
	}

}
