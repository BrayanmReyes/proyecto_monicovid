package pe.akiramenai.project.unasam.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Entity
@Table(name="Sesion")
public class Sesion implements Serializable{

	private static final long serialVersionUID=2L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idSesion;
	
	@Column(name="emailSesion", nullable=true)
	@Pattern(regexp = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" + "\\@" + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" + "(" + "\\." + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" + ")+", message = "Coloque un correo electrónico válido")
	private String emailSesion;

	@Column(name="dniSesion", nullable=true)
	private String dniSesion;
	
	@Column(name="passwordSesion", nullable=true)
	private String passwordSesion;

	public Sesion() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public Sesion(int idSesion, String emailSesion,String dniSesion,String passwordSesion) {
		super();
		this.idSesion = idSesion;
		this.emailSesion = emailSesion;
		this.dniSesion=dniSesion;
		this.passwordSesion = passwordSesion;
	}



	public int getIdSesion() {
		return idSesion;
	}

	public void setIdSesion(int idSesion) {
		this.idSesion = idSesion;
	}
	public String getEmailSesion() {
		return emailSesion;
	}

	public void setEmailSesion(String emailSesion) {
		this.emailSesion = emailSesion;
	}

	public String getPasswordSesion() {
		return passwordSesion;
	}

	public void setPasswordSesion(String passwordSesion) {
		this.passwordSesion = passwordSesion;
	}
	public String getDniSesion() {
		return dniSesion;
	}

	public void setDniSesion(String dniSesion) {
		this.dniSesion = dniSesion;
	}
	
}
