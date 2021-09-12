package pe.akiramenai.project.unasam.spring.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pe.akiramenai.project.unasam.spring.service.IReporteService;
import pe.akiramenai.project.unasam.spring.service.ITemperaturaService;
import pe.akiramenai.project.unasam.spring.service.IUsuarioService;
import pe.akiramenai.project.unasam.spring.serviceimpl.UsuarioServiceImpl;


@Controller
@RequestMapping("/graph")
public class GraphController {
	@Autowired
	private ITemperaturaService aService;
	
	@Autowired
	private IReporteService rService;
	
	
	
	
	@Autowired
	private IUsuarioService eService;//paciente
	
	@Autowired
	private MessageController mController;//paciente
	
	@Autowired
	private UsuarioServiceImpl uImpl;


	//Normal
	@RequestMapping("/reporteTemperatura")
	public String listarTemperaturasUsuario(Map<String, Object>model)
	{
		model.put("listaTemperaturas", aService.listar());
		model.put("listaUsuarios", eService.listar());
		return "monicovidReporteTemperaturaUsuario";
	}
	
	
	@RequestMapping("/reporteOxigenacion")
	public String listarOxigenoUsuario(Map<String, Object>model)
	{
		model.put("listaTemperaturas", aService.listar());
		model.put("listaUsuarios", eService.listar());
		return "monicovidReporteOxigenoUsuario";
	}
	
	@RequestMapping("/irAlerta")
	public String irEnviarSMS(Model model)
	{	
		mController.testSMS();
		mController.enviarMensajeComplicacion();
		model.addAttribute("listaReportes", rService.buscarporPacienteUserNameOrdenado(eService.obtenerUsuario()));
		uImpl.setMensaje("Usted presenta complicaciones en su salud");
		model.addAttribute("mensaje", uImpl.getMensaje());
		return "monicovidPacienteVerReportes";
	}
	
	//Paciente Buscado
	@RequestMapping("/reporteTemperaturaPacienteBuscado")
	public String listarTemperaturasPacienteBuscado(Map<String, Object>model)
	{
		
		return "monicovidReporteTemperaturaPacienteBuscado";
	}
	
	@RequestMapping("/reporteOxigenacionPacienteBuscado")
	public String listarOxigenoPacienteBuscado(Map<String, Object>model)
	{
		return "monicovidReporteOxigenoPacienteBuscado";
	}
}

