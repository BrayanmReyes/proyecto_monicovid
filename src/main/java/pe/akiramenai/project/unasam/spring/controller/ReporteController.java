package pe.akiramenai.project.unasam.spring.controller;

import java.text.ParseException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.akiramenai.project.unasam.spring.model.Reporte;
import pe.akiramenai.project.unasam.spring.service.IOxigenoService;
import pe.akiramenai.project.unasam.spring.service.IReporteService;
import pe.akiramenai.project.unasam.spring.service.ITemperaturaService;
import pe.akiramenai.project.unasam.spring.service.IUsuarioService;
import pe.akiramenai.project.unasam.spring.serviceimpl.UsuarioServiceImpl;

@Controller
@RequestMapping("/reporte")
public class ReporteController {

	@Autowired
	private IUsuarioService uService;
	
	@Autowired
	private IReporteService rService;

	@Autowired
	private ITemperaturaService tService;
	
	@Autowired
	private IOxigenoService oService;
	
	private String back;
	
	@Autowired
	private UsuarioServiceImpl uImpl;

	@RequestMapping("/verReportes")
	public String verReportes(Model model)
	{
		model.addAttribute("listaReportes", rService.buscarporPacienteUserNameOrdenado(uService.obtenerUsuario()));
		if(tService.listarTemperaturabyUsername(uService.obtenerUsuario()).size()>0){
			if(tService.isComplicacionTemperatura(uService.obtenerUsuario()) || oService.isComplicacionOxigeno(uService.obtenerUsuario()))
				return "redirect:/graph/irAlerta";
			else{
				model.addAttribute("listaReportes", rService.buscarporPacienteUserNameOrdenado(uService.obtenerUsuario()));
				uImpl.setMensaje("Usted se encuentra estable");
				model.addAttribute("mensaje", uImpl.getMensaje());
				return "monicovidPacienteVerReportes";
			}
		}
		else{
			model.addAttribute("listaReportes", null);
			model.addAttribute("mensaje", null);
			return "monicovidPacienteVerReportes";
		}

	}
	
	@RequestMapping("/verReportesSintoma/{id}")
	public String verReportesSintoma(@PathVariable Long id, Model model, RedirectAttributes objRedir)
	throws ParseException{
		
		Optional<Reporte>objReporte=rService.buscarId(id);
		if(objReporte==null)
		{
			objRedir.addFlashAttribute("mensaje", "Ocurrió un error al cargar");
			return "redirect://reporte/verReportes";
			
		}
		else
		{
			model.addAttribute("ciclos", uService.listarCiclos(1980,2999));
			if(objReporte.isPresent())
				objReporte.ifPresent(o->model.addAttribute("reporte", o));
			
			return "monicovidPacienteVerSintomas";
		}
	}
		
	@RequestMapping("/back")
	public String getBack() {
		return back;
	}

	public void setBack(String back) {
		this.back = back;
	}
	
}
