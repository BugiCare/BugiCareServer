package hsu.bugicare.bugicareserver.service.impl;

import hsu.bugicare.bugicareserver.domain.Refrigerator;
import hsu.bugicare.bugicareserver.repository.RefrigeratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GraphService {
    private final RefrigeratorRepository refriRepository;

    @Autowired
    public GraphService(RefrigeratorRepository refriRepository) {
        this.refriRepository = refriRepository;
    }

    public String refrigeratorDayCount(){
        List<Refrigerator> refrigerator = refriRepository.findWithSameMinute();
        return String.valueOf(refrigerator.size());
    }

}
