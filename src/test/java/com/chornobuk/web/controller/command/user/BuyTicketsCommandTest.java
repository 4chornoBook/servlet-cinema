package com.chornobuk.web.controller.command.user;

import com.chornobuk.web.controller.Path;
import com.chornobuk.web.model.entity.MovieSession;
import com.chornobuk.web.model.entity.Order;
import com.chornobuk.web.model.entity.Ticket;
import com.chornobuk.web.model.entity.User;
import com.chornobuk.web.model.repository.implementation.OrderRepository;
import com.chornobuk.web.model.repository.implementation.TicketRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

public class BuyTicketsCommandTest {

	@InjectMocks
	BuyTicketsCommand command;

	@Mock
	HttpServletRequest request;

	@Mock
	HttpServletResponse response;

	@Mock
	HttpSession session;

	@Mock
	TicketRepository ticketRepository;

	@Mock
	OrderRepository orderRepository;

	MovieSession testMovieSession;

	@Before
	public void setUp() {
		MockitoAnnotations.openMocks(this);

		testMovieSession = new MovieSession(1);
		testMovieSession.setTicketPrice(100);

		Mockito.doNothing().when(orderRepository).add(Mockito.isA(Order.class), Mockito.isA(Ticket[].class));
		Mockito.when(request.getSession()).thenReturn(session);
		Mockito.when(session.getAttribute("user")).thenReturn(new User(1));
		Mockito.when(session.getAttribute("orderSession")).thenReturn(testMovieSession);
//		String cardOwnerName = req.getParameter("cardOwner");
//		String cardNumber = req.getParameter("cardNumber");
//		String cardExpirationDate = req.getParameter("cardExpirationDate");
//		req.getParameter("cvvCode");
//		req.getSession().getAttribute("orderPlaces");
//		req.getSession().getAttribute("orderSession");
//		req.getSession().getAttribute("user");
//		req.getSession().getAttribute("orderCreatingTime"));
//		resp.sendRedirect(Path.INDEX_PAGE);
	}

	@Test
	public void execute() throws Exception {
		Mockito.when(request.getParameter("cardOwner")).thenReturn("TEST PERSON");
		Mockito.when(request.getParameter("cardNumber")).thenReturn("1234567890123456");
		Mockito.when(request.getParameter("cardExpirationDate")).thenReturn("12/28");
		Mockito.when(request.getParameter("cvvCode")).thenReturn("123");
		Mockito.when(session.getAttribute("orderPlaces")).thenReturn(new int[]{1,2,3});
		Mockito.when(session.getAttribute("orderCreatingTime")).thenReturn(LocalDateTime.now());
		Mockito.when(ticketRepository.getBySession(Mockito.isA(Ticket.class), Mockito.isA(MovieSession.class))).thenReturn(null);
		command.execute(request, response);
		Mockito.verify(response, Mockito.times(1)).sendRedirect(Path.INDEX_PAGE);
	}

	@Test
	public void executeWrongDateFormat() {
		Mockito.when(request.getParameter("cardOwner")).thenReturn("TEST PERSON");
		Mockito.when(request.getParameter("cardNumber")).thenReturn("1234567890123456");
		Mockito.when(request.getParameter("cardExpirationDate")).thenReturn("wrongDate");
		Mockito.when(request.getParameter("cvvCode")).thenReturn("123");
		Mockito.when(session.getAttribute("orderPlaces")).thenReturn(new int[]{1,2,3});
		Mockito.when(session.getAttribute("orderCreatingTime")).thenReturn(LocalDateTime.now());
		Mockito.when(ticketRepository.getBySession(Mockito.isA(Ticket.class), Mockito.isA(MovieSession.class))).thenReturn(null);
		String result = command.execute(request, response);
		assertEquals(Path.BUY_TICKETS_PAGE, result);
	}

	@Test
	public void executeNotValidOwner() {
		Mockito.when(request.getParameter("cardOwner")).thenReturn("test PeRSON");
		Mockito.when(request.getParameter("cardNumber")).thenReturn("1234567890123456");
		Mockito.when(request.getParameter("cardExpirationDate")).thenReturn("12/28");
		Mockito.when(request.getParameter("cvvCode")).thenReturn("123");
		Mockito.when(session.getAttribute("orderPlaces")).thenReturn(new int[]{1,2,3});
		Mockito.when(session.getAttribute("orderCreatingTime")).thenReturn(LocalDateTime.now());
		Mockito.when(ticketRepository.getBySession(Mockito.isA(Ticket.class), Mockito.isA(MovieSession.class))).thenReturn(null);
		String result = command.execute(request, response);
		assertEquals(Path.BUY_TICKETS_PAGE, result);
	}

