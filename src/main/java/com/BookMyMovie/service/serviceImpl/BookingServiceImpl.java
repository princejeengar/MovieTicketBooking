package com.BookMyMovie.service.serviceImpl;

import com.BookMyMovie.dto.*;
import com.BookMyMovie.entity.Booking;
import com.BookMyMovie.entity.Seat;
import com.BookMyMovie.entity.AppUser;
import com.BookMyMovie.repository.BookingRepo;
import com.BookMyMovie.repository.SeatRepo;
import com.BookMyMovie.repository.AppUserRepo;
import com.BookMyMovie.service.BookingService;
import com.BookMyMovie.service.TwilioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    private BookingRepo bookingRepo;

    @Autowired
    private SeatRepo seatRepo;

    @Autowired
    private AppUserRepo appUserRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private TwilioService twilioService;

    @Override
    public BookingDTO createBooking(BookingDTO bookingDTO) {
        Booking booking = modelMapper.map(bookingDTO, Booking.class);
        Seat seat = seatRepo.findById(bookingDTO.getSeatId())
                .orElseThrow(() -> new RuntimeException("Seat not found"));

        if (seat.getShow() == null) {
            throw new RuntimeException("Show not found for the given seat");
        }

        AppUser appUser = appUserRepo.findById(bookingDTO.getAppUserId())
                .orElseThrow(() -> new RuntimeException("AppUser not found"));

        booking.setSeat(seat);
        booking.setAppUser(appUser);

        Booking savedBooking = bookingRepo.save(booking);
        BookingDTO savedBookingDTO = modelMapper.map(savedBooking, BookingDTO.class);

        if (seat.getShow() != null) {
            savedBookingDTO.setSeatDTO(modelMapper.map(seat, SeatDTO.class));
            savedBookingDTO.getSeatDTO().setShowDTO(modelMapper.map(seat.getShow(), ShowDTO.class));
        }

        savedBookingDTO.setUsername(appUser.getUsername());
        savedBookingDTO.setContactNumber(appUser.getContactNumber());
        savedBookingDTO.setEmail(appUser.getEmail());

        // Prepare and send SMS
        String smsContent = String.format(
                "Dear %s, your booking for '%s' at %s, %s on %s has been confirmed. Seat: %s. Enjoy the movie!",
                appUser.getUsername(),
                seat.getShow().getMovie().getMovieName(),
                seat.getShow().getCinema().getCinemaName(),
                seat.getShow().getCinema().getCinemaAddress(),
                seat.getShow().getShowTime(),
                seat.getSeatNumber()
        );
        twilioService.sendSMS(appUser.getContactNumber(), smsContent);

        return savedBookingDTO;
    }


    @Override
    public List<BookingDTO> getAllBookings() {
        List<Booking> bookings = bookingRepo.findAll();

        return bookings.stream()
                .map(booking -> {
                    BookingDTO bookingDTO = modelMapper.map(booking, BookingDTO.class);

                    // Map Seat and Show details
                    if (booking.getSeat() != null && booking.getSeat().getShow() != null) {
                        SeatDTO seatDTO = modelMapper.map(booking.getSeat(), SeatDTO.class);
                        ShowDTO showDTO = modelMapper.map(booking.getSeat().getShow(), ShowDTO.class);

                        // Map Movie and Cinema in ShowDTO
                        if (booking.getSeat().getShow().getMovie() != null) {
                            showDTO.setMovie(modelMapper.map(booking.getSeat().getShow().getMovie(), MovieDTO.class));
                        }
                        if (booking.getSeat().getShow().getCinema() != null) {
                            showDTO.setCinema(modelMapper.map(booking.getSeat().getShow().getCinema(), CinemaDTO.class));
                        }

                        seatDTO.setShowDTO(showDTO);
                        bookingDTO.setSeatDTO(seatDTO);
                    }

                    // Map AppUser details
                    if (booking.getAppUser() != null) {
                        bookingDTO.setUsername(booking.getAppUser().getUsername());
                        bookingDTO.setContactNumber(booking.getAppUser().getContactNumber());
                        bookingDTO.setEmail(booking.getAppUser().getEmail());
                    }

                    return bookingDTO;
                })
                .collect(Collectors.toList());
    }


    @Override
    public void deleteBooking(Long bookingId) {
        bookingRepo.deleteById(bookingId);
    }
}
