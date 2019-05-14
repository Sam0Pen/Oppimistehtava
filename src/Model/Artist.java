package Model;

public class Artist {

	private long id;
	private String name;
	
	public Artist(long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	public long getId() {
		return this.id;
	}
	
	@Override
	public String toString(){
		return this.name + " / " + this.id;
	}
}
