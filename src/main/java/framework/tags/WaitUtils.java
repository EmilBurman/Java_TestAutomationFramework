package framework.tags;

import java.time.Instant;

public class WaitUtils {

    private interface CheckAny<T>{
        Boolean check(T toCheck);
    }

    private interface GetAny<T>{
        T get();
    }

    public static <T> T waitUntilReady(GetAny<T> getValueFunction, CheckAny<T> checkIfReadyFunction, long maxWait, long pollInterval) throws InterruptedException {
        Instant start = Instant.now();
        T result;
        do {
            result = getValueFunction.get();
            if (checkIfReadyFunction.check(result))
                return result;
            Thread.sleep(pollInterval);
        } while(start.plusMillis(maxWait).compareTo(Instant.now())> 0);
        throw new InterruptedException("Max wait time for action have been reached.");
    }

    public void sleep(int millisec){
        try {
            Thread.sleep(millisec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
