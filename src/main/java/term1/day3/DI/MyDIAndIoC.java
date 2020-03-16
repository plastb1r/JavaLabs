package term1.day3.DI;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import term1.day1.Person;

public class MyDIAndIoC {

    private static HashMap<String, Object> context = new HashMap<>();

    public static HashMap<String, Object> getContext() {
        return context;
    }

    public MyDIAndIoC() {
        loadBeans();
    }

    public static void ClearContext() {
        MyDIAndIoC.context.clear();
    }

    public <T> T inject(T object) throws InjectException {
        try {
            Field[] fields = object.getClass().getDeclaredFields();

            for (Field field : fields) {
                if (field.isAnnotationPresent(Inject.class)) {

                    if (context.containsKey(field.getType().getName())) {
                        field.setAccessible(true);
                        Object injObj = context.get(field.getType().getName());
                        field.set(object, injObj);
                    }
                }
            }

            Person p = null;
            p.getFirstName();
        } catch (Exception e) {
            throw new InjectException("An error was made, during injection in " + object.toString(), e);
        }
        return object;
    }

    private void loadBeans() {
        String filePath = "src\\main\\resources\\properties";

        File file = new File(filePath);
        Scanner scanner;
        try {
            scanner = new Scanner(file);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        scanner.useDelimiter(" = ");
        while (scanner.hasNext()) {
            String fieldName = scanner.next();
            String className = scanner.next();
            try {
                context.put(fieldName, Class.forName(className).newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        scanner.close();
    }
}
