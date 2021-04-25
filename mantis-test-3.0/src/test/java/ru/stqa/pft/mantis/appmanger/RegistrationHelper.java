package ru.stqa.pft.mantis.appmanger;

public class RegistrationHelper extends HelperBase {

    public RegistrationHelper(ApplicationManager app) {
        super(app);
    }

    public void start(String username, String userEmail) {
        wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");
    }
}
