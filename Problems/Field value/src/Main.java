import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * Get value for a given public field or null if the field does not exist or is not accessible.
 */
class FieldGetter {

    public Object getFieldValue(Object object, String fieldName) {
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (Modifier.isPublic(field.getModifiers()) && field.getName().equals(fieldName)) {
                try {
                    return field.get(object);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
        return null;
    }
}