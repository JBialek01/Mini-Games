package web;

import app.guessnumber.UserNumberProviderInterface;

class UserNumberWebProvider implements UserNumberProviderInterface {

    int number;

    public UserNumberWebProvider(int number) {
        this.number = number;
    }

    @Override
    public int returnUserNumber() {
        return number;
    }
}
