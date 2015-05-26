package org.funcoding.bbl.model;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Version;
import org.funcoding.bbl.Format;
import javax.persistence.Enumerated;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.funcoding.bbl.model.User;
import java.util.Set;
import java.util.HashSet;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
@Entity
@XmlRootElement
public class Event implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	@Version
	@Column(name = "version")
	private int version;

	@Column
	@NotNull
	private String title;

	@Column(length = 140)
	@Size(max = 140)
	private String summary;

	@Enumerated
	@NotNull
	private Format format;

	@Column(length = 2000)
	@Size(max = 2000)
	private String description;

	@Column
	private String level;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date start;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date end;

	@Column
	@NotNull
	private String room;

	@ManyToMany
	private Set<User> speakers = new HashSet<User>();

	public Long getId() {
		return this.id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public int getVersion() {
		return this.version;
	}

	public void setVersion(final int version) {
		this.version = version;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Event)) {
			return false;
		}
		Event other = (Event) obj;
		if (id != null) {
			if (!id.equals(other.id)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Format getFormat() {
		return format;
	}

	public void setFormat(Format format) {
		this.format = format;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (id != null)
			result += "id: " + id;
		result += ", version: " + version;
		if (title != null && !title.trim().isEmpty())
			result += ", title: " + title;
		if (summary != null && !summary.trim().isEmpty())
			result += ", summary: " + summary;
		if (format != null)
			result += ", format: " + format;
		if (description != null && !description.trim().isEmpty())
			result += ", description: " + description;
		if (level != null && !level.trim().isEmpty())
			result += ", level: " + level;
		if (start != null)
			result += ", start: " + start;
		if (end != null)
			result += ", end: " + end;
		if (room != null && !room.trim().isEmpty())
			result += ", room: " + room;
		return result;
	}

	public Set<User> getSpeakers() {
		return this.speakers;
	}

	public void setSpeakers(final Set<User> speakers) {
		this.speakers = speakers;
	}
}