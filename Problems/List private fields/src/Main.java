import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.stream.Collectors;

/*class PrivateObject {

    private String privateString = null;

    public PrivateObject(String privateString) {
        this.privateString = privateString;
    }

    private String getPrivateString(){
        return this.privateString;
    }
}

class Main {
    public static void main(String[] args) throws Exception {
        PrivateObject privateObject = new PrivateObject("str");
        FieldGetter fieldGetter = new FieldGetter();
        List<String> list = fieldGetter.getPrivateFields(privateObject);
        System.out.println(list);
    }
}*/

class FieldGetter {

    public List<String> getPrivateFields(Object object) {
        Field[] fields = object.getClass().getDeclaredFields();
        List<String> list = new ArrayList<>();
        for (Field field : fields) {
            if (Modifier.isPrivate(field.getModifiers())) {
                list.add(field.getName());
            }
        }
        Collections.sort(list);
        return list;
    }
}