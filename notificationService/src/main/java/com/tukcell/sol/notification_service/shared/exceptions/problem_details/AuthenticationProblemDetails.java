package com.tukcell.sol.notification_service.shared.exceptions.problem_details;

public class AuthenticationProblemDetails extends ProblemDetails {
    public AuthenticationProblemDetails() {
        setTitle("Authentication Error");
        setDetail("You are not authenticated.");
        setType("http://mydomain.com/exceptions/authentication");
        setStatus("401");
    }
}
