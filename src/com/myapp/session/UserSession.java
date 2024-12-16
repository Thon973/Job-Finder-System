
package com.myapp.session;

public class UserSession {
    private static String email;

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String newEmail) {
        email = newEmail;
    }

    public static void refreshSession(String userEmail) {
        setEmail(userEmail);
    }
}
