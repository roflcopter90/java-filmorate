package ru.yandex.practicum.filmorate.storage.user;

import ru.yandex.practicum.filmorate.model.User;

import java.util.Collection;

public interface UserStorage {

    User add(User user);

    User get(int id);

    User update(User user);

    Collection<User> findAll();

    boolean isAdded(int id);
}
