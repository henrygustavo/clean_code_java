package pe.edu.unmsm.fisi.upg.ads.dirtycode.domain;

import java.util.Arrays;
import java.util.List;

import pe.edu.unmsm.fisi.upg.ads.dirtycode.exceptions.NoSessionsApprovedException;
import pe.edu.unmsm.fisi.upg.ads.dirtycode.exceptions.SpeakerDoesntMeetRequirementsException;

public class Speaker {

    private String firstName;
    private String lastName;
    private String email;
    private int yearsExperience;
    private boolean hasBlog;
    private String blogURL;
    private WebBrowser browser;
    private List<String> certifications;
    private String employer;
    private int registrationFee;
    private List<Session> sessions;

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getYearsExperience() {
        return yearsExperience;
    }

    public void setYearsExperience(int yearsExperience) {
        this.yearsExperience = yearsExperience;
    }

    public boolean isHasBlog() {
        return hasBlog;
    }

    public void setHasBlog(boolean hasBlog) {
        this.hasBlog = hasBlog;
    }

    public String getBlogURL() {
        return blogURL;
    }

    public void setBlogURL(String blogURL) {
        this.blogURL = blogURL;
    }

    public WebBrowser getBrowser() {
        return browser;
    }

    public void setBrowser(WebBrowser browser) {
        this.browser = browser;
    }

    public List<String> getCertifications() {
        return certifications;
    }

    public void setCertifications(List<String> certifications) {
        this.certifications = certifications;
    }

    public String getEmployer() {
        return employer;
    }

    public void setEmployer(String employer) {
        this.employer = employer;
    }

    public int getRegistrationFee() {
        return registrationFee;
    }

    public void setRegistrationFee(int registrationFee) {
        this.registrationFee = registrationFee;
    }

    public Integer register(IRepository repository) throws Exception {

        this.validateInformation();
        this.validateSkills();
        this.validateSessions();

        this.registrationFee = repository.getRegistrationFee(yearsExperience);

        return repository.saveSpeaker(this);
    }

    private void validateInformation() {

        if (this.firstName.isEmpty()) {
            throw new IllegalArgumentException("First Name is required");
        }
        if (this.lastName.isEmpty()) {
            throw new IllegalArgumentException("Last name is required.");
        }
        if (this.email.isEmpty()) {
            throw new IllegalArgumentException("Email is required.");
        }
    }

    private void validateSkills() throws Exception {

        boolean isQualified = isAGoodProfessional() || !hasRedFlags();

        if (!isQualified) {
            throw new SpeakerDoesntMeetRequirementsException(
                    "Speaker doesn't meet our abitrary and capricious standards.");
        }

    }

    private void validateSessions() throws Exception {

        boolean hasAllSessionApproved = true;

        if (this.sessions.isEmpty()) {
            throw new IllegalArgumentException("Can't register speaker with no sessions to present.");
        }

        for (Session session : sessions) {

            if (!IsSessionAboutOldTechnologies(session)) {
                session.setApproved(true);
            } else {
                hasAllSessionApproved = false;
                break;
            }
        }

        if (!hasAllSessionApproved) {
            throw new NoSessionsApprovedException("No sessions approved.");
        }
    }

    private boolean IsSessionAboutOldTechnologies(Session session) {
        String[] oldTechnologies = new String[]{"Cobol", "Punch Cards", "Commodore", "VBScript"};

        for (String technology : oldTechnologies) {

            if (session.getTitle().contains(technology) || session.getDescription().contains(technology)) {
                return true;
            }
        }

        return false;
    }

    private boolean isAGoodProfessional() {

        List<String> employersList = Arrays.asList("Pluralsight", "Microsoft", "Google", "Fog Creek Software", "37Signals", "Telerik");
        int numberCertifications = 3;
        int maxNumberYearsExperience = 10;

        return ((this.yearsExperience > maxNumberYearsExperience || this.hasBlog
                || this.certifications.size() > numberCertifications || employersList.contains(this.employer)));
    }

    private boolean hasRedFlags() {

        List<String> domains = Arrays.asList("aol.com", "hotmail.com", "prodigy.com", "compuserve.com");

        int validBrowserVersion = 9;
        String[] splitted = this.email.split("@");
        String emailDomain = splitted[splitted.length - 1];

        return domains.contains(emailDomain)
                || (browser.getName() == WebBrowser.BrowserName.InternetExplorer && browser.getMajorVersion() < validBrowserVersion);
    }
}
