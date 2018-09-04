public class polymorphism {
    public static void main(String[] args) {
        Object[] array = new Object[3];
        array[0] = new Object();
        array[1] = "Hello!";
        array[2] = 1;

        for (int i = 0; i < array.length; i++){
            array[i].toString(); //Будет вызван метод наследника если метод переопределен
        }
    }
}