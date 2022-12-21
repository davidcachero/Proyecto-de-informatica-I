package models;

public class Usuario {
	
	private String id;
	private String name;
	private float saldo;
	
	public Usuario(String id, String name, float saldo) {
		this.id = id;
		this.name = name;
		this.saldo = saldo;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public float getSaldo() {
		return saldo;
	}

	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}
	
	public boolean checkId (String id) {
		return this.id.equals(id);
	}
	
	public void calcularSaldo (float saldo) {
		if ((this.saldo - saldo)>0) 
			this.saldo = this.saldo - saldo;
		else
			System.out.println("No hay suficiente saldo"); ///Cambiar esto??
		
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	

	
}
