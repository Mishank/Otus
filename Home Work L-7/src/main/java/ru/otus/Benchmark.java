package ru.otus;

class Benchmark implements BenchmarkMBean {
    private volatile int size = 0;

    @SuppressWarnings("InfiniteLoopStatement")
    void run() {
        int local = size;
        Object[] array = new Object[local];

        System.out.println("Starting the loop");


         while (true) { // в цикле создадим обьекты массива и заполним пустыми строками


            System.out.println("Array of size: " + array.length + " created");
            for (int i = 0; i < local; i++) {
                array[i] = new String(new char[0]);
            }
            System.out.println("Created " + local + " objects.");
        }
    }

    @Override
    public int getSize() { // подключение к удаленному MBeanserver
        return size; // вернем размер массива
    }

    @Override
    public void setSize(int size) {// так же можно поменять size
        this.size = size;
    }

}