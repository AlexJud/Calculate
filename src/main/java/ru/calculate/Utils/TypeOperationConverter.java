package ru.calculate.Utils;

import ru.calculate.model.TypeOperation;

import java.beans.PropertyEditorSupport;

public class TypeOperationConverter extends PropertyEditorSupport {
    public void setAsText(final String text) throws IllegalArgumentException {
        setValue(TypeOperation.fromValue(text));
    }
}
