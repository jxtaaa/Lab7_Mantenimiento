package org.mps.authentication;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class IntegrationTestIT {

    @Test
    public void testUserRegistrationRegisterWithValidArgumentsIncreasesCredentialStoreSize(){
        Date birthDate = new Date(1,1,2001);
        var passwordString = new PasswordString("password,123");
        var credentialStore = new CredentialStoreSet();
        var userRegistration = new UserRegistration();
        userRegistration.register(birthDate, passwordString, credentialStore);
        assertEquals(credentialStore.size(), 1);
    }
    @Test
    public void testUserRegistrationRegisterWithInvalidBirthDateAndNotRepeatedNotIncreasesCredentialStoreSize(){
        Date birthDate = new Date(31,2,2001);
        var passwordString = new PasswordString("password,123");
        var credentialStore = new CredentialStoreSet();
        var userRegistration = new UserRegistration();
        userRegistration.register(birthDate, passwordString, credentialStore);
        assertEquals(credentialStore.size(), 0);
    }
    @Test
    public void testUserRegistrationRegisterWithInvalidPasswordAndNotRepeatedNotIncreasesCredentialStoreSize(){
        Date birthDate = new Date(1,1,2001);
        var passwordString = new PasswordString("password123");
        var credentialStore = new CredentialStoreSet();
        UserRegistration userRegistration = new UserRegistration();
        userRegistration.register(birthDate,passwordString,credentialStore);
        assertEquals(0, credentialStore.size());
    }
    @Test
    public void testUserRegistrationRegisterWithValidArgumentsAndRepeatedNotIncreasesCredentialStoreSize(){
        Date birthDate = new Date(1,1,2001);
        var passwordString = new PasswordString("password,123");
        var credentialStore = new CredentialStoreSet();
        UserRegistration userRegistration = new UserRegistration();
        userRegistration.register(birthDate,passwordString,credentialStore);
        userRegistration.register(birthDate,passwordString,credentialStore);
        assertEquals(1, credentialStore.size());
    }
    @Test
    public void testCredentialStoreWithValidArgumentsAndNotRepeatedReturnsVALIDATION_OK(){
        Date birthDate = new Date(1,1,2001);
        var passwordString = new PasswordString("password,123");
        var credentialStore = new CredentialStoreSet();
        var credentialStoreValidator = new CredentialValidator(birthDate, passwordString, credentialStore);
        assertEquals(credentialStoreValidator.validate(), CredentialValidator.ValidationStatus.VALIDATION_OK);
    }

    @Test
    public void testCredentialStoreWithInvalidBirthDateAndNotRepeatedReturnsBIRTHDAY_INVALID() {
        Date birthDate = new Date(31, 2, 2001);
        var passwordString = new PasswordString("password,123");
        var credentialStore = new CredentialStoreSet();
        var credentialStoreValidator = new CredentialValidator(birthDate, passwordString, credentialStore);
        assertEquals(credentialStoreValidator.validate(), CredentialValidator.ValidationStatus.BIRTHDAY_INVALID);
    }

    @Test
    public void testCredentialStoreWithInvalidBirthDateAndNotRepeatedReturnsPASSWORD_INVALID() {
        Date birthDate = new Date(15, 2, 2001);
        var passwordString = new PasswordString("password123");
        var credentialStore = new CredentialStoreSet();
        var credentialStoreValidator = new CredentialValidator(birthDate, passwordString, credentialStore);
        assertEquals(credentialStoreValidator.validate(), CredentialValidator.ValidationStatus.PASSWORD_INVALID);
    }

    @Test
    public void testCredentialStoreWithValidArgumentsAndRepeatedReturnsEXISTING_CREDENTIAL() {
        Date birthDate = new Date(15, 2, 2001);
        var passwordString = new PasswordString("password,123");
        var credentialStore = new CredentialStoreSet();
        var credentialStoreValidator = new CredentialValidator(birthDate, passwordString, credentialStore);
        credentialStore.register(birthDate, passwordString);
        assertEquals(credentialStoreValidator.validate(), CredentialValidator.ValidationStatus.EXISTING_CREDENTIAL);
    }

    @Test
    public void testCredentialStoreRegisterWithValidArgumentsIncreasesCredentialStoreSize(){
        var credentialStore = new CredentialStoreSet();
        Date birthDate = new Date(1,1,2001);
        var passwordString = new PasswordString("password,123");
        credentialStore.register(birthDate, passwordString);
        assertEquals(credentialStore.size(), 1);
    }

    @Test
    public void testCredentialStoreRegisterTwiceWithValidArgumentsIncreasesCredentialStoreSizeTwice(){
        var credentialStore = new CredentialStoreSet();
        Date birthDate = new Date(1,1,2001);
        var passwordString = new PasswordString("password,123");

        Date birthDate1 = new Date(1,1,2002);
        var passwordString1 = new PasswordString("password,1234");

        credentialStore.register(birthDate, passwordString);
        credentialStore.register(birthDate1, passwordString1);
        assertEquals(credentialStore.size(), 2);
    }

    @Test
    public void testUserRegistrationRegisterTwiceWithValidArgumentsIncreasesCredentialStoreSizeTwice(){
        Date birthDate = new Date(1,1,2001);
        var passwordString = new PasswordString("password,123");
        var credentialStore = new CredentialStoreSet();
        var userRegistration = new UserRegistration();
        userRegistration.register(birthDate, passwordString, credentialStore);

        Date birthDate1 = new Date(1,1,2002);
        var passwordString1 = new PasswordString("password,1234");
        userRegistration.register(birthDate1, passwordString1, credentialStore);
        assertEquals(credentialStore.size(), 2);
    }

    @Test
    public void testCredentialStoreRegisterWithInvalidBirthDateAndNotRepeatedIncreasesCredentialStoreSize(){
        var credentialStore = new CredentialStoreSet();
        Date birthDate = new Date(31,2,2001);
        var passwordString = new PasswordString("password,123");
        credentialStore.register(birthDate, passwordString);
        assertEquals(credentialStore.size(), 1);
    }

    @Test
    public void testCredentialStoreRegisterWithInvalidPasswordAndNotRepeatedIncreasesCredentialStoreSize(){
        var credentialStore = new CredentialStoreSet();
        Date birthDate = new Date(15,2,2001);
        var passwordString = new PasswordString("password123");
        credentialStore.register(birthDate, passwordString);
        assertEquals(credentialStore.size(), 1);
    }
    @Test
    public void testCredentialStoreRegisterWithValidArgumentsAndRepeatedNotIncreasesCredentialStoreSizeAndThrowException(){
        var credentialStore = new CredentialStoreSet();
        Date birthDate = new Date(15,2,2001);
        var passwordString = new PasswordString("password,123");
        credentialStore.register(birthDate, passwordString);
        assertThrows(CredentialExistsException.class, () -> credentialStore.register(birthDate, passwordString)) ;

    }

}
