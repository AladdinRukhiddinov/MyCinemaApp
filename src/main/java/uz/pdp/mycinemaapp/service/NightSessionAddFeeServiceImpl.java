package uz.pdp.mycinemaapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.mycinemaapp.common.MessageService;
import uz.pdp.mycinemaapp.entity.NightSessionAddFee;
import uz.pdp.mycinemaapp.entity.SessionTime;
import uz.pdp.mycinemaapp.exeption.RestException;
import uz.pdp.mycinemaapp.payload.ApiResponse;
import uz.pdp.mycinemaapp.payload.dtos.NightSessionAddFeeDto;
import uz.pdp.mycinemaapp.repository.NightSessionAddFeeRepository;
import uz.pdp.mycinemaapp.repository.SessionTimeRepository;
import uz.pdp.mycinemaapp.service.interfaces.NightSessionAddFeeService;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class NightSessionAddFeeServiceImpl implements NightSessionAddFeeService {

    private final NightSessionAddFeeRepository nightSessionAddFeeRepository;
    private final SessionTimeRepository sessionTimeRepository;

    @Override
    public ApiResponse getAllNightSessionAddFees() {
        List<NightSessionAddFee> nightSessionAddFees = nightSessionAddFeeRepository.findAll();
        return new ApiResponse(MessageService.getMessage("SUCCESS"), true, nightSessionAddFees);
    }

    @Override
    public ApiResponse getNightSessionAddFee(UUID id) {
        NightSessionAddFee nightSessionAddFee = nightSessionAddFeeRepository.findById(id).orElseThrow(() -> new RestException(MessageService.getMessage("NIGHT_SESSION_ADD_FEE_NOT_FOUND"), HttpStatus.NOT_FOUND));
        return new ApiResponse(MessageService.getMessage("SUCCESS"), true, nightSessionAddFee);
    }

    @Override
    public ApiResponse addNightSessionAddFee(NightSessionAddFeeDto dto) {
        SessionTime sessionTime = sessionTimeRepository.findById(dto.getTimeId()).orElseThrow(() -> new RestException(MessageService.getMessage("TIME_NOT_FOUND"), HttpStatus.NOT_FOUND));

        if (nightSessionAddFeeRepository.existsByTimeId(dto.getTimeId()))
            throw new RestException(MessageService.getMessage("NIGHT_SESSION_ADD_FEE_ALREADY_EXISTS"), HttpStatus.CONFLICT);

        NightSessionAddFee save = nightSessionAddFeeRepository.save(new NightSessionAddFee(dto.getPercentage(), sessionTime));
        return new ApiResponse(MessageService.getMessage("NIGHT_SESSION_ADD_FEE_SAVED"), true, save);


    }

    @Override
    public ApiResponse editNightSessionAddFee(UUID id, NightSessionAddFeeDto dto) {
        NightSessionAddFee nightSessionAddFee = nightSessionAddFeeRepository.findById(id).orElseThrow(() -> new RestException(MessageService.getMessage("NIGHT_SESSION_ADD_FEE_NOT_FOUND"), HttpStatus.NOT_FOUND));
        SessionTime sessionTime = sessionTimeRepository.findById(dto.getTimeId()).orElseThrow(() -> new RestException(MessageService.getMessage("TIME_NOT_FOUND"), HttpStatus.NOT_FOUND));

        if (nightSessionAddFeeRepository.existsByTimeIdAndPercentageAndIdNot(dto.getTimeId(), dto.getPercentage(), id))
            throw new RestException(MessageService.getMessage("NIGHT_SESSION_ADD_FEE_ALREADY_EXISTS"), HttpStatus.CONFLICT);

        nightSessionAddFee.setTime(sessionTime);
        nightSessionAddFee.setPercentage(dto.getPercentage());
        NightSessionAddFee save = nightSessionAddFeeRepository.save(nightSessionAddFee);
        return new ApiResponse(MessageService.getMessage("NIGHT_SESSION_ADD_FEE_EDITED"), true, save);
    }

    @Override
    public ApiResponse deleteNightSessionAddFee(UUID id) {
        NightSessionAddFee nightSessionAddFee = nightSessionAddFeeRepository.findById(id).orElseThrow(() -> new RestException(MessageService.getMessage("NIGHT_SESSION_ADD_FEE_NOT_FOUND"), HttpStatus.NOT_FOUND));
        nightSessionAddFeeRepository.delete(nightSessionAddFee);
        return new ApiResponse(MessageService.getMessage("NIGHT_SESSION_ADD_FEE_DELETED"), true);
    }
}