	@Test
	public void executeNotValidCarNumber() {
		Mockito.when(request.getParameter("cardOwner")).thenReturn("TEST PERSON");
		Mockito.when(request.getParameter("cardNumber")).thenReturn("123456789012345");
		Mockito.when(request.getParameter("cardExpirationDate")).thenReturn("12/28");
		Mockito.when(request.getParameter("cvvCode")).thenReturn("123");
		Mockito.when(session.getAttribute("orderPlaces")).thenReturn(new int[]{1,2,3});
		Mockito.when(session.getAttribute("orderCreatingTime")).thenReturn(LocalDateTime.now());
		Mockito.when(ticketRepository.getBySession(Mockito.isA(Ticket.class), Mockito.isA(MovieSession.class))).thenReturn(null);
		String result = command.execute(request, response);
		assertEquals(Path.BUY_TICKETS_PAGE, result);
	}

	@Test
	public void executeNotValidDate() {
		Mockito.when(request.getParameter("cardOwner")).thenReturn("TEST PERSON");
		Mockito.when(request.getParameter("cardNumber")).thenReturn("1234567890123456");
		Mockito.when(request.getParameter("cardExpirationDate")).thenReturn("12/21");
		Mockito.when(request.getParameter("cvvCode")).thenReturn("123");
		Mockito.when(session.getAttribute("orderPlaces")).thenReturn(new int[]{1,2,3});
		Mockito.when(session.getAttribute("orderCreatingTime")).thenReturn(LocalDateTime.now());
		Mockito.when(ticketRepository.getBySession(Mockito.isA(Ticket.class), Mockito.isA(MovieSession.class))).thenReturn(null);
		String result = command.execute(request, response);
		assertEquals(Path.BUY_TICKETS_PAGE, result);
	}

	@Test
	public void executeNotValidCVV() {
		Mockito.when(request.getParameter("cardOwner")).thenReturn("TEST PERSON");
		Mockito.when(request.getParameter("cardNumber")).thenReturn("1234567890123456");
		Mockito.when(request.getParameter("cardExpirationDate")).thenReturn("12/28");
		Mockito.when(request.getParameter("cvvCode")).thenReturn("12q");
		Mockito.when(session.getAttribute("orderPlaces")).thenReturn(new int[]{1,2,3});
		Mockito.when(session.getAttribute("orderCreatingTime")).thenReturn(LocalDateTime.now());
		Mockito.when(ticketRepository.getBySession(Mockito.isA(Ticket.class), Mockito.isA(MovieSession.class))).thenReturn(null);
		String result = command.execute(request, response);
		assertEquals(Path.BUY_TICKETS_PAGE, result);
	}


	@Test
	public void executeTicketsAlreadyBought() {
		Mockito.when(request.getParameter("cardOwner")).thenReturn("TEST PERSON");
		Mockito.when(request.getParameter("cardNumber")).thenReturn("1234567890123456");
		Mockito.when(request.getParameter("cardExpirationDate")).thenReturn("12/28");
		Mockito.when(request.getParameter("cvvCode")).thenReturn("123");
		Mockito.when(session.getAttribute("orderPlaces")).thenReturn(new int[]{1,2,3});
		Mockito.when(session.getAttribute("orderCreatingTime")).thenReturn(LocalDateTime.now());
		Mockito.when(ticketRepository.getBySession(Mockito.isA(Ticket.class), Mockito.isA(MovieSession.class))).thenReturn(new Ticket(1));
		String result = command.execute(request, response);
		assertEquals(Path.BUY_TICKETS_PAGE, result);
	}

	@Test
	public void executeAllPaymentDataNotValid() {
		Mockito.when(request.getParameter("cardOwner")).thenReturn("TEST perSon");
		Mockito.when(request.getParameter("cardNumber")).thenReturn("123456789012345");
		Mockito.when(request.getParameter("cardExpirationDate")).thenReturn("12/20");
		Mockito.when(request.getParameter("cvvCode")).thenReturn("12");
		Mockito.when(session.getAttribute("orderPlaces")).thenReturn(new int[]{1,2,3});
		Mockito.when(session.getAttribute("orderCreatingTime")).thenReturn(LocalDateTime.now());
		Mockito.when(ticketRepository.getBySession(Mockito.isA(Ticket.class), Mockito.isA(MovieSession.class))).thenReturn(null);
		String result = command.execute(request, response);
		assertEquals(Path.BUY_TICKETS_PAGE, result);
	}
}