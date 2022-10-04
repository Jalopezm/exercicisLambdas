package ExerciciLambdas;

import org.junit.experimental.theories.Theories;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Random;
import java.util.function.*;


public class Exercicis {

    /**
     * Torna un supplier que sempre torna "Hello"
     */
    public static Supplier<String> helloSupplier() {
        return new Supplier<String>() {
            @Override
            public String get() {
                return "Hello";
            }
        };
//        return () -> "Hello";
    }

    /**
     * Torna un Predicate que mira si l'string és buit
     */
    public static Predicate<String> isEmptyPredicate() {
        return new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.length() == 0;
            }
        };
//        return (s) -> s.length()==0;
    }

    /**
     * Torna un Function que accepta un String que retorna aquest string repetit
     * n vegades, on n es passa com a argument de la funció.
     */
    public static BiFunction<String, Integer, String> stringMultiplier() {
//        return new BiFunction<String, Integer, String>() {
//            @Override
//            public String apply(String s, Integer integer) {
//                String result = s;
//                for (int i = 1; i < integer; i++) {
//                  result+=s;
//                }
//                return result;
//            }
//        };
        return (s, integer) -> s += s.repeat(integer - 1);
    }

    /**
     * Torna un Function que converteix un BigDecimal a String que comença pel símbol "$"
     */
    public static Function<BigDecimal, String> toDollarStringFunction() {
        return new Function<BigDecimal, String>() {
            @Override
            public String apply(BigDecimal bigDecimal) {
                return "$" + bigDecimal;
            }
        };
//        return (bigDecimal) -> "$"+bigDecimal;
    }

    /**
     * Es reben dos paràmetres que representen un rang (min,max) i retorna
     * un Predicate<String> que verifica si la longitud de l'string està
     * dins aquest rang.
     */
    public static Predicate<String> lengthInRangePredicate(int min, int max) {
        return new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.length() >= min && s.length() <= max;
            }
        };
