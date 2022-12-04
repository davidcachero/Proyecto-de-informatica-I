package models;

public class Usuario {
	
	private String id;
	private float saldo;
	
	public Usuario(String id, float saldo) {
		this.setId(id);
		this.setSaldo(saldo);
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
	
}
