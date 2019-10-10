/*
 * Create by SangKwon on 2019. 7. 8.
 */

package md.ysk5898.com.bindapi.data;

public class User {

    private final String firstName;
    private final String lastName;
    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public String getFirstName() {
        return this.firstName;
    }
    public String getLastName() {
        return this.lastName;
    }
}
