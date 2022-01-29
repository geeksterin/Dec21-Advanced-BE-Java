package in.geekster.springdatajpademo.utils;

public class SecurityContextUtil {

    private static final ThreadLocal<String> REQUESTING_CLIENT = new ThreadLocal<>();

    public static void setRequestingClient(final String requestingClient) {
        REQUESTING_CLIENT.set(requestingClient);
    }

    public static String getRequestingClient() {
        return REQUESTING_CLIENT.get();
    }

    public static void clear() {
        REQUESTING_CLIENT.remove();
    }
}
