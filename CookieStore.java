package gizbo.com.gizbo.Utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/**
 * Created by me on 3/8/2017.
 */

public class CookieStore implements CookieJar {

    private final Set<Cookie> cookieStore = new HashSet<>();

    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        cookieStore.addAll(cookies);
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {

        List<Cookie> validCookies = new ArrayList<>();
        for (Cookie cookie : cookieStore) {
            LogCookie(cookie);
            if (cookie.expiresAt() < System.currentTimeMillis()) {

            } else {
                validCookies.add(cookie);
            }
        }
        return validCookies;
    }


    private void LogCookie(Cookie cookie) {
        System.out.println("String: " + cookie.toString());
        System.out.println("Expires: " + cookie.expiresAt());
        System.out.println("Hash: " + cookie.hashCode());
        System.out.println("Path: " + cookie.path());
        System.out.println("Domain: " + cookie.domain());
        System.out.println("Name: " + cookie.name());
        System.out.println("Value: " + cookie.value());
    }
}

