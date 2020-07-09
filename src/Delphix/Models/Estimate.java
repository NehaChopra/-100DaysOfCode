package Delphix.Models;

/*
 * Model - Estimate
 * parameters - minutes , platform, direction, delay, finalEtd
 */
public class Estimate {
	private String minutes;
	private String platform;
	private String direction;
	private String delay;
	private int finalEtd;

	public String getMinutes() {
		return minutes;
	}

	public void setMinutes(String minutes) {
		this.minutes = minutes;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getDelay() {
		return delay;
	}

	public void setDelay(String delay) {
		this.delay = delay;
	}

	public int getFinalEtd() {
		int min = 0;
		int del = 0;
		if (this.minutes != null && this.minutes.equalsIgnoreCase("Leaving")) {
			return 0;
		}
		if (this.minutes != null && this.minutes != "") {
			min = Integer.parseInt(this.minutes);
		}
		if (this.delay != null && this.delay != "") {
			del = Integer.parseInt(this.delay);
		}
		this.finalEtd = min + del;
		return this.finalEtd;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((delay == null) ? 0 : delay.hashCode());
		result = prime * result + ((direction == null) ? 0 : direction.hashCode());
		result = prime * result + finalEtd;
		result = prime * result + ((minutes == null) ? 0 : minutes.hashCode());
		result = prime * result + ((platform == null) ? 0 : platform.hashCode());
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
		Estimate other = (Estimate) obj;
		if (delay == null) {
			if (other.delay != null)
				return false;
		} else if (!delay.equals(other.delay))
			return false;
		if (direction == null) {
			if (other.direction != null)
				return false;
		} else if (!direction.equals(other.direction))
			return false;
		if (finalEtd != other.finalEtd)
			return false;
		if (minutes == null) {
			if (other.minutes != null)
				return false;
		} else if (!minutes.equals(other.minutes))
			return false;
		if (platform == null) {
			if (other.platform != null)
				return false;
		} else if (!platform.equals(other.platform))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Estimate [minutes=" + minutes + ", platform=" + platform + ", direction=" + direction + ", delay="
				+ delay + ", finalEtd=" + finalEtd + "]";
	}

}
