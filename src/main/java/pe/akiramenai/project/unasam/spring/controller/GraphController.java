package pe.akiramenai.project.unasam.spring.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.akiramenai.project.unasam.spring.service.IContactoService;
import pe.akiramenai.project.unasam.spring.service.IReporteService;
import pe.akiramenai.project.unasam.spring.service.ITemperaturaService;
import pe.akiramenai.project.unasam.spring.service.IUsuarioService;


@Controller
@RequestMapping("/graph")
public class GraphController {
	@Autowired
	private ITemperaturaService aService;
	
	@Autowired
	private IContactoService cService;
	
	@Autowired
	private IReporteService rService;
	
	
	@Autowired
	private IUsuarioService eService;//paciente
	
	@Autowired
	private MessageController mController;//paciente
	
	
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
		model.addAttribute("mensaje", "Usted presenta complicaciones en su salud");
		return "monicovidPacienteVerReportes";
	}
}

