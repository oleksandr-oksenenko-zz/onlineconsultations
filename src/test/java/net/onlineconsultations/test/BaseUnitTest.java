package net.onlineconsultations.test;

import org.junit.Before;
import org.mockito.MockitoAnnotations;

public class BaseUnitTest {
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
}
