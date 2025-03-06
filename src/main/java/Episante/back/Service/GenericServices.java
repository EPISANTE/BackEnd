package Episante.back.Service;

import java.lang.reflect.Field;
import java.util.Map;



public class GenericServices {
    public static <T> String genericUpdate(T target, Map<String, Object> updates) {
        for (Map.Entry<String, Object> entry : updates.entrySet()) {
            Field field;
            try {
                field = target.getClass().getDeclaredField(entry.getKey());
                field.setAccessible(true);
                field.set(target, entry.getValue());
            }
            catch (NoSuchFieldException e) {
                return "Field not found: " + entry.getKey();
            }
            catch (IllegalArgumentException e) {
                return "Invalid value: " + entry.getKey() + " for the field: " + entry.getKey();
            }
            catch (IllegalAccessException e) {
                return "You don't have access to the field: " + entry.getKey();
            }

        }

        return "Updated Successfully";

    }




}
