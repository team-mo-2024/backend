package teammo.com.teammo.service;

import java.util.Random;
import teammo.com.teammo.model.Apartment;
import teammo.com.teammo.repository.ApartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApartmentService {

    @Autowired
    private ApartmentRepository apartmentRepository;

    public List<Apartment> findAllApartments() {
        return apartmentRepository.findAll();
    }

    public Apartment saveApartment(Apartment apartment) {
        apartment.setIdCode(generateRandomId()); // ID를 수동으로 할당
        System.out.println("Apartment: " + apartment);
        return apartmentRepository.save(apartment);
    }

    private String generateRandomId() {
        Random random = new Random();
        int randomId = 10000 + random.nextInt(90000); // 10000부터 99999까지의 랜덤 숫자 생성
        return String.valueOf(randomId);
    }
}
