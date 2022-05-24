package org.mps.authentication;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class IntegrationTestIT {

    @Test
    public void testLevel1(){
        UserRegistration userRegistration = new UserRegistration();

        Date mockDate = mock(Date.class);
        PasswordString mockPassword = mock(PasswordString.class);
        CredentialStore credentialStore = mock(CredentialStore.class);
        CredentialValidator credentialValidator = mock(CredentialValidator.class);

        userRegistration.register(mockDate, mockPassword,credentialStore,credentialValidator);
        when(credentialValidator.validate()).thenReturn(CredentialValidator.ValidationStatus.VALIDATION_OK);

        when(credentialStore.size()).thenReturn(1);

        assertEquals(1, credentialStore.size());
    }

    @Test
    public void testLevel2(){
        UserRegistration userRegistration = new UserRegistration();

        Date mockDate = mock(Date.class);
        PasswordString mockPassword = mock(PasswordString.class);
        CredentialStore credentialStore = mock(CredentialStore.class);

        CredentialValidator credentialValidator = new CredentialValidator(mockDate, mockPassword, credentialStore);
        userRegistration.register(mockDate, mockPassword, credentialStore, credentialValidator);

        when(credentialStore.size()).thenReturn(1);

        assertEquals(1, credentialStore.size());
    }

    @Test
    public void testLevel3_1(){
        UserRegistration userRegistration = new UserRegistration();

        Date date = new Date(10,3,2001);
        PasswordString mockPassword = mock(PasswordString.class);
        CredentialStore credentialStore = mock(CredentialStore.class);
        CredentialValidator credentialValidator = new CredentialValidator(date,mockPassword,credentialStore);

        when(mockPassword.validate()).thenReturn(true);
        when(credentialStore.credentialExists(date,mockPassword)).thenReturn(false);

        userRegistration.register(date, mockPassword,credentialStore,credentialValidator);

        when(credentialStore.size()).thenReturn(1);

        assertEquals(1, credentialStore.size());
    }

    @Test
    public void testLevel3_2(){
        UserRegistration userRegistration = new UserRegistration();

        Date mockDate = mock(Date.class);
        PasswordString passwordString = new PasswordString("password,123");
        CredentialStore credentialStore = mock(CredentialStore.class);

        CredentialValidator credentialValidator = new CredentialValidator(mockDate, passwordString, credentialStore);
        userRegistration.register(mockDate, passwordString, credentialStore, credentialValidator);

        when(credentialStore.size()).thenReturn(1);

        assertEquals(1, credentialStore.size());
    }

    @Test
    public void testLevel3_3(){
        UserRegistration userRegistration = new UserRegistration();

        Date mockDate = mock(Date.class);
        PasswordString mockPassword = mock(PasswordString.class);
        CredentialStore credentialStore = new CredentialStoreSet();

        when(mockPassword.validate()).thenReturn(true);
        when(mockDate.validate()).thenReturn(true);

        CredentialValidator credentialValidator = new CredentialValidator(mockDate, mockPassword, credentialStore);
        userRegistration.register(mockDate, mockPassword, credentialStore, credentialValidator);

        assertEquals(1, credentialStore.size());
    }

    @Test
    public void testLevel4_1(){
        UserRegistration userRegistration = new UserRegistration();

        Date date = new Date(10,3,2001);
        PasswordString password = new PasswordString("password,1234");
        CredentialStore credentialStore = mock(CredentialStore.class);
        CredentialValidator credentialValidator = new CredentialValidator(date,password,credentialStore);
        when(credentialStore.credentialExists(date,password)).thenReturn(false);
        userRegistration.register(date, password,credentialStore,credentialValidator);

        when(credentialStore.size()).thenReturn(1);

        assertEquals(1, credentialStore.size());
    }

    @Test
    public void testLevel4_2(){
        UserRegistration userRegistration = new UserRegistration();

        Date date = new Date(1,1,2001);
        PasswordString mockPassword = mock(PasswordString.class);
        CredentialStore credentialStore = new CredentialStoreSet();

        when(mockPassword.validate()).thenReturn(true);

        CredentialValidator credentialValidator = new CredentialValidator(date, mockPassword, credentialStore);
        userRegistration.register(date, mockPassword, credentialStore, credentialValidator);

        assertEquals(1, credentialStore.size());
    }

    @Test
    public void testLevel4_3(){
        UserRegistration userRegistration = new UserRegistration();

        Date mockDate = mock(Date.class);
        PasswordString password = new PasswordString("password1234,");
        CredentialStore credentialStore = new CredentialStoreSet();

        when(mockDate.validate()).thenReturn(true);

        CredentialValidator credentialValidator = new CredentialValidator(mockDate, password, credentialStore);
        userRegistration.register(mockDate, password, credentialStore, credentialValidator);

        assertEquals(1, credentialStore.size());
    }

    @Test
    public void testLevel5(){
        UserRegistration userRegistration = new UserRegistration();

        Date date = new Date(10,3,2001);
        PasswordString password = new PasswordString("password,1234");
        CredentialStore credentialStore = new CredentialStoreSet();
        CredentialValidator credentialValidator = new CredentialValidator(date,password,credentialStore);

        userRegistration.register(date, password,credentialStore,credentialValidator);

        assertEquals(1, credentialStore.size());
    }
}
