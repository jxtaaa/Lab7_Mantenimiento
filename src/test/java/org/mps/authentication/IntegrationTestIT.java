package org.mps.authentication;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    public void testCredentialStoreWithValidArgumentsAndNotRepeatedReturnsVALIDATION_OK(){
        Date birthDate = new Date(1,1,2001);
        var passwordString = new PasswordString("password,123");
        var credentialStore = new CredentialStoreSet();
        var credentialStoreValidator = new CredentialValidator(birthDate, passwordString, credentialStore);
        assertEquals(credentialStoreValidator.validate(), CredentialValidator.ValidationStatus.VALIDATION_OK);

    }
    @Test
    public void testUserRegistrationWithInvalidPasswordAndNotRepeatedReturnsPASSWORD_INVALID(){
        Date birthDate = new Date(1,1,2001);
        var passwordString = new PasswordString("password123");
        var credentialStore = new CredentialStoreSet();
        UserRegistration userRegistration = new UserRegistration();
        userRegistration.register(birthDate,passwordString,credentialStore);
        assertEquals(0, credentialStore.size());
    }

    @Test
    public void testCredentialStoreWithInvalidBirthDateAndNotRepeatedReturnsBIRTHDAY_INVALID() {
        Date birthDate = new Date(31, 2, 2001);
        var passwordString = new PasswordString("password,123");
        var credentialStore = new CredentialStoreSet();
        var credentialStoreValidator = new CredentialValidator(birthDate, passwordString, credentialStore);
        assertEquals(credentialStoreValidator.validate(), CredentialValidator.ValidationStatus.BIRTHDAY_INVALID);
    }


}
