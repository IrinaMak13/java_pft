package ru.stqa.pft.sandbox;

import org.testng.annotations.Test;

public class PointTests {

  @Test
  public void testArea(){
    Point p1 = new Point (1,3);
    Point p2 = new Point (1,9);
    assert Point.distance(p1, p2) == 6.0;
  }
  @Test
  public void testArea2(){
    Point p1 = new Point (4,2);
    Point p2 = new Point (11,2);
    assert Point.distance(p1, p2) == 7.0;
  }
  @Test
  public void testArea3(){
    Point p1 = new Point (-1,2);
    Point p2 = new Point (7,2);
    assert Point.distance(p1, p2) == 8.0;
  }
}


