package pe.akiramenai.project.unasam.spring.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.akiramenai.project.unasam.spring.model.Usuario;
import pe.akiramenai.project.unasam.spring.repository.IRoleDAO;
import pe.akiramenai.project.unasam.spring.service.IReporteService;
import pe.akiramenai.project.unasam.spring.service.IUsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private IUsuarioService uService;
	
	@Autowired
	private IReporteService rService;
	
	@Autowired
	private IRoleDAO roleDAO;
	
	@Autowired
	private SesionController sController;
	
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model)
	{
		Usuario nuevoUsuario = new Usuario();
		nuevoUsuario.setComorbilidad("Ninguna");
		model.addAttribute("usuario", nuevoUsuario);
		return "adminUsuario";
	}

	@RequestMapping("/irModificarContraseña")
	public String irModificarContraseña(Model model)
	{
		model.addAttribute("usuario", new Usuario());
		return "modificarContraseña";
	}
	
	@RequestMapping("/verReportes")
	public String verReportes(Model model)
	{
		model.addAttribute("listaReportes", rService.buscarporPacienteUserNameOrdenado(uService.obtenerUsuario()));
		return "monicovidPacienteVerReportes";
	}
	
	@RequestMapping("/listaPacientes")
	public String medicoListarPacientes(Model model)
	{
		model.addAttribute("listaUsuarios", uService.listarPacientes());
		model.addAttribute("texto", "Lista de Pacientes");
		return "monicovidMedicoListarPacientes";
	}

	@RequestMapping("/listaProcesos")
	public String listarA(Map<String, Object>model)
	{
		model.put("listaEstudiantes", uService.listarPacientes());
		return "adminListProcesos";
	}


	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Usuario objUsuario, BindingResult binRes, Model model)
			throws ParseException
	{
		if(binRes.hasErrors()) {
			model.addAttribute("ciclos", uService.listarCiclos(1980,2999));
			return "adminUsuario";
		
		}
		else {
			
				boolean flag=uService.insertar(objUsuario);
				if(flag) {
					roleDAO.insertarRole(objUsuario.getAutoridad(), objUsuario.getId());
					return "redirect:/usuario/lista";}
				else {
					model.addAttribute("mensaje", "Ocurrió un error");
					return "redirect:/usuario/irRegistrar";
				}							
			}		
	}	
	
	@RequestMapping("/recuperado")
	public String recuperado(@ModelAttribute Usuario objUsuario, BindingResult binRes, Model model)
			throws ParseException
	{
		if(binRes.hasErrors()) {
			return "monicovidBienvenido";
		
		}
		else {
				Usuario usuarioActual=uService.obtenerObjetoUsuario();
				
				if(objUsuario==null)
				{
					return "redirect:/monicovid/index";
					
				}
				else
				{
					Date dateActual=new Date();
					usuarioActual.setRecuperado(dateActual.toString());
					uService.modificar(usuarioActual);
					return "monicovidBienvenido";
				}						
			}		
	}	
	
	
	@RequestMapping("/lista")
	public String lista(Model model)
	{
		model.addAttribute("listaUsuarios", uService.listar());
		model.addAttribute("texto", "Lista de todos los Usuarios");
		sController.setMensaje(null);
		return "adminListUsuarios";
	}
}
