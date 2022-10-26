package com.ucsal.cucoreminders.security;

public class ManagerEndpointsAuxiliary {
    public static final String[] WHITE_LIST = {
            "/oauth/token",
            "/cucoreminder/users/salvar",
            "/h2-console/**",
    };
}
