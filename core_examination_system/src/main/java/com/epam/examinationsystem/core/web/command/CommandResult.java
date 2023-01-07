package com.epam.examinationsystem.core.web.command;

public class CommandResult {

    private final String page;
    private final boolean isRedirect;

    public CommandResult(String page) {
        this.page = page;
        this.isRedirect = false;
    }

    public CommandResult(String page, boolean isRedirect) {
        this.page = page;
        this.isRedirect = isRedirect;
    }

    public String getPage() {
        return page;
    }

    public boolean isRedirect() {
        return isRedirect;
    }
}
