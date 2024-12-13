package dev.endribegaj.hospitalmanagementsystem.repsitories;




import dev.endribegaj.hospitalmanagementsystem.models.Patient;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    Optional<Patient> findByPersonalNo(String personalNo);
    List<Patient> findAllByCityAndGender(String city, char gender);

    List<Patient> findAllByFirstNameContaining(String firstName);

    List<Patient> findAllByBirthDateBetweenOrderByBirthDate(LocalDate from, LocalDate to);

    List<Patient> findAllByActive(boolean active, Limit limit);

    List<Patient> findAllByFirstNameOrLastNameOrCityOrBirthDate(String firstName, String lastName, String city, LocalDate birthDate);



    List<Patient> findAllByEmailEndingWithAndPhoneNumberStartingWith(String email, String phoneNumber);



    Long countAllByPersonalNoOrEmail(String personalNo, String email);


}