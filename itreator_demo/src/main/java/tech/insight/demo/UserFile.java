package tech.insight.demo;

import tech.insight.User;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * @author lijiaobin
 * @version 1.0
 * @project java_basic
 * @description
 * @date 2025/5/11 22:36:28
 */
public class UserFile implements Iterable<User> {
    private final File file;

    public UserFile(File file) {
        this.file = file;
    }


    @Override
    public Iterator<User> iterator() {
        return new UserFileIte();
    }

    class UserFileIte implements Iterator<User> {
        List<User> userList = loadUsersFromFile();

        int index = 0;

        private List<User> loadUsersFromFile() {
            try {
                return Files.readAllLines(file.toPath()).stream().map(line -> {
                    String substring = line.substring(1, line.length() - 1);
                    String[] split = substring.split(",");
                    return new User(split[0], Integer.parseInt(split[1]));
                }).collect(Collectors.toList());
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        public boolean hasNext() {
            return index != userList.size();
        }

        @Override
        public User next() {
            if (index >= userList.size()) {
                throw new NoSuchElementException();
            }
            int currentIndex = index;
            index++;
            return userList.get(currentIndex);
        }
    }
}
