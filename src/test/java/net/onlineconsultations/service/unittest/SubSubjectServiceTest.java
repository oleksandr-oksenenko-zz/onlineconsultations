package net.onlineconsultations.service.unittest;

import net.onlineconsultations.dao.SubSubjectDao;
import net.onlineconsultations.dao.exception.NoSuchRecordException;
import net.onlineconsultations.domain.Consultant;
import net.onlineconsultations.domain.SubSubject;
import net.onlineconsultations.domain.Subject;
import net.onlineconsultations.service.SubSubjectService;
import net.onlineconsultations.service.impl.SubSubjectServiceImpl;
import net.onlineconsultations.test.BaseUnitTest;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class SubSubjectServiceTest extends BaseUnitTest {
    @Mock
    private SubSubjectDao subSubjectDao;

    @InjectMocks
    private SubSubjectService subSubjectService = new SubSubjectServiceImpl();

    @Test
    public void testRemove_hp() {
        Subject subject = new Subject(1L, "subject_name", "subject_description");
        SubSubject subSubject = new SubSubject(1L, "sub_subject_name", "sub_subject_description", subject);
        subSubject.addSubSubjectConsultant(
                new Consultant("username", "password", "firstName",
                        "middleName", "lastName", "qualification")
        );

        when(subSubjectDao.getById(1L)).thenReturn(subSubject);

        subSubjectService.remove(1L);

        verify(subSubjectDao).getById(1L);
        verify(subSubjectDao).remove(subSubject);
        verifyNoMoreInteractions(subSubjectDao);

        assertTrue(subSubject.getSubSubjectConsultants().isEmpty());
    }

    @Test(expected = NoSuchRecordException.class)
    public void testRemove_noSuchEntity() {
        when(subSubjectDao.getById(1L)).thenReturn(null);

        try {
            subSubjectService.remove(1L);
        } catch (Throwable e) {
            verify(subSubjectDao).getById(1L);
            verifyNoMoreInteractions(subSubjectDao);
            throw e;
        }
    }
}
