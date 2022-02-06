package in.geekster.springsecuritydemo.utils;

public class LoggedInContext {

    private static final ThreadLocal<Long> CURRENT_USER = new ThreadLocal<>();

    public static void setCurrentUser(final Long currentUser) {
        CURRENT_USER.set(currentUser);
    }

    public static Long getCurrentUser() {
        return CURRENT_USER.get();
    }

    public static void clear() {
        CURRENT_USER.remove();
    }
}
