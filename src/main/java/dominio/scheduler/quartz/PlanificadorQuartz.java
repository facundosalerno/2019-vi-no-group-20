package dominio.scheduler.quartz;

import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import dominio.excepciones.PlanificadorQuartzException;
import dominio.scheduler.Intervalo;
import dominio.scheduler.Planificador;
import dominio.scheduler.Tarea;

public class PlanificadorQuartz implements Planificador {

	private Scheduler scheduler;
	
	public PlanificadorQuartz() {
		try {
			scheduler = new StdSchedulerFactory().getScheduler();
			scheduler.start();
		} catch (SchedulerException e) {
			throw new PlanificadorQuartzException(e);
		}
	}
	
	@Override
	public void planificarTarea(Tarea tarea, Intervalo intervalo) {
		Trigger trigger = obtenerTriggerDeIntervalo(intervalo);
		JobDetail jobDetail = obtenerJobDetailDeTarea(tarea);
		
		try {
			scheduler.scheduleJob(jobDetail, trigger);
		} catch (SchedulerException e) {
			throw new PlanificadorQuartzException(e);
		}		
	}

	private JobDetail obtenerJobDetailDeTarea(Tarea tarea) {
		JobDetail jobDetail = JobBuilder.newJob((new Job() {
			
			@Override
			public void execute(JobExecutionContext context) throws JobExecutionException {
				tarea.ejecutar();
			}
		}).getClass()).build();
		return jobDetail;
	}

	private Trigger obtenerTriggerDeIntervalo(Intervalo intervalo) {
		Trigger trigger = TriggerBuilder
				.newTrigger()
				.withSchedule(
						CronScheduleBuilder.cronSchedule(intervalo.obtenerComoString())
						)
				.build();
		return trigger;
	}

	
	
}
