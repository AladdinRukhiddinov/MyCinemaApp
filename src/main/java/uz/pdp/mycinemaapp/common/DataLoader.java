package uz.pdp.mycinemaapp.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import uz.pdp.mycinemaapp.entity.*;
import uz.pdp.mycinemaapp.entity.enums.MovieStatus;
import uz.pdp.mycinemaapp.repository.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    final RowRepository rowRepository;
    final HallRepository hallRepository;
    final AttachmentRepository attachmentRepository;
    final MovieRepository movieRepository;
    final SessionDateRepository sessionDateRepository;
    final SessionTimeRepository sessionTimeRepository;
    final MovieAnnouncementRepository movieAnnouncementRepository;
    final MovieSessionRepository movieSessionRepository;
    final ActorRepository actorRepository;
    final DirectorRepository directorRepository;
    final GenreRepository genreRepository;
    final DistributorRepository distributorRepository;
    final PriceCategoryRepository priceCategoryRepository;
    final SeatRepository seatRepository;

    public DataLoader(
            RowRepository rowRepository,
            HallRepository hallRepository,
            AttachmentRepository attachmentRepository,
            MovieRepository movieRepository,
            SessionDateRepository sessionDateRepository,
            SessionTimeRepository sessionTimeRepository,
            MovieAnnouncementRepository movieAnnouncementRepository,
            MovieSessionRepository movieSessionRepository,
            ActorRepository actorRepository,
            DirectorRepository directorRepository,
            GenreRepository genreRepository,
            DistributorRepository distributorRepository,
            PriceCategoryRepository priceCategoryRepository,
            SeatRepository seatRepository
    ) {
        this.rowRepository = rowRepository;
        this.hallRepository = hallRepository;
        this.attachmentRepository = attachmentRepository;
        this.movieRepository = movieRepository;
        this.sessionDateRepository = sessionDateRepository;
        this.sessionTimeRepository = sessionTimeRepository;
        this.movieAnnouncementRepository = movieAnnouncementRepository;
        this.movieSessionRepository = movieSessionRepository;
        this.actorRepository = actorRepository;
        this.directorRepository = directorRepository;
        this.genreRepository = genreRepository;
        this.distributorRepository = distributorRepository;
        this.priceCategoryRepository = priceCategoryRepository;
        this.seatRepository = seatRepository;
    }

    @Value(("${spring.sql.init.mode}"))
    private String initialMode;

    @Override
    public void run(String... args) throws Exception {

        if (initialMode.equals("always")) {
            // HALL OBJ CREATE QILINADI
            Hall zal1 = new Hall("Zal 1");
            Hall zal2 = new Hall("Zal 2");
            Hall zal3Vip = new Hall("Zal 3 VIP", 20.0);

            //ROW LIST CREATE QILINADI
            List<Row> rowList1 = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                rowList1.add(new Row(i + 1, zal1));
            }
            List<Row> rowList2 = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                rowList2.add(new Row(i + 1, zal2));
            }
            List<Row> rowList3 = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                rowList3.add(new Row(i + 1, zal3Vip));
            }

            zal1.setRowList(rowList1);
            zal2.setRowList(rowList2);
            zal3Vip.setRowList(rowList3);

            // ZAL1, ZAL2, ZAL3VIP DB GA SAQLANADI
            hallRepository.save(zal1);
            hallRepository.save(zal2);
            hallRepository.save(zal3Vip);

            // PriceCategory
            PriceCategory lux = priceCategoryRepository.save(new PriceCategory("LUX", 5.0, 0));
            PriceCategory normal = priceCategoryRepository.save(new PriceCategory("NORMAL", 0.0, 1));
            // seats
            for (int i = 0; i < 10; i++) {
                seatRepository.save(new Seat(i + 1, rowList1.get(1), lux));
            }
            for (int i = 0; i < 10; i++) {
                seatRepository.save(new Seat(i + 1, rowList2.get(1), normal));
            }

            //attachment img
            Attachment movieImg = attachmentRepository.save(new Attachment("movieImg", 100000, "image/jpg"));
            Attachment actorImg = attachmentRepository.save(new Attachment("actorImg", 150000, "image/jpg"));
            Attachment directorImg = attachmentRepository.save(new Attachment("directorImg", 150000, "image/jpg"));
            Attachment trailer = attachmentRepository.save(new Attachment("batman", 2000000, "image/jpg"));

            // actors
            Actor actor = new Actor("Leonardo Dicaprio", "Born in 1972", actorImg);
            Actor saveActor = actorRepository.save(actor);
            List<Actor> actorList = new ArrayList<>(List.of(saveActor));

            // directors
            Director director = new Director("Zulfiqor Musaqov", "Born in 1962", directorImg);
            Director saveDirector = directorRepository.save(director);
            List<Director> directorList = new ArrayList<>(List.of(saveDirector));

            // Distributors
            Distributor distributor = new Distributor("DreamWorks", "Create in 1950");
            Distributor saveDistributor = distributorRepository.save(distributor);

            // Genre
            Genre genre = new Genre("Drama");
            Genre saveGenre = genreRepository.save(genre);
            List<Genre> genreList = new ArrayList<>(List.of(saveGenre));

            // MOVIES
            Movie movie1 = new Movie(
                    "Batman", "Marvel.2022", 140, 150000d, movieImg, trailer, LocalDate.now(), 5050000d, actorList, directorList, genreList, saveDistributor, 8.5, MovieStatus.ACTIVE);
            Movie movie2 = new Movie(
                    "Spiderman", "Fantastic", 110, 150000d, movieImg, trailer, LocalDate.now(), 5050000d, actorList, directorList, genreList, saveDistributor, 8.5, MovieStatus.ACTIVE);
            Movie movie3 = new Movie(
                    "Superman", "Anythings", 120, 150000d, movieImg, trailer, LocalDate.now(), 5050000d, actorList, directorList, genreList, saveDistributor, 8.5, MovieStatus.ACTIVE);

            Movie batman = movieRepository.save(movie1);
            Movie spiderman = movieRepository.save(movie2);
            Movie superman = movieRepository.save(movie3);

            // SESSION DATES
            SessionDate march17 = new SessionDate(LocalDate.of(2023, 5, 17));

            SessionDate march18 = new SessionDate(LocalDate.of(2023, 5, 18));
            SessionDate march19 = new SessionDate(LocalDate.of(2023, 5, 19));
            sessionDateRepository.save(march17);
            sessionDateRepository.save(march18);
            sessionDateRepository.save(march19);

            //SESSION TIMES
            SessionTime hour11 = new SessionTime(LocalTime.of(11, 0));
            SessionTime hour13 = new SessionTime(LocalTime.of(13, 0));
            SessionTime hour15 = new SessionTime(LocalTime.of(15, 0));
            SessionTime hour18 = new SessionTime(LocalTime.of(18, 0));
            sessionTimeRepository.save(hour11);
            sessionTimeRepository.save(hour13);
            sessionTimeRepository.save(hour15);
            sessionTimeRepository.save(hour18);

            //MOVIE ANNOUNCEMENTS
            MovieAnnouncement batmanAfisha = movieAnnouncementRepository.save(
                    new MovieAnnouncement(batman, true));
            MovieAnnouncement spidermanAfisha = movieAnnouncementRepository.save(
                    new MovieAnnouncement(spiderman, true));
            MovieAnnouncement supermanAfisha = movieAnnouncementRepository.save(
                    new MovieAnnouncement(superman, true));

            // MOVIE SESSIONS

            movieSessionRepository.save(
                    new MovieSession(
                            batmanAfisha,
                            zal1,
                            march18,
                            hour11,
                            hour13
                    )
            );
            movieSessionRepository.save(
                    new MovieSession(
                            batmanAfisha,
                            zal1,
                            march18,
                            hour15,
                            hour18
                    )
            );
            movieSessionRepository.save(
                    new MovieSession(
                            spidermanAfisha,
                            zal3Vip,
                            march18,
                            hour15,
                            hour18
                    )
            );

            movieSessionRepository.save(
                    new MovieSession(
                            spidermanAfisha,
                            zal2,
                            march19,
                            hour11,
                            hour13
                    )
            );
            movieSessionRepository.save(
                    new MovieSession(
                            spidermanAfisha,
                            zal2,
                            march19,
                            hour15,
                            hour18
                    )
            );

            movieSessionRepository.save(
                    new MovieSession(
                            supermanAfisha,
                            zal3Vip,
                            march19,
                            hour11,
                            hour13
                    )
            );
        }
    }
}
