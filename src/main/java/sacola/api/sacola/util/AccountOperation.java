package sacola.api.sacola.util;

import sacola.api.sacola.model.User;

public class AccountOperation {

    private final OperationType type;
    private final User user;

    public AccountOperation(OperationType type, User user) {
        this.type = type;
        this.user = user;
    }

    public OperationType getType() {
        return type;
    }

    public User getUser() {
        return user;
    }

    public enum OperationType {
        CREATE, UPDATE, DELETE
    }

}
