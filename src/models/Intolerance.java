package models;

public class Intolerance {
	
	private int id;
	private String name;
	private String image;
	private boolean visibility;
	
	public Intolerance(int id, String nombre, String image) {
		super();
		this.id = id;
		this.name = nombre;
		this.image = image;
		this.visibility = true; 
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}	

	public boolean isVisibility() {
		return visibility;
	}

	public void setVisibility(boolean visibility) {
		this.visibility = visibility;
	}
}
