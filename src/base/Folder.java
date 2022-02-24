package base;

import java.util.ArrayList;

public class Folder {
	private ArrayList<Note> notes;
	private String name; 
	
	public Folder(String name) {
		this.name = name; 
		notes = new ArrayList<Note>();
	}
	
	public void addNote(Note note) {
		notes.add(note);
	}
	
	public String getName() {
		return this.name;
	}
	
	public ArrayList<Note> getNotes() {
		return notes;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Folder other = (Folder) obj;
		return name.equals(other.name);
	}

	@Override
	public String toString() {
		int nText = 0;
		int nImage = 0;
		
		for (Note note: notes) {
			if (note instanceof TextNote) {
				nText++;
			}
			if (note instanceof ImageNote) {
				nImage++;
			}
		}
		
		return name + ":" + nText + ":" + nImage;
	}
	
	
}
