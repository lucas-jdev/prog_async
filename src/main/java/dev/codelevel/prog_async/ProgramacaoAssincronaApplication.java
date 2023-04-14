package dev.codelevel.prog_async;

import dev.codelevel.prog_async.framework.IFrameworkServer;
import dev.codelevel.prog_async.framework.spring.SpringRunner;

public class ProgramacaoAssincronaApplication {

	public static void main(String[] args) {
		IFrameworkServer framework = new SpringRunner();
		framework.start(args);
	}

}
