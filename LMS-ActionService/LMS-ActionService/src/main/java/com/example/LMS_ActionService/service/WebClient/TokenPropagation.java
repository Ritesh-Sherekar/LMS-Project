package com.example.LMS_ActionService.service.WebClient;

public class TokenPropagation {

    private static final ThreadLocal<String>threadLocal=new ThreadLocal<>();

    public static void setToken(String token)
    {
        threadLocal.set(token);
    }

    public static String getToken()
    {
        return threadLocal.get();
    }
}
