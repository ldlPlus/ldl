package plus.ldl.q4transfervalue;

/**
 * @author ldl.plus
 * @date 2020年05月15日  9:49
 */
public class TransferValueTest {


    public static void main(String[] args) {
        TransferValueTest test = new TransferValueTest();
        int age = 20;
        test.changeValue1(age);
        System.out.println("age = " + age);

        Person person = new Person("abc");
        test.changeValue2(person);
        System.out.println("person = " + person.getName());

        String str = "abc";
        test.changeValue3(str);
        System.out.println("str = " + str);
    }

    private void changeValue3(String str) {
        str = "xxx";
    }

    private void changeValue2(Person person) {
        person.setName("xxx");
    }

    private void changeValue1(int age) {
        age = 30;
    }
}
