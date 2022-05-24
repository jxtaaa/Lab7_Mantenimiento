package org.mps.authentication;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class IntegrationTestIT {

    @Test
    public void UserRegistration(){
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
}
