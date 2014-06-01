package net.onlineconsultations.service.unittest;

import net.onlineconsultations.dao.SubjectDao;
import net.onlineconsultations.dao.exception.NoSuchRecordException;
import net.onlineconsultations.domain.SubSubject;
import net.onlineconsultations.domain.Subject;
import net.onlineconsultations.service.SubjectService;
import net.onlineconsultations.service.impl.SubjectServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.*;

public class SubjectServiceTest {
    @Mock
    private SubjectDao subjectDao;

    @InjectMocks
    private SubjectService subjectService = new SubjectServiceImpl();

    @Test
    public void testRemove_hp() {
        Subject subject = new Subject(1L, "subject_name", "subject_description");
        subject.addSubSubject(new SubSubject("sub_subject_name", "sub_subject_description", subject));

        when(subjectDao.getById(1L)).thenReturn(subject);

        subjectService.remove(1L);

        verify(subjectDao, times(1)).getById(1L);
        verify(subjectDao, times(1)).remove(subject);
        verifyNoMoreInteractions(subjectDao);

        Assert.assertTrue(subject.getSubSubjects().isEmpty());
    }

    @Test(expected = NoSuchRecordException.class)
    public void testRemove_noSuchEntity() {
        when(subjectDao.getById(1L)).thenReturn(null);

        try {
            subjectService.remove(1L);
        } catch (Throwable e) {
            verify(subjectDao, never()).remove(any(Subject.class));
            throw e;
        }
    }
}