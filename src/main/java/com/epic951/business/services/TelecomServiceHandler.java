package com.epic951.business.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epic951.data.entities.TelecomService;
import com.epic951.data.repositories.TelecomServiceRepository;
import com.epic951.utilities.TestUtilities;

@Service
public class TelecomServiceHandler {

	private TelecomServiceRepository serviceRepository;

	@Autowired
	public TelecomServiceHandler(TelecomServiceRepository serviceRepository) {
		this.serviceRepository = serviceRepository;
	}

	public void setTelecomServiceRepository(TelecomServiceRepository telecomServiceRepository) {
		this.serviceRepository = telecomServiceRepository;
	}

	public TelecomService addOrUpdateService(TelecomService s) {
		// Validation is required ..
		TelecomService newService = null;
		boolean alreadyAdded = serviceRepository.findByTelecomServiceName(s.getTelecomServiceName()).isPresent();
		boolean viableForUpdate = serviceRepository.findByTelecomServiceId(s.getTelecomServiceId()).isPresent();
		if (!alreadyAdded && s.getTelecomServiceName() != null && !s.getTelecomServiceName().isEmpty()
				&& s.getOperatorName() != null && !s.getOperatorName().isEmpty() && s.getOperatorId() > 0) {
			newService = validateParameters(s, "Create");
		}
		if (viableForUpdate) {
			newService = validateParameters(s, "Update");
		}
		return newService;
	}

	public TelecomService findServiceById(int id) {
		return serviceRepository.findByTelecomServiceId(id).get();
	}

	public Integer deleteService(TelecomService s) {
		if (s.getTelecomServiceName() != null && !s.getTelecomServiceName().isEmpty()) {
			return serviceRepository.deleteByTelecomServiceName(s.getTelecomServiceName());
		} else if (s.getTelecomServiceId() > 0) {
			return serviceRepository.deleteByTelecomServiceId(s.getTelecomServiceId());
		}
		return 0;
	}

	public List<TelecomService> getAllTelecomServices() {
		List<TelecomService> services = new ArrayList<>();
		serviceRepository.findAll().forEach(services::add);
		return services;
	}

	private TelecomService initializeProduct(TelecomService s, String status) {
		TelecomService temp = null;
		System.err.println(s.toString());
		if (status.toLowerCase().equals("update")) {
			temp = serviceRepository.findByTelecomServiceId(s.getTelecomServiceId()).get();
		} else if (status.toLowerCase().equals("create")) {
			temp = TestUtilities.createTestTelecomService(0, "", "", false, 0, 1, 1, 1);
		}
		temp = TestUtilities.createTestTelecomService(s.getTelecomServiceId(), s.getOperatorName(),
				s.getTelecomServiceName(), s.isTelecomServiceType(),
				(s.getOperatorId() <= 0 ? temp.getOperatorId() : s.getOperatorId()),
				(s.getOperatorServiceId() <= 0 ? temp.getOperatorServiceId() : s.getOperatorServiceId()),
				(s.getOperatorPackageId() <= 0 ? temp.getOperatorPackageId() : s.getOperatorPackageId()),
				(s.getRating() <= 0 ? temp.getRating() : s.getRating()));
		return temp;
	}

	private TelecomService validateParameters(TelecomService s, String status) {
		TelecomService temp = null;
		switch (s.getOperatorName().toLowerCase()) {
		case "vodafone":
			if (s.getOperatorPackageId() > 0 && s.getOperatorServiceId() > 0) {
				temp = serviceRepository.save(initializeProduct(s, status));
			}
			break;
		case "etisalat":
			if (s.getOperatorPackageId() > 0) {
				temp = serviceRepository.save(initializeProduct(s, status));
			}
			break;
		case "orange":
			if (s.getOperatorServiceId() > 0) {
				temp = serviceRepository.save(initializeProduct(s, status));
			}
			break;
		default:
			temp = serviceRepository.save(initializeProduct(s, status));
			break;
		}
		return temp;
	}
}
