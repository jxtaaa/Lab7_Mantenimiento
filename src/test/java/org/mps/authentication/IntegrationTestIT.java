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
        when(mockDate.validate()).thenReturn(true);
        when(mockPassword.validate()).thenReturn(true);
        when(credentialStore.credentialExists(mockDate,mockPassword)).thenReturn(false);

        userRegistration.register(mockDate, mockPassword,credentialStore);

        when(credentialStore.size()).thenReturn(1);
        assertEquals(1, credentialStore.size());
    }
}
