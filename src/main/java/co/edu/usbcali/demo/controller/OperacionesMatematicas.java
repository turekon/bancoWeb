package co.edu.usbcali.demo.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.edu.usbcali.demo.dto.Resultado;

@RestController
@RequestMapping("/operacionesMatematicas")
public class OperacionesMatematicas {

	@RequestMapping(value="/sumar/{numeroUno}/{numeroDos}",method=RequestMethod.GET)
	public Resultado sumar(@PathVariable("numeroUno") Integer n1, @PathVariable("numeroDos") Integer n2) {
		Resultado resultado = new Resultado();
		resultado.setValor(n1 + n2);
		return resultado;
	}
	
	@RequestMapping(value="/restar/{numeroUno}/{numeroDos}",method=RequestMethod.GET)
	public Resultado restar(@PathVariable("numeroUno") Integer n1, @PathVariable("numeroDos") Integer n2) {
		Resultado resultado = new Resultado();
		resultado.setValor(n1 - n2);
		return resultado;
	}
	
	@RequestMapping(value="/multiplicar/{numeroUno}/{numeroDos}",method=RequestMethod.GET)
	public Resultado multiplicar(@PathVariable("numeroUno") Integer n1, @PathVariable("numeroDos") Integer n2) {
		Resultado resultado = new Resultado();
		resultado.setValor(n1 * n2);
		return resultado;
	}
	
	@RequestMapping(value="/dividir/{numeroUno}/{numeroDos}",method=RequestMethod.GET)
	public Resultado dividir(@PathVariable("numeroUno") Integer n1, @PathVariable("numeroDos") Integer n2) {
		Resultado resultado = new Resultado();
		resultado.setValor(n1 / n2);
		return resultado;
	}
	
}
