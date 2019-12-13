package by.vsu.controller;

public class Forward {
    private String url;
    private boolean redirect;

    public Forward(String url, boolean redirect){
        this.url = url;
        this.redirect = redirect;
    }

    public Forward(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public boolean isRedirect() {
        return redirect;
    }
}
