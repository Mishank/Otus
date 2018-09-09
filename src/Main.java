public class Main {
    public static void main(String[] args) {
        A aB = new B();
        A aC = new C();
        B bB = new B();
        B bC = new C();
        C cC = new C();

        System.out.println(aC.diff(1,2));
    }
}
