import org.testng.Assert;
import org.testng.annotations.Test;

public class FirstTest {
    @Test
    public void testDistance() {
        Point p1 = new Point(10, 10);
        Assert.assertEquals(p1.distance(15, 15), 7.0710678118654755);
    }

    @Test
    public void testValuePoint() {
        Point p1 = new Point(10,10);
        Assert.assertEquals(p1.x1 = 10, 10);
        Assert.assertEquals(p1.y1 = 10, 10);
        Assert.assertEquals(p1.distance(15,15), p1.distance(15,15));
    }
}
