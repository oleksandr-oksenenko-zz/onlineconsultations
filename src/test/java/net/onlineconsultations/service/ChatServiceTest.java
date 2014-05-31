package net.onlineconsultations.service;

import net.onlineconsultations.dao.ChatDao;
import net.onlineconsultations.dao.ChatMessageDao;
import net.onlineconsultations.domain.Chat;
import net.onlineconsultations.domain.Consultant;
import net.onlineconsultations.service.impl.ChatServiceImpl;
import net.onlineconsultations.test.BaseUnitTest;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class ChatServiceTest extends BaseUnitTest {
    @Mock
    private ChatDao chatDao;

    @Mock
    private ChatMessageDao chatMessageDao;

    @InjectMocks
    private ChatService chatService = new ChatServiceImpl();

    @Test
    public void testStartNewChat_hp() {
        Consultant consultant = new Consultant();

        Chat newChat = chatService.startNewChat(consultant);

        when(chatDao.findBySessionId(any(String.class))).thenReturn(null);

        verify(chatDao).save(newChat);
        verifyNoMoreInteractions(chatDao);

        Assert.assertEquals(newChat.getConsultantInChat(), consultant);
        Assert.assertTrue(newChat.isAnonymInChat());
        Assert.assertFalse(newChat.isConsultantInChat());
    }

}