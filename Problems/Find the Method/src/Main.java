import java.lang.reflect.Method;

class MethodFinder {

    public static String findMethod(String methodName, String[] classNames) throws ClassNotFoundException {
        for (String className :
                classNames) {
            Class cl = Class.forName(className);
            Method[] methods = cl.getMethods();
            for (Method method : methods) {
                if (method.getName().equalsIgnoreCase(methodName)) {
                    return cl.getName();
                }
            }
        }
        return null;
    }
}