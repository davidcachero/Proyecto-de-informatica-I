package models;

public class VisualMsg {

	private String type;
	private Object msg;

	
	public VisualMsg(String type, Object msg) {
		this.type = type;
		this.msg = msg;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public Object getMsg() {
		return msg;
	}
	public void setMsg(Object msg) {
		this.msg = msg;
	}
}
