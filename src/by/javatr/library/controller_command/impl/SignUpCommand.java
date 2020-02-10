package by.javatr.library.controller_command.impl;

import by.javatr.library.bean.userstatus.UserStatus;
import by.javatr.library.controller_command.Command;
import by.javatr.library.console.ConsoleRequest;
import by.javatr.library.console.ConsoleResponse;
import by.javatr.library.exception.command.CommandException;
import by.javatr.library.exception.service.ServiceException;
import by.javatr.library.service.GeneralService;
import by.javatr.library.service.impl.GeneralServiceImpl;

/**
 *Class of command "Sign up to the system", implements {@link Command}
 *no properties
 *@author Zakharov Artem
 *@version 1.0
 */
public class SignUpCommand implements Command {
    /** field service*/
    private GeneralService service;
    /** field request*/
    private ConsoleRequest request;
    /** field response*/
    private ConsoleResponse response;

    /**
     * Constructor - makes an object
     * @param requestFromUser request with some information
     */
    public SignUpCommand(ConsoleRequest requestFromUser) {
        service = GeneralServiceImpl.getINSTANCE();
        request = requestFromUser;
    }

    /**
     * Method with UI logic
     * @return text of {@link ConsoleResponse}
     * @throws CommandException if was an error during the getting information
     */
    @Override
    public String execute() throws CommandException {
        String userName = request.getParameter("userName");
        String email = request.getParameter("email");
        UserStatus status = UserStatus.READER;
        String password = request.getParameter("password");
        String confirmedPassword = request.getParameter("confirmedPassword");
        try {
            service.signUpOperation(userName, email, status, password, confirmedPassword);
            response = new ConsoleResponse("Welcome, your account was successfully registered!");

            return response.getResponse();
        } catch (ServiceException e){
            response = new ConsoleResponse("Invalid information");
            throw new CommandException(response.getResponse(), e);
        }
    }
}
