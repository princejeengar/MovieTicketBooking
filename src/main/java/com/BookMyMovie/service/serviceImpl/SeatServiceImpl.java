package com.BookMyMovie.service.serviceImpl;

import com.BookMyMovie.dto.SeatDTO;
import com.BookMyMovie.dto.ShowDTO;
import com.BookMyMovie.dto.MovieDTO;
import com.BookMyMovie.dto.CinemaDTO;
import com.BookMyMovie.entity.Seat;
import com.BookMyMovie.entity.Show;
import com.BookMyMovie.repository.SeatRepo;
import com.BookMyMovie.service.SeatService;
import com.BookMyMovie.service.ShowService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeatServiceImpl implements SeatService {
    @Autowired
    private SeatRepo seatRepo;

    @Autowired
    private ShowService showService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public SeatDTO createSeat(SeatDTO seatDTO) {
        // Validate and fetch the Show entity using showId
        if (seatDTO.getShowId() == null) {
            throw new IllegalArgumentException("Show ID must not be null.");
        }
        Show show = showService.getShowById(seatDTO.getShowId());
        if (show == null) {
            throw new IllegalArgumentException("No Show found with ID: " + seatDTO.getShowId());
        }

        // Map DTO to Entity
        Seat seat = new Seat();
        seat.setSeatNumber(seatDTO.getSeatNumber());
        seat.setAvailable(seatDTO.getIsAvailable());
        seat.setShow(show); // Set the Show entity

        // Save and return the saved Seat as DTO
        Seat savedSeat = seatRepo.save(seat);

        // Map back to DTO
        SeatDTO savedSeatDTO = new SeatDTO();
        savedSeatDTO.setSeatId(savedSeat.getSeatId());
        savedSeatDTO.setSeatNumber(savedSeat.getSeatNumber());
        savedSeatDTO.setIsAvailable(savedSeat.isAvailable());
        savedSeatDTO.setShowId(savedSeat.getShow().getShowId());

        return savedSeatDTO;
    }

    @Override
    public List<SeatDTO> getAllSeats() {
        List<Seat> seats = seatRepo.findAll();

        return seats.stream()
                .map(seat -> {
                    SeatDTO seatDTO = modelMapper.map(seat, SeatDTO.class);

                    if (seat.getShow() != null) {
                        ShowDTO showDTO = modelMapper.map(seat.getShow(), ShowDTO.class);
                        if (seat.getShow().getMovie() != null) {
                            MovieDTO movieDTO = modelMapper.map(seat.getShow().getMovie(), MovieDTO.class);
                            showDTO.setMovie(movieDTO);
                        }
                        if (seat.getShow().getCinema() != null) {
                            CinemaDTO cinemaDTO = modelMapper.map(seat.getShow().getCinema(), CinemaDTO.class);
                            showDTO.setCinema(cinemaDTO);
                        }
                        seatDTO.setShowDTO(showDTO);
                    }
                    return seatDTO;
                })
                .collect(Collectors.toList());
    }


    @Override
    public void deleteSeat(Long seatId) {
        seatRepo.deleteById(seatId);
    }
}