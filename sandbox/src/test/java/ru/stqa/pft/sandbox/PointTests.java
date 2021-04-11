package ru.stqa.pft.sandbox;

import org.testng.annotations.Test;

public class PointTests {

  @Test
  public void testArea(){
    Point p1 = new Point (6,7);
    Point p2 = new Point (8,9);
   assert p1.distance(p2) == 2.8284271247461903;
  }
  @Test
  public void testArea2(){
    Point p1 = new Point (10,15);
    Point p2 = new Point (11,12);
    assert p1.distance(p2) == 3.1622776601683795;
  }
  @Test
  public void testArea3(){
    Point p1 = new Point (18,20);
    Point p2 = new Point (3,4);
    assert p1.distance(p2) == 21.93171219946131;
  }
}


