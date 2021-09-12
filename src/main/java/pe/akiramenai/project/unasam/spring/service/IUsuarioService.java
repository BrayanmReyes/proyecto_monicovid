package pe.akiramenai.project.unasam.spring.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import pe.akiramenai.project.unasam.spring.model.Usuario;

public interface IUsuarioService {
		public boolean insertar(Usuario Usuario);
		public boolean modificar(Usuario Usuario);
		public boolean modificarNuevaPassword(Usuario Usuario);
		public void eliminar(Long id);
		public Optional<Usuario> buscarId(Long id);
		public Optional<Usuario> listarId(Long id);
		public List<Usuario> listar();
		public List<Usuario> buscarUsuario(String dni);
		public List<Usuario> buscarEmailUsuario(String email);
		public String buscarEmail(Long id);
		public List<Usuario> listarPacientes();
		public List<String> listarCiclos(int anio1, int anio2);
		public Optional<Usuario>listarCodigo(String codigo);
		public Usuario getUsuario();
		public void setUsuario(Usuario usuario);
		public List<Usuario> buscarByApellidos(String apellido);
		public List<Usuario> buscarByCodigo(String username);
		//Encontrar el usuario por UserName
		public Usuario buscarPorUserName(String username);
		public String obtenerUsuario();
		public Usuario obtenerObjetoUsuario();
		public Date obtenerDate();
		public void cambiarUsuarioRecuperado(String recuperado, Long id);
}
