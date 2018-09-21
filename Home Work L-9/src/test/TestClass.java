package test;

@SuppressWarnings("unused")
public class TestClass {
    private static final int DEFAULT_A  = 0;
    private static final String DEFAULT_S = "";

    private int a;
    private String s;

    public TestClass() {
        this(DEFAULT_A, DEFAULT_S);
    }

    public TestClass(Integer a) { // use in ReflectionHelperTest
        this(a, DEFAULT_S);
    }

    TestClass(Integer a, String s) { // use in ReflectionHelperTest
        this.a = a;
        this.s = s;
    }

    int getA() { // use in ReflectionHelperTest
        return a;
    }

    String getS() { // use in ReflectionHelperTest
        return s;
    }

    private void setDefault(){ // use in ReflectionHelperTest
        a = DEFAULT_A;
        s = DEFAULT_S;
    }
}