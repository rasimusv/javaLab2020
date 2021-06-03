package ru.itis.rasimusv.services;

import org.springframework.stereotype.Service;
import ru.itis.rasimusv.dto.MethodCallDto;
import ru.itis.rasimusv.models.MethodCall;
import ru.itis.rasimusv.repositories.MethodCallLogRepository;

@Service
public class MethodCallLogServiceImpl implements MethodCallLogService {

    private final MethodCallLogRepository methodCallLogRepository;

    public MethodCallLogServiceImpl(MethodCallLogRepository methodCallLogRepository) {
        this.methodCallLogRepository = methodCallLogRepository;
    }

    @Override
    public void addMethodCall(MethodCallDto methodCallDto) {
        methodCallLogRepository.save(MethodCall.builder()
                .methodName(methodCallDto.getMethodName())
                .callTime(methodCallDto.getCallTime())
                .build());
    }

}
