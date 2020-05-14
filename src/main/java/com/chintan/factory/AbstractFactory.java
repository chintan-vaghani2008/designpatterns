package com.chintan.factory;


import org.reflections.Reflections;
import sun.reflect.Reflection;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Factory of factories.
 */
public class AbstractFactory {

    public static void main(String[] args) {
        IHotBeverage iHotBeverage1 =
                BeverageMachine.prepareBeverage(BeverageMachine.BeverageType.TEA);
        iHotBeverage1.consume();
        IHotBeverage iHotBeverage2 =
                BeverageMachine.prepareBeverage(BeverageMachine.BeverageType.COFFEE);
        iHotBeverage2.consume();
    }

    interface IHotBeverage {
        public void consume();
    }

    static class Tea implements IHotBeverage {
        public void consume() {
            System.out.println("Consuming Tea !!!");
        }
    }

    static class Coffee implements IHotBeverage {
        public void consume() {
            System.out.println("Consuming Coffee !!!");
        }
    }

    interface IBeverageFactory {
        public IHotBeverage prepare();
    }

    static class TeaFactory implements IBeverageFactory {
        public Tea prepare() {
            System.out.println("Preparing tea from Tea factory");
            return new Tea();
        }
    }

    static class CoffeeFactory implements IBeverageFactory {
        public Coffee prepare() {
            System.out.println("Preparing Coffee from Coffee factory");
            return new Coffee();
        }
    }

    /**
     * Abstract factory class
     */
    static class BeverageMachine {
        private static Map<String, IBeverageFactory> factoryInstances = new HashMap<>();
        static {
            Set<Class<? extends IBeverageFactory>> subTypes = new Reflections("com.chintan.factory")
                    .getSubTypesOf(IBeverageFactory.class);
            for(Class<? extends IBeverageFactory> subType : subTypes) {
                String typeStr = subType.getSimpleName().replace("Factory", "").toUpperCase();
                try {
                    factoryInstances.put(typeStr, subType.getDeclaredConstructor().newInstance());
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }
        }

        enum BeverageType {
            TEA,
            COFFEE
        }

        public static IHotBeverage prepareBeverage(BeverageType beverageType) {
            IBeverageFactory factory = factoryInstances.get(beverageType.name());
            return factory.prepare();
        }
    }
}
