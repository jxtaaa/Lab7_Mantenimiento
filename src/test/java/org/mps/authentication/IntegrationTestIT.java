package org.mps.authentication;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IntegrationTestIT {

    @Test
    public void testCredentialStoreWithValidArgumentsAndNotRepeatedReturnsVALIDATION_OK(){
        Date birthDate = new Date(1,1,2001);
        var passwordString = new PasswordString("password,123");
        var credentialStore = new CredentialStoreSet();
        var credentialStoreValidator = new CredentialValidator(birthDate, passwordString, credentialStore);
        assertEquals(credentialStoreValidator.validate(), CredentialValidator.ValidationStatus.VALIDATION_OK);
    }
}
