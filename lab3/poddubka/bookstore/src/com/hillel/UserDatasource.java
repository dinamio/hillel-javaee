package com.hillel;

import java.io.*;

public class UserDatasource {

    private static final UserDatasource USER_DATASOURCE = new UserDatasource();

    public static User getByUsernameAndPassword(String username, String password) {


        try (FileReader fileReader = new FileReader(new File("/Users/johndoe/IdeaProjects/30days/bookstore/web/user.txt"));
             BufferedReader bufferedReader = new BufferedReader(fileReader);) {

            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                String[] cols = line.split(",");
                if (cols[0].equals(username) && cols[1].equals(password)) {

                    User user = new User();
                    user.setUserName(cols[0]);
                    user.setUserPassword(cols[1]);

                    return user;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static User setUserData(String username, String password) {


        try (FileWriter fileWriter = new FileWriter("/Users/johndoe/IdeaProjects/30days/bookstore/web/user.txt", true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);) {

            User user = new User(username, password);

            String text = user.toString();
            bufferedWriter.write(text);
            bufferedWriter.append('\n');
            bufferedWriter.flush();

            return user;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
