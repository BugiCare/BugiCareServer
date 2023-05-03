package hsu.bugicare.bugicareserver.dto;

import hsu.bugicare.bugicareserver.domain.Manager;
import lombok.Builder;
import lombok.Getter;

import java.net.MalformedURLException;

@Getter
@Builder
public class ManagerResponseDto {
    private Long id;
    private String name;
    private String center_name;
    private String phone;

    public ManagerResponseDto ManagertoManagerResponseDto(Manager manager) throws MalformedURLException {
        this.id = manager.getId();
        this.name = manager.getName();
        this.center_name = manager.getCenter_name();
        this.phone = manager.getPhone();

        return this;
    }
}
