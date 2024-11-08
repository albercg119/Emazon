package com.emzon.user.configuration.utilities.constants;

import org.junit.jupiter.api.Test;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.*;

class WebSecurityConfigConstantsTest {

    @Test
    void constants_ShouldHaveCorrectValues() {
        assertEquals("/users/create", WebSecurityConfigConstants.USER_CREATE_ENDPOINT);
        assertEquals("httpBasic", WebSecurityConfigConstants.AUTH_TYPE);
    }

    @Test
    void constructor_ShouldBePrivate() throws Exception {
        Constructor<WebSecurityConfigConstants> constructor = WebSecurityConfigConstants.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));

        constructor.setAccessible(true);
        constructor.newInstance();
    }

    @Test
    void class_ShouldBeFinal() {
        assertTrue(Modifier.isFinal(WebSecurityConfigConstants.class.getModifiers()));
    }
}