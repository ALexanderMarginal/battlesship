import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;

/**
 Get list of public fields the object declares (inherited fields should be skipped).
 */

/*class Main {
    public static void main(String[] args) {
        TestClass testClass = new TestClass();
        FieldGetter fieldGetter = new FieldGetter();
        String[] fields = fieldGetter.getPublicFields(testClass);
        System.out.println(Arrays.toString(fields));
    }
}

class TestSuperClass {
    private String privateSuperField = "";
    public String publicSuperField = "";
}

class TestClass extends TestSuperClass {
    private String privateField = "";
    public String publicField = "";
}*/

class FieldGetter {

    public String[] getPublicFields(Object object) {
        ArrayList<String> publicFields = new ArrayList<>();
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field: fields){
            if ((Modifier.isPublic(field.getModifiers()))){
                publicFields.add(field.getName());
            }
        }
        String[] strings = new String[publicFields.size()];
        for (int i = 0; i < publicFields.size(); i++) {
            strings[i] = publicFields.get(i);
        }
        return strings;
    }
}