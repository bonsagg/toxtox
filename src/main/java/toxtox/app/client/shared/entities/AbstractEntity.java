package toxtox.app.client.shared.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PersistenceException;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@MappedSuperclass
public abstract class AbstractEntity implements Serializable {
	private static final long serialVersionUID = -2243797978702361686L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id = null;

	@Version
	@Column(name = "version")
	private int version = 0;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false, nullable = false)
	private Date creationTimestamp;

	public Date getCreationTimestamp() {
		return creationTimestamp;
	}

	public void setCreationTimestamp(Date creationTimestamp) {
		if(this.creationTimestamp != null) {
//			my gwt-compiler fails here. (Philipp)
//			throw new PersistenceException("CreationTimestamp not updatable!");
		}

		this.creationTimestamp = creationTimestamp;
	}

	@Temporal(TemporalType.TIMESTAMP)
	private Date changeTimestamp;

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
	
	@PrePersist
	private void prePersist() {
		setCreationTimestamp(new Date());
	}

	@PreUpdate
	private void preUpdate() {
		this.changeTimestamp = new Date();
	}

	public String toString() {
		String result = "";
		if (id != null)
			result += id;
		return result;
	}

	@Override
	public boolean equals(Object that) {
		if (this == that) {
			return true;
		}

		if (that == null) {
			return false;
		}

		if (getClass() != that.getClass()) {
			return false;
		}

		if (id != null) {
			return id.equals(((AbstractEntity) that).id);
		}

		return super.equals(that);
	}

	@Override
	public int hashCode() {
		if (id != null) {
			return id.hashCode();
		}

		return super.hashCode();
	}
	
	public boolean isTransient() {
		return id == null;
	}
}