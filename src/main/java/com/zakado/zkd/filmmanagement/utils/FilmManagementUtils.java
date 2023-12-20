package com.zakado.zkd.filmmanagement.utils;

import com.zakado.zkd.filmmanagement.model.dto.ActorRequest;
import com.zakado.zkd.filmmanagement.model.dto.MoviesRequest;
import com.zakado.zkd.filmmanagement.model.entity.Actor;
import com.zakado.zkd.filmmanagement.model.entity.Movie;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FilmManagementUtils {


    public static String removeSpace(String title) {
        return title.chars()
                .filter(c -> !Character.isWhitespace(c))
                .mapToObj(c -> String.valueOf((char) c))
                .collect(Collectors.joining());
    }


    public static Date convertStringToDate(final String fecha) {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return formato.parse(fecha);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static String convertDateToString(final Date fecha) {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.format(fecha);
    }

    public static Actor actorRequestToEntity(ActorRequest actorRequest) {
        return Actor.builder()
                .nid(actorRequest.getNid())
                .name(actorRequest.getName())
                .dob(convertStringToDate(actorRequest.getDob()))
                .image(actorRequest.getImage())
                .cob(actorRequest.getCob())
                .genre(actorRequest.getGenre())
                .build();
    }


    public static ActorRequest entityToActorRequest(Actor actorSaved) {
        return ActorRequest.builder()
                .nid(actorSaved.getNid())
                .name(actorSaved.getName())
                .dob(convertDateToString(actorSaved.getDob()))
                .image(actorSaved.getImage())
                .cob(actorSaved.getCob())
                .genre(actorSaved.getGenre())
                .build();
    }

    public static Movie movieRequestToEntity(MoviesRequest moviesRequest) {
        return Movie.builder()
                .nid(moviesRequest.getNid())
                .title(moviesRequest.getTitle())
                .year(moviesRequest.getYear())
                .duration(moviesRequest.getDuration())
                .country(moviesRequest.getCountry())
                .image(moviesRequest.getImage())
                .synopsis(moviesRequest.getSynopsis())
                .build();
    }

    public static MoviesRequest entityToMovieRequest(Movie movie) {
        MoviesRequest request = MoviesRequest.builder()
                .nid(movie.getNid())
                .title(movie.getTitle())
                .year(movie.getYear())
                .duration(movie.getDuration())
                .country(movie.getCountry())
                .image(movie.getImage())
                .synopsis(movie.getSynopsis())
                .build();
        if (Objects.nonNull(movie.getActors()) && !movie.getActors().isEmpty()) {
            request.setActors(movie.getActors().stream().map(FilmManagementUtils::entityToActorRequest)
                    .collect(Collectors.toSet()));
        } else {
            request.setActors(new HashSet<>());
        }

        return request;

    }
}
