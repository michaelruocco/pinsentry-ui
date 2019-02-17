package uk.co.mruoc.pinsentry;

import spark.*;
import spark.template.velocity.*;
import java.util.*;
import static spark.Spark.*;

public class Ui {

    public static void main(final String[] args) {

        exception(Exception.class, (e, request, response) -> e.printStackTrace());
        staticFiles.location("/public");
        port(9999);

        get("/", (request, response) -> renderIdentify(request));
        get("/identify", (request, response) -> renderIdentify(request));
        get("/respond", (request, response) -> renderRespond(request));
        get("/sign", (request, response) -> renderSign(request));
    }

    private static String renderIdentify(final Request request) {
        final Map<String, Object> model = new HashMap<>();
        model.put("function", "Identify");
        return renderTemplate("velocity/index.vm", model);
    }

    private static String renderRespond(final Request request) {
        final Map<String, Object> model = new HashMap<>();
        model.put("function", "Respond");
        model.put("isRespond", true);
        //model.put("filter", Optional.ofNullable(statusStr).orElse(""));
        //model.put("activeCount", TodoDao.ofStatus(Status.ACTIVE).size());
        //model.put("anyCompleteTodos", TodoDao.ofStatus(Status.COMPLETE).size() > 0);
        //model.put("allComplete", TodoDao.all().size() == TodoDao.ofStatus(Status.COMPLETE).size());
        //model.put("status", Optional.ofNullable(statusStr).orElse(""));
        return renderTemplate("velocity/index.vm", model);
    }

    private static String renderSign(final Request request) {
        final Map<String, Object> model = new HashMap<>();
        model.put("function", "Sign");
        model.put("isSign", true);
        return renderTemplate("velocity/index.vm", model);
    }

    private static String renderTemplate(final String template, final Map model) {
        return new VelocityTemplateEngine().render(new ModelAndView(model, template));
    }
}
