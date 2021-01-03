package lk.ijse.Model;

abstract class vehicle{
    int i=0;
    void run() {

    }
    abstract void test();
}
public class test extends vehicle {
    int i=1;
    @Override
    void test() {

    }

    public static void main(String[] args) {
        vehicle v1=new test();
        test t1=new test();
        System.out.println(v1.i );
        System.out.println(t1.i );
    }
}
