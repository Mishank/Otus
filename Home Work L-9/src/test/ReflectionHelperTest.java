package test;
import ru.otus.main.ReflectionHelper;
import ru.otus.main.annotations.*;

import org.junit.Assert;


public class ReflectionHelperTest {
    @SuppressWarnings("ConstantConditions")
    @Test
    public void instantiate() {
        TestClass testClass = ReflectionHelper.instantiate(TestClass.class);
        Assert.assertEquals(0, testClass.getA());
        Assert.assertEquals("", testClass.getS());


    }

    @Test
    public void getFieldValue() {
        Assert.assertEquals("A", ReflectionHelper.getFieldValue(new TestClass(1, "A"), "s"));

    }

    @Before
    public void setFieldValue() {
        TestClass test = new TestClass(1, "A");
        Assert.assertEquals("A", test.getS());

    }

    @After
    public void callMethod() {
        Assert.assertEquals("A", ReflectionHelper.callMethod(new TestClass(1, "A"), "getS"));

        TestClass test = new TestClass(1, "A");
        ;
    }

}