package org.kyslyi;

import java.util.List;
import org.kyslyi.handler.UserRequestHandler;
import org.kyslyi.model.UserRequest;

public class Dispatcher {

    private final List<UserRequestHandler> handlers;

    public Dispatcher(List<UserRequestHandler> handlers) {
        this.handlers = handlers;
    }

    public boolean dispatch(UserRequest userRequest) {
        for (UserRequestHandler userRequestHandler : handlers) {
            if(userRequestHandler.isApplicable(userRequest)){
                userRequestHandler.handle(userRequest);
                return true;
            }
        }
        return false;
    }
}