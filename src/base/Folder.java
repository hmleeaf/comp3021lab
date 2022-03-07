package base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Folder implements Comparable<Folder> {
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
	
	@Override
	public int compareTo(Folder f) {
		return name.compareTo(f.name);
	}
	
	public void sortNotes() {
		Collections.sort(notes);
	}
	
	public List<Note> searchNotes(String keywords) {
		List<Note> output = new ArrayList<Note>();
		List<String> words = new ArrayList<String>(Arrays.asList(keywords.split(" ")));
		
		for (Note n : notes) {
			boolean valid = true;
			for (Iterator<String> iter = words.iterator(); iter.hasNext(); ) { // uses explicit iterator because needs to operate on iterator
				String word = iter.next();
				if (word.equals("or") || word.equals("OR")) {
					if (valid) {
						iter.next(); // skip "right operand" of OR operation since "left operand" exists in note
					} else {
						valid = true; // gives a second chance to the "right operand" of the OR operation since "left operand" does not exist
					}
				} else {
					if (n instanceof ImageNote) {
						if (!n.getTitle().toLowerCase().contains(word.toLowerCase())) {
							valid = false;
						}
					} else { // instanceof TextNote
						if (!n.getTitle().toLowerCase().contains(word.toLowerCase()) && !((TextNote)n).content.toLowerCase().contains(word.toLowerCase())) {
							valid = false;
						}
					}
				}
			}
			
			if (valid) {
				output.add(n);
			}
		}
		
		return output;
	}
	
}
