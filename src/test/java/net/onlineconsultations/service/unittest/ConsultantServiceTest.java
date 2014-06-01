package net.onlineconsultations.service.unittest;

import net.onlineconsultations.dao.ConsultantDao;
import net.onlineconsultations.dao.exception.NoSuchRecordException;
import net.onlineconsultations.domain.Consultant;
import net.onlineconsultations.domain.SubSubject;
import net.onlineconsultations.domain.Subject;
import net.onlineconsultations.service.ConsultantService;
import net.onlineconsultations.service.impl.ConsultantServiceImpl;
import net.onlineconsultations.test.BaseUnitTest;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class ConsultantServiceTest extends BaseUnitTest {
    @Mock
    private ConsultantDao consultantDao;

    @InjectMocks
    private ConsultantService consultantService = new ConsultantServiceImpl();

    @Test
    public void testRecalculateConsultantRating_hp() {
        Consultant consultant = new Consultant("username", "password", "firstName",
                "middleName", "lastName", "qualification");
        double newMark = 1d;

        assertEquals(consultant.getRating(), 0d, 1e-6);

        consultantService.recalculateConsultantRating(consultant, newMark);

        verify(consultantDao).merge(consultant);
        verifyNoMoreInteractions(consultantDao);
        assertEquals(consultant.getRating(), newMark, 1e-6);
        reset(consultantDao);

        newMark = 5d;
        consultantService.recalculateConsultantRating(consultant, newMark);

        verify(consultantDao).merge(consultant);
        verifyNoMoreInteractions(consultantDao);
        assertEquals(consultant.getRating(), (1d + 5d) / 2, 1e-6);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRecalculateConsultantRating_consultantIsNull() {
        try {
            consultantService.recalculateConsultantRating(null, 1d);
        } catch (Throwable e) {
            verifyZeroInteractions(consultantDao);
            throw e;
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRecalculateConsultantRating_markNotInRange_1() {
        Consultant consultant = new Consultant("username", "password", "firstName",
                "middleName", "lastName", "qualification");
        double newMark = 1d - 1e-6;

        try {
            consultantService.recalculateConsultantRating(consultant, newMark);
        } catch (Throwable e) {
            verifyZeroInteractions(consultantDao);
            throw e;
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRecalculateConsultantRating_markNotInRange_2() {
        Consultant consultant = new Consultant("username", "password", "firstName",
                "middleName", "lastName", "qualification");
        double newMark = 0d + 1e-6;

        try {
            consultantService.recalculateConsultantRating(consultant, newMark);
        } catch (Throwable e) {
            verifyNoMoreInteractions(consultantDao);
            throw e;
        }
    }

    @Test
    public void testLinkSubSubjectToConsultant_hp() {
        Consultant consultant = createDummyConsultant();
        Subject subject = createDummySubject();
        SubSubject subSubject = createDummySubSubject(subject);

        consultantService.linkSubSubjectToConsultant(consultant, subSubject);

        verify(consultantDao).merge(consultant);
        verifyNoMoreInteractions(consultantDao);

        assertEquals(consultant.getSubSubjects().size(), 1);
        assertTrue(consultant.getSubSubjects().contains(subSubject));
    }

    @Test(expected = IllegalStateException.class)
    public void testLinkSubSubjectToConsultant_alreadyLinked() {
        Consultant consultant = createDummyConsultant();
        Subject subject = createDummySubject();
        SubSubject subSubject = createDummySubSubject(subject);

        consultantService.linkSubSubjectToConsultant(consultant, subSubject);

        try {
            consultantService.linkSubSubjectToConsultant(consultant, subSubject);
        } catch (Throwable e) {
            verifyZeroInteractions(consultantDao);
            throw e;
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLinkSubSubjectToConsultant_nullConsultant() {
        Subject subject = createDummySubject();
        SubSubject subSubject = createDummySubSubject(subject);

        try {
            consultantService.linkSubSubjectToConsultant(null, subSubject);
        } catch (Throwable e) {
            verifyZeroInteractions(consultantDao);
            throw e;
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLinkSubSubjectToConsultant_nullSubSubject() {
        Consultant consultant = createDummyConsultant();

        try {
            consultantService.linkSubSubjectToConsultant(consultant, null);
        } catch (Throwable e) {
            verifyZeroInteractions(consultantDao);
            throw e;
        }
    }

    @Test
    public void testUnlinkSubSubjectFromConsultant_hp() {
        Subject subject = createDummySubject();
        SubSubject subSubject = createDummySubSubject(subject);
        Consultant consultant = createDummyConsultant();

        consultantService.linkSubSubjectToConsultant(consultant, subSubject);
        reset(consultantDao);

        consultantService.unlinkSubSubjectFromConsultant(consultant, subSubject);

        verify(consultantDao).merge(consultant);
        verifyNoMoreInteractions(consultantDao);

        assertTrue(consultant.getSubSubjects().isEmpty());
    }

    @Test(expected = IllegalStateException.class)
    public void testUnlinkSubSubjectFromConsultant_notLinked() {
        Subject subject = createDummySubject();
        SubSubject subSubject = createDummySubSubject(subject);
        Consultant consultant = createDummyConsultant();

        try {
            consultantService.unlinkSubSubjectFromConsultant(consultant, subSubject);
        } catch (Throwable e) {
            verifyZeroInteractions(consultantDao);
            throw e;
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUnlinkSubSubjectFromConsultant_nullConsultant() {
        Subject subject = createDummySubject();
        SubSubject subSubject = createDummySubSubject(subject);

        try {
            consultantService.unlinkSubSubjectFromConsultant(null, subSubject);
        } catch (Throwable e) {
            verifyZeroInteractions(consultantDao);
            throw e;
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUnlinkSubSubjectFromConsultant_nullSubSubject() {
        Consultant consultant = createDummyConsultant();

        try {
            consultantService.unlinkSubSubjectFromConsultant(consultant, null);
        } catch (Throwable e) {
            verifyZeroInteractions(consultantDao);
            throw e;
        }
    }

    @Test
    public void testChangeConsultantStatus_hp() {
        Consultant consultant = createDummyConsultant();

        consultantService.changeConsultantStatus(consultant, true);

        verify(consultantDao).merge(consultant);
        verifyNoMoreInteractions(consultantDao);
        reset(consultantDao);

        assertTrue(consultant.getWaitingForChat());

        consultantService.changeConsultantStatus(consultant, false);

        verify(consultantDao).merge(consultant);
        verifyNoMoreInteractions(consultantDao);

        assertFalse(consultant.getWaitingForChat());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testChangeConsultantStatus_nullConsultant() {
        try {
            consultantService.changeConsultantStatus(null, true);
        } catch (Throwable e) {
            verifyZeroInteractions(consultantDao);
            throw e;
        }
    }

    @Test
    public void testRemove_hp() {
        Subject subject = createDummySubject();
        SubSubject subSubject = createDummySubSubject(subject);
        Consultant consultant = createDummyConsultant();

        consultantService.linkSubSubjectToConsultant(consultant, subSubject);
        reset(consultantDao);

        when(consultantDao.getById(1L)).thenReturn(consultant);

        consultantService.remove(1L);

        verify(consultantDao).getById(1L);
        verify(consultantDao).remove(consultant);
        verifyNoMoreInteractions(consultantDao);

        assertTrue(consultant.getSubSubjects().isEmpty());
        assertFalse(subSubject.getSubSubjectConsultants().contains(consultant));
    }

    @Test(expected = NoSuchRecordException.class)
    public void testRemove_noSuchEntity() {
        try {
            consultantService.remove(1L);
        } catch (Throwable e) {
            verify(consultantDao).getById(1L);
            verifyNoMoreInteractions(consultantDao);
            throw e;
        }
    }


    private Consultant createDummyConsultant() {
        return new Consultant(
                "username",
                "password",
                "firstName",
                "middleName",
                "lastName",
                "qualification"
        );
    }

    private Subject createDummySubject() {
        return new Subject(
                "subject_name",
                "subject_description"
        );
    }

    private SubSubject createDummySubSubject(Subject parentSubject) {
        SubSubject subSubject = new SubSubject(
                "sub_subject_name",
                "sub_subject_description",
                parentSubject
        );
        parentSubject.addSubSubject(subSubject);
        return subSubject;
    }
}
