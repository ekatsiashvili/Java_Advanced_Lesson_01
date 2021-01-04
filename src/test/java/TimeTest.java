import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;

public class TimeTest {

    @Rule
    public TestWatcher testWatcher = new TestWatcher() {

        protected void failed(Throwable e, org.junit.runner.Description description) {
            System.out.println("FAILED --> " + description.getMethodName());
        };

        protected void succeeded(org.junit.runner.Description description) {
            System.out.println("SUCCEED --> " + description.getMethodName());
        };

    };

    @Test
    public void timeTest1() throws FailTimeException {
        new Time(15, 20);
    }

    @Test(expected = FailTimeException.class)
    public void timeTest2() throws FailTimeException {
        new Time(9, 65);
    }

    @Test(expected = FailTimeException.class)
    public void timeTest3() throws FailTimeException {
        new Time(29, 10);
    }

    @Test
    public void timeToMinutesTest() throws FailTimeException {
        Integer time = Time.timeToMinutes(new Time(14, 40));
        Assert.assertTrue(time == 880);
    }

    @Test
    public void sumTime1Test() throws FailTimeException {
        Time actualSum = Time.sumTime(new Time(9, 40), new Time(1, 40));
        Time expectedSum = new Time(11, 20);
        Assert.assertEquals(expectedSum, actualSum);
    }

    @Test
    public void sumTime2Test() throws FailTimeException {
        Time actualSum = Time.sumTime(new Time(14, 10), new Time(2, 02));
        Time expectedSum = new Time(16, 12);
        Assert.assertEquals(expectedSum, actualSum);
    }

    @Test
    public void compareTime1Test() throws FailTimeException {
        Assert.assertTrue(Time.compareTime(new Time(10, 45), new Time(8, 20)));
    }

    @Test
    public void compareTime2Test() throws FailTimeException {
        Assert.assertFalse(Time.compareTime(new Time(9,05), new Time(16,10)));
    }

    @Test
    public void checkTimeInterval1Test() throws FailTimeException {
        Assert.assertFalse(Time.checkTimeInterval(new Time(8,35), new Time(9,50), new Time(9,40), new Time(13,30)));
    }


    @Test
    public void checkTimeInterval2Test() throws FailTimeException {
        Assert.assertFalse(Time.checkTimeInterval(new Time(8,35), new Time(13,07), new Time(9,47), new Time(11,02)));
    }

    @Test
    public void checkTimeInterval3Test() throws FailTimeException {
        Assert.assertTrue(Time.checkTimeInterval(new Time(8,35), new Time(9,15), new Time(9,47), new Time(11,02)));
    }

}