package net.onlineconsultations.service.unittest;

import net.onlineconsultations.dao.ChatDao;
import net.onlineconsultations.dao.ChatMessageDao;
import net.onlineconsultations.domain.Chat;
import net.onlineconsultations.domain.ChatMessage;
import net.onlineconsultations.domain.ChatStatus;
import net.onlineconsultations.domain.Consultant;
import net.onlineconsultations.service.ChatService;
import net.onlineconsultations.service.impl.ChatServiceImpl;
import net.onlineconsultations.test.BaseUnitTest;
import org.apache.commons.lang.RandomStringUtils;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDateTime;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class ChatServiceTest extends BaseUnitTest {
    private static final Consultant dummyConsultant = new Consultant();

    @Mock
    private ChatDao chatDao;

    @Mock
    private ChatMessageDao chatMessageDao;

    @InjectMocks
    private ChatService chatService = new ChatServiceImpl();

    @Test
    public void testStartNewChat_hp() {
        when(chatDao.findBySessionId(any(String.class))).thenReturn(null);

        Chat newChat = chatService.startNewChat(dummyConsultant);

        InOrder inOrder = inOrder(chatDao);
        inOrder.verify(chatDao).findBySessionId(any(String.class));
        inOrder.verify(chatDao).save(newChat);

        inOrder.verifyNoMoreInteractions();

        assertEquals(newChat.getConsultantInChat(), dummyConsultant);
        assertTrue(newChat.isAnonymInChat());
        assertFalse(newChat.isConsultantInChat());
        // chat session id length should be 32
        assertEquals(newChat.getSessionId().length(), 32);
    }

    @Test
    public void testStartNewChat_sessionAlreadyExists() {
        when(chatDao.findBySessionId(any(String.class)))
                .thenReturn(new Chat())
                .thenReturn(null);

        Chat newChat = chatService.startNewChat(dummyConsultant);

        InOrder inOrder = inOrder(chatDao);
        inOrder.verify(chatDao, times(2)).findBySessionId(any(String.class));
        inOrder.verify(chatDao).save(newChat);

        inOrder.verifyNoMoreInteractions();

        assertEquals(newChat.getConsultantInChat(), dummyConsultant);
        assertTrue(newChat.isAnonymInChat());
        assertFalse(newChat.isConsultantInChat());
        // chat session id length should be 32
        assertEquals(newChat.getSessionId().length(), 32);
    }

    @Test
    public void testEndChatForAnonym_consultantInChat() {
        Chat testChat = new Chat(
                RandomStringUtils.randomAlphanumeric(32),
                dummyConsultant,
                true
        );
        testChat.setConsultantInChat(true);

        chatService.endChatForAnonym(testChat);

        verify(chatDao).merge(testChat);
        verifyNoMoreInteractions(chatDao);

        assertFalse(testChat.isAnonymInChat());
        assertTrue(testChat.isConsultantInChat());
        assertEquals(testChat.getStatus(), ChatStatus.ACTIVE);
    }

    @Test
    public void testEndChatForAnonym_consultantNotInChat() {
        Chat testChat = new Chat(
                RandomStringUtils.randomAlphanumeric(32),
                dummyConsultant,
                true
        );
        testChat.setConsultantInChat(false);

        chatService.endChatForAnonym(testChat);

        verify(chatDao).merge(testChat);
        verifyNoMoreInteractions(chatDao);

        assertFalse(testChat.isAnonymInChat());
        assertFalse(testChat.isConsultantInChat());
        assertEquals(testChat.getStatus(), ChatStatus.CLOSED);
    }

    @Test(expected = IllegalStateException.class)
    public void testEndChatForAnonym_anonymNotInChat() {
        Chat testChat = new Chat(
                RandomStringUtils.randomAlphanumeric(32),
                dummyConsultant,
                false
        );

        try {
            chatService.endChatForAnonym(testChat);
        } catch (Throwable e) {
            verify(chatDao).merge(testChat);
            assertEquals(testChat.getStatus(), ChatStatus.CLOSED);
            throw e;
        }
    }

    @Test
    public void testEndChatForConsultant_anonymInChat() {
        Chat testChat = new Chat(
                RandomStringUtils.randomAlphanumeric(32),
                dummyConsultant,
                true
        );
        testChat.setConsultantInChat(true);

        chatService.endChatForConsultant(testChat, dummyConsultant);

        verify(chatDao).merge(testChat);
        verifyNoMoreInteractions(chatDao);

        assertTrue(testChat.isAnonymInChat());
        assertFalse(testChat.isConsultantInChat());
        assertEquals(testChat.getStatus(), ChatStatus.ACTIVE);
    }

    @Test
    public void testEndChatForConsultant_anonymNotInChat() {
        Chat testChat = new Chat(
                RandomStringUtils.randomAlphanumeric(32),
                dummyConsultant,
                false
        );
        testChat.setConsultantInChat(true);

        chatService.endChatForConsultant(testChat, dummyConsultant);

        verify(chatDao).merge(testChat);
        verifyNoMoreInteractions(chatDao);

        assertFalse(testChat.isAnonymInChat());
        assertFalse(testChat.isConsultantInChat());
        assertEquals(testChat.getStatus(), ChatStatus.CLOSED);
    }

    @Test(expected = IllegalStateException.class)
    public void testEndChatForConsultant_consultantNotInChat() {
        Chat testChat = new Chat(
                RandomStringUtils.randomAlphanumeric(32),
                dummyConsultant,
                false
        );
        testChat.setConsultantInChat(false);

        try {
            chatService.endChatForConsultant(testChat, dummyConsultant);
        } catch (Throwable e) {
            verify(chatDao).merge(testChat);
            assertEquals(testChat.getStatus(), ChatStatus.CLOSED);
            throw e;
        }
    }

    @Test
    public void testPostNewMessage_hp() {
        final Chat chat = new Chat(
                RandomStringUtils.randomAlphanumeric(32),
                dummyConsultant,
                true
        );
        chat.setConsultantInChat(true);

        final String body = "body";
        final LocalDateTime timestamp = LocalDateTime.now(DateTimeZone.UTC);
        ChatMessage testChatMessage = new ChatMessage(
                body,
                timestamp,
                chat,
                false
        );

        chatService.postNewMessage(testChatMessage);

        verify(chatMessageDao).save(testChatMessage);
        verifyNoMoreInteractions(chatMessageDao);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPostNewMessage_nullBody() {
        final Chat chat = new Chat(
                RandomStringUtils.randomAlphanumeric(32),
                dummyConsultant,
                true
        );
        chat.setConsultantInChat(true);

        final String body = null;
        final LocalDateTime timestamp = LocalDateTime.now(DateTimeZone.UTC);
        ChatMessage testChatMessage = new ChatMessage(
                body,
                timestamp,
                chat,
                false
        );

        try {
            chatService.postNewMessage(testChatMessage);
        } catch (Throwable e) {
            verify(chatMessageDao, never()).save(testChatMessage);
            throw e;
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPostNewMessage_nullTimestamp() {
        final Chat chat = new Chat(
                RandomStringUtils.randomAlphanumeric(32),
                dummyConsultant,
                true
        );
        chat.setConsultantInChat(true);

        final String body = "body";
        final LocalDateTime timestamp = null;
        ChatMessage testChatMessage = new ChatMessage(
                body,
                timestamp,
                chat,
                false
        );

        try {
            chatService.postNewMessage(testChatMessage);
        } catch (Throwable e) {
            verify(chatMessageDao, never()).save(testChatMessage);
            throw e;
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPostNewMessage_nullChat() {
        final Chat chat = new Chat(
                RandomStringUtils.randomAlphanumeric(32),
                dummyConsultant,
                true
        );
        chat.setConsultantInChat(true);

        final String body = "body";
        final LocalDateTime timestamp = LocalDateTime.now(DateTimeZone.UTC);
        ChatMessage testChatMessage = new ChatMessage(
                body,
                timestamp,
                null,
                false
        );

        try {
            chatService.postNewMessage(testChatMessage);
        } catch (Throwable e) {
            verify(chatMessageDao, never()).save(testChatMessage);
            throw e;
        }
    }

    @Test(expected = IllegalStateException.class)
    public void testPostNewMessage_chatClosed() {
        final Chat chat = new Chat(
                RandomStringUtils.randomAlphanumeric(32),
                dummyConsultant,
                false
        );
        chat.setStatus(ChatStatus.CLOSED);

        final String body = "body";
        final LocalDateTime timestamp = LocalDateTime.now(DateTimeZone.UTC);
        ChatMessage testChatMessage = new ChatMessage(
                body,
                timestamp,
                chat,
                false
        );

        try {
            chatService.postNewMessage(testChatMessage);
        } catch (Throwable e) {
            verify(chatMessageDao, never()).save(testChatMessage);
            throw e;
        }
    }

    @Test
    public void testSetConsultantInChat_hp() {
        final Chat chat = new Chat(
                RandomStringUtils.randomAlphanumeric(32),
                dummyConsultant,
                true
        );

        chatService.setConsultantInChat(chat);

        verify(chatDao).merge(chat);

        assertTrue(chat.isConsultantInChat());
    }
}