package pl.decerto.rekrutacja.bpawlowski;

class ValueWrapper<T> {
    T value;

    public ValueWrapper(T value) {
        this.value = value;
    }

    public ValueWrapper() {
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
