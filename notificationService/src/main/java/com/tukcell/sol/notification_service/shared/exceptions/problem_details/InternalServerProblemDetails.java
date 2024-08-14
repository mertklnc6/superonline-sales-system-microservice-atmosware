package com.tukcell.sol.notification_service.shared.exceptions.problem_details;

public class InternalServerProblemDetails extends ProblemDetails {
    public InternalServerProblemDetails() {
        setTitle("Internal Server Error");
        setDetail("An unexpected error occurred.");
        setType("http://mydomain.com/exceptions/internal");
        setStatus("500");
    }
}
