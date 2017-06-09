package io.gabbm.ticket_office.api.connector.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class RenewOAuth2Token implements Job {
	public void execute(JobExecutionContext context) throws JobExecutionException {  
		//OauthRestUtil.renewToken(0);
	}
}
