package com.xf.grasp;

import java.util.Date;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.helpers.TriggerUtils;
import org.quartz.impl.StdSchedulerFactory;

public class GraspScheduler {
	public void startScheduler() throws Exception {
		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
		JobDetail jobDetail = new JobDetail("graspJob", "graspGroup",
				GraspJob.class);
		Trigger trigger = TriggerUtils.makeMinutelyTrigger(Config.INTERVAL);
		trigger.setName("graspTrgger");
		trigger.setGroup("graspGroup");
		trigger.setStartTime(new Date());
		scheduler.scheduleJob(jobDetail, trigger);
		scheduler.start();
	}
}
