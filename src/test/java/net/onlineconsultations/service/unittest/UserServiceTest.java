package net.onlineconsultations.service.unittest;

import net.onlineconsultations.dao.UserDao;
import net.onlineconsultations.domain.Administrator;
import net.onlineconsultations.domain.User;
import net.onlineconsultations.service.UserService;
import net.onlineconsultations.service.impl.UserServiceImpl;
import net.onlineconsultations.test.BaseUnitTest;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

public class UserServiceTest extends BaseUnitTest {
    @Mock
    private UserDao userDao;

    @Mock
    private MessageDigestPasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService = new UserServiceImpl();

    @Test
    public void testSave_hp() {
        User user = new Administrator("username", "password");

        userService.save(user);

        verify(userDao).save(user);
        verifyNoMoreInteractions(userDao);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSave_nullUsername() {
        User user = new Administrator(null, "password");

        try {
            userService.save(user);
        } catch (Throwable e) {
            verifyZeroInteractions(userDao);
            throw e;
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSave_nullPassword() {
        User user = new Administrator("username", null);

        try {
            userService.save(user);
        } catch (Throwable e) {
            verifyZeroInteractions(userDao);
            throw e;
        }
    }
}