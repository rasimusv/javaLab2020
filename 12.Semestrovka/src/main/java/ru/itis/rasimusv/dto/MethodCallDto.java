package ru.itis.rasimusv.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.rasimusv.models.MethodCall;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MethodCallDto {

    private Long id;
    private String methodName;
    private Date callTime;

    public static MethodCallDto from(MethodCall methodCall) {
        if (methodCall == null) {
            return null;
        }
        return MethodCallDto.builder()
                .id(methodCall.getId())
                .methodName(methodCall.getMethodName())
                .callTime(methodCall.getCallTime())
                .build();
    }

    public static List<MethodCallDto> from(List<MethodCall> methodCalls) {
        return methodCalls.stream()
                .map(MethodCallDto::from)
                .collect(Collectors.toList());
    }
}




