package fieldanalysis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.stream.Stream;

import fieldanalysis.pojo.BarrenSection;
import fieldanalysis.pojo.LandPlot;
import fieldanalysis.pojo.Point;

/**
 * 
 * @author Shannon
 * 
 * Input (STDIN)
 * 	- any number of barren sections, each separated by one space
 * 	- each barren section must contain 4 integers separated by one space
 *  - the format of a barren section is as follows:
 *  		"<lower left x coordinate> <lower left y coordinate> <upper right x coordinate> <upper right y coordinate>"
 *  		e.g. "0 292 399 307"
 *  
 *  - the range of x is [0, 399]
 *  - the range of y is [0, 599]
 *  
 *  - example input with four barren sections:
 *  		"48 192 351 207" "48 392 351 407" "120 52 135 547" "260 52 275 547"
 *  
 *  Output (STDOUT)
 *   - the field's fertile areas (contiguous areas of non-barren land) listed from smallest to largest
 *   
 *  Example input -> output:
 *  	"0 292 399 307" -> [116800, 116800]
 *  	"48 192 351 207" "48 392 351 407" "120 52 135 547" "260 52 275 547" -> [22816, 192608]
 *
 */
public class FieldAnalysis {
	
	public static final int FIELD_WIDTH = 400;
	public static final int FIELD_LENGTH = 600;
	
	public static void main(String[] args) {
		
		BarrenSection[] barrenSections = parseInput(args);
		
		LandPlot[][] field = initializeField(barrenSections);
		
		List<Integer> fertileAreas = searchField(field);
		
		System.out.println(fertileAreas);
	}

	public static List<Integer> searchField(LandPlot[][] field) {
		List<Integer> areas = new ArrayList<>();

		int area = -1;
		while (area != 0) {
			area = findFertileArea(field);
			if (area != 0) {
				areas.add(area);
			}
		}
		
		Collections.sort(areas);
		return areas;
	}
	
	private static int findFertileArea(LandPlot[][] field) {
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field[i].length; j++) {
				if (isValid(i, j) && !field[i][j].isSearched()) {
					return findFertileAreaDFS(field, field[i][j]);
				}
			}
		}
		return 0;	// no fertile areas remain
	}
	
	private static int findFertileAreaDFS(LandPlot[][] field, LandPlot initialLandPlot) {
		int area = 0;
		Stack<LandPlot> stack = new Stack<>();
		stack.push(initialLandPlot);
		
		while (!stack.isEmpty()) {
			LandPlot landPlot = stack.pop();
			if (!landPlot.isSearched()) {
				landPlot.setSearched(true);
				for (LandPlot neighbor : getNeighbors(field, landPlot)) {
					stack.push(neighbor);
				}
				area++;
			}
		}
		return area;
	}
	
	private static List<LandPlot> getNeighbors(LandPlot[][] field, LandPlot landPlot) {
		int x = landPlot.getPoint().getX();
		int y = landPlot.getPoint().getY();
		
		List<Point> neighboringPoints = Arrays.asList(new Point(x, y + 1), new Point(x, y - 1), new Point(x + 1, y), new Point(x - 1, y));
		
		List<LandPlot> neighbors = new ArrayList<>();
		
		for (Point point : neighboringPoints) {
			if (isValid(point.getX(), point.getY())) {
				neighbors.add(field[point.getX()][point.getY()]);
			}
		}
		
		return neighbors;
	}
	
	private static boolean isValid(int x, int y) {
		return x >= 0 && x < FIELD_WIDTH && y >=0 && y < FIELD_LENGTH;
	}

	public static LandPlot[][] initializeField(BarrenSection[] barrenSections) {
		LandPlot[][] field = new LandPlot[400][600];
		
		for (int i = 0; i < barrenSections.length; i++) {
			markBarrenSection(field, barrenSections[i]);
		}
		
		// initialize each plot in the field
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field[i].length; j++) {
				if (field[i][j] == null) {
					field[i][j] = new LandPlot(i, j, false);
				}
			}
		}
		return field;
	}
	
	private static void markBarrenSection(LandPlot[][] field, BarrenSection barrenSection) {
		Point lowerLeft = barrenSection.getLowerLeft();
		Point upperRight = barrenSection.getUpperRight();
		
		for (int i = lowerLeft.getX(); i <= upperRight.getX(); i++)	{
			for (int j = lowerLeft.getY(); j <= upperRight.getY(); j++) {
				field[i][j] = new LandPlot(i, j, true);
			}
		}
	}
	
	public static BarrenSection[] parseInput(String[] args) {
		int n = args.length;
		BarrenSection[] barrenSections = new BarrenSection[n];
		for (int i = 0; i < n; i++) {
			int[] coordinates = parseIntArray(args[i].split(" "));
			if (coordinates.length != 4) {
				System.out.println("Incorrectly formatted input");
				System.exit(1);
			}
			barrenSections[i] = new BarrenSection(coordinates[0], coordinates[1], coordinates[2], coordinates[3]);
		}
		return barrenSections;
	}
	
	private static int[] parseIntArray(String[] array) {
		int[] intArray = null;
		try {
			intArray = Stream.of(array).mapToInt(Integer::parseInt).toArray();
		} catch (NumberFormatException e) {
			System.out.println("Incorrectly formatted input");
			System.exit(1);
		}
		return intArray;
	}
}
