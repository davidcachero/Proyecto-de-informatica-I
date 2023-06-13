package models;

public class Intolerance {
	
	private int id;
	private String nombre;
	private String image;
	
	public Intolerance(int id, String nombre, String image) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.image = image;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	
	

}
