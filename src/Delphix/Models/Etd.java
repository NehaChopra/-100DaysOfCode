package Delphix.Models;

import java.util.List;

/*
 * Model - EstDest
 * parameters - destination , abbreviation, limited, estimate
 */
public class Etd {
	private String destination;
	private String abbreviation;
	private String limited;
	private List<Estimate> estimate;

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public String getLimited() {
		return limited;
	}

	public void setLimited(String limited) {
		this.limited = limited;
	}

	public List<Estimate> getEstimate() {
		return estimate;
	}

	public void setEstimate(List<Estimate> estimate) {
		this.estimate = estimate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((abbreviation == null) ? 0 : abbreviation.hashCode());
		result = prime * result + ((destination == null) ? 0 : destination.hashCode());
		result = prime * result + ((estimate == null) ? 0 : estimate.hashCode());
		result = prime * result + ((limited == null) ? 0 : limited.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Etd other = (Etd) obj;
		if (abbreviation == null) {
			if (other.abbreviation != null)
				return false;
		} else if (!abbreviation.equals(other.abbreviation))
			return false;
		if (destination == null) {
			if (other.destination != null)
				return false;
		} else if (!destination.equals(other.destination))
			return false;
		if (estimate == null) {
			if (other.estimate != null)
				return false;
		} else if (!estimate.equals(other.estimate))
			return false;
		if (limited == null) {
			if (other.limited != null)
				return false;
		} else if (!limited.equals(other.limited))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Etd [destination=" + destination + ", abbreviation=" + abbreviation + ", limited=" + limited
				+ ", estimate=" + estimate + "]";
	}

}
