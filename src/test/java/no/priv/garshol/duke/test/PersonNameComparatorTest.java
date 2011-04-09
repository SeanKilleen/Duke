
package no.priv.garshol.duke.test;

import org.junit.Test;
import org.junit.Before;
import static junit.framework.Assert.assertEquals;

import no.priv.garshol.duke.PersonNameComparator;

public class PersonNameComparatorTest {
  private PersonNameComparator comp;

  @Before
  public void setUp() {
    comp = new PersonNameComparator();
  }
  
  @Test
  public void testEmpty() {
    assertEquals(1.0, comp.compare("", ""));
  }

  @Test
  public void testEqual() {
    assertEquals(1.0, comp.compare("lars marius garshol", "lars marius garshol"));
  }

  @Test
  public void testNotAtAllEqual() {
    assertEquals(0.0, comp.compare("abcde fghij", "lars marius"));
    assertEquals(0.0, comp.compare("lars marius", "abcde fghij"));
  }

  @Test
  public void testInitial() {
    assertEquals(0.9, comp.compare("lars marius garshol", "lars m. garshol"));
    assertEquals(0.9, comp.compare("lars m. garshol", "lars marius garshol"));
  }

  @Test
  public void testInitialWithoutPeriod() {
    assertEquals(0.9, comp.compare("lars marius garshol", "lars m garshol"));
    assertEquals(0.9, comp.compare("lars m garshol", "lars marius garshol"));
  }
  
  @Test
  public void testMissingMiddleName() {
    assertEquals(0.8, comp.compare("lars marius garshol", "lars garshol"));
    assertEquals(0.8, comp.compare("lars garshol", "lars marius garshol"));
  }
  
  @Test
  public void testEditDistance() {
    assertEquals(0.9, comp.compare("lars marius garshol", "lars marus garshol"));
    assertEquals(0.9, comp.compare("lars marus garshol", "lars marius garshol"));
  }

  @Test
  public void testSingleWordDiff() {
    assertEquals(0.0, comp.compare("abcde", "lars"));
  }

  @Test
  public void testReversedOrder() {
    assertEquals(0.9, comp.compare("zhu bin", "bin zhu"));
  }
  
}