package Delphix.Models;

/*
 * Model - DepartingTrain
 * parameters - station , finalEtd, destination
 */
public class DepartingTrain implements Comparable<DepartingTrain> {
	private String station;
	private int finalEtd;
	private String destination;

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public int getFinalEtd() {
		return finalEtd;
	}

	public void setFinalEtd(int finalEtd) {
		this.finalEtd = finalEtd;
	}

	public String getStation() {
		return station;
	}

	public void setStation(String station) {
		this.station = station;
	}

	@Override
	public int compareTo(DepartingTrain departingTrain) {
		return (this.getFinalEtd() - departingTrain.getFinalEtd());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((destination == null) ? 0 : destination.hashCode());
		result = prime * result + finalEtd;
		result = prime * result + ((station == null) ? 0 : station.hashCode());
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
		DepartingTrain other = (DepartingTrain) obj;
		if (destination == null) {
			if (other.destination != null)
				return false;
		} else if (!destination.equals(other.destination))
			return false;
		if (finalEtd != other.finalEtd)
			return false;
		if (station == null) {
			if (other.station != null)
				return false;
		} else if (!station.equals(other.station))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DepartingTrain [station=" + station + ", finalEtd=" + finalEtd + ", destination=" + destination + "]";
	}

}
