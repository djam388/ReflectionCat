import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) throws Exception {
        Class<?> catClass = Cat.class;
        Cat myCat = (Cat) catClass.getDeclaredConstructor().newInstance();

        Field[] fields = catClass.getDeclaredFields();

        for (Field field : fields) {
            if (!field.canAccess(myCat)) {
                field.setAccessible(true);
                if (field.getName().equals("name")) {
                    field.set(myCat, "Lolo");
                } else if (field.getName().equals("age")) {
                    field.set(myCat, 2);
                }
            }
            if (java.lang.reflect.Modifier.isPrivate(field.getModifiers())) {
                System.out.println(field.getName() + " - " + field.getType().getSimpleName());
            }
        }

        Field nameField = catClass.getDeclaredField("name");
        nameField.setAccessible(true);
        Field ageField = catClass.getDeclaredField("age");
        ageField.setAccessible(true);
        System.out.println("Cat name: " + nameField.get(myCat) + ", cat age: " + ageField.get(myCat));
    }
}

class Cat {
    private String name;
    private int age;
}