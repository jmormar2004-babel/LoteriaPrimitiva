package com.bootcamp.loteriaprimitiva;

import com.bootcamp.loteriaprimitiva.controllers.ControladorUsuario;
import org.apache.logging.log4j.LogManager;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.util.logging.Logger;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(LoteriaPrimitivaApplication.class);
	}

}
