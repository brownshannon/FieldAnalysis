package fieldanalysis;

import static fieldanalysis.FieldAnalysis.FIELD_LENGTH;
import static fieldanalysis.FieldAnalysis.FIELD_WIDTH;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

import fieldanalysis.FieldAnalysis;
import fieldanalysis.pojo.BarrenSection;
import fieldanalysis.pojo.LandPlot;

public class FieldAnalysisTest {
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	
	@Before
	public void setUpStreams() {
	    System.setOut(new PrintStream(outContent));
	}
	
	@Test
	public void testMain_1() {
		String[] args = new String[4];
		args[0] = "48 192 351 207";
		args[1] = "48 392 351 407";
		args[2] = "120 52 135 547";
		args[3] = "260 52 275 547";
		
		FieldAnalysis.main(args);
		assertThat(outContent.toString().trim(), is(equalTo("[22816, 192608]")));
	}
	
	@Test
	public void testMain_2() {
		String[] args = new String[1];
		args[0] = "0 292 399 307";
		
		FieldAnalysis.main(args);
		assertThat(outContent.toString().trim(), is(equalTo("[116800, 116800]")));
	}
	
	@Test
	public void testParseInput() {
		String[] args = new String[1];
		args[0] = "0 292 399 307";
		
		BarrenSection[] barrenSections = FieldAnalysis.parseInput(args);
		assertThat(barrenSections.length, is(equalTo(1)));
		assertThat(barrenSections[0], is(equalTo(new BarrenSection(0, 292, 399, 307))));
	}
	
	@Test
	public void testInitializeField() {
		BarrenSection[] barrenSections = new BarrenSection[1];
		barrenSections[0] = new BarrenSection(0, 292, 399, 307);
		
		LandPlot[][] field = FieldAnalysis.initializeField(barrenSections);
		assertThat(field.length, is(equalTo(FIELD_WIDTH)));
		assertThat(field[0].length, is(equalTo(FIELD_LENGTH)));
		
		// check corners of barren section marked as searched
		assertTrue(field[0][292].isSearched());
		assertTrue(field[399][307].isSearched());
		
		// check random plots in field within barren section marked as searched
		assertTrue(field[215][300].isSearched());
		assertTrue(field[300][292].isSearched());
	}
}
