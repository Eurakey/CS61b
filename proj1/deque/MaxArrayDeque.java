package deque;

import java.util.Comparator;


public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private final Comparator<T> comparator;

    public MaxArrayDeque(Comparator<T> c) {
        comparator = c;
    }

    public T max() {
        return max(comparator);
    }

    public T max(Comparator<T> c) {
        if (size() == 0) return null;
        int maxIndex = 0;
        for (int i = 0; i < size(); i++) {
            T currentItem = get(i);
            if (c.compare(get(maxIndex), currentItem) < 0) {
                maxIndex = i;
            }
        }
        return get(maxIndex);
    }

}
