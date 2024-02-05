package org.travel.safirilinks.serviceLayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.travel.safirilinks.Models.Matatu;
import org.travel.safirilinks.Models.loginModel;
import org.travel.safirilinks.Repository.MatatuRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MatatuService {
    private final MatatuRepository matatuRepository;

    @Autowired
    public MatatuService(MatatuRepository matatuRepository) {
        this.matatuRepository = matatuRepository;
    }

    public List<Matatu> getAllMatatus() {
        return matatuRepository.findAll();
    }

    public Optional<Matatu> getMatatuById(Long id) {
        return matatuRepository.findById(id);
    }

    public Matatu saveMatatu(Matatu matatu) {
        return matatuRepository.save(matatu);
    }

    public void deleteMatatu(Long id) {
        matatuRepository.deleteById(id);
    }

}
