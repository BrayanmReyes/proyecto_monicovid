package pe.akiramenai.project.unasam.spring.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;

@Entity
@Table(name="users")
public class Usuario implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column
	@NotBlank(message="No puede estar vacío")
	@Pattern(regexp =  "[^0-9]*", message = "Este campo no debe contener números")
	private String nombre;
	
	@Column
	@NotBlank(message="No puede estar vacío")
	@Pattern(regexp =  "[^0-9]*", message = "Este campo no debe contener números")
	private String apellido;
	
	@Column(unique = true)
	@NotBlank(message="No puede estar vacío")
	@Size(min=8,message="El DNI debe tener 8 dígitos")
	@Digits(fraction = 0, integer = 9, message="El DNI no debe contener letras ni caracteres especiales")
	private String dni;
	
	@Column
	//@Email(message="Coloque un correo electrónico válido")
	private String email;
	
	@Column(name="numero", nullable=true)
	@Size(min=9,message="El número de celular debe tener 9 dígitos")
	@Digits(fraction = 0, integer = 11, message="El número de celular no debe contener letras ni caracteres especiales")
	private String numero;
	
	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}
	@Column
	@NotBlank(message="No puede estar vacío")
	private String direccion;
	
	@Column(unique = true)
	//@Email(message="Coloque un correo electrónico válido")
	@NotBlank(message="No puede estar vacío")
	@Pattern(regexp = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" + "\\@" + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" + "(" + "\\." + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" + ")+", message = "Coloque un correo electrónico válido")
	private String username;

	@Column
	@NotBlank(message="No puede estar vacío")
	private String password;

	@Transient
	@NotBlank(message="No puede estar vacío")
	private String confirmPassword;
	
	@Column(name="comorbilidad", nullable=true)
	private String comorbilidad;
	
	@Column(name="recuperado", nullable=true)
	private String recuperado;
	
	@Column
	@NotNull
	private boolean enable;
	
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private List<Role> roles;
	
	@Column
	@NotBlank(message="No puede estar vacío")
	private String autoridad;
	

	public String getRecuperado() {
		return recuperado;
	}

	public void setRecuperado(String recuperado) {
		this.recuperado = recuperado;
	}
				
	public Usuario(Long id) {
		super();
		this.id = id;
	}

	public Usuario() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}
	public String getAutoridad() {
		return autoridad;
	}
	public void setAutoridad(String autoridad) {
		this.autoridad = autoridad;
	}

	public String getComorbilidad() {
		return comorbilidad;
	}

	public void setComorbilidad(String comorbilidad) {
		this.comorbilidad = comorbilidad;
	}
		
}
