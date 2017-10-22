package fieldanalysis.pojo;

/**
 * 
 * @author Shannon Brown
 *
 * LandPlot represents a one square meter area of land
 *  - point: the coordinates/location of the landPlot
 *  - searched: whether or not the landPlot has been searched
 *  
 */
public class LandPlot {
	private Point point;
	private boolean searched;
	
	public LandPlot(int x, int y, boolean searched) {
		this.point = new Point(x, y);
		this.searched = searched;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((point == null) ? 0 : point.hashCode());
		result = prime * result + (searched ? 1231 : 1237);
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
		LandPlot other = (LandPlot) obj;
		if (point == null) {
			if (other.point != null)
				return false;
		} else if (!point.equals(other.point))
			return false;
		if (searched != other.searched)
			return false;
		return true;
	}

	public Point getPoint() {
		return point;
	}
	public void setPoint(Point plot) {
		this.point = plot;
	}
	public boolean isSearched() {
		return searched;
	}
	public void setSearched(boolean searched) {
		this.searched = searched;
	}
}
