package at.htlsaalfelden;

import java.util.List;

public interface IConverter<T> {
    String toString(List<T> object);
    List<T> fromString(String string);
}
