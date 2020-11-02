package sample.utils;

public class ValidationUtils {
    public boolean isEmptyOrNull(String textFieldValue) {
        return textFieldValue != null && !textFieldValue.isEmpty();
    }

}
