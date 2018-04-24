package pojos;

import exceptions.InvalidDataException;


public class Cinema {
	

	private int id;
	private String name;
	private String address;
	
	public Cinema(String name,String address) throws InvalidDataException{
		setName(name);
		setAddress(address);
	}
	
	
	
	public Cinema(int id, String name, String address) throws InvalidDataException {
		this(name,address);
		setId(id);
	}



	//getters
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getAddress() {
		return address;
	}
	
	//setters
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) throws InvalidDataException {
		if(!name.isEmpty()) {
			this.name = name;
		}
		else {
			throw new InvalidDataException("Invalid name");
		}
	}
	public void setAddress(String address) throws InvalidDataException {
		if(!address.isEmpty()) {
			this.address = address;
		}
		else {
			throw new InvalidDataException("Invalid address");
		}
	}



	@Override
	public String toString() {
		return "Cinema [id=" + id + ", name=" + name + ", address=" + address + "]";
	}


	
	
}
