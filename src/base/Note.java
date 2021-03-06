package base;
import java.util.Date;
import java.util.Objects;

public class Note implements Comparable<Note>, java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Date date;
	private String title;
	
	public Note(String title) {
		this.title = title;
		date = new Date(System.currentTimeMillis());
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public boolean equals(Note note) {
		return this.title.equals(note.getTitle());
	}

	@Override
	public int hashCode() {
		return Objects.hash(title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Note other = (Note) obj;
		return Objects.equals(title, other.title);
	}
	
	@Override
	public int compareTo(Note o) {
		return date.compareTo(o.date);
	}
	
	public String toString() {
		return date.toString() + "\t" + title;
	}
}