//        return (s) -> s.length() >= min && s.length()<=max;
    }

    /**
     * Retorna un Supplier de números enters aleatoris
     */
    public static IntSupplier randomIntSupplier() {
        return new IntSupplier() {
            @Override
            public int getAsInt() {
                return (int) (Math.random() * 100);
            }
        };
//        return () -> (int) (Math.random()*100);
    }


    /**
     * Retorna un IntUnaryOperator que reb un int com un límit que a la vegada
     * retorna un número aleatori dins aquest límit
     */
    public static IntUnaryOperator boundedRandomIntSupplier() {
        return new IntUnaryOperator() {
            @Override
            public int applyAsInt(int i) {
                return new Random().nextInt(0, i);
            }
        };
//        return (i) -> new Random().nextInt(0,i);
    }

    /**
     * Retorna un IntUnaryOperator que calcula un quadrat d'un número
     */
    public static IntUnaryOperator intSquareOperation() {
        return new IntUnaryOperator() {
            @Override
            public int applyAsInt(int i) {
                return i * i;
            }
        };
//        return (i) -> i*i;
    }

    /**
     * Retorna un LongBinaryOperator que realitza l'operació de suma
     */
    public static LongBinaryOperator longSumOperation() {
        return new LongBinaryOperator() {
            @Override
            public long applyAsLong(long l, long l1) {
                return l + l1;
            }
        };
//        return (l,l1) -> l+ l1;
    }

    /**
     * Retorna un ToIntFunction<String> que converteix un String a un Integer
     */
    public static ToIntFunction<String> stringToIntConverter() {
        return new ToIntFunction<String>() {
            @Override
            public int applyAsInt(String s) {
                return Integer.parseInt(s);
            }
        };
//        return (s) -> Integer.parseInt(s);
    }

    /**
     * Reb un paràmetre n (int), i retorna un Supplier que proporciona un IntUnaryOperator
     * que realitza la funció f(x) = n * x
     */
    public static Supplier<IntUnaryOperator> nMultiplyFunctionSupplier(int n) {
        return new Supplier<IntUnaryOperator>() {
            @Override
            public IntUnaryOperator get() {
                return i -> n * i;
            }
        };

    }

    /**
     * Retorna una funció que composa funcions amb la funció trim() de String
     */
    public static UnaryOperator<Function<String, String>> composeWithTrimFunction() {
        return new UnaryOperator<Function<String, String>>() {
            @Override
            public Function<String, String> apply(Function<String, String> stringStringFunction) {
                return stringStringFunction.compose(s -> s.trim());
            }
        };
//        return (f) -> f.compose(s -> s.trim());
    }

    /**
     * Reb un Runnable com a paràmetre i retorna un Supplier<Thread>
     * Aquest thread s'iniciarà quan es cridi al mètode "get()" del supplier.
     */
    public static Supplier<Thread> runningThreadSupplier(Runnable runnable) {
        return new Supplier<Thread>() {
            @Override
            public Thread get() {
                Thread t = new Thread(runnable);
                t.start();
                return t;
            }
        };
//        return () -> {
//            Thread t = new Thread(runnable);
//            t.start();
//            return t;
//        };
    }

    /**
     * Retorna un Consumer que accepta un Runnable com a paràmetre i l'executa
     * dins un nou fil (thread)
     */
    public static Consumer<Runnable> newThreadRunnableConsumer() {
        return new Consumer<Runnable>() {
            @Override
            public void accept(Runnable runnable) {
                Thread t = new Thread(runnable);
                t.start();
            }
        };
//      return (runnable)-> new Thread(runnable).start();
    }

    /**
     * Retorna una Function que accepta una instància d'un Runnable i retorna
     * un Supplier d'un Thread que s'ha creat per aquest Runnable.
     */
    public static Function<Runnable, Supplier<Thread>> runnableToThreadSupplierFunction() {
//        return new Function<Runnable, Supplier<Thread>>() {
//            @Override
//            public Supplier<Thread> apply(Runnable runnable) {
//                Thread t = new Thread(runnable);
//                t.start();
//                return new Supplier<Thread>() {
//                    @Override
//                    public Thread get() {
//                        return t;
//                    }
//                };
//            }
//        };
        return (runneable) -> () -> {
            Thread t = new Thread(runneable);
            t.start();
            return t;
        };
    }

    /**
     * Retorna una BiFunction que té 2 paràmetres.
     * <p>
     * El primer serà un IntUnaryOperator, que és una funció "integer".
     * <p>
     * El segon serà un IntPredicate, que és una condició sobre un integer.
     * <p>
     * Aquesta BiFunction tornarà una funció composada, que farà el següent:
     * <p>
     * - Si el IntPredicate verifica la seva condició, aplicarà la funció
     * - Si el IntPredicate no es compleix, retorna el mateix element que s'ha rebut
     */
    public static BiFunction<IntUnaryOperator, IntPredicate, IntUnaryOperator> functionToConditionalFunction() {
        return new BiFunction<IntUnaryOperator, IntPredicate, IntUnaryOperator>() {
            @Override
            public IntUnaryOperator apply(IntUnaryOperator intUnaryOperator, IntPredicate intPredicate) {
                return new IntUnaryOperator() {
                    @Override
                    public int applyAsInt(int i) {
                        if (intPredicate.test(i)) {
                            return intUnaryOperator.applyAsInt(i);
                        }
                        return i;
                    }
                };
            }
        };

//        return (imp,ip) -> (i) -> {
//          if (ip.test(i)){
//              return imp.applyAsInt(i);
//          }
//          return i;
//        };

    }

    /**
     * Torna una BiFunction, on el primer paràmetre és un un Map (la clau és una nom d'una funció
     * i el valor és una funció tipus IntUnaryOperator), el segon paràmetre és un String, que és
     * també el nom d'una funció.
     * <p>
     * Si el Map conté una funció amb el nom del segon paràmetre, aleshores es torna la funció
     * emmagatzemada al map. Si no hi és, tornem la funció "identity()", que no és més que una
     * funció que retorna el mateix paràmetre que li passem.
     */
    public static BiFunction<Map<String, IntUnaryOperator>, String, IntUnaryOperator> functionLoader() {
        return new BiFunction<Map<String, IntUnaryOperator>, String, IntUnaryOperator>() {
            @Override
            public IntUnaryOperator apply(Map<String, IntUnaryOperator> stringIntUnaryOperatorMap, String s) {
                if (stringIntUnaryOperatorMap.containsKey(s)) {
                    return stringIntUnaryOperatorMap.get(s);
                }
                return IntUnaryOperator.identity();
            }
        };
//        return (m, s) -> {
//            if (m.containsKey(s)) {
//                return m.get(s);
//            }
//            return (i) -> i;
//        };
    }

    /**
     * Retorna un Supplier d'un Supplier d'un Supplier de l'string "BEN FET!"
     */
    public static Supplier<Supplier<Supplier<String>>> trickyWellDoneSupplier() {
//        return new Supplier<Supplier<Supplier<String>>>() {
//            @Override
//            public Supplier<Supplier<String>> get() {
//                return new Supplier<Supplier<String>>() {
//                    @Override
//                    public Supplier<String> get() {
//                        return null;
//                    }
//                };
//            }
        return () -> () -> () -> "BEN FET!";
    }

}
