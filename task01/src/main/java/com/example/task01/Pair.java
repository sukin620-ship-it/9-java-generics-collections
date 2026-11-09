package com.example.task01;

import java.util.Objects;
import java.util.function.BiConsumer;

public class Pair<T, U> {
    private final T a;
    private final U b;

    private Pair(T a, U b) {
        this.a = a;
        this.b = b;
    }
    public static <T, U> Pair<T, U> of(T a, U b) {return new Pair<>(a, b);}
    public T getFirst() {return a;}
    public U getSecond() {return b;}

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {return true;}
        if (!(obj instanceof Pair<?, ?>)) {return false;}

        Pair<?, ?> other = (Pair<?, ?>) obj;
        return Objects.equals(a, other.a)
                && Objects.equals(b, other.b);
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b);
    }

    public void ifPresent(BiConsumer<? super T, ? super U> action) {
        if (a != null && b != null) {action.accept(a, b);}
    }
}
