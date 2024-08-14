package com.tukcell.sol.notification_service.shared.mapping;

import org.mapstruct.MapperConfig;
import org.mapstruct.ReportingPolicy;

@MapperConfig(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface MapstructService {
}
