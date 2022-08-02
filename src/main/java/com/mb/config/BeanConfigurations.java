package com.mb.config;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import com.mb.service.AuditorAwareImpl;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class BeanConfigurations
{

	@Bean
	public AuditorAware<String> auditorAware()
	{
		return new AuditorAwareImpl();

	}

	@Bean
	public ModelMapper modelMapper()
	{
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
		return mapper;
	}
}
