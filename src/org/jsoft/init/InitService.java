package org.jsoft.init;

import java.util.List;

import org.jsoft.model.Status;
import org.jsoft.service.StatusService;
import org.jsoft.util.StatusConstants;

public class InitService {

	private StatusService statusService;
	
	public void init(){
		Status status = new Status();
		status.setColumns("source");
		try {
			List<Status> sourceList = statusService.list(status);
			StatusConstants.sourceList = sourceList;
			for(Status s : sourceList){
				StatusConstants.sourceMap.put(s.getK(), s.getV());
			}
			status.setColumns("status");
			List<Status> statusList = statusService.list(status);
			StatusConstants.statusList = statusList;
			for(Status s : statusList){
				StatusConstants.statusMap.put(s.getK(), s.getV());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public StatusService getStatusService() {
		return statusService;
	}

	public void setStatusService(StatusService statusService) {
		this.statusService = statusService;
	}
	
}
