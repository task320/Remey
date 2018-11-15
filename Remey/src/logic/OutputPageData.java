package logic;

import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

public class OutputPageData {
	private static final String PREFIX_LOGIN = "asset/";
	private static final String PREFIX_MAIN = "asset/";
    private static final String SUFFIX = ".html";


	public static String outputLoginPage() throws Exception{
		Map map = new HashMap();
		return new ThymeleafTemplateEngine(PREFIX_LOGIN,SUFFIX).render(new ModelAndView(map,"login"));
	}

	public static String outputMainPage() throws Exception{
		Map map = new HashMap();
		return new ThymeleafTemplateEngine(PREFIX_MAIN,SUFFIX).render(new ModelAndView(map,"index"));
	}

}
