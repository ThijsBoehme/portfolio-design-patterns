package creational_patterns.singleton;

import java.util.function.Supplier;

class SingletonTester {
    public static boolean isSingleton(Supplier<Object> func) {
        Object instance1 = func.get();
        Object instance2 = func.get();

        return instance1 == instance2;
    }
}
