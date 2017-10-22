package fieldanalysis.pojo;

/**
 * 
 * @author Shannon Brown
 * 
 * BarrenSection represents one rectangular barren section in the field
 *  - lowerLeft: the coordinates of the lower left square meter of the barrenSection
 *  - upperRight: the coordinates of the upper right square meter of the barrenSection
 *  
 */
public class BarrenSection {
	private Point lowerLeft;
	private Point upperRight;
	
	public BarrenSection(int x0, int y0, int x1, int y1) {
		this.lowerLeft = new Point(x0, y0);
		this.upperRight = new Point(x1, y1);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lowerLeft == null) ? 0 : lowerLeft.hashCode());
		result = prime * result + ((upperRight == null) ? 0 : upperRight.hashCode());
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
		BarrenSection other = (BarrenSection) obj;
		if (lowerLeft == null) {
			if (other.lowerLeft != null)
				return false;
		} else if (!lowerLeft.equals(other.lowerLeft))
			return false;
		if (upperRight == null) {
			if (other.upperRight != null)
				return false;
		} else if (!upperRight.equals(other.upperRight))
			return false;
		return true;
	}

	public Point getLowerLeft() {
		return lowerLeft;
	}
	public void setLowerLeft(Point lowerLeft) {
		this.lowerLeft = lowerLeft;
	}
	public Point getUpperRight() {
		return upperRight;
	}
	public void setUpperRight(Point upperRight) {
		this.upperRight = upperRight;
	}
}
